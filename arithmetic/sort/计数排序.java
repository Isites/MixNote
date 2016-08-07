
package com.xw.sort;

public class 计数排序 {
    //待排序数组
    private int num[] = {20,14,10,34,44,87,99,15};
    //将数组从大到小的递减排列
    private int b[];
    public 计数排序(){
	b = new int[num.length];
    }
    public void sort(){
	//此算法有局限性，在使用时可以灵活的更改
	//现在就先按照书上来吧
	int c[] = new int[100];//默认c初始化
	for(int i = 0; i < num.length; i++){//初始化一个用来记录每一元素比他大的元素个数
	    c[ num[i] ]++;    //在c中存放值为i的元素个数
	}
	for(int i = c.length-2; i > -1; i--){
	    c[i] = c[i] + c[i+1];                   //在c[i]中存放值小于等于i的元素个数
	}
	for(int i = num.length-1; i > -1; i--){
	    b[ c[ num[i] ]-1 ] = num[i];        //根据c[i]的值把元素放到数组b的对应位置
	    //c[ num[i] ] --;
	}
    }
    public void display(){
   	for(int i = 0; i < b.length; i++){
   	    System.out.print(b[i]+"\t");
   	}
   }
    public static void main(String[] args) {
	计数排序 sort = new 计数排序();
	long start = System.currentTimeMillis();
	sort.sort();
	long end = System.currentTimeMillis();
	System.out.println("Run time is "+(end - start));
	sort.display();
    }
}
