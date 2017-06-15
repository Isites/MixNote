[TOC]

#### 网站后端结构

```sequence
access ->> nginx: static resource request
nginx ->> access: static resource response




```

```sequence
access ->> nginx: dynamic resource request
nginx ->> php_fpm: dynamic resource request
php_fpm ->> nginx: dynamic resource reponse
nginx ->> access: dynamic resource reponse
```

#### php-fpm简介

FPM(FastCGI进程管理器)用于替换PHP FastCGI的大部分附加功能, 对于高负载王章非常有用. 简要描述几个我关注的特点:

* 支持平滑停止/启动的高级进程管理功能
* 在发生意外情况的时候能够重新启动并缓存被破坏的opcode
* 动态/静态子进程产生


#### 遇到问题必看的三种log

##### nginx日志

对于网站来说能不能访问, 能不能访问到是我们最为关心的事情.检查的两种办法

1. 直接浏览器访问看能否正常访问
2. 配合浏览器访问查看nginx访问日志和错误日志

nginx日志位置:**/opt/data1/nginxlogs**

##### esmeralda日志

esmeralda日志是我们网站运行时产生的日志, 它直接反映了我们代码是否正常运行.遇到问题, 最常查看的就是esmeralda日志. 注意log中的关键词: **Fatal Error**

日志目录:**/var/www/http/*-prod/var/log/**

##### php-fpm日志

fpm的日志查看相对较少, 但是如果需要查看fpm日志的时候, 问题就已经相对严重了, 要引起高度重视.

日志目录:**/var/log/php-fpm/**

##### 如何快速定位问题

* 浏览器中网站能够正常访问, 或者点击点连接跳转到首页:**一般是代码的问题, 查看esmeralada日志**
* 页面能够访问, esmeralada日志正常: **记得要绕缓存查看nginx日志**
* 页面不能正常访问:**三种日志都要看**

#### fpm参数

在上一次报警中, 查看到fpm的error的关键词为**zend_mm_heap corrupted**, 初步认为是, fpm运行时内存不够. 优化策略如下:

1. 反馈给开发, 优化代码结构
2. 修改fpm参数

* **pm.max_children**: pm设置为stack是表示创子进程的数量, pm设置为dynamic时表示最大可创建的子进程数量. 我们一般是设置为dynamic.**根据访问量和系统本身设置其最大进程数, 可以有效保证每个子进程分的得内存空间**
* **pm.max_requests**: 设置每个进程重生之前的服务的请求数.**对于存在内存泄露的第三方模块来说非常有用**. *而我的观点是, 可以及时释放代码中产生的脏内存*
* **request_terminate_timeout**: 设置单个请求的超时中止时间.**避免数据库死锁导致积累大量的php请求.**