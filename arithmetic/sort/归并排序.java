package com.xw.sort;


public class 归并排序 {

    // 待排序数组
    private int num[] =  { 20, 14, 10, 34, 44, 87, 99, 15 };
    private int numSort[] = new int[8];
    /**
     * @param a
     *     数组元素的
     * @param i
     * @param m
     * @param n
     * 
     *最终实现将两个有序序列合并
     */
    public void merge(int a[],int b[], int i, int m, int n){
	//将有序的a[i...m]和a[m+1...n]归并为有序序列 b[]
	int k = i,j = m+1;
	for (; i<= m && j<= n; k++) {    //将a中记录由小到大的地并入b中
	    if( a[i] < a[j]) b[k] = a[i++];
	    else b[k] = a[j++];
	    
	}
	while(i <= m){
	    b[k++] = a[i++];                       //将剩余的a[i...m]复制到b中
	}
	while(j <= n){
	    b[k++] = a[j++];                       //将剩余的a[j....n]复制到b中         
	}
    }    
    
    
    //此方法自己做了部分修改
    
//    public void sort(int a[], int s, int t){
//	//将a[s...t]归并排序为b[s...t]
//	if(s == t);
//	else{
//	    int m = (s+t)/2; //将a[s...t]平分为a[s...m]和a[m+1...t]
//	    sort(a,s, m);   //注释先等一下
//	    sort(a,m+1, t);
//	    merge(a, numSort, s, m, t);
//	    for(int i = s; i <= t; i++)
//		a[i] = numSort[i];
//	}
//    }
    
    
    public void sort1(int a[], int b[], int s, int t){
	//将a[s...t]归并排序为b[s...t]
	if(s == t) b[s] = a[s];
	else{
	    int m = (s+t)/2; //将a[s...t]平分为a[s...m]和a[m+1...t]
	    sort1(a,b,s, m);   //注释先等一下
	    sort1(a,b,m+1, t);
	    merge(a,b, s, m, t);
	    for(int i = s; i <= t; i++){
		a[i] = b[i];
	    }
	}
    }
    
    
    
    public void display(){
	for(int i = 0; i < numSort.length; i++){
   	    System.out.print(numSort[i]+"\t");
   	}
    }
    
    public static void main(String[] args) {
	
	/**
	 *与快速排序和堆排序相比，归并排序的最大特点是它是一种稳定的排序方法
	 *其时间复杂度为O(nlogn)
	 *另外需要同等的空间复杂度*/
	
	归并排序 sort = new 归并排序();
	long start = System.currentTimeMillis();
	//sort.sort(sort.num,0,sort.num.length-1);
	sort.sort1(sort.num, sort.numSort, 0, sort.num.length-1);
	long end = System.currentTimeMillis();
	System.out.println("Run time is "+(end - start));
	sort.display();
    }
    
    
    
    
}
