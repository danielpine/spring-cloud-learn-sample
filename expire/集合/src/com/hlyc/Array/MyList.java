package com.hlyc.Array;

public class MyList {
	
	
	
	private static int size;
	static Object[] elementData;
    public static int indexOfMyself(Object o) {
		if(o==null){
			for(int i=0;i<size;size++){	
				if(elementData[i]==null)return i;
			}
		}else{
			for(int i=0;i<size;size++){
				if(elementData[i]==o)return i;				
			}
		}
		return -1;
}


}
