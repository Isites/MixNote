package com.xw.sort;

public class ð������ {
    //����������
    private int num[] = {20,14,10,34,44,87,99,15};
    //�Ӵ�С�ĵݼ�����
    public void sort(){
	for(int i = 0; i < num.length-1; i++){//���ѭ����ÿѭ��һ�Σ�����ǰ���ֵ�ϸ�
	    for(int  j = num.length-1; j > i; j--){//�ڲ�ѭ��������ѭ�������ֵ�Ƶ�i��λ��
		if(num[j] > num[j-1]){//��num[j]����num[j-1]ʱ���������ߵ�λ��
		    int tmp = num[j];
		    num[j] = num[j-1];
		    num[j-1] = tmp;
		}
	    }
	}
    }
    
    public void display(){
	for(int i = 0; i < num.length; i++){
	    System.out.print(num[i]+"\t");
	}
    }
    
    public static void main(String args[]){
	/**
	 * ������ʱ�临�Ӷ�ΪO(n^2)
	 * ��õ������Ϊ���򣬽������н���
	 * ѡ�����򡢿�������ϣ�����򡢶��������ȶ��������㷨��
	 * ð�����򡢲������򡢹鲢����ͻ����������ȶ��������㷨��*/
	ð������  sort = new ð������();
	long start = System.currentTimeMillis();
	sort.sort();
	long end = System.currentTimeMillis();
	System.out.println("Run time is "+(end - start));
	sort.display();
    }
}
