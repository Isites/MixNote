package com.xw.data;

import java.util.Scanner;

public class ��Ҷ˹���� {
    
    
    private static Scanner s;


    public static void display(���ر�Ҷ˹���� p, String str){
	
	double yp = p.getYP(str);
	double np = p.getNP(str);
	
	if(yp > np)
	    System.out.println("yes");
	else System.out.println("no");
    }
    
    
    public static void main(String[] args) {
	s = new Scanner(System.in);
	���ر�Ҷ˹���� p = new ���ر�Ҷ˹����("database.txt");
	System.out.println("�����������:");
	String str = s.next();
	display(p, str);
	
    }
    

}
