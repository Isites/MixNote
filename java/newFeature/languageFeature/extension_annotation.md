### 扩展注解的支持

> java 8扩展了注解的上下文。现在几乎可以为任何东西添加注解： 局部变量、泛型类、父类与接口的实现，就连方法的异常也能添加注解。

```java
public class Annotations{
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
  public @interface NonEmpty{}
  public static class Holder<@NonEmpty T> extends @NonEmpty Object{
    public void method() throws @NonEmpty Exception{}
  }
  @SuppressWarnings("unused")
  public static void main(String args[]){
    final Holder<String> holder = new @NonEmpty Holder<String>();
    @NonEmpty Collection<@NonEmpty String> strings = new ArrayList<>();
  }
}
```

ElementType.TYPE_USE和ElementType.TYPE_PARAMETER是两个新添加的用于描述适当的注解上下文的元素类型。在java语言中，注解处理API也有小的改动来识别新增的类型注解。