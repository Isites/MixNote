package com.xw.sort;

public class �������� {
    // ����������
    private int num[] = { 20, 14, 10, 34, 44, 87, 99, 15 };
    
    
    /**֮ǰʹ�õķ���
     * if(num[i] < num[i-1]){//���ж��Ƿ��б�Ҫ���в�����ڲ�����
	       int key = num[i];
	       num[i] = num[i-1];
	       int j = i-2;
	       for(;  j > 0 && key < num[j]; j--){
		   num[j+1] = num[j];
	       }
	       if(j==0 && key < num[j]){//����������������������
		   num[j+1] = num[j];
		   num[j] = key;
	       }
	       else num[j+1] = key;
	   }*/

    // ���������е�������
    public void sort() {
	for (int i = 1; i < num.length; i++) {
	    // ���ѭ����һ��Ԫ�ز�����������ֱ������
	    // ���������Ż������
	    int j = i - 1;
	    int key = num[i];
	    for (; j >= 0 && key < num[j]; j--) {
		num[j + 1] = num[j];
	    }
	    num[j + 1] = key;   //��Ϊ�������ѭ����j������һ��1�������ڸ�ֵ��ʱ��Ҫ���¼�1
	}
    }

    public void display() {
	for (int i = 0; i < num.length; i++) {
	    System.out.print(num[i] + "\t");
	}
    }

    public static void main(String[] args) {
	/**
	 * ����������㷨ʱ�临�Ӷ�ΪO(n^2)
	 */
	�������� sort = new ��������();
	long start = System.currentTimeMillis();
	sort.sort();
	long end = System.currentTimeMillis();
	System.out.println("Run time is " + (end - start));
	sort.display();
    }

}
