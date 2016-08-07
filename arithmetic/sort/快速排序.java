package com.xw.sort;

import javax.naming.ldap.SortControl;

public class 快速排序 {
    private int num[] = { 20, 14, 10, 34, 44, 87, 99, 15 };
    
    //一趟快速排序的算法
    public int partition(int arr[], int p, int r) {
	//交换数组A[p....r]的记录，枢轴记录到位，并返回其所在位置，此时在
	//它之前（后）的记录均不大（小）于它
	int pkey = arr[p];  //用数组的第一个元素作为枢轴记录
	while( p < r){       //从数组的两端交替向中间扫描
	    
	    while( p < r && arr[r] >= pkey){
		r--;
	    }
	    arr[p]  = arr[r];  //将比枢轴小的记录移到低端
	    while (p < r && arr[p] <= pkey) {
		p++;
	    }
	    arr[r] = arr[p];
	}
	arr[p] = pkey; //枢轴记录到位
	return p;
    }
    
    //递归形式的快速排序算法
    public void sort(int arr[], int low, int high){
	//对顺序表中的子序列arr[low....high]作快速排序
	if(low < high){                                                   //长度大于1;
	    int pindex = partition(arr, low, high);              //将arr[low,,,,high]一分为二
	    sort(arr, low, pindex-1);                                 // 对低子表递归排序，pindex是枢轴位置
	    sort(arr, pindex+1, high);                               //对高子表递归排序
	}
    }
    
    public void display(){
   	for(int i = 0; i < num.length; i++){
   	    System.out.print(num[i]+"\t");
   	}
   }
    
    public static void main(String[] args) {
	/**
	 * 通常快速排序被认为是，在所有同数量级（O(nlogn)）的排序
	 * 方法中，其平均性能最好
	 * 但若序列基本有序时，快速排序将蜕化为冒泡排序
	 * 时间发杂度变为O(n^2)*/
	快速排序 sort = new 快速排序();
	long start = System.currentTimeMillis();
	sort.sort(sort.num, 0, sort.num.length-1);
	long end = System.currentTimeMillis();
	System.out.println("Run time is "+(end - start));
	sort.display();
    }
    
    
    
    
    
}
