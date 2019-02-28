package niofile.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolServer {
  private ExecutorService exec = Executors.newFixedThreadPool(10);
  private ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
  // 使用Map保存每个连接，当OP_READ就绪时，根据key找到对应的文件对其进行写入。若将其封装成一个类，作为值保存，可以再上传过程中显示进度等等
  Map<SelectionKey, FileChannel> fileMap = new HashMap<SelectionKey, FileChannel>();
  
  public static void main(String[] args) throws IOException {
    ThreadPoolServer server = new ThreadPoolServer();
    server.startServer();
  }
  
  public void startServer() throws IOException {
    Selector selector = Selector.open();
    ServerSocketChannel serverChannel = ServerSocketChannel.open();
    serverChannel.configureBlocking(false);
    serverChannel.bind(new InetSocketAddress(8888));
    serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    System.out.println("服务器已开启...");
    while (true) {
      int num = selector.select();
      if (num == 0)
        continue;
      Iterator<SelectionKey> it = selector.selectedKeys().iterator();
      while (it.hasNext()) {
        SelectionKey key = it.next();
        if (key.isAcceptable()) {
          ServerSocketChannel serverChannel1 = (ServerSocketChannel) key.channel();
          SocketChannel socketChannel = serverChannel1.accept();
          if (socketChannel == null)
            continue;
          socketChannel.configureBlocking(false);
          SelectionKey key1 = socketChannel.register(selector, SelectionKey.OP_READ);
          InetSocketAddress remoteAddress = (InetSocketAddress) socketChannel.getRemoteAddress();
          File file =
              new File(remoteAddress.getHostName() + "_" + remoteAddress.getPort() + ".txt");
          FileChannel fileChannel = new FileOutputStream(file).getChannel();
          fileMap.put(key1, fileChannel);
          System.out.println(socketChannel.getRemoteAddress() + "连接成功...");
          writeToClient(socketChannel);
        } else if (key.isReadable()) {
          readData(key);
        }
        // NIO的特点只会累加，已选择的键的集合不会删除，ready集合会被清空
        // 只是临时删除已选择键集合，当该键代表的通道上再次有感兴趣的集合准备好之后，又会被select函数选中
        it.remove();
      }
    }
  }

  private void writeToClient(SocketChannel socketChannel) throws IOException {
    buffer.clear();
    buffer.put((socketChannel.getRemoteAddress() + "连接成功").getBytes());
    buffer.flip();
    socketChannel.write(buffer);
    buffer.clear();
  }
  
  protected void readData(final SelectionKey key) throws IOException {
    // 移除掉这个key的可读事件，已经在线程池里面处理
    key.interestOps(key.interestOps() & (~SelectionKey.OP_READ));
    exec.execute(new Runnable() {
      @Override
      public void run() {
        ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
        FileChannel fileChannel = fileMap.get(key);
        buffer.clear();
        SocketChannel socketChannel = (SocketChannel) key.channel();
        int num = 0;
        try {
          while ((num = socketChannel.read(buffer)) > 0) {
            buffer.flip();
            // 写入文件
            fileChannel.write(buffer);
            buffer.clear();
          }
        } catch (IOException e) {
          key.cancel();
          e.printStackTrace();
          return;
        }
        // 调用close为-1 到达末尾
        if (num == -1) {
          try {
            fileChannel.close();
            System.out.println("上传完毕");
            buffer.put((socketChannel.getRemoteAddress() + "上传成功").getBytes());
            buffer.clear();
            socketChannel.write(buffer);
          } catch (IOException e) {
            e.printStackTrace();
          }
          // 只有调用cancel才会真正从已选择的键的集合里面移除，否则下次select的时候又能得到
          // 一端close掉了，其对端仍然是可读的，读取得到EOF，返回-1
          key.cancel();
          return;
        }
        // Channel的read方法可能返回0，返回0并不一定代表读取完了。
        // 工作线程结束对通道的读取，需要再次更新键的ready集合，将感兴趣的集合重新放在里面
        key.interestOps(key.interestOps() | SelectionKey.OP_READ);
        // 调用wakeup，使得选择器上的第一个还没有返回的选择操作立即返回即重新select
        key.selector().wakeup();
      }
    });
  }
}
