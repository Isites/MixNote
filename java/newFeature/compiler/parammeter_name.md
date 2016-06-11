### 参数名字

> 很长一段时间里，java 程序员一直在发明不同的方式使得方法参数的名字能保留在java字节码中，并且能够在运行时获取他们（比如，Paranamer类库）。最终，在java 8中把这个强烈要求的功能添加到语言层面（通过反射API与Parameter.getName）与字节码文件（通过新版的javac -parameters选项）中。

```java
public class ParameterNames{
  public static void main(String []args){
    Method method = ParameterNames.class.getMethod("main", String[].class);
    for(final Parameter parameter : method.getParameters()){
      System.out.println("Parameter: " + parameter.getName());
  	}
  }
}
```

如果不适用`-parameters`参数来编译这个类，然后运行这个类，会得到下面的输出：

> Parameter: arg0

如果使用`-parameters`参数来编译这个类，程序的结构会有所不同（参数的真实名字将会显示出来）：

> Parameter: args

对于有经验的Maven用户，通过maven-compiler-plugin的配置可以将`-parameters`参数添加到编译器中去。

```xm
<plugin>
	<groupId>org.apache.mavent.plugins</groupId>
	<aftifactId>maven-compiler-plugin</aftifactId>
	<version>3.1</version>
	<configuration>
		<compilerArgument>-parameters</compilerArgument>
		<source>1.8</source>
		<target>1.8</target>
	</configuration>
</plugin>
```

