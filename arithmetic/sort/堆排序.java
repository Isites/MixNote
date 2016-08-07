package com.xw.sort;

public class ������ {
    // ����������
    private int num[] = { 20, 14, 10, 34, 44, 87, 99, 15 };
    
    //������ĳһ��Ԫ��A[i]Ϊ���ڵ��������Ϊһ���µĶѵ��㷨
    public void heapIfy(int a[],int i, int length){
	int l = left(i);
	int r = right(i);
	int larger;
	if( l <= length && a[l] > a[i])
	    larger = l;          //�Ƚ�a[i]��������a[l],����largerָ��ϴ���
	else larger = i;
	if (r <= length && a[r] > a[larger])
	    larger = r;         //�Ƚ�a[larger]�����Һ��ӵĴ�С������ָ��a[i],a[r],a[l]�������
	if(larger != i){
	    int tmp = a[i];  //���a[i]С������Ů���򽻻�a[i]�������Һ����нϴ���
	    a[i] = a[larger]; //Ȼ��ͨ���ݹ����
	    a[larger] = tmp;
	    heapIfy(a, larger, length); //����a����a[lager]Ϊ����������ʹ���Ϊ��
	}
    }
    //�����ѵ��㷨
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
    //���ڷ��ظ��ڵ����Ϊi���������
    public int left(int i){
	return 2*i;
    }
    //���ڷ��ظ��ڵ����Ϊi���Һ������
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
	 * �������ʱ�临�Ӷ�ΪO(nlogn),���е���buildheap��ʱ��
	 * ΪO(n)������n-1��heapIfy��ÿһ�ε�ʱ�临�Ӷ�ΪO(n)
	 * �������㷨���������±Ƚϴ���ΪO(nlogn),�Ҳ���Ҫ����Ŀռ䣬���ڿ�������
	 * �͹鲢���򣬵���Ҳ��ȱ�㣬���л�������ʱ�����нϸߵ�ʱ�����
	 * ���������͹鲢�㷨��ȣ�������Ƚϴ�����Ȼ��ΪO(nlogn)�׵ģ�
	 * ����ϵ���ϴ�ԼΪ2O(nlogn)�������������*/
	������ sort = new ������();
	long start = System.currentTimeMillis();
	sort.sort();
	long end = System.currentTimeMillis();
	System.out.println("Run time is "+(end - start));
	sort.display();
    }

}
