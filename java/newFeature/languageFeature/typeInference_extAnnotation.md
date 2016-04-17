###类型推测

> java 8 在类型推测方面有了很大的提高。在很多情况下，编译器可以推测出确定的参数类型，这样就能是代码更整洁。

```java
public class Value<T>{
  public static<T> T defaultValue(){
    return null;
  }
  public T getOrDefault(T value, T defaultValue){
    return value != null ? value : defaultValue;
  }
}
public class TypeInferenc {
  public static void main(String args[]){
    final Value<String> value = new Value<>();
    value.getOrDefault("22", Value.defaultValue);
  }
}
```

Value.defaultValue()的参数类型可以被推测出，所以就不必明确给出。在java 7中， 相同的例子将不会通过编译，正确的书写方式是Value