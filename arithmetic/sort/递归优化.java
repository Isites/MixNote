package com.xw.sort;

import java.util.Scanner;

public class �ݹ��Ż� {
    private static Scanner scanner;
    //Ĭ��b1 ��b2 ��ֵΪ1��c��ֵΪ3
    
    public static int fib(int n,int b1,int b2,int c){
	if(n < 3) return 1;
	else {
	    if( n== c) return b1+b2;
	    else return fib(n,  b2, b1+b2, c+1);
	}
    }
    public static void main(String[] args) {
	scanner = new Scanner(System.in);
	int n = scanner.nextInt();
	
	System.out.println(fib(n,1,1,3));
	
    }
}
