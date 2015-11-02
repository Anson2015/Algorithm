package test.sort;

public class SortUtil {
	public static boolean isSort(int[] array){
		int i=0;
		while(i+1<array.length){
			if(array[i]<=array[i+1]){   //����һ��С�ڵ���ǰ��һ��
				i++;
			}else{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * �ַ���������
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
