package algorithm.sort;

public class SortAlgorithm {
	
	/**
	 * ð������
	 * ˮ�бȽϴ�ġ��ݡ����ȳ�ˮ��
	 * ʱ�临�Ӷ�n+(n-1)+....+1 = O(n^2)
	 * @param before
	 * @return
	 */
	public static int[] bubbleSort(int[] bf ){
		for(int i=bf.length-1;i>-1;i--){
			for(int j=0;j<i;j++){
				if(bf[i]>=bf[j]){		// compare 
					continue;
				}else{					// change the position
					changePosition(i,j,bf);
				}
			}
		}
		return bf;
	}
	
	/**
	 * �������� 
	 * ���ƴ���ʱ����һ����ȡһ�������������űȽϣ�Ȼ����ں���λ��
	 * ʱ�临�Ӷ�1+2+3+...+(n-1) = O(n^2)
	 * @param bf
	 * @return
	 */
	public static int[] insertionSort(int[] bf){
		for(int i=0;i<bf.length;i++){
			int j = i;
			while(j>0&&bf[j]<bf[j-1]){
				changePosition(j-1,j,bf);
				j--;
			}
		}
		return bf;
	}
	
	public static int[] quickSort(int[] bf){
		int begin= 0;
		int end = bf.length-1;
		quickOnly(begin,end,bf);
		return bf;
	}
	private static void quickOnly(int begin,int end,int[] bf){
		if(begin>=end){ //�������
			return;
		}else{
			int i= begin;
			int j = end-1;
			int temp = bf[end];
			while(i<=j){
				while(j>=begin&&temp<=bf[j]) j--;
				while(i<=end&&temp>=bf[i]) i++;
				if(j<begin){ //��ʱtemp����С��
					changePosition(begin, end, bf);
					quickOnly(begin+1,end,bf);
					return;
				}else if(i>end){ //��ʱtemp������
					quickOnly(begin,end-1,bf);
					return;
				}else if(i<j){ //��������λ��
					changePosition(i, j, bf);
					i++;
					j--;
				}
			}
			changePosition(i, end, bf); //ʵ�ַָ�����,ע��߽��ȡ��
			quickOnly(begin, j, bf);
			quickOnly(i+1,end,bf);
		}
	}
	
	private static void changePosition(int i,int j,int[] bf){
		int temp = bf[j];
		bf[j] = bf[i];
		bf[i] = temp;
	}
	
}
