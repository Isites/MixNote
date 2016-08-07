package com.xw.sort;

/**
 * 用二分法求一元非线性方程 f(x) = x^3/2+2x^2-8=0 利用迭代法解放程
 * 
 * 
 * 算法思路：
 * f(x) 在[a,b]上连续
 * [a0,b0] = [a,b] c = (a0+b0)/2
 * if (f(c)  == 0) printf("root");
 * 
 * f(a0)f(c) < 0 [a0,b0] = [a0,c] c = (a0+b0)/2
 * f(b0)f(c) < 0 [a0,b0] = [c,b0] c = (a0+b0)/2
 * 
 * 判断是否有f(c) = 0,反复迭代或者 |a0-b0|<0.0001时，就认为找到了方程的解
 */

public class 迭代法 {

    public static void div(double x1, double x2){
	double f1 = 0.5*Math.pow(x1, 3)+2*Math.pow(x1, 2)-8;
	double f2 = 0.5*Math.pow(x2, 3)+2*Math.pow(x2, 2)-8;
	
	if(f1*f2 > 0){
	    System.out.println("No Root");
	    return;
	}
	double f,x;
	do{
	    x = 0.5*(x1+x2);
	    f = 0.5*Math.pow(x, 3)+2*Math.pow(x, 2)-8;
	    if(f==0) break;
	    
	    if(f1*f < 0){
		x2 = x;
		f2 = 0.5*Math.pow(x2, 3)+2*Math.pow(x2, 2)-8;
	    } 
	    else{
		x1 = x;
		f1 = 0.5*Math.pow(x1, 3)+2*Math.pow(x1, 2)-8;
	    }
	}while(Math.abs(f)>=1e-4);
	
	System.out.println("Root = "+x);
    }
    
    public static void main(String[] args) {
	div(0, 2);
    }
    
}
