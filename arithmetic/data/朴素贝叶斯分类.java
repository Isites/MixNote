package com.xw.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * ������һ����ǰ�ᣬ�Ǿ��Ǳ�Ҷ˹��ʽ�� 
 * P(A|B)P(B) = P(B|A)P(A) 
 * ������ʽ�ӿ�ֻ֪��Ҫ����һ�ߵĵ�ʽ����
 * */

public class ���ر�Ҷ˹���� {

    private Map<String, StringData> yesMap;
    private Map<String, StringData> noMap;

    // ����yes��no�Ļ�׼����
    private double p2[];
    // ����Ϊyes�ĸ��������
   // private double yifp[];
    // ����Ϊno�ĸ��������
    //private double nifp[];

    public ���ر�Ҷ˹����(String path) {
	init(path);
	initp();
    }

    // ����һЩ��ʼ������
    private void init(String path) {
	yesMap = new HashMap<String, StringData>();
	noMap = new HashMap<String, StringData>();
	readData(path);
    }

    // �Եõ������ݽ���һϵ�д���õ���Ҷ˹�㷨���м����
    private void initp() {
	// ��Ҫʹ�õĸ��ʳ�ʼ��
	p2 = new double[2];
	//yifp = new double[10];
	//nifp = new double[10];

	get2P();
	//inityifp();
	//initnifp();
    }

    // �õ�yes��no����ĸ���
    private void get2P() {
	int yes = getYCount();
	int no = getNCount();
	p2[0] = yes/1.0d / (yes + no);
	p2[1] = no/1.0d / (yes + no);
    }
    {
//    // ��ȡ���л���yes�������������
//    private void inityifp() {
//	// �����ﲻ�������������˳��
//	int yes = getYCount();
//	Iterator<StringData> iterator = yesMap.values().iterator();
//	int i = 0;
//	yifp[i] = iterator.next().count/1.0d / yes;
//	while (iterator.hasNext()) {
//	    i++;
//	    yifp[i] = iterator.next().count /1.0d/ yes;
//	}
//	//����û�е�Ԫ��ȫ����ֵΪ1�������
//	while(i < 10){
//	    yifp[i] = 1/1.0d / yes;
//	    i++;
//	}
//	
//    }

//    private void initnifp() {
//	// �����ﲻ�������������˳��
//	int no = getNCount();
//	Iterator<StringData> iterator = noMap.values().iterator();
//	int i = 0;
//	nifp[i] = iterator.next().count/1.0d / no;
//	while (iterator.hasNext()) {
//	    i++;
//	    nifp[i] = iterator.next().count/1.0d / no;
//	}
//	//����û�е�Ԫ��ȫ����ֵΪ1�������
//	while(i < 10){
//	    nifp[i] = 1/1.0d / no;
//	    System.out.println(1/1.0d/no);
//	    i++;
//	}
//    }

    // ��ʱ���Բ����ڵ�Ԫ��������
    // //�ж��Ƿ����ĳһ������Ԫ�ز����ڣ��򷵻����Ԫ��
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

    // ��ѵ�������ж�ȡԪ�ز���ѵ������������
    private void readData(String path) {

	File file = new File(path);

	try {
	    BufferedReader rd = new BufferedReader(new InputStreamReader(
		    new FileInputStream(file)));
	    String line = rd.readLine();

	    while (line != null) {
		// ������Զ�ȡ��ÿһ������������
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
	// ��������ַ����ָ����һ���ַ�������
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
	    //�������û�е�Ԫ��������
	    if(noMap.get(elements[i]) != null)
		p *= noMap.get(elements[i]).count/1.0d/no;
	    else p *= 1/1.0d/no;
	}
	return p;
    }    
}

// ��������ݽṹ�������洢һ���ַ������ַ���������
class StringData {

    public StringData(String str, int count) {
	this.str = str;
	this.count = count;
    }

    String str;
    int count;
}
