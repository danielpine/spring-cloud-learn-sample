
package 集合.src.com.hlyc.Array;

public class MyArrayList {
	private Object[] elementData;
	private int size;
	
	public MyArrayList(){
		this(10);
	}
	public MyArrayList(int length){
		if(length<0){
			throw new MyException();
		}
		elementData=new Object[length];
	}
	//添加 末尾添加
	public void add(Object obj){
		//在添加时应该考虑是否扩容
		ensureCapcity();
		elementData[size++]=obj;
	}
	
	public int size(){
		return size;
	}
	//根据下边获取元素
	public Object get(int index){
		checkRange(index);
		return elementData[index];
	}
	//判断容器中是否存在数据
	public boolean isEmpty(){
		return size==0;
	}
	
	public int indexOf(Object obj){
		if(obj==null){
			for(int i=0;i<size;i++){
				if(null==elementData[i]){
					return i;
				}
			}
		}
		for(int i=0;i<size;i++){
			if(obj.equals(elementData[i])){
				return i;
			}
		}
		return -1;
	}
	
	public boolean contains(Object obj){
		return indexOf(obj)>-1;
	}
	
	public Object set(int index,Object obj){
		//边界检查
		checkRange(index);
		
		Object object = elementData[index];
		elementData[index]=obj;
		return object;
		
	}
	
	public boolean remove(Object obj){
	/*	//不存在
		if(!contains(obj)){
			return false;
		}
		//存在
		if(contains(obj)){
		}
		*/
		int index =indexOf(obj);
		if(index==-1){
			return false;
		}if(index!=-1){
			System.arraycopy(elementData, index+1, elementData, index, size-index-1);
			size--;
			return true;
		}
		//为了编译通过
		return false;
	}
	
	public Object remove(int index){
		return null;
	}
	
	
	//扩容
	private void ensureCapcity(){
		if(size==elementData.length){
			Object[] newElementData=new Object[elementData.length*2+1];
			System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
			elementData=newElementData;
		}
	}
	//检查边界
	private void checkRange(int index){
		if(index<0||index>size){
			throw new MyException();
		}
	}
}
//自定义异常
class MyException extends RuntimeException{
	public MyException(){
		System.out.println("参数不和法");
	}
}

