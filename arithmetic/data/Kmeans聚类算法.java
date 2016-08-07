package com.xw.data;

import java.util.ArrayList;



/**
 * 1���Ⱦ���
 * 2�����¼���������ĵ�
 * 3���ظ���������ֱ�����ĵ�ı仯��ֵС�ڸ�����ֵ���ߴﵽѭ������*/

public class Kmeans�����㷨 {
    //�Լ�����һ����ֵ������ʾһ���������ĵ�ı仯��ֵ
    public  final static float CHANGED_VALVE = 0.5f;
    //�㷨ֹͣ�������ﵽ���ĵ�������
    public final static int MAX_COUNT = 1000;
    //�ڳ���һ���ľ���֮��ѡ�����ƫ�����ľ���(������Ϊ)
    //private final static float ABANDON_VALVE = 20.f;
    //��Ҫ���ֵĶ��ٸ�����
    private int n;
    //���� ���������
    private ArrayList<PointClass> cList;
    //������ɵ����еĵ�
    private ArrayList<Point> plist;
    /**
     * ����Ҫ���ǿմص����
     * ��Σ���Ҫ����*/
    public Kmeans�����㷨(int count, int k){
	//�������������г�ʼ��
	 init(count,k);
	 //ʵ�־����㷨��Ӧ�û��кܶ����⣩
	 kmeans();
    }
    
    private void init(int count,int k){
	this.n = k;
	
	cList = new ArrayList<PointClass>();
	plist = new CreatePoint(count).getPoints();
	
	if(k >= count || k == 1 )
	    try {
		throw new Exception("������Ϸ������ݣ�");
	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	//��ʼʱ����ѡȡk������㲢���뵽clist��
	for(int i = 0 ; i < k; i++){
	    cList.add(new PointClass(plist.get(i)));
	}
    }
    
    //�Ը�������з��࣬���������⼸��������ĵ���뵽���Ե�����
    private void classIfication(Point p){
	float [] md = new float[cList.size()];
	//����õ㵽���������ĵľ���
	for(int i = 0; i < cList.size(); i++){
	    md[i] = clacD(cList.get(i).point, p);
	}
	//��һ��k����¼����ʷ�����±�
	int k = 0;
	float min = md[0];
	for(int i = 0; i < md.length; i++ ){
	    if(md[i] < min){
		min = md[i];
		k = i;
	    }
	}
	//���õ���뵽�±�Ϊk�ķ�����
	cList.get(k).pList.add(p);
    }
    //�����еĵ�ֱ�ۺϵ�����������ķ�����
    private void group(){
	for(int i = 0; i < plist.size(); i++ ){
	    classIfication(plist.get(i));
	}
    }
    
    //���¼������ĵ�,������һ�ε�ı仯����
    //���޸���Ӧ��ֵ
    private void calcCenterP(){
	for(int i = 0; i < cList.size(); i++){
	    float x  = 0,y = 0;
	    for(int j = 0; j < cList.get(i).pList.size(); j++){
		x += cList.get(i).pList.get(j).x;
		y += cList.get(i).pList.get(j).y;
	    }
	    
	    x /= cList.get(i).pList.size();
	    y /= cList.get(i).pList.size(); 
	    
	    Point p = new Point(x, y);
	    cList.get(i).lastDeviation = clacD(p, cList.get(i).point);
	    cList.get(i).point = p;
	}
    } 
    
    private void kmeans(){
	for(int i = 1; i <= MAX_COUNT && !isCanOver(); i++){
	    group();
	    calcCenterP();
	    removeIndex();
	}
    }
    
    //�ж��Ƿ����еı仯ֵ���Ѿ�С�ڸ����ķ�ֵ�����������κα仯
    private boolean isCanOver(){
	for(int i = 0; i < cList.size(); i++){
	    if(cList.get(i).lastDeviation >= CHANGED_VALVE)
		return false;
	}
	return true;
    }
    
    //�������ĵ�󣬽����еı�ǵ�ȫ����������ں����ظ�����
    private void removeIndex(){
	for(int i = 0; i < cList.size(); i++){
	    cList.get(i).pList.clear();//�������
	}
    }
    
    
    //���ڼ���ƽ�� ������֮��ľ���
    private float clacD(Point p1, Point p2){
	float x = Math.abs(p1.x - p2.x);
	float y = Math.abs(p1.y - p2.y);
	float d = (float) Math.sqrt(x*x +  y*y);	
	return d;
    }

}



//�½�һ��������ݽṹ
class Point{
    float x;
    float y;
    public Point(float x, float y){
	this.x = x;
	this.y = y;
    }
}

class PointClass{
    Point point;
    //��һ�ε�ƫ��ֵ
    float lastDeviation = Kmeans�����㷨.MAX_COUNT;
    ArrayList<Point> pList = new ArrayList<Point>();
    public PointClass(Point p){
	point = p;
    }
}


