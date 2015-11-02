package algorithm.sort;

import sun.misc.Sort;
import test.sort.SortUtil;

public class SortAlgorithm {

	/**
	 * 冒泡排序 水中比较大的“泡”最先出水面 时间复杂度n+(n-1)+....+1 = O(n^2)
	 * 
	 * @param before
	 * @return
	 */
	public static int[] bubbleSort(int[] bf) {
		for (int i = bf.length - 1; i > -1; i--) {
			for (int j = 0; j < i; j++) {
				if (bf[i] >= bf[j]) { // compare
					continue;
				} else { // change the position
					changePosition(i, j, bf);
				}
			}
		}
		return bf;
	}

	/**
	 * 选择排序，和冒泡排序相反，从小到大
	 * 
	 * @param bf
	 * @return
	 */
	public static int[] choiceSort(int[] bf) {
		for (int i = 0; i < bf.length; i++) {
			for (int j = i + 1; j < bf.length; j++) {
				if (bf[i] > bf[j]) {
					changePosition(i, j, bf);
				}
			}
		}
		return bf;
	}

	/**
	 * 插入排序 类似打牌时插排一样，取一个依次与手中排比较，然后放在合适位置 时间复杂度1+2+3+...+(n-1) = O(n^2)
	 * 
	 * @param bf
	 * @return
	 */
	public static int[] insertionSort(int[] bf) {
		for (int i = 0; i < bf.length; i++) {
			int j = i;
			while (j > 0 && bf[j] < bf[j - 1]) {
				changePosition(j - 1, j, bf);
				j--;
			}
		}
		return bf;
	}

	/**
	 * 快速排序，定位元素在数组中的排列顺序
	 * 
	 * @param bf
	 * @return
	 */
	public static int[] quickSort(int[] bf) {
		int begin = 0;
		int end = bf.length - 1;
		quickOnly(begin, end, bf);
		return bf;
	}

	private static void quickOnly(int begin, int end, int[] bf) {
		if (begin >= end) { // 排序完毕
			return;
		} else {
			int i = begin;
			int j = end - 1;
			int temp = bf[end];
			while (i <= j) {
				while (j >= begin && temp <= bf[j])
					j--;
				while (i <= end && temp >= bf[i])
					i++;
				if (j < begin) { // 此时temp是最小的
					changePosition(begin, end, bf);
					quickOnly(begin + 1, end, bf);
					return;
				} else if (i > end) { // 此时temp是最大的
					quickOnly(begin, end - 1, bf);
					return;
				} else if (i < j) { // 交换两者位置
					changePosition(i, j, bf);
					i++;
					j--;
				}
			}
			changePosition(i, end, bf); // 实现分割条件,注意边界的取舍
			quickOnly(begin, j, bf);
			quickOnly(i + 1, end, bf);
		}
	}

	/**
	 * 归并排序主要思想是递归，分为两种实现方式（由内而外，由外而内）
	 * 
	 * @param bf
	 * @return
	 */
	public static int[] megerSort(int[] bf) {
		int[] result = megerOnly(0, bf.length - 1, bf);
		return result;
	}

	private static int[] megerOnly(int i, int j, int[] bf) {
		if (i == j) { // 指向同一位置，结束递归
			return bf;
		} else { // 数组进行分组，使其局部有序，然后再插入排序；
			int[] bfA = megerOnly(i, i + (j - i) / 2, bf);
			int[] bfB = megerOnly(i + (j - i) / 2 + 1, j, bf);
			int[] temp = arrayClone(bf);
			int tempi = i;
			int tempj = i + (j - i) / 2 + 1;
			for (int count = i; count < j + 1; count++) {
				boolean flagi = tempi > i + (j - i) / 2;
				boolean flagj = tempj > j;
				if (flagi && !flagj) {
					temp[count] = bfB[tempj++];
				} else if (!flagi && flagj) {
					temp[count] = bfA[tempi++];
				} else if (!(flagi || flagj)) {
					if (bfB[tempj] > bfA[tempi]) {
						temp[count] = bfA[tempi++];
					} else {
						temp[count] = bfB[tempj++];
					}
				}

			}
			bf = temp;
			System.out.println(SortUtil.toString(bf));
			return bf;
		}
	}

	/**
	 * 堆排序，堆排序的前提是制造堆有序，即任何根节点大于其子节点。
	 * 然后，利用上升法或者下降法进行排序构造堆有序，取根节点即为当前最大值
	 * @param bf
	 * @return
	 */
	public static int[] heapSort(int[] bf){
		int i = bf.length-1;
		// 堆有序 swim
		while(i>=(bf.length-1)/2){
			int temp = i;
			while(temp>=0&&bf[temp]>bf[temp/2]){
				changePosition(temp, temp/2, bf);
				temp = temp/2;
			}
			i--;
		}
		System.out.println(SortUtil.toString(bf));
		//sink
		for(int n=bf.length-1;n>=0;n--){
			if(n==bf.length-1){  //目前堆已经有序，直接取值
				changePosition(0, n, bf);
			}else{
				// 构造堆有序
				System.out.println(n);
				int j = 0;
				while(2*j+1<=n){
					if(2*j+2<=n){
						int q = bf[2*j+1]>bf[2*j+2]?bf[2*j+1]:bf[2*j+2];
						if(bf[j]<q){
							int p = bf[2*j+1]>bf[2*j+2]?2*j+1:2*j+2;
							changePosition(j,p,bf);
							j = p;
						}else{
							break;
						}
					}else{
						if(bf[j]<bf[2*j+1]){
							changePosition(j,2*j+1,bf);
						}
						break;
					}
				}
				// 取堆根节点
				System.out.println(SortUtil.toString(bf)+"before");
				changePosition(0, n, bf);
				System.out.println(SortUtil.toString(bf)+"after");
			}
		}
		return bf;
	}

	private static void changePosition(int i, int j, int[] bf) {
		int temp = bf[j];
		bf[j] = bf[i];
		bf[i] = temp;
	}

	private static int[] arrayClone(int[] bf) {
		int[] temp = new int[bf.length];
		int i = 0;
		while (i < temp.length) {
			temp[i] = bf[i];
			i++;
		}
		return temp;
	}

}
