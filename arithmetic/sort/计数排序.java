
package com.xw.sort;

public class �������� {
    //����������
    private int num[] = {20,14,10,34,44,87,99,15};
    //������Ӵ�С�ĵݼ�����
    private int b[];
    public ��������(){
	b = new int[num.length];
    }
    public void sort(){
	//���㷨�о����ԣ���ʹ��ʱ�������ĸ���
	//���ھ��Ȱ�����������
	int c[] = new int[100];//Ĭ��c��ʼ��
	for(int i = 0; i < num.length; i++){//��ʼ��һ��������¼ÿһԪ�ر������Ԫ�ظ���
	    c[ num[i] ]++;    //��c�д��ֵΪi��Ԫ�ظ���
	}
	for(int i = c.length-2; i > -1; i--){
	    c[i] = c[i] + c[i+1];                   //��c[i]�д��ֵС�ڵ���i��Ԫ�ظ���
	}
	for(int i = num.length-1; i > -1; i--){
	    b[ c[ num[i] ]-1 ] = num[i];        //����c[i]��ֵ��Ԫ�طŵ�����b�Ķ�Ӧλ��
	    //c[ num[i] ] --;
	}
    }
    public void display(){
   	for(int i = 0; i < b.length; i++){
   	    System.out.print(b[i]+"\t");
   	}
   }
    public static void main(String[] args) {
	�������� sort = new ��������();
	long start = System.currentTimeMillis();
	sort.sort();
	long end = System.currentTimeMillis();
	System.out.println("Run time is "+(end - start));
	sort.display();
    }
}
