### 升级正在使用的数据库

1. 登陆AWS的管理面板并且打开亚马逊的RDS控制台.地址<https://console.aws.amazon.com/rds>

2. 创建一个mysql实例的读副本,此过程将创建数据库的升级副本

   1). 在控制面板选择**Instances**,选择一个你想要升级的数据库实例

   2). 选择**Instance Actions**, 并且选择**Create Read Replica**

   3). 为你的读副本提供一个值作为DB实例的标识,并且保证DB实例类和设置与MySQL 5.5 DB实例匹配

   4). 选择**Yes**, 成功创建**Read Replica**

3. 当Read Replica的状态显示为可用时, 可以将数据库升级为5.6

   1). 在控制台,选择**Instances**, 并且选择你刚刚穿件的Read Replica

   2). 选择**Instance Actions**, 并且选择**Modify**

   3). **数据库引擎版本**选择升级到MySQL 5.6, 并且选择**Apply Immediately**. 然后选择**Continue**

   4). 选择**Modify DB Instance**开始升级

4. 当升级完成并且状态显示为可用,验证Read Replica 和MySQL 5.5 DB实例已经升级到最新

