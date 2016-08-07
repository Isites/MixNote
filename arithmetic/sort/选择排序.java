package com.xw.sort;

public class 选择排序 {
    //待排序数组
    private int num[] = {20,14,10,34,44,87,99,15};
    //将待排序数组排序为递增
    public void sort(){
	int min ; //存放最小值
	int iPos ; //存放最小值的下标
	for(int i = 0; i < num.length-1; i++ ){
	    //外层循环，每一次循环将当前仍无序的元素中最小的值
	    //放到位置i
	    min = num[i];//初始化
	    iPos = i;
	    for(int j = i+1; j < num.length; j++ ){
		if(num[j] < min){//找出最小值
		    min = num[j];
		    iPos = j;
		}
	    }
	    //将最小元素和当前位置的元素交换
	    num[iPos] = num[i];
	    num[i] = min;
	}
    }
    public void display(){
	for(int i = 0; i < num.length; i++){
	    System.out.print(num[i]+"\t");
	}
    }
    public static void main(String args[]){
	/**
	 * 循环次数是0.5(n-1)*n，所以算法时间复杂度仍为O(n^2)
	 * 不过每次外层循环只产生以此交换，所以在数据顺序较乱世，可以减少一定的交换次数
	 * 选择排序和交换排序的区别在于增加了变量后一定程度上减少了交换的次数*/
	选择排序  sort = new 选择排序();
	long start = System.currentTimeMillis();
	sort.sort();
	long end = System.currentTimeMillis();
	System.out.println("Run time is "+(end - start));
	sort.display();
    }
}
