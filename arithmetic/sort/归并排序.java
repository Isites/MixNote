package com.xw.sort;


public class �鲢���� {

    // ����������
    private int num[] =  { 20, 14, 10, 34, 44, 87, 99, 15 };
    private int numSort[] = new int[8];
    /**
     * @param a
     *     ����Ԫ�ص�
     * @param i
     * @param m
     * @param n
     * 
     *����ʵ�ֽ������������кϲ�
     */
    public void merge(int a[],int b[], int i, int m, int n){
	//�������a[i...m]��a[m+1...n]�鲢Ϊ�������� b[]
	int k = i,j = m+1;
	for (; i<= m && j<= n; k++) {    //��a�м�¼��С����ĵز���b��
	    if( a[i] < a[j]) b[k] = a[i++];
	    else b[k] = a[j++];
	    
	}
	while(i <= m){
	    b[k++] = a[i++];                       //��ʣ���a[i...m]���Ƶ�b��
	}
	while(j <= n){
	    b[k++] = a[j++];                       //��ʣ���a[j....n]���Ƶ�b��         
	}
    }    
    
    
    //�˷����Լ����˲����޸�
    
//    public void sort(int a[], int s, int t){
//	//��a[s...t]�鲢����Ϊb[s...t]
//	if(s == t);
//	else{
//	    int m = (s+t)/2; //��a[s...t]ƽ��Ϊa[s...m]��a[m+1...t]
//	    sort(a,s, m);   //ע���ȵ�һ��
//	    sort(a,m+1, t);
//	    merge(a, numSort, s, m, t);
//	    for(int i = s; i <= t; i++)
//		a[i] = numSort[i];
//	}
//    }
    
    
    public void sort1(int a[], int b[], int s, int t){
	//��a[s...t]�鲢����Ϊb[s...t]
	if(s == t) b[s] = a[s];
	else{
	    int m = (s+t)/2; //��a[s...t]ƽ��Ϊa[s...m]��a[m+1...t]
	    sort1(a,b,s, m);   //ע���ȵ�һ��
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
	 *���������Ͷ�������ȣ��鲢���������ص�������һ���ȶ������򷽷�
	 *��ʱ�临�Ӷ�ΪO(nlogn)
	 *������Ҫͬ�ȵĿռ临�Ӷ�*/
	
	�鲢���� sort = new �鲢����();
	long start = System.currentTimeMillis();
	//sort.sort(sort.num,0,sort.num.length-1);
	sort.sort1(sort.num, sort.numSort, 0, sort.num.length-1);
	long end = System.currentTimeMillis();
	System.out.println("Run time is "+(end - start));
	sort.display();
    }
    
    
    
    
}
