package com.xw.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 首先有一个大前提，那就是贝叶斯公式： 
 * P(A|B)P(B) = P(B|A)P(A) 
 * 由上述式子可知只需要计算一边的等式即可
 * */

public class 朴素贝叶斯分类 {

    private Map<String, StringData> yesMap;
    private Map<String, StringData> noMap;

    // 对于yes和no的基准概率
    private double p2[];
    // 条件为yes的各分类概率
   // private double yifp[];
    // 条件为no的各分类概率
    //private double nifp[];

    public 朴素贝叶斯分类(String path) {
	init(path);
	initp();
    }

    // 进行一些初始化操作
    private void init(String path) {
	yesMap = new HashMap<String, StringData>();
	noMap = new HashMap<String, StringData>();
	readData(path);
    }

    // 对得到的数据进行一系列处理得到贝叶斯算法的中间过程
    private void initp() {
	// 对要使用的概率初始化
	p2 = new double[2];
	//yifp = new double[10];
	//nifp = new double[10];

	get2P();
	//inityifp();
	//initnifp();
    }

    // 得到yes和no两类的概率
    private void get2P() {
	int yes = getYCount();
	int no = getNCount();
	p2[0] = yes/1.0d / (yes + no);
	p2[1] = no/1.0d / (yes + no);
    }
    {
//    // 求取所有基于yes分类的条件概率
//    private void inityifp() {
//	// 在这里不考虑数组里面的顺序
//	int yes = getYCount();
//	Iterator<StringData> iterator = yesMap.values().iterator();
//	int i = 0;
//	yifp[i] = iterator.next().count/1.0d / yes;
//	while (iterator.hasNext()) {
//	    i++;
//	    yifp[i] = iterator.next().count /1.0d/ yes;
//	}
//	//对于没有的元素全部赋值为1在求概率
//	while(i < 10){
//	    yifp[i] = 1/1.0d / yes;
//	    i++;
//	}
//	
//    }

//    private void initnifp() {
//	// 在这里不考虑数组里面的顺序
//	int no = getNCount();
//	Iterator<StringData> iterator = noMap.values().iterator();
//	int i = 0;
//	nifp[i] = iterator.next().count/1.0d / no;
//	while (iterator.hasNext()) {
//	    i++;
//	    nifp[i] = iterator.next().count/1.0d / no;
//	}
//	//对于没有的元素全部赋值为1在求概率
//	while(i < 10){
//	    nifp[i] = 1/1.0d / no;
//	    System.out.println(1/1.0d/no);
//	    i++;
//	}
//    }

    // 暂时不对不存在的元素做处理
    // //判断是否对于某一条件的元素不存在，则返回这个元素
    // private String checkedMap(Map<String, StringData> m,int i){
    //
    //
    //
    // return null;
    // }
    }
    private int getYCount() {
	int num1 = 0;
	if (yesMap.get("youth") != null) {
	    num1 += yesMap.get("youth").count;
	}
	if (yesMap.get("middle_aged") != null) {
	    num1 += yesMap.get("middle_aged").count;
	}
	if (yesMap.get("senior") != null) {
	    num1 += yesMap.get("senior").count;
	}
	return num1;

    }

    private int getNCount() {
	int num1 = 0;
	if (noMap.get("youth") != null) {
	    num1 += noMap.get("youth").count;
	}
	if (noMap.get("middle_aged") != null) {
	    num1 += noMap.get("middle_aged").count;
	}
	if (noMap.get("senior") != null) {
	    num1 += noMap.get("senior").count;
	}
	return num1;
    }

    // 从训练样本中读取元素并对训练样本做处理
    private void readData(String path) {

	File file = new File(path);

	try {
	    BufferedReader rd = new BufferedReader(new InputStreamReader(
		    new FileInputStream(file)));
	    String line = rd.readLine();

	    while (line != null) {
		// 在这里对读取的每一行数据做处理
		String elements[] = getStrArr(line);
		addMap(elements);
		line = rd.readLine();
	    }
	    rd.close();
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    private String[] getStrArr(String str) {
	// 将传入的字符串分割并返回一个字符串数组
	return str.split(" ");
    }

    private void addMap(String arr[]) {
	switch (arr[arr.length - 1]) {
	case "yes":
	    addYMap(arr);
	    break;
	case "no":
	    addNmap(arr);
	    break;
	}
    }

    private void addYMap(String arr[]) {
	for (int i = 0; i < arr.length - 1; i++) {
	    if (yesMap.get(arr[i]) != null) {
		yesMap.get(arr[i]).count++;
	    } else {
		yesMap.put(arr[i], new StringData(arr[i], 1));
	    }
	}
    }

    private void addNmap(String arr[]) {
	for (int i = 0; i < arr.length - 1; i++) {
	    if (noMap.get(arr[i]) != null) {
		noMap.get(arr[i]).count++;
	    } else {
		noMap.put(arr[i], new StringData(arr[i], 1));
	    }
	}
    }
    
    public double getYP(String str){
	String elements[] = getStrArr(str);
	int yes = getYCount();
	double p = p2[0];
	for(int i = 0; i < elements.length; i++ ){
	    if(yesMap.get(elements[i]) != null)
		p *= yesMap.get(elements[i]).count/1.0d/yes;
	    else p *= 1/1.0d/yes;
	}
	return p;
    }
    public double getNP(String str){
	String elements[] = getStrArr(str);
	int no = getNCount();
	double p = p2[1];
	for(int i = 0; i < elements.length; i++ ){
	    //在这里对没有的元素做处理
	    if(noMap.get(elements[i]) != null)
		p *= noMap.get(elements[i]).count/1.0d/no;
	    else p *= 1/1.0d/no;
	}
	return p;
    }    
}

// 定义个数据结构，用来存储一个字符串和字符串的数量
class StringData {

    public StringData(String str, int count) {
	this.str = str;
	this.count = count;
    }

    String str;
    int count;
}
