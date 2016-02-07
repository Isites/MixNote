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
>程序控制台输出如下：
>default method
>overridden method

