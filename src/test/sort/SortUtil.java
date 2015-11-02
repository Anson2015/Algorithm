package test.sort;

public class SortUtil {
	public static boolean isSort(int[] array){
		int i=0;
		while(i+1<array.length){
			if(array[i]<=array[i+1]){   //后面一个小于等于前面一个
				i++;
			}else{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 字符串化数组
	 * @param array
	 * @return
	 */
	public static String toString(int[] array){
		StringBuffer sb = new StringBuffer();
		for(int temp:array){
			sb.append(temp+"  ");
		}
		return sb.toString();
	}
}
