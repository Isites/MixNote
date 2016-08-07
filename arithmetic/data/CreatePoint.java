package com.xw.data;

import java.util.ArrayList;
import java.util.Random;

public class CreatePoint {

    private ArrayList<Point> list;
    private int cp;

    public CreatePoint(int count) {
	list = new ArrayList<Point>();
	cp = count;
	addPoint();
    }

    // 使用随机数的方式产生cp个point，并把产生的点加入到list中
    // 暂时假定产生的点的坐标范围（x,y）均在0-99之间
    public void addPoint() {
	Random r = new Random();

	for (int i = 0; i < cp; i++) {
	    float x = r.nextInt(100);
	    float y = r.nextInt(100);
	    //先检查是否已经存在这个点，如果已经存在则重新生成
	    while (isExisits(x, y)) {
		x = r.nextInt(100);
		y = r.nextInt(100);
	    }
	    list.add(new Point(x, y));
	}
    }

    private boolean isExisits(float x, float y) {

	for (int i = 0; i < list.size(); i++) {
	    Point point = list.get(i);
	    if(x == point.x && y == point.y) return true;
	}
	return false;
    }
    
    public ArrayList<Point> getPoints(){
	return this.list;
    }

}
