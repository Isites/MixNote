### Project结构类型

![](http://static.open-open.com/lib/uploadImg/20150708/20150708104746_354.png)

* `app/build` app模块的gradle编译文件
* `app/build.gradle` app模块的gradle编译文件
* `app/app.imi` app模块的配置文件
* `app/proguard-rules.pro` ap模块proguard文件
* `build.gradle` 项目的gradle编译文件
* `settings.gradle` 定义项目包含那些模块
* `gradlew` 编译脚本， 可以在命令行执行打包
* `local.properties` 配置SDK/NDK
* `MyApplication.iml` 项目配置文件
* `External Libraries` 项目依赖的Lib， 编译时自动下载的

### Android结构类型

![](http://static.open-open.com/lib/uploadImg/20150708/20150708104746_961.png)

* `app/manifests` AndroidManifest.xml配置文件目录
* `app/java` 源码目录
* `app/res` 资源文件目录
* `Gradle Scripts` gradle编译相关的脚本