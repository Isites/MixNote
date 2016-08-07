package com.xw.sort;

public class 交换排序 {
  //待排序数组
    private int num[] = {20,14,10,34,44,87,99,15};
    //实现数组递增排序
    public void sort(){
	for(int i = 0; i < num.length-1; i++){//外层循环，每一次循环找出当前最小值
	    for(int j = i+1; j < num.length;  j++){//内层循环找出当前最小值
		if(num[j] < num[i]){
		    int tmp = num[j];
		    num[j] = num[i];
		    num[i] = tmp;
		}
	    }
	}
    }
    /**
     * 冒泡排序和交换排序的区别在于
     * 冒泡排序是和相邻的元素交换，从而把一个最大或最小的值逐渐上浮到指定位置
     * 交换排序则是和指定位置元素交换，找到最小元素并和制定位置的元素交换*/
    public void display(){
   	for(int i = 0; i < num.length; i++){
   	    System.out.print(num[i]+"\t");
   	}
   }
    public static void main(String args[]){
	//算法时间复杂度为O(n^2)
	交换排序 sort = new 交换排序();
	long start = System.currentTimeMillis();
	sort.sort();
	long end = System.currentTimeMillis();
	System.out.println("Run time is "+(end - start));
	sort.display();
    }
}
