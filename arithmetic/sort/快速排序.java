package com.xw.sort;

import javax.naming.ldap.SortControl;

public class �������� {
    private int num[] = { 20, 14, 10, 34, 44, 87, 99, 15 };
    
    //һ�˿���������㷨
    public int partition(int arr[], int p, int r) {
	//��������A[p....r]�ļ�¼�������¼��λ��������������λ�ã���ʱ��
	//��֮ǰ���󣩵ļ�¼������С������
	int pkey = arr[p];  //������ĵ�һ��Ԫ����Ϊ�����¼
	while( p < r){       //����������˽������м�ɨ��
	    
	    while( p < r && arr[r] >= pkey){
		r--;
	    }
	    arr[p]  = arr[r];  //��������С�ļ�¼�Ƶ��Ͷ�
	    while (p < r && arr[p] <= pkey) {
		p++;
	    }
	    arr[r] = arr[p];
	}
	arr[p] = pkey; //�����¼��λ
	return p;
    }
    
    //�ݹ���ʽ�Ŀ��������㷨
    public void sort(int arr[], int low, int high){
	//��˳����е�������arr[low....high]����������
	if(low < high){                                                   //���ȴ���1;
	    int pindex = partition(arr, low, high);              //��arr[low,,,,high]һ��Ϊ��
	    sort(arr, low, pindex-1);                                 // �Ե��ӱ�ݹ�����pindex������λ��
	    sort(arr, pindex+1, high);                               //�Ը��ӱ�ݹ�����
	}
    }
    
    public void display(){
   	for(int i = 0; i < num.length; i++){
   	    System.out.print(num[i]+"\t");
   	}
   }
    
    public static void main(String[] args) {
	/**
	 * ͨ������������Ϊ�ǣ�������ͬ��������O(nlogn)��������
	 * �����У���ƽ���������
	 * �������л�������ʱ�����������ɻ�Ϊð������
	 * ʱ�䷢�Ӷȱ�ΪO(n^2)*/
	�������� sort = new ��������();
	long start = System.currentTimeMillis();
	sort.sort(sort.num, 0, sort.num.length-1);
	long end = System.currentTimeMillis();
	System.out.println("Run time is "+(end - start));
	sort.display();
    }
    
    
    
    
    
}
