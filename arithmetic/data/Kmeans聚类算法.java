package com.xw.data;

import java.util.ArrayList;



/**
 * 1、先聚类
 * 2、重新计算聚类中心点
 * 3、重复上述步骤直到中心点的变化阀值小于给定阀值或者达到循环上限*/

public class Kmeans聚类算法 {
    //自己定义一个阀值用来表示一个聚类中心点的变化阀值
    public  final static float CHANGED_VALVE = 0.5f;
    //算法停止的条件达到最大的迭代次数
    public final static int MAX_COUNT = 1000;
    //在超过一定的距离之后选择放弃偏差过大的距离(个人认为)
    //private final static float ABANDON_VALVE = 20.f;
    //想要划分的多少个聚类
    private int n;
    //创建 聚类的数组
    private ArrayList<PointClass> cList;
    //随机生成的所有的点
    private ArrayList<Point> plist;
    /**
     * 首先要考虑空簇的情况
     * 其次，还要考虑*/
    public Kmeans聚类算法(int count, int k){
	//对两个变量进行初始化
	 init(count,k);
	 //实现聚类算法（应该还有很多问题）
	 kmeans();
    }
    
    private void init(int count,int k){
	this.n = k;
	
	cList = new ArrayList<PointClass>();
	plist = new CreatePoint(count).getPoints();
	
	if(k >= count || k == 1 )
	    try {
		throw new Exception("请输入合法的数据！");
	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	//初始时，先选取k个随机点并加入到clist中
	for(int i = 0 ; i < k; i++){
	    cList.add(new PointClass(plist.get(i)));
	}
    }
    
    //对各个点进行分类，即将距离这几类中最近的点放入到各自的组中
    private void classIfication(Point p){
	float [] md = new float[cList.size()];
	//算出该点到各分类中心的距离
	for(int i = 0; i < cList.size(); i++){
	    md[i] = clacD(cList.get(i).point, p);
	}
	//用一个k来记录最合适分类的下标
	int k = 0;
	float min = md[0];
	for(int i = 0; i < md.length; i++ ){
	    if(md[i] < min){
		min = md[i];
		k = i;
	    }
	}
	//将该点加入到下标为k的分类中
	cList.get(k).pList.add(p);
    }
    //将所有的点分别聚合到各自最相近的分类中
    private void group(){
	for(int i = 0; i < plist.size(); i++ ){
	    classIfication(plist.get(i));
	}
    }
    
    //重新计算中心点,和他上一次点的变化距离
    //并修改相应的值
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
    
    //判断是否所有的变化值都已经小于给定的阀值，即不会做任何变化
    private boolean isCanOver(){
	for(int i = 0; i < cList.size(); i++){
	    if(cList.get(i).lastDeviation >= CHANGED_VALVE)
		return false;
	}
	return true;
    }
    
    //更改中心点后，将所有的标记点全部清除，便于后面重复聚类
    private void removeIndex(){
	for(int i = 0; i < cList.size(); i++){
	    cList.get(i).pList.clear();//清除内容
	}
    }
    
    
    //用于计算平面 上两个之间的距离
    private float clacD(Point p1, Point p2){
	float x = Math.abs(p1.x - p2.x);
	float y = Math.abs(p1.y - p2.y);
	float d = (float) Math.sqrt(x*x +  y*y);	
	return d;
    }

}



//新建一个点的数据结构
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
    //上一次的偏差值
    float lastDeviation = Kmeans聚类算法.MAX_COUNT;
    ArrayList<Point> pList = new ArrayList<Point>();
    public PointClass(Point p){
	point = p;
    }
}


