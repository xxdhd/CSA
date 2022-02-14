# Spring



## 1.spring

[Central Repository: org/springframework/spring (maven.org)](https://repo1.maven.org/maven2/org/springframework/spring/)

``` java
<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.2.0.RELEASE</version>
</dependency>

    <!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>5.2.0.RELEASE</version>
</dependency>
    
```





### 1.1七大模块

![img](http://dl2.iteye.com/upload/attachment/0130/0879/6770f2b2-112f-38bf-97b3-f5ec902bdf35.png)

### 1.2 Spring Boot

一个快速开发的脚手架

约定大于配置

### 1.3 Spring cloud

基于Spring Boot实现的



## 2.IOC

控制反转（IOC） 是Spring的核心内容。

 

### IOC 本质

将程序的控制权由程序员交给用户。



## 3.HelloSpring

``` 
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="..." class="...">  
        <!-- collaborators and configuration for this bean go here -->
    </bean>

    <bean id="..." class="...">
        <!-- collaborators and configuration for this bean go here -->
    </bean>

    <!-- more bean definitions go here -->

</beans>
```

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="hello" class="com.kong.pojo.hello">
        <!-- collaborators and configuration for this bean go here -->
        <property name="str" value="Spring"/>
    </bean>


    <!-- more bean definitions go here -->

</beans>
```



**bean = 对象      new Hello();**

**id = 变量名**

**class = new 的对象**

**property 相当于设置对象中属性的值**

ref：引用Spring容器中创好的对象



**对象由Spring创建，属性值由Spring设置**

**反转：不由程序创建对象，由程序接收对象**



```
ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//对象都在Spring中管理，需要使用的时候就去取
hello hel = (hello) context.getBean("hello");
System.out.println(hel.toString());
```



## 4.IOC 创建对象的方式

1.使用无参构造创建构造

2.假设使用有参构造

 1.下标赋值

```
<bean id="user" class="com.kong.pojo.User">
    <!-- collaborators and configuration for this bean go here -->
    <constructor-arg index="0" value="好动"/>
```

 2.参数赋值

``` java
<bean id="user" class="com.kong.pojo.User">
        <!-- collaborators and configuration for this bean go here -->
        <constructor-arg type="java.lang.String" value="浩东"/>
    </bean>
```

3.参数名

``` java
<bean id="user" class="com.kong.pojo.User">
        <!-- collaborators and configuration for this bean go here -->
        <constructor-arg name="name" value="浩东"/>
    </bean>
```



## 5.Spring 配置

### 5.1 别名alias

``` 
<alias name="user" alias="nn"/>
```

### 5.2bean 配置

id:bin的唯一标识符（对象名）

class：bin对象所对应的权限命名

name：也是别名

### 5.3 import

可以把多个配置文件导入整合成一个



## 6.依赖注入

### 6.1构造器注入



### 6.2 set注入

依赖注入：set注入

依赖：bean对象的创建依赖于容器

注入：bean对象中的所有属性，由容器来注入

#### 6.2.1 复杂类型

```java
package com.kong.pojo;

public class Address {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

```



#### 6.2.2真实测试

```java
public class Student {
    private String name;
    private Address address;
    private String[] books;
    private List<String> hobbys;
    private Map<String,String> card;
    private Set<String> games;
    private Properties info;
    private String wife;
```



#### 6.2.3 xml配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="address" class="com.kong.pojo.Address">
        <property name="address" value="江西"/>
    </bean>
    <bean id="student" class="com.kong.pojo.Student">
        <!-- collaborators and configuration for this bean go here -->
        <!--第一种，普通值注入，value-->
        <property name="name" value="志豪"/>
        <!--第二种，bean注入-->
        <property name="address" ref="address"/>
        <!--第三种，数组注入-->
        <property name="books">
            <array>
                <value>红楼梦</value>
                <value>水浒传</value>
                <value>三国演义</value>
                <value>西游记</value>
            </array>
        </property>
        <!--第四种，List-->
        <property name="hobbys">
            <list>
                <value>象棋</value>
                <value>舞蹈</value>
                <value>唱歌</value>
            </list>
        </property>
        <!--第五种，Map-->
        <property name="card">
            <map>
                <entry key="身份证" value="123456"/>
                <entry key="学生卡" value="123482"/>
            </map>
        </property>
        <!--第6种，Set-->
        <property name="games">
            <set>
                <value>CF</value>
                <value>LOL</value>
            </set>
        </property>
        <!--第6种，null-->
        <property name="wife">
            <null></null>
        </property>
        <!--第7种，Properties-->
        <property name="info">
            <props>
                <prop key="学号">201645458</prop>
                <prop key="性别">男</prop>
                <prop key="民族">汉</prop>
            </props>
        </property>
    </bean>

    <!-- more bean definitions go here -->

</beans>
```





#### 6.2.4结果

Student{name='志豪', address=Address{address='江西'}, books=[红楼梦, 水浒传, 三国演义, 西游记], hobbys=[象棋, 舞蹈, 唱歌], card={身份证=123456, 学生卡=123482}, games=[CF, LOL], info={学号=201645458, 性别=男, 民族=汉}, wife='null'}

### 6.3扩展方式注入

#### p命名空间：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="user" class="com.kong.pojo.User" p:name="王瑞按" p:age="15">
        <!-- collaborators and configuration for this bean go here -->
    </bean>



    <!-- more bean definitions go here -->

</beans>
```



#### c命名空间（有参构造）：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:c="http://www.springframework.org/schema/c"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="user" class="com.kong.pojo.User" p:name="王瑞" p:age="15">
        <!-- collaborators and configuration for this bean go here -->
    </bean>

    <bean id="user2" class="com.kong.pojo.User" c:name="是我" c:age="58">

    </bean>

    <!-- more bean definitions go here -->

</beans>
```



### 7.bean的自动装配

#### byname

会自动查找和set方法后面的值对应的bean id

#### bytype

会自动查找和set方法对象属性对应的bean id



### 使用注解自动装配

1.导入约束

2.配置注解的支持

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>

</beans>
```

**<context:annotation-config/>**

**@Autowired**:在属性上使用或在set方法上使用

#### Resourse 和 Autowired

都是用来自动装配的，都可以放在属性字段上

autowired通过bytype实现

resourse默认通过byname，如果找不到名字则用bytype

## 8.使用注解开发

使用注解开发必须保证aop包存在，需要导入context约束。

```java
@Component
```

等价于

```xml
<bean id="user" class="com.kong.pojo.User"/>
```



```java
@Value("abcde")
```

等价于

```xml
<property name="name" value="abcde"/>
```



```java
@Scope("prototype")
```



## 9.使用java配置Spring

注册一个bean

```java
@Bean
```

方法的名字就相当于标签种的id，方法的返回值相当于class。



**@ComponentScan("")**:扫描

**@Configuration**:代表这是一个配置类，类似于beans.xml

```java
package com.kong.config;

import com.kong.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Myconfig {
    @Bean
    public User getUser(){
        return new User();
    }
}
```



```java
public class test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Myconfig.class);
        User getUser = (User) context.getBean("getUser");
        System.out.println(getUser.getName());
    }
}
```



## 10.AOP

**面向切面编程**

Spring实现AOP

### 10.1 Spring 接口

```java
public void before(Method method, Object[] objects, Object o) throws Throwable {
        
    }
```

method:要执行的目标对象的方法

objects：参数

o：目标对象

```java
public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        
    }
