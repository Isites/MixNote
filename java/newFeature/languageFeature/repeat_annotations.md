###重复注解

> java 5 引入了注解机制，然而，使用注解的一个限制是相同的注解在同一位置只能声明一次，不能声明多次。java 8引入了重复注解机制，这样 相同的注解可以在同一地方声明多次。重复注解机制本身必须用`@Repeatable`注解。事实上，这并不是语言层面上的改变，更多的是编译器的技巧，底层原理保持不变。

```java
public class RepeatingAnnotations{
  @Target(ElementType.TYPE)
  @Retention(RetentionPolicy.RUNTIME)
  public @interface Filters{
    Filter[] value();
  }
  @Target(ElementType.TYPE)
  @Rentention(RetentionPolicy.RUNTIME)
  @Repeatable(Filters.class)
  public @interface Filter{
    String value();
  }
  @Filter("filter1")
  @Filter("filter2")
  public interface Filterable{}
  public static void main(String args[]){
    for(Filter filter : Filterable.class.getAnnotationsByType(Filter.class)){
      System.out.println(filter.value());
  	}
  }
}
```

> 如上，这里有个使用@Repeatable(Filters.class)注解的注解类Filter，Filters仅仅是Filter注解的数组，但java编译器并不想让程序员意识到Filters的存在。这样，接口Filterable就拥有了两次Filter()注解。同时，反射相关的API提供了新的函数		`getAnnotationsByType()` 来返回重复注解的类型(请注意Filterable.class.getAnnotation(Filters.class)经编译器处理后将会返回Filters的实例)