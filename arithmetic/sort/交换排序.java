package com.xw.sort;

public class �������� {
  //����������
    private int num[] = {20,14,10,34,44,87,99,15};
    //ʵ�������������
    public void sort(){
	for(int i = 0; i < num.length-1; i++){//���ѭ����ÿһ��ѭ���ҳ���ǰ��Сֵ
	    for(int j = i+1; j < num.length;  j++){//�ڲ�ѭ���ҳ���ǰ��Сֵ
		if(num[j] < num[i]){
		    int tmp = num[j];
		    num[j] = num[i];
		    num[i] = tmp;
		}
	    }
	}
    }
    /**
     * ð������ͽ����������������
     * ð�������Ǻ����ڵ�Ԫ�ؽ������Ӷ���һ��������С��ֵ���ϸ���ָ��λ��
     * �����������Ǻ�ָ��λ��Ԫ�ؽ������ҵ���СԪ�ز����ƶ�λ�õ�Ԫ�ؽ���*/
    public void display(){
   	for(int i = 0; i < num.length; i++){
   	    System.out.print(num[i]+"\t");
   	}
   }
    public static void main(String args[]){
	//�㷨ʱ�临�Ӷ�ΪO(n^2)
	�������� sort = new ��������();
	long start = System.currentTimeMillis();
	sort.sort();
	long end = System.currentTimeMillis();
	System.out.println("Run time is "+(end - start));
	sort.display();
    }
}