```



```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd"
>
    <bean id="userService" class="com.kong.service.UserServiceimpl"/>
    <bean id="log" class="com.kong.log.log"/>
    <bean id="afterlog" class="com.kong.log.Afterlog"/>

    <!--配置AOP 导入依赖-->
    <aop:config>
        <!--切入点：expression：表达式execution(要执行的位置-->
        <aop:pointcut id="pointcut" expression="execution(* com.kong.service.UserServiceimpl.*(..))"/>
        <!--执行环绕增加-->
        <aop:advisor advice-ref="log" pointcut-ref="pointcut"/>
        <aop:advisor advice-ref="afterlog" pointcut-ref="pointcut"/>
    </aop:config>


</beans>
```



### 





```java
public class Mytest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserService userService =(UserService) context.getBean("userService");

        userService.sel();
    }
}
```



### 10.2 自定义类实现

```xml
    <bean id="diy" class="com.kong.diy.DiyPointCut"/>

    <aop:config>
        <aop:aspect ref="diy">
            <aop:pointcut id="point" expression="execution(* com.kong.service.UserServiceimpl.*(..))"/>
            <aop:before method="before" pointcut-ref="point"/>
            <aop:after method="after" pointcut-ref="point"/>
        </aop:aspect>
    </aop:config>
```



### 10.3注解实现

```xml
    <bean id="anpc" class="com.kong.diy.AnnotationPointCut"/>

    <aop:aspectj-autoproxy/>
