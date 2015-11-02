package algorithm.sort;

import sun.misc.Sort;
import test.sort.SortUtil;

public class SortAlgorithm {

	/**
	 * ð������ ˮ�бȽϴ�ġ��ݡ����ȳ�ˮ�� ʱ�临�Ӷ�n+(n-1)+....+1 = O(n^2)
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
	 * ѡ�����򣬺�ð�������෴����С����
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
	 * �������� ���ƴ���ʱ����һ����ȡһ�������������űȽϣ�Ȼ����ں���λ�� ʱ�临�Ӷ�1+2+3+...+(n-1) = O(n^2)
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
	 * �������򣬶�λԪ���������е�����˳��
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
		if (begin >= end) { // �������
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
				if (j < begin) { // ��ʱtemp����С��
					changePosition(begin, end, bf);
					quickOnly(begin + 1, end, bf);
					return;
				} else if (i > end) { // ��ʱtemp������
					quickOnly(begin, end - 1, bf);
					return;
				} else if (i < j) { // ��������λ��
					changePosition(i, j, bf);
					i++;
					j--;
				}
			}
			changePosition(i, end, bf); // ʵ�ַָ�����,ע��߽��ȡ��
			quickOnly(begin, j, bf);
			quickOnly(i + 1, end, bf);
		}
	}

	/**
	 * �鲢������Ҫ˼���ǵݹ飬��Ϊ����ʵ�ַ�ʽ�����ڶ��⣬������ڣ�
	 * 
	 * @param bf
	 * @return
	 */
	public static int[] megerSort(int[] bf) {
		int[] result = megerOnly(0, bf.length - 1, bf);
		return result;
	}

	private static int[] megerOnly(int i, int j, int[] bf) {
		if (i == j) { // ָ��ͬһλ�ã������ݹ�
			return bf;
		} else { // ������з��飬ʹ��ֲ�����Ȼ���ٲ�������
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
	 * �����򣬶������ǰ������������򣬼��κθ��ڵ�������ӽڵ㡣
	 * Ȼ�����������������½������������������ȡ���ڵ㼴Ϊ��ǰ���ֵ
	 * @param bf
	 * @return
	 */
	public static int[] heapSort(int[] bf){
		int i = bf.length-1;
		// ������ swim
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
			if(n==bf.length-1){  //Ŀǰ���Ѿ�����ֱ��ȡֵ
				changePosition(0, n, bf);
			}else{
				// ���������
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
				// ȡ�Ѹ��ڵ�
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
