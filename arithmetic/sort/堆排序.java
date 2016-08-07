package com.xw.sort;

public class 堆排序 {
    // 待排序数组
    private int num[] = { 20, 14, 10, 34, 44, 87, 99, 15 };
    
    //调整以某一个元素A[i]为根节点的子树成为一个新的堆的算法
    public void heapIfy(int a[],int i, int length){
	int l = left(i);
	int r = right(i);
	int larger;
	if( l <= length && a[l] > a[i])
	    larger = l;          //比较a[i]和其左孩子a[l],令其larger指向较大者
	else larger = i;
	if (r <= length && a[r] > a[larger])
	    larger = r;         //比较a[larger]和其右孩子的大小，令其指向a[i],a[r],a[l]的最大者
	if(larger != i){
	    int tmp = a[i];  //如果a[i]小于其子女，则交换a[i]和其左右孩子中较大者
	    a[i] = a[larger]; //然后通过递归调整
	    a[larger] = tmp;
	    heapIfy(a, larger, length); //调整a中以a[lager]为根的子树，使其成为堆
	}
    }
    //建立堆的算法
    public void buildHeap(int a[]){
	for(int i = a.length/2; i >=0; i--){
	    heapIfy(a, i, a.length-1);
	}
    }
    public void sort(){
	buildHeap(num);
	for (int i = num.length-1; i > 0; i--) {
	    int tmp = num[0];
	    num[0] = num[i];
	    num[i] = tmp;
	    heapIfy(num, 0, i-1);
	}
    }
    //用于返回根节点序号为i的左孩子序号
    public int left(int i){
	return 2*i;
    }
    //用于返回根节点序号为i的右孩子序号
    public int  right(int i) {
	return 2*i+1;
    }
    public void display(){
   	for(int i = 0; i < num.length; i++){
   	    System.out.print(num[i]+"\t");
   	}
   }
    
    public static void main(String[] args) {
	/**
	 * 堆排序的时间复杂度为O(nlogn),其中调用buildheap的时间
	 * 为O(n)，调用n-1次heapIfy，每一次的时间复杂度为O(n)
	 * 堆排序算法在最坏的情况下比较次数为O(nlogn),且不需要额外的空间，优于快速排序
	 * 和归并排序，但他也有缺点，序列基本有序时，会有较高的时间代价
	 * 与快速排序和归并算法相比，堆排序比较次数虽然仍为O(nlogn)阶的，
	 * 但其系数较大，约为2O(nlogn)，所以排序较慢*/
	堆排序 sort = new 堆排序();
	long start = System.currentTimeMillis();
	sort.sort();
	long end = System.currentTimeMillis();
	System.out.println("Run time is "+(end - start));
	sort.display();
    }

}
