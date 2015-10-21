package test.sort;

import java.util.Random;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import algorithm.sort.SortAlgorithm;

public class TestSortAlgorithm {
	private int[] bf;
	@Before
	public void before(){
		Random rondom = new Random();
		bf = new int[rondom.nextInt(10)+1];
		for(int i=0;i<bf.length;i++){
			bf[i] = rondom.nextInt(100);
		}
	}
	/**
	 * Ã°ÅÝÅÅÐò
	 */
	@Ignore
	
	public void testBubbleSort(){
		String bfA = toString(bf);
		System.out.println("the array's length is "+bf.length);
		System.out.println("before sort:"+bfA);
		int[] result = SortAlgorithm.bubbleSort(bf);
		String afA = toString(result);
		System.out.println("after sort:"+afA);
	}
	@Ignore
	public void testInsertionSort(){
		String bfA = toString(bf);
		System.out.println("the array's length is "+bf.length);
		System.out.println("before sort:"+bfA);
		int[] result = SortAlgorithm.insertionSort(bf);
		String afA = toString(result);
		System.out.println("after sort:"+afA);
	}
	
	@Test
	public void testQuickSort(){
		String bfA = toString(bf);
//		int[] bf= {2,1,4,56,3};
		System.out.println("the array's length is "+bf.length);
		System.out.println("before sort:"+bfA);
		int[] result = SortAlgorithm.quickSort(bf);
		String afA = toString(result);
		System.out.println("after sort:"+afA);
	}
	
	/**
	 * ×Ö·û´®»¯Êý×é
	 * @param array
	 * @return
	 */
	private String toString(int[] array){
		StringBuffer sb = new StringBuffer();
		for(int temp:array){
			sb.append(temp+"  ");
		}
		return sb.toString();
	}
}
