# Mysql 作业

第五周作业

 

**一、Mysql入门练习题**

\1.    

 

Create table cqupt_student(

studentid Varchar(10),

name Varchar(20),

sex Varchar(2),

age Integer,

Fee DECIMAL(10,2),

address Varchar(50),

memo Varchar(300));

 

\2.    

Create table CourseAa(

   Aa1 Varchar(20),

​    Aa2 Integer,

​    Aa3 DECIMAL(10));

\3.    

 

​    Create table ChooseBb(

​        Aa1 Varchar(20),

​        Aa2 Integer,

​        Aa3 DECIMAL(10));

\4.     ALTER TABLE ChooseBb ADD (Bb4 Varchar (20) NOT NULL DEFAULT ‘系统测试值’);

\5.     

 ALTER TABLE ChooseBb ADD(Bb5 Varchar(10),PRIMARY KEY(Bb5));

\6.    

 

 CREATE VIEW View_Choosebb(View_bb1,View_bb2,View_bb3)

 AS SELECT Bb1,Bb4,Bb5

 FROM ChooseBb;

\7.    

DROP VIEW View_Choosebb;

\8.    

CREATE INDEX Index_bb2 on ChooseBb(Bb2 ASC);

CREATE INDEX Index_bb4 on ChooseBb(Bb4 DESC);

\9.    

DROP INDEX Index_bb2 on ChooseBb;

\10.  

CREATE table test(

 Name VARCHAR(20),

 Age Integer,

 Score NUMERIC(10,2),

 Address VARCHAR(60));

 

\11.  

insert into test VALUES ('赵一',20,580.00,'重邮宿舍 12-3-5');

insert into test VALUES ('钱二',19,540.00,'南福苑 5-2-9');

insert into test VALUES ('孙三',21,555.50,'学生新区 21-5-15');

insert into test VALUES ('李四',22,505.00,'重邮宿舍 8-6-22');

insert into test VALUES ('周五',20,495.50,'学生新区 23-4-8');

insert into test VALUES ('吴六',19,435.00,'南福苑 2-5-12');

 

\12.  

create table test_temp(

  Name VARCHAR(20),

  Age INTEGER,

  Score NUMERIC(10,2),

  Address VARCHAR(60));

 

\13.  用 INSERT 语句对表 test_temp,插入如下 3 条记录：

 insert into test_temp VALUES('郑七',21,490.50,'重邮宿舍 11-2-1');

insert into test_temp VALUES('张八',20,560.00,'南福苑 3-3-3');

insert into test_temp VALUES('王九',10,515.00,'学生新区 19-7-1');

 

\14.  

 insert into test SELECT * FROM test_temp;

 

\15.  

 update test set Score = Score+5 where Age<=20;

 

\16.  

 update test set Age = Age-1 where Address like '南福苑%';

 

\17.  

 delete from test where   Age>=21 and Score>=500;

 

\18.  

 delete from test where   Score<550 and Address like '重邮宿舍%';

 

\19.  Create table Student(

 SNO Varchar(20),

 Name Varchar(10),

 Age Integer,

 College Varchar(30));

 

\20.  

 create table Course(

  CourseID varchar(15),

  CourseName varchar(30),

  CourseBeforeID varchar(15));

 

\21.  

 Create Table Choose(

  SNO varchar(20),

  CourseID VARCHAR(30),

  Score DECIMAL(5,2));

 

\22.  

 INSERT into Student VALUES ('S00001','张三',20,'计算机学院');

INSERT into Student VALUES ('S00002','李四',19,'通信学院');

INSERT into Student VALUES ('S00003','王五',21,'计算机学院');

 

\23.  

 INSERT into Course VALUES ('C1','计算机引论',NULL);

INSERT into Course VALUES ('C2','C语言','C1');

INSERT into Course VALUES ('C3','数据结构','C2');

 

\24.  

 INSERT into Choose VALUES ('S00001','C1','95');

INSERT into Choose VALUES ('S00001','C2','80');

INSERT into Choose VALUES ('S00001','C3','84');

INSERT into Choose VALUES ('S00002','C1','80');

INSERT into Choose VALUES ('S00002','C2','85');

INSERT into Choose VALUES ('S00003','C1','78');

INSERT into Choose VALUES ('S00003','C3','70');

 

\25.  

 SELECT SNO,Name from Student where College = '计算机学院';

 

\26.  

 SELECT * from Student where Age BETWEEN 20 and 23;

 

\27.  用 SELECT 语句的 COUNT()聚集函数，统计 Student 表中学生总人数。

 SELECT Count(SNO) from Student;

 

\28.  分

 SELECT max(Score)

from Choose

where CourseID ='C1';

SELECT min(Score)

from Choose

where CourseID ='C1';

SELECT sum(Score)

from Choose

where CourseID='C1';

SELECT avg(Score)

from Choose

where CourseID='C1';

 

\29.  

 select CourseID,CourseName

from Course

where CourseBeforeID is null;

 

\30.  用 SELECT 语句的连接查询，查询学生的学号、姓名、选修的课程名及成绩。

 select Choose.SNO ,Name,CourseName,Score

from Choose,Course,student

where Course.CourseID = Choose.CourseID and Student.SNO = Choose.SNO;

 

\31. select *

from student a

where EXISTS(

select *

from student b

where Name ='张三' and a.College=b.College);

 

\32. select SNO,Score

from Choose

where CourseID ='C1' and Score<(SELECT Score

from choose, student

where student.SNO = Choose.SNO and Name = '张三' and CourseID = 'C1');

 

\33. select SNO

from choose

where CourseID ='C1'

UNION all

select SNO

from choose

where CourseID ='C3';

 

\34. select SNO

from choose

where CourseID ='C1'

UNION DISTINCT

select SNO

from choose

 

 