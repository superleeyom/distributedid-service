# distributedid-service

## 数据库自增

利用 MySQL 数据库的自增主键，来作为唯一的全局 id，专门用一张表来生成主键，表结构如下：

```sql
CREATE TABLE `distributed_id` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `extra` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '额外字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='主键id生成';
```

`extra`是额外字段，因为你要总要插入点什么，才能生成一个自增主键 id，这种方式存在问题是，每次生成一个id，都要请求MySQL，如果 MySQL 挂掉了，那么也就无法生成唯一 id 了，存在一定的风险。

## 数据库多主模式

既然一台 MySQL 有风险，那么可以将 MySQL 扩展为两台，采用双主模式集群，设置每台 MySQL 的步长和起始值，其中一台 MySQL 的起始值为 1，步长为 2，一台的起始值为 2，步长为 2，配置如下：

```
# mysql1，这里需要注意，执行如下命令前，最好先删除主键id生成表，否则可能不生效
# 最好连接 mysql 命令行界面执行，Navicat 等工具有时候会不生效
set global auto_increment_offset = 1;
set global auto_increment_increment = 2;

# mysql2
set global auto_increment_offset = 2;
set global auto_increment_increment = 2;

# 查询是否修改成功
SHOW global VARIABLES LIKE 'auto_inc%'; 
```

通过如上的配置后，`MySQL-1` 生成：`1、3、5、7 ... `系列主键 id，`MySQL-2` 生成 `2、4、6、8 ...` 系列的主键 id，即使其中一台 MySQL 挂了，另外一台 MySQL 还可以继续生成 id，然后专门搭建一个后台服务 distributedd-service 服务，暴露 http 接口，专门用于其他服务生成分布式 id。这里我写了一个示例：[distributedd-service ](https://github.com/superleeyom/distributedid-service)，可以参考一下；

但是这种还是有很大局限性，如果要再增加一台 MySQL，那该怎么办，首先，要调整新增加 MySQL 的起始值足够大，其次，之前两台的 MySQL 的步长肯定得修改，而且在修改步长的同时，很有可能会产生重复的 id，所以这种方式也不太可行。