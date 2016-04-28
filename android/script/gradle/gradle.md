## 什么是Gradle

> Gradle 以module 来管理project， 在Gradle构建的Gradle project中通常包含application module(com.android.application) , 与library module(com.android.library)两种module。
>
> 在Gradle的project中需要使用，基本上全部都使用.gradle文件来配置，是一个脚本化的工程结构，而非原先ADT中那种eclipse的可视化结构。gradle的依赖管理极其强大，几乎所有的开源项目都可以简单的通过一条compile指令完成依赖的配置

## Gradle 基本概念

> 首先我们学习几个gradle的脚本语法，掌握了这几个语法，你就能非常简单的用gradle构建打包android项目了。看一个最简单的android `build.gradle`. 

```
buildscript{
  repositories{
  	mavenCentral()
  }
  dependencies{
  	classpath 'com.android.tools.build:gradle:0.4'
  }
  apply plugin: 'android'
  android{
  	compileSdkVersion 17
  }
}
```

* `buildscript{}` 设置脚本的运行环境

> Returns a hander to create repositories whiched are used for retrieving dependencies and uploading artifacts produced by the project.

* `repositories{}` 支持java 依赖管理(maven/ivy), 用于项目的依赖。


* `apply plugin:`  声明构建的项目类型
* `android{}` 设置编译android项目的参数，构建android项目的所有配置都在这里完成

## Gralde打包

> 默认输出release apk是没有签名的， 那么我们需要签名的很简单，只需要在android{}里面补充如下即可。

```
signingConfigs{
  myConfig{
  	storeFile file("gradle.keystore");
  	storePassword "gradle"
  	keyAlias "gradle"
  	keyPassword "gradle"
  }
  buildTypes{
  	release{
  		signingConfig signingConfigs.myConfig
    }
  }
}
```

> 然后运行**gradle clean** **gradle build**, 这次在build/apk里面多了一个[项目名]-release-unaligined，，这个是没有进行zipAlign优化的版本。而[项目名]-release就是我们签名，并且zipAlign的apk包了。如果要混淆包，只需要在原来的基础上加上如下脚本

```
buildTypes{
  release{
  	signingConfig signingConfigs.myConfig
  	runProguard true
  	proguardFile 'proguard-android.txt'
  }
}
```

