# Mysql基础

## 创建数据库

**CREATE DATABASE** 数据库名;

例如：Create database student;

## 删除数据库

**drop database** 数据库名;

例如：drop database student;

## Mysql数据类型

### 数值类型

| TINYINT      | 1 Bytes                                  | (-128，127)                                                  | (0，255)                                                     | 小整数值        |
| ------------ | ---------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | --------------- |
| SMALLINT     | 2 Bytes                                  | (-32 768，32 767)                                            | (0，65 535)                                                  | 大整数值        |
| MEDIUMINT    | 3 Bytes                                  | (-8 388 608，8 388 607)                                      | (0，16 777 215)                                              | 大整数值        |
| INT或INTEGER | 4 Bytes                                  | (-2 147 483 648，2 147 483 647)                              | (0，4 294 967 295)                                           | 大整数值        |
| BIGINT       | 8 Bytes                                  | (-9,223,372,036,854,775,808，9 223 372 036 854 775 807)      | (0，18 446 744 073 709 551 615)                              | 极大整数值      |
| FLOAT        | 4 Bytes                                  | (-3.402 823 466 E+38，-1.175 494 351 E-38)，0，(1.175 494 351 E-38，3.402 823 466 351 E+38) | 0，(1.175 494 351 E-38，3.402 823 466 E+38)                  | 单精度 浮点数值 |
| DOUBLE       | 8 Bytes                                  | (-1.797 693 134 862 315 7 E+308，-2.225 073 858 507 201 4 E-308)，0，(2.225 073 858 507 201 4 E-308，1.797 693 134 862 315 7 E+308) | 0，(2.225 073 858 507 201 4 E-308，1.797 693 134 862 315 7 E+308) | 双精度 浮点数值 |
| DECIMAL      | 对DECIMAL(M,D) ，如果M>D，为M+2否则为D+2 | 依赖于M和D的值                                               | 依赖于M和D的值                                               | 小数值          |

### 日期时间

| DATE      | 3    | 1000-01-01/9999-12-31                                        | YYYY-MM-DD          | 日期值                   |
| --------- | ---- | ------------------------------------------------------------ | ------------------- | ------------------------ |
| TIME      | 3    | '-838:59:59'/'838:59:59'                                     | HH:MM:SS            | 时间值或持续时间         |
| YEAR      | 1    | 1901/2155                                                    | YYYY                | 年份值                   |
| DATETIME  | 8    | 1000-01-01 00:00:00/9999-12-31 23:59:59                      | YYYY-MM-DD HH:MM:SS | 混合日期和时间值         |
| TIMESTAMP | 4    | 1970-01-01 00:00:00/2038结束时间是第 **2147483647** 秒，北京时间 **2038-1-19 11:14:07**，格林尼治时间 2038年1月19日 凌晨 03:14:07 | YYYYMMDD HHMMSS     | 混合日期和时间值，时间戳 |

### 字符串类型

| CHAR       | 0-255 bytes           | 定长字符串                      |
| ---------- | --------------------- | ------------------------------- |
| VARCHAR    | 0-65535 bytes         | 变长字符串                      |
| TINYBLOB   | 0-255 bytes           | 不超过 255 个字符的二进制字符串 |
| TINYTEXT   | 0-255 bytes           | 短文本字符串                    |
| BLOB       | 0-65 535 bytes        | 二进制形式的长文本数据          |
| TEXT       | 0-65 535 bytes        | 长文本数据                      |
| MEDIUMBLOB | 0-16 777 215 bytes    | 二进制形式的中等长度文本数据    |
| MEDIUMTEXT | 0-16 777 215 bytes    | 中等长度文本数据                |
| LONGBLOB   | 0-4 294 967 295 bytes | 二进制形式的极大文本数据        |
| LONGTEXT   | 0-4 294 967 295 bytes | 极大文本数据                    |

## 创建数据表

**CREATE TABLE** table_name (column_name column_type);

例如：

Create table CourseAa(

   Aa1 Varchar(20),

​    Aa2 Integer,

​    Aa3 DECIMAL(10));

## 删除数据表

**DROP TABLE** table_name ;

## 插入数据

```
INSERT INTO table_name VALUES ( value1, value2,...valueN );
```

例如：

insert into test VALUES ('赵一',20,580.00,'重邮宿舍 12-3-5');

### 查询数据

```
SELECT column_name,column_name
FROM table_name
[WHERE Clause]
[LIMIT N][ OFFSET M]
```

例如：查询所有计算机学院的学生名字学号

SELECT SNO,Name from Student where College = '计算机学院';

### where语句

如需有条件地从表中选取数据，可将 WHERE 子句添加到 SELECT 语句中。

例如：查询所有年龄在20-23之间的学生信息

 SELECT * from Student where Age BETWEEN 20 and 23;

### Update

如果我们需要修改或更新 MySQL 中的数据，我们可以使用 SQL UPDATE 命令来操作。

例如：将年龄小于20岁的学生分数+5

 update test set Score = Score+5 where Age<=20;

## Delete

你可以使用 SQL 的 DELETE FROM 命令来删除 MySQL 数据表中的记录。

例如：delete from test where  Age>=21 and Score>=500;

## Like

SQL LIKE 子句中使用百分号 **%**字符来表示任意字符，类似于UNIX或正则表达式中的星号 *****。

如果没有使用百分号 **%**, LIKE 子句与等号 **=** 的效果是一样的。

例如： delete from test where   Score<550 and Address like '重邮宿舍%';

## Union

MySQL UNION 操作符用于连接两个以上的 SELECT 语句的结果组合到一个结果集合中。多个 SELECT 语句会删除重复的数据。

## 排序

可以使用 MySQL 的 **ORDER BY** 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。

## GROUP BY

GROUP BY 语句根据一个或多个列对结果集进行分组。

在分组的列上我们可以使用 COUNT, SUM, AVG,等函数。

```
SELECT column_name, function(column_name)
FROM table_name
WHERE column_name operator value
GROUP BY column_name;
```

