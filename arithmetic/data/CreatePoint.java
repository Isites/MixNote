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

    // ʹ��������ķ�ʽ����cp��point�����Ѳ����ĵ���뵽list��
    // ��ʱ�ٶ������ĵ�����귶Χ��x,y������0-99֮��
    public void addPoint() {
	Random r = new Random();

	for (int i = 0; i < cp; i++) {
	    float x = r.nextInt(100);
	    float y = r.nextInt(100);
	    //�ȼ���Ƿ��Ѿ���������㣬����Ѿ���������������
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
