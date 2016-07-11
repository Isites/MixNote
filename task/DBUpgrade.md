[TOC]

### 创建只读副本

1. 在控制面板选择**Instances**,选择一个你想要升级的数据库实例
2. 选择**Instance Actions**, 并且选择**Create Read Replica**
3. 为你的读副本提供一个值作为DB实例的标识,并且保证DB实例类和设置与MySQL 5.5 DB实例匹配
4. 选择**Yes**, 成功创建**Read Replica**

### 升级数据库引擎版本

1. 当Read Replica的状态显示为可用时, 可以将数据库升级为5.6

   1). 在控制台,选择**Instances**, 并且选择你刚刚创建的Read Replica

   2). 选择**Instance Actions**, 并且选择**Modify**

   3). **数据库引擎版本**选择升级到MySQL 5.6, 并且选择**Apply Immediately**. 然后选择**Continue**

   4). 选择**Modify DB Instance**开始升级

2. 当升级完成并且状态显示为可用,验证和MySQL 5.5 DB实例相关的Read Replica 是否随主MySQL 5.5

   实例保持最新。通过连接到Read Replica使用`SHOW SLAVE STATUS`命令来验证更新。如果**Seconds_Behind_Master**字段为0，则这个副本已经升级到最新。


### 提升从库

使你的5.6版本的MySQL Read Replica 成为一个主DB实例

**注意**

当你晋升MySQL 5.6 Read Replica 为一个独立的、单一的亚马逊DB实例，它将不再隶属于MySQL 5.5的副本。我们建议在窗口维护期间升级MySQL 5.6 Read Replica，**建议您在源 MySQL 5.5 数据库实例处于只读模式并且所有写入操作都暂停的维护时段期间，提升 MySQL 5.6 只读副本**。当升级完成后，你可以将写操作导入升级后的MySQL 5.6 DB实例以保证不会有写操作丢失。

此外，我们建议**升级之前执行必要的数据定义语言(DDL)操作**，例如创建索引。这种方法避免了在升级后在性能方面的消极影响。升级Read Replica，执行以下步骤：

1. 在控制台，选择**Instances**,并且选择你刚刚升级的Read Replica
2. 选择**Instance Actions**, 并且选择**Promote Read Replica**.
3. 启动自动备份。[更多信息](http://docs.aws.amazon.com/AmazonRDS/latest/UserGuide/USER_WorkingWithAutomatedBackups.html). 选择**Continue**
4.  选择**Yes**, **Promote Read Replica**

### 数据库实例重命名

1. 现在，你有一个升级的MySQL数据库。此时，你可以引导你的应用连接新的MySQL 5.6 实体，增加Read Replicas ，启动多种亚马逊支持等。


### 把提升过程中的数据复制到新库

