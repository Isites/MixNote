package com.xw.data;

import java.util.Scanner;

public class 贝叶斯测试 {
    
    
    private static Scanner s;


    public static void display(朴素贝叶斯分类 p, String str){
	
	double yp = p.getYP(str);
	double np = p.getNP(str);
	
	if(yp > np)
	    System.out.println("yes");
	else System.out.println("no");
    }
    
    
    public static void main(String[] args) {
	s = new Scanner(System.in);
	朴素贝叶斯分类 p = new 朴素贝叶斯分类("database.txt");
	System.out.println("请输入测试类:");
	String str = s.next();
	display(p, str);
	
    }
    

}
