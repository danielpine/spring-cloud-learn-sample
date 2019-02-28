
package ����.src.com.hlyc.Array;

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
	//��� ĩβ���
	public void add(Object obj){
		//�����ʱӦ�ÿ����Ƿ�����
		ensureCapcity();
		elementData[size++]=obj;
	}
	
	public int size(){
		return size;
	}
	//�����±߻�ȡԪ��
	public Object get(int index){
		checkRange(index);
		return elementData[index];
	}
	//�ж��������Ƿ��������
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
		//�߽���
		checkRange(index);
		
		Object object = elementData[index];
		elementData[index]=obj;
		return object;
		
	}
	
	public boolean remove(Object obj){
	/*	//������
		if(!contains(obj)){
			return false;
		}
		//����
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
		//Ϊ�˱���ͨ��
		return false;
	}
	
	public Object remove(int index){
		return null;
	}
	
	
	//����
	private void ensureCapcity(){
		if(size==elementData.length){
			Object[] newElementData=new Object[elementData.length*2+1];
			System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
			elementData=newElementData;
		}
	}
	//���߽�
	private void checkRange(int index){
		if(index<0||index>size){
			throw new MyException();
		}
	}
}
//�Զ����쳣
class MyException extends RuntimeException{
	public MyException(){
		System.out.println("�������ͷ�");
	}
}

