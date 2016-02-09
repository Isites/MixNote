###默认方法
>java 8 用默认方法与静态方法来扩展接口的声明。默认方法与抽象方法不同之处在于抽象方法必须要求实现，但是默认方法则没有这个要求。(如果有必要的话，可以覆盖这个默认实现)

```java
public interface Defaulable{
	default String notRequired(){
    	return "default method";
    }
}
public class DefaulableImpl implements Defaulable{}
public class OverridableImpl implements Defaulable{
	public String notRequired(){
    	return "overridden method";
    }
}
```
###静态方法
>java 8 的接口中可以`声明(并且提供实现)静态方法`。


```java
public interface DefaulableFactory{
	static Defaulable create(Supplier<Defaulable> supplier){
    	return supplier.get();
    }
}
public class Client{
	public static void main(String args[]){
    	Defaulable defaulable = DefaulableFactoy.create(DefaulableImpl::new);
        System.out.println(defaulable.notRequired());
        defaulable = DefaulableFactory.create(OverridableImpl::new);
        System.out.println(defauable.notRequired());
    }
}
```
输出结果：
default method  
overridden method

###抽象类与接口对比
|相同点|不同点|
|------------|----------------------|
|1. 都是抽象类型;2. 都有可以实现方法(以前接口不行);3. 都可以不需要实现类或者继承去实现所有方法，(以前不行，现在接口默认方法不需要实现者实现)|1. 抽象类不可以多重继承，接口可以(无论是多重类型还是多重行为继承);2. 抽象类和接口所反映出的设计理念不同。其实抽象类表示的是`"is-a"`关系，接口表示的是`"like-a"`关系;3. 接口中定义的变量默认是public static final型，且必须给其初始值，所以实现类中不能重新定义，也不能改变其值;抽象类中的变量默认是friendly型，其值可以在子类中重新定义，也可重新赋值|


###多重继承冲突说明
>由于同一个方法可以从不同的接口引入，自然而然的会有冲突现象，默认方法判断冲突的规则如下
>1. 一个声明在类里面的方法优先于任何默认方法(classes always win)
>2. 否则，会优先选取具体的实现

```java
public interface A{
	default void show(){
    	System.out.println("A");
    }
}
public interface B extends A{
	default void show(){
    	System.out.println("B");
    }
}
public class C implements A, B{
	public static void main(String args[]){
    	new C().show();
    }
}
```
输出结果: B

>如果想调用A的默认函数，则用到`新语法`X.super.method(...)，下面修改C类,实现A接口，重写一个show方法

```java
public class C implements A{
	@Override
	public void show(){
    	A.super.show();
    }
    public static void main(String args[]){
    	new C().show();
    }
}
```
输出结果: A





