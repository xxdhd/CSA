

## springboot



## 1.入门

HelloworldApplication

程序的主入口



```
HelloworldApplicationTests
```

单元测试



**同级建包**

![image-20220227225853055](C:\Users\kongzhihao\AppData\Roaming\Typora\typora-user-images\image-20220227225853055.png)





更改端口号

```yaml
server.port=8081
```

**banner.txt**





## 2.原理

### 自动装配

#### pom.xml

spring-boot-dependencies:核心依赖在父工程中

引入spring-boot依赖（启动器）时不需要指定版本

使用什么功能找对应的启动器即可

#### 主程序

```java
@SpringBootApplication
//启动类下的所有应用
```

标注这是一个springboot应用

```java
public static void main(String[] args) {
    SpringApplication.run(Springdemo1HelloworldApplication.class, args);
}
```

启动应用



```java
@SpringBootConfiguration：springboot的配置
	@Configuration spring配置类
    @Component spring组件
	
@EnableAutoConfiguration:  自动配置
    @AutoConfigurationPackage :自动配置包
        @Import({Registrar.class})：导入包注册
    @Import({AutoConfigurationImportSelector.class}):自动配置导入选择器
    
       
//获取所有配置
List<String> configurations = this.getCandidateConfigurations(annotationMetadata, attributes);        
```



获取候选的配置



结论：所有的springboot的自动配置都是在启动的时候扫描并加载

：spring.factories，所有的自动配置类都在里面，但是不一定生效，要进行判断，导入了对应的stat就有启动器，就能生效。



run方法

```java
SpringApplication.run(Springdemo1HelloworldApplication.class, args);
```







## 3.yaml语法



k = v =>k: v



```yaml
server:
  port: 8080

```

存对象

```yaml
student:
  name: zhansan
  age: 3

student: {name: zhangsan,age: 13}


pets:
  - cat
  - dog
  - pig
```

yaml给实体类赋值

```yaml
person:
  name: zhangsan
  age: 31
  ha: false
  birth: 2021/12/3
  maps: {k1: v1,k2: v2}
  lists:
    - code
    - mhsic
    - girl
  dog:
    name: 旺财
    age: 12
```



```java

@SpringBootTest
class Springboot02ConfigApplicationTests {

    @Autowired
    private Person person;
    @Test
    void contextLoads() {
        System.out.println(person);
    }

}

```

实体类：

```java

@Component
@ConfigurationProperties(prefix = "person")
```

@ConfigurationProperties:实体类中的属性与配置文件中的一一绑定。



@propertySource（value：=）：加载指定的配置文件



### JSR303

**![img](https://upload-images.jianshu.io/upload_images/3145530-8ae74d19e6c65b4c?imageMogr2/auto-orient/strip|imageView2/2/w/654/format/webp)**





## 4.多环境

优先级：

1.项目路径下的config文件夹

2.项目路径下的文件

3.资源路径下的config文件夹

4.资源路径下的文件



选择激活哪个环境

spring.profiles.active=





```yaml
server:
  port: 8081
spring:
  profiles:
    active: dev
---
server:
  port: 8082
spring:
  profiles: dev

---
server:
  port: 8083
spring:
  profiles: test

```





## 5.Web开发

xxxAutoConfiguration:向容器自动配置组件

xxxProperties：自动装配类，装配配置文件中自定义的内容



### 5.1静态资源导入



webjars：

### 5.2thymeleaf

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div th:text="${msg}"></div>
</body>
</html>
```

th:text="${}"

th:utext="${}"



### 5.3MVC自动装配

```java
package com.kong.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

//扩展mvc
//自己定义功能，只需要写这个组件，交给springboot ，自动装配
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Bean
    public ViewResolver myviewResolver(){
        return new MyViewResolver();   
    }



    //viewResolver 实现 视图解析器类，视图解析器
    public static class MyViewResolver implements ViewResolver {
        @Override
        public View resolveViewName(String viewName, Locale locale) throws Exception {
            return null;
        }
    }


}


```

```java
//视图跳转

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/kong").setViewName("test");
    }


}

