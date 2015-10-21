package algorithm.sort;

public class SortAlgorithm {
	
	/**
	 * 冒泡排序
	 * 水中比较大的“泡”最先出水面
	 * 时间复杂度n+(n-1)+....+1 = O(n^2)
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
	 * 插入排序 
	 * 类似打牌时插排一样，取一个依次与手中排比较，然后放在合适位置
	 * 时间复杂度1+2+3+...+(n-1) = O(n^2)
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
		if(begin>=end){ //排序完毕
			return;
		}else{
			int i= begin;
			int j = end-1;
			int temp = bf[end];
			while(i<=j){
				while(j>=begin&&temp<=bf[j]) j--;
				while(i<=end&&temp>=bf[i]) i++;
				if(j<begin){ //此时temp是最小的
					changePosition(begin, end, bf);
					quickOnly(begin+1,end,bf);
					return;
				}else if(i>end){ //此时temp是最大的
					quickOnly(begin,end-1,bf);
					return;
				}else if(i<j){ //交换两者位置
					changePosition(i, j, bf);
					i++;
					j--;
				}
			}
			changePosition(i, end, bf); //实现分割条件,注意边界的取舍
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
