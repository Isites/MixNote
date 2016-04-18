> 删除日志

```shell
find ./ -name "*log" -exec{}
```

> 查看当前目录下文件个数

```shell
find ./ |　wc -l
```

> 在.bashrc中设置命名别名

```shell
alias lsl='ls -lrt'
alias lm='ls -al|more'
```

> 搜寻文件或目录

```shell
find ./ -name "core" | xargs file
```

> find是实时查找，如果需要更快的查询，可试试locate；locate会为文件系统建立索引数据库，如果有文件更新，需要定期执行更新命令来更新索引库。

```shell
locate string
```

> 只看前10行

```shell
head 
```