```



```java
public class AnnotationPointCut {
    @Before("execution(* com.kong.service.UserServiceimpl.*(..))")
    public void before(){
        System.out.println("方法执行前");
    }
    @After("execution(* com.kong.service.UserServiceimpl.*(..))")
    public void after(){
        System.out.println("方法执行后");
    }
    @Around("execution(* com.kong.service.UserServiceimpl.*(..))")
    public void around(ProceedingJoinPoint jp) throws Throwable{
        System.out.println("环绕前");


        Object proceed = jp.proceed();
    }
}
```







## 11.代理模式

### 11.1静态代理

抽象角色：用接口和抽象类

真实角色：被代理的角色

代理角色：代理真实角色

真实角色不用去关注公共业务

产生扩展时，方便集中管理。



### 11.2动态代理



代理类是动态生成的

1.基于接口的，2.基于类的





## 12 整合mybatis

### 1.编写数据源



### 2.sqlSessionFactory

**配置文件**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--Datasource使用Spring替换mybatis  -->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/test?serverTimezone=UTC&amp;useUnicode=true&amp;character=utf8&amp;useSSL=true"/>
        <property name="username" value="root"/>
        <property name="password" value="mysql"/>
    </bean>


    <!--sqlsessionFactory-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource" />
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <property name="mapperLocations" value="classpath:com/kong/mapper/*.xml"/>
    </bean>

    <!--mybatis配置-->
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <constructor-arg index="0" ref="sqlSessionFactory"/>
    </bean>

    <bean id="userMapper" class="com.kong.mapper.UserMapperImpl">
        <property name="sqlSession" ref="sqlSession"/>
    </bean>
</beans>
```



### 3.给接口实现类

package com.kong.mapper;

import com.kong.pojo.User;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public class UserMapperImpl implements UserMapper{

```java
//所有操作都使用sqlSession执行
private SqlSessionTemplate sqlSession;

public void setSqlSession(SqlSessionTemplate sqlSession){
    this.sqlSession=sqlSession;
}

public List<User> selectUser(){
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    return mapper.selectUser();
}
}
```


### 4.测试

**测试**

```java
import com.kong.mapper.UserMapper;
import com.kong.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Mytest11 {
    @Test
    public void test() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");

        UserMapper userMapper = context.getBean("userMapper",UserMapper.class);
        for(User user : userMapper.selectUser()){
            System.out.println(user);
        }
    }
}

```



### 第二种方法

**applicationContext.xml**

```xml

    <import resource="spring-dao.xml"/>

    <bean id="userMapper" class="com.kong.mapper.UserMapperImpl">
        <property name="sqlSession" ref="sqlSession"/>
    </bean>
    <bean id="userMappertwo" class="com.kong.mapper.UserMappertwo">
        <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
    </bean>

    </beans>
```

**实现类**

```java
package com.kong.mapper;

import com.kong.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class UserMappertwo extends SqlSessionDaoSupport implements UserMapper{
    @Override
    public List<User> selectUser() {

        return getSqlSession().getMapper(UserMapper.class).selectUser();
    }
}

```



## 13 声明式事务

```java
    <!--mybatis配置-->
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <constructor-arg index="0" ref="sqlSessionFactory"/>
    </bean>
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <!--配置事务的类-->
    <tx:advice id="txadvice" transaction-manager="transactionManager">
        <tx:attributes>
            <tx:method name="add" propagation="REQUIRED"/>
            <tx:method name="delete" propagation="REQUIRED"/>
            <tx:method name="update" propagation="REQUIRED"/>
            <tx:method name="select" read-only="true"/>
            <tx:method name="*" propagation="REQUIRED"/>
        </tx:attributes>
    </tx:advice>

    <!--配置事务切入-->
    <aop:config>
        <aop:pointcut id="txPointCut" expression="execution(* com.kong.Mapper.*.*(..))"/>
        <aop:advisor advice-ref="txadvice" pointcut-ref="txPointCut"/>
    </aop:config>
```

**sql**

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.kong.mapper.UserMapper">
    <select id="selectUser" resultType="user">
        select * from test.user;
    </select>
    <insert id="addUser" parameterType="user">
        insert into test.user (id, name,pwd) values (#{id},#{name},#{pwd});
    </insert>

    <delete id="deleteUser" parameterType="int">
        deletes from test.user where id=#{id}
    </delete>
</mapper>
```

## 14 作业

