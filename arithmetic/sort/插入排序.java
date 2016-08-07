package com.xw.sort;

public class 插入排序 {
    // 待排序数组
    private int num[] = { 20, 14, 10, 34, 44, 87, 99, 15 };
    
    
    /**之前使用的方法
     * if(num[i] < num[i-1]){//先判断是否有必要进行插入的内层排序
	       int key = num[i];
	       num[i] = num[i-1];
	       int j = i-2;
	       for(;  j > 0 && key < num[j]; j--){
		   num[j+1] = num[j];
	       }
	       if(j==0 && key < num[j]){//对于特殊的情况所做的讨论
		   num[j+1] = num[j];
		   num[j] = key;
	       }
	       else num[j+1] = key;
	   }*/

    // 将整个序列递增排列
    public void sort() {
	for (int i = 1; i < num.length; i++) {
	    // 外层循环将一个元素插入有序序列直至整个
	    // 序列有序，优化过后的
	    int j = i - 1;
	    int key = num[i];
	    for (; j >= 0 && key < num[j]; j--) {
		num[j + 1] = num[j];
	    }
	    num[j + 1] = key;   //因为在上面的循环中j都会多减一个1，所以在赋值的时候要重新加1
	}
    }

    public void display() {
	for (int i = 0; i < num.length; i++) {
	    System.out.print(num[i] + "\t");
	}
    }

    public static void main(String[] args) {
	/**
	 * 插入排序的算法时间复杂度为O(n^2)
	 */
	插入排序 sort = new 插入排序();
	long start = System.currentTimeMillis();
	sort.sort();
	long end = System.currentTimeMillis();
	System.out.println("Run time is " + (end - start));
	sort.display();
    }

}