```



### 5.4国际化

![image-20220301202449786](C:\Users\kongzhihao\AppData\Roaming\Typora\typora-user-images\image-20220301202449786.png)

```properties
spring.messages.basename=i18n.login
```

```xml
<button class="btn btn-lg btn-primary btn-block" type="submit" >[[#{login.btn}]]</button>
			<p class="mt-5 mb-3 text-muted">© 2017-2018</p>
			<a class="btn btn-sm" th:href="@{/index.html(1='zh_CN')}">中文</a>
			<a class="btn btn-sm" th:href="@{/index.html(1='en_US')}">English</a>
```

```xml
<input type="text" class="form-control" th:placeholder="#{login.username}" required="" autofocus="">
			<input type="password" class="form-control" th:placeholder="#{login.password}" required="">
```

```java
public class MyLocaleResolver implements LocaleResolver {


    //解析请求
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        //获取请求中的语言参数
        String parameter = request.getParameter("1");
        Locale aDefault = Locale.getDefault();
        //如果携带了国际化参数
        if(!StringUtils.isEmpty(parameter)){
            String[]  split= parameter.split("_");
            aDefault=new Locale(split[0],split[1]);
        }
        return aDefault;
    }}
```



//如果信息不为空

```xml
<p style="color: red" th:text="${msg}" th:if="${not #strings.isEmpty(msg)}"></p>
```





## 6.JDBC

数据源

```yaml
spring:
  datasource:
    username: root
    password: mysql
    url: jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8
    driver-class-name: com.mysql.cj.jdbc.Driver

```

sql语句

```java
 @GetMapping("/userlist")
    public List<Map<String,Object>> userlist(){
        String sql  = "select * from test.user";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }

    @GetMapping("/adduser")
    public String addUser(){
        String sql = "insert into test.user(age, username, password) values (24,'小王','147')";
        int update = jdbcTemplate.update(sql);
        return "addteok";
    }

    @GetMapping("/updateuser/{id}")
    public String updateUser(@PathVariable("id") int id){
        String sql = "update test.user set username=?,password=? where age="+id;

        Object[] objects=new Object[2];
        objects[0] = "张三";
        objects[1] = "zzz";

        int update = jdbcTemplate.update(sql,objects);
        return "updateteok";
    }

    @GetMapping("/deleuser/{id}")
    public String deleteUser(@PathVariable("id") int id){
        String sql = "delete from  test.user where age=?";
        int update = jdbcTemplate.update(sql,id);
        return "deleteok";
    }
```



### 6.1Druid

```java
package com.kong.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import javax.servlet.Servlet;
import javax.sql.DataSource;
import java.util.Date;
import java.util.HashMap;

@Controller
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }
    //后台监控
    @Bean
    public ServletRegistrationBean a(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        HashMap<String,String> initparam = new HashMap<>();
        initparam.put("loginUsername","admin");
        initparam.put("loginPassword","123");
        initparam.put("allow","");
        bean.setInitParameters(initparam);

        return bean;
    }
}
```



### 6.2mybatis

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.kong.mapper.ConsumerMapper">
    <select id="queryConsumerList" resultType="Consumer">
        select * from test.consumer
    </select>

    <select id="queryById" resultType="Consumer">
        select * from test.consumer where id=#{id}
    </select>

    <insert id="add" parameterType="Consumer">
        insert into test.consumer (id, username, password, sex, phone_num, email, introduction) VALUES (#{id},#{username},#{password},#{sex},#{phone_num},#{email},#{introduction})
    </insert>

    <update id="update" parameterType="Consumer">
        update test.consumer set username=#{username},password=#{password},sex=#{sex},phone_num=#{phone_num},email=#{email},introduction=#{introduction} where id=#{id}
    </update>

    <delete id="delete" parameterType="int">
        delete from test.consumer where id=#{id}
    </delete>
</mapper>
```



整合：

```yaml

mybatis.type-aliases-package=com.kong.pojo
mybatis.mapper-locations=classpath:mybatis/mapper/*.xml
```

```java
//controller
@RestController
public class ConsumerController {
    @Autowired
    private ConsumerMapper consumerMapper;

    @GetMapping("/userlist")
    public List<Consumer> queryConsumerList(){
        List<Consumer> consumerList = consumerMapper.queryConsumerList();
        for(Consumer consumer:consumerList){
            System.out.println(consumer);
        }
        return consumerList;
    }

    @GetMapping("/adduser")
    public String add(){
        consumerMapper.add(new Consumer(3,"王五","123456",1,"147","1@qq.com","我是傻逼"));
        return "add ok";
    }

    @GetMapping("/updateuser")
    public String update(){
        consumerMapper.update(new Consumer(3,"王五","123456",1,"147","1@qq.com","我是sb"));
        return "update ok";
    }

    @GetMapping("deleteuser")
    public String delete(){
        consumerMapper.delete(3);
        return "delete ok";
    }
```

步骤：

1.导入包

2.配置文件

3.mybatis配置

4.编写sql

5.业务层调用dao层

6.controller调用service层

文件结构：

![image-20220302110649579](C:\Users\kongzhihao\AppData\Roaming\Typora\typora-user-images\image-20220302110649579.png)

