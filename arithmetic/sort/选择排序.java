package com.xw.sort;

public class ѡ������ {
    //����������
    private int num[] = {20,14,10,34,44,87,99,15};
    //����������������Ϊ����
    public void sort(){
	int min ; //�����Сֵ
	int iPos ; //�����Сֵ���±�
	for(int i = 0; i < num.length-1; i++ ){
	    //���ѭ����ÿһ��ѭ������ǰ�������Ԫ������С��ֵ
	    //�ŵ�λ��i
	    min = num[i];//��ʼ��
	    iPos = i;
	    for(int j = i+1; j < num.length; j++ ){
		if(num[j] < min){//�ҳ���Сֵ
		    min = num[j];
		    iPos = j;
		}
	    }
	    //����СԪ�غ͵�ǰλ�õ�Ԫ�ؽ���
	    num[iPos] = num[i];
	    num[i] = min;
	}
    }
    public void display(){
	for(int i = 0; i < num.length; i++){
	    System.out.print(num[i]+"\t");
	}
    }
    public static void main(String args[]){
	/**
	 * ѭ��������0.5(n-1)*n�������㷨ʱ�临�Ӷ���ΪO(n^2)
	 * ����ÿ�����ѭ��ֻ�����Դ˽���������������˳������������Լ���һ���Ľ�������
	 * ѡ������ͽ���������������������˱�����һ���̶��ϼ����˽����Ĵ���*/
	ѡ������  sort = new ѡ������();
	long start = System.currentTimeMillis();
	sort.sort();
	long end = System.currentTimeMillis();
	System.out.println("Run time is "+(end - start));
	sort.display();
    }
}
