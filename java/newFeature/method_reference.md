##方法引用

> 方法引用提供了非常有用的语法，可以直接引用已有Java类或对象(实例)的方法或构造器。与lambda联合使用，方法引用可以使语言的构造更紧凑简洁，减少冗余代码。

```java
public static class Car{
  public static Car create(final Supplier<Car> supplier){
  	return supplier.get();
  }
  public static void collide(final Car car){
  	System.out.println(car.toString());
  }
  public void follow(final Car car){  
    System.out.println(car.toString());
  }
  public void repair(){
  	System.out.println(this.toString());
  }
}
```

> 第一种方法引用是构造器引用，它的语法是`Class::new`,或者更一般的`Class<T>::new`.请注意构**造器没有参数**。

```java
final Car car = Car.create(Car::new);
final List<Car> cars = Arrays.asList(car);
```

> 第二种方法引用是静态方法引用，它的语法是`Class::static_method`。请注意这个方法接受一个Car类型的参数

```java
cars.forEach(Car::collide);
```

> 第三种方法引用是`特定类型的任意对象`的方法引用，它的语法是是`Class::method`。请注意这个方法没有参数。

```java
cars.forEach(Car::repair);
```

> 最后，第四种方法引用是`特定对象`的方法引用，它的语法是`instance::method`。请注意，这个方法接受一个Car类型的参数

```java
final Car car = Car.create(Car::new);
cars.forEach(car::follow);
```

