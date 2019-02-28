package test.python;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;

public class PythonCli {

  public static void main(String[] args) throws IOException {
    InetAddress ia = InetAddress.getLocalHost();
    System.out.println(ia);
    getLocalMac(ia);



  }
  
  private static void getLocalMac(InetAddress ia) throws SocketException {
    // TODO Auto-generated method stub
    //获取网卡，获取地址
    byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
    System.out.println("mac数组长度："+mac.length);
    StringBuffer sb = new StringBuffer("");
    for(int i=0; i<mac.length; i++) {
        if(i!=0) {
            sb.append("-");
        }
        //字节转换为整数
        int temp = mac[i]&0xff;
        String str = Integer.toHexString(temp);
        System.out.println("每8位:"+str);
        if(str.length()==1) {
            sb.append("0"+str);
        }else {
            sb.append(str);
        }
    }
    System.out.println("本机MAC地址:"+sb.toString().toUpperCase());
}

  public static int runPy(String pyPath, String cmd) {
    StringBuffer bat = new StringBuffer();
    bat.append("@echo off \n");
    String c = "python " + pyPath + "  " + cmd;
    System.out.println(c);
    bat.append(c + " \n");
    bat.append("exit \n");
    File bf = new File("bat.bat");
    BufferedWriter out;
    try {
      out = new BufferedWriter(new FileWriter(bf));
      out.write(bat.toString());
      out.flush();
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    int i = -1;// 运行结果
    Process process = null;
    try {
      // Runtime.getRuntime()返回当前应用程序的Runtime对象
      // Process可以控制该子进程的执行或获取该子进程的信息。
      // exec()方法指示Java虚拟机创建一个子进程执行指定的可执行程序，并返回与该子进程对应的Process对象实例。
      // process = Runtime.getRuntime().exec("cmd.exe /c start /b bat.bat");
      process = Runtime.getRuntime().exec("cmd.exe /c start bat.bat");
      BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line = null;
      while ((line = in.readLine()) != null) {
        System.out.println(">" + line);
      }
      in.close();
      i = process.waitFor(); //// 等待子进程完成再往下执行。
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (process != null) {
        process.destroy();
      }
    }
    if (i == 0) {

      System.out.println("Batch Execute Completed.");
    } else {
      System.err.println("Batch Execute Failed.");
    }

    return i;
  }

}
