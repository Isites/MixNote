package com.xw.sort;

public class 冒泡排序 {
    //待排序数组
    private int num[] = {20,14,10,34,44,87,99,15};
    //从大到小的递减排序
    public void sort(){
	for(int i = 0; i < num.length-1; i++){//外层循环，每循环一次，将当前最大值上浮
	    for(int  j = num.length-1; j > i; j--){//内层循环，反复循环将最大值移到i的位置
		if(num[j] > num[j-1]){//当num[j]大于num[j-1]时，交换两者的位置
		    int tmp = num[j];
		    num[j] = num[j-1];
		    num[j-1] = tmp;
		}
	    }
	}
    }
    
    public void display(){
	for(int i = 0; i < num.length; i++){
	    System.out.print(num[i]+"\t");
	}
    }
    
    public static void main(String args[]){
	/**
	 * 最坏情况下时间复杂度为O(n^2)
	 * 最好的情况下为正序，将不会有交换
	 * 选择排序、快速排序、希尔排序、堆排序不是稳定的排序算法，
	 * 冒泡排序、插入排序、归并排序和基数排序是稳定的排序算法。*/
	冒泡排序  sort = new 冒泡排序();
	long start = System.currentTimeMillis();
	sort.sort();
	long end = System.currentTimeMillis();
	System.out.println("Run time is "+(end - start));
	sort.display();
    }
}
