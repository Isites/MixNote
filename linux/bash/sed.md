> stream editor for filtering and transforming text
>
> 用于过滤和转换文本的流处理器



> sed是一种在线编辑器，它一次处理一行内容。处理时，把当前处理的行存储在临时缓冲区，称为“模式空间”(pattern space)， 接着用sed命令处理缓冲区中的内容，处理完成后，把缓冲区的内容送往屏幕。接着处理下一行，这样不断重复，至到文件末尾。文件内容并没有改变，除非你使用重定向存储输出。	

`sed 命令行格式：` 

```shell
sed [-nefri] 'command' 输入文本
```

常用选项：

​	**-n** : 使用安静(silent)模式。在一般sed用法中，所有来自STDIN的资料一般都会被列出到屏幕上。但如果加上 `-n`参数后，则只有经过sed特殊处理的哪一行（或者动作）才会被列出来；

​	**-e**: 直接在指令模式上进行sed动作编辑；

​	**-f**:  直接将sed的动作写在一个档案内， -f filename 则可以执行filename内的sed 动作；

​	**-r**: sed的动作支援的是延伸型正规表示法的语法(预设是基础正规表示法语法)；

​	**-i**: 直接修改读取的档案内容，而不是荧幕输出。

常用命令：

​	**a**: 新增，`a`的后面可以接字串，而这些字串会在新的一行出现(目前的下一行)；

​	**c**: 取代，`c`的后面可以接字串，这些字串可以取代n1，n2之间的行；

​	**d**: 删除

​	**i**: 插入， `i`后面可以接字串，而这些字串会在新的一行出现(目前的上一行)；

​	**p**: 列印， 亦即将某个选择的资料印出。通常`p`会与参数`-n`一起运作；

​	**s**: 取代， 可以直接进行取代工作哩！通常这个`s`的动作可以搭配正规表示法；列如`1,20s/old/new/g`

**举例**

> 假设有一个文件名为`test`

```shell
#删除某一行
sed '1d' test
#删除最后一行
sed '$d' test
#删除第一行到第二行
sed '1,2d' test
#删除第二行到最后一行
sed '2,$d' test 
####################################
#显示第一行
sed -n '1p' test
#显示最后一行
sed -n '$p' test
#显示第一行到第二行
sed -n '1,2p' test
#显示第二行到最后一行
sed -n '2,$p' test
###################################
#查询包括关键字ruby所在所有行，如果出现关键字，则使用'\'屏蔽特殊含义
sed -n '/ruby/p' test
###################################
#第一行增加字符串“drink tea”
sed '1a drink tea' test
#第一行到第三行增加字符串“drink tea”
sed '1,3a drink tea' test
#第一行后增加多行，使用换行符\n
sed '1a drink tea\nor coffee'
#第一行代替为Hi
sed '1c Hi' test
#第一行到第二行代替为Hi
sed '1,2c Hi' test
###################################
#格式：sed 's/要替换的字符串/新的字符串/g' 要替换的字符串可以用正则表达式
#替换ruby为bird
sed -n '/ruby/p' test | sed 's/ruby/bird/g'
#删除ruby
sed -n '/ruby/p' test | sed 's/ruby//g'
###################################
#在文件test中最后一行直接输入bye
sed -i '$a bye' test
#删除匹配行
sed -i '/匹配字符串/d' test
#替换匹配行中的某个字符串
sed -i '/匹配字符串/s/替换源字符串/替换目标字符串/g' test

```

