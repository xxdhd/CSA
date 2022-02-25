# SpringMVC

## 1.Spring集成Web

使用ServletContextListener 监听Web应用启动。

### 1.1UserServlet

```java

        ServletContext servletContext = this.getServletContext();
        ApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        UserService userService = (UserService) app.getBean("userservice");
        userService.cast();
```



### 1.2 ContextLoaderListener

```java
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
        ApplicationContext app = new ClassPathXmlApplicationContext(contextConfigLocation);

        servletContext.setAttribute("app",app);
        System.out.println("spring容器创建容器");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
```

### 1.3 WebApplicationContextUtils

```java
public class WebApplicationContextUtils {
    public static ApplicationContext getWebApplicationContext(ServletContext servletContext){
        return (ApplicationContext) servletContext.getAttribute("app");
    }
}

```

### 1.4 spring 提供获取应用上下文

![image-20220222182547965](C:\Users\kongzhihao\AppData\Roaming\Typora\typora-user-images\image-20220222182547965.png)

### 1.5 总结

![image-20220222183244338](C:\Users\kongzhihao\AppData\Roaming\Typora\typora-user-images\image-20220222183244338.png)



## 2.SpringMVC

1.导入SpringMVC

2.配置Servlet

3.编写Controller

4.将Controller 使用注解配置到Spring容器中（@Controller）

5.配置组件扫描（Spring-mvc.xml）

6.执行测试

### 2.1 执行流程

![image-20220222214114265](C:\Users\kongzhihao\AppData\Roaming\Typora\typora-user-images\image-20220222214114265.png)



![image-20220222215534372](C:\Users\kongzhihao\AppData\Roaming\Typora\typora-user-images\image-20220222215534372.png)



### 2.2@RequestMapping()

```java
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
pu blic class UserController {


    @RequestMapping("/quick")
    public  String save(){
        System.out.println("controller save");
        return "/success.jsp";
    }
}
```



### 2.3SpringMVC数据响应方式

![image-20220223153957345](C:\Users\kongzhihao\AppData\Roaming\Typora\typora-user-images\image-20220223153957345.png)

#### 2.3.1 返回字符串

```java
@RequestMapping(value = "/quick",method = RequestMethod.GET,params = {"username"})
    public  String save(){
        System.out.println("controller save");
        return "success";
    }
```

```jsp
<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <!--/jsp/xx.jsp-->
        <property name="prefix" value="/jsp/"></property>
        <property name="suffix" value=".jsp"></property>
    </bean>
```

prefix+"String"+suffix



#### 2.3.2 返回ModelAndView

Model：封装数据

View：展示数据

```java
    @RequestMapping(value = "/quick4")
    public String save4(Model model) {;

        model.addAttribute("username","空没好果子");
        //设置模型数据
        return "success";
    }

    @RequestMapping(value = "/quick3")
    public ModelAndView save3(ModelAndView modelAndView) {;
        //设置模型数据
       modelAndView.addObject("username","kongzhihaoxxdhd");
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @RequestMapping(value = "/quick2")
    public ModelAndView save2() {;


        ModelAndView modelAndView = new ModelAndView();
        //设置模型数据
        modelAndView.addObject("username","kongzhihao");
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @RequestMapping(value = "/quick",method = RequestMethod.GET,params = {"username"})
    public  String save(){
        System.out.println("controller save");
        return "success";
    }
```



#### 2.3.3 直接返回字符串

```java

    @RequestMapping(value = "/quick6")
    public void save6(HttpServletResponse response) throws IOException {;
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print("孔志豪123");
        //设置模型数据
    }


    @RequestMapping(value = "/quick5")
    public String save5(HttpServletRequest httpServletRequest) {;

        httpServletRequest.setAttribute("username","孔志豪");
        //设置模型数据
        return "success";
    }
```



**如果直接返回字符串而不是跳转** 通过@ResponseBody.

告知SpringMVC该方法不进行试图跳转 直接进行数据显示

```java
 @RequestMapping(value = "/quick7" )
 @ResponseBody
    public String save7() throws IOException {;
        return "xxdhd7";
        //设置模型数据
    }
```

##### 返回json字符串

```java
@RequestMapping(value = "/quick9" )
    @ResponseBody
    public String save9() throws IOException {;
        User user = new User();
        user.setUsername("lisi");
        user.setAge(30);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);

        return json;
    }
```



```xml
<dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
            <version>2.9.0</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.9.0</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
            <version>2.9.0</version>
        </dependency>
```



#### 2.3.4 返回对象集合

```java
@RequestMapping(value = "/quick10" )
    @ResponseBody
    public User save10() throws IOException {
        User user = new User();
        user.setUsername("zhangsan1");
        user.setAge(25);
        return user;
    }
```

```xml
<bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter">
        <property name="messageConverters">
            <list>
                <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter"/>
            </list>
        </property>
    </bean>
```

![image-20220223164906498](C:\Users\kongzhihao\AppData\Roaming\Typora\typora-user-images\image-20220223164906498.png)



### 2.4.Spring 获得请求参数

#### 基本类型数据

```java
@RequestMapping(value = "/quick11" )
   @ResponseBody
public void save11(String username, int age) throws IOException {
        System.out.println(username);
        System.out.println(age);
    }
```

访问/quick11?username=xxx&age=xx



#### pojo数据

pojo参数的属性名与请求参数的name一致，参数值会自动映射匹配。

```java
@RequestMapping(value = "/quick12" )
    @ResponseBody
    public void save12(User user) throws IOException {
        System.out.println(user);
    }
```



#### 数组类型参数

```java
@RequestMapping(value = "/quick13" )
    @ResponseBody
    public void save13(String[] strs) throws IOException {
        System.out.println(Arrays.asList(strs));
    }
```



#### 集合类型参数

获得集合参数时，要将几何参数包装到pojo中。



```java
@RequestMapping(value = "/quick15" )
    @ResponseBody
    public void save15(@RequestBody List<User> userList) throws IOException {
        System.out.println(userList);
    }
```

```xml
<%--ajax.jsp
  Created by IntelliJ IDEA.
  User: kongzhihao
  Date: 2022/2/23
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <script>
        var userList = new Array();
        userList.push({username:"zhangsan" ,age:18});
        userList.push({username:"lisi" ,age:28});

        $.ajax({
            type:"POST",
            url:"${pageContext.request.contextPath}/user/quick15",
            data:JSON.stringify(userList),
            contentType:"application/json;charset=utf-8"
        });
    </script>
</head>
<body>

</body>
</html>

```

开放资源的访问 mapping ：地址               location：资源所在目录

```xml
<mvc:resources mapping="/js/**" location="/js/"/>
```

```xml
<mvc:default-servlet-handler/>
```



#### 中文乱码

web.xml配置

```xml
 <filter>
    <filter-name>CharacterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>UTF-8</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>CharacterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
```



#### @requestParam

```java
@RequestParam(value = "name", required = false ,defaultValue = "wadjaw")
```

value:映射

required：是否必要

defaultValue：默认

#### Restful风格

请求：url+请求方式

GET：获取资源

POST: 新建资源

PUT：更新资源

DELETE:删除资源

```java
 @RequestMapping(value = "/quick17/{username}" )
    @ResponseBody
    public void save17(@PathVariable(value = "username") String username) throws IOException {
        System.out.println(username);
    }
```

访问：

/quick17/username  读取username



#### 自定义类型转换器

1.定义转换器类实现Converter接口

2.在配置文件中声明转换器

3.在<annotation-driven>中引用转换器

```xml
 <mvc:annotation-driven conversion-service="conversionService"/>

<bean id="conversionService" class="org.springframework.context.support.ConversionServiceFactoryBean">
        <property name="converters">
            <list>
                <bean class="com.item.converter.DateConverter"></bean>
            </list>
        </property>
    </bean>
```



#### Servlet相关API

 



#### 请求头

@RequestHeader

value:头的名称

```java
@RequestMapping(value = "/quick21" )
@ResponseBody
public void save21(@CookieValue(value = "Hm_lvt_eaa57ca47dacb4ad4f5a257001a3457c") String jsessionid) throws IOException {
    System.out.println(jsessionid);

}
```



#### 单文件上传

![image-20220224213152420](C:\Users\kongzhihao\AppData\Roaming\Typora\typora-user-images\image-20220224213152420.png)

1.导入坐标

```xml
<dependency>
    <groupId>commons-fileupload</groupId>
    <artifactId>commons-fileupload</artifactId>
    <version>1.3.1</version>
</dependency>
<dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.4</version>
</dependency>
```



2.配置文件上传解析器

spring-mvc

```XML
<bean id="CommonsMultipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
    <property name="defaultEncoding" value="UTF-8"/>
    <property name="maxUploadSize" value="500000"/>
</bean>
```



3.编写代码

```java
@RequestMapping(value = "/quick22" )
    @ResponseBody
    public void save22(String username, MultipartFile uploadFile) throws IOException {
        System.out.println(username);
        String originalFilename = uploadFile.getOriginalFilename();
        uploadFile.transferTo(new File("D:\\javafile\\"+originalFilename));

    }
```





### 2.5. JdbcTemplate

```
jdbc:mysql://localhost:3306/test?serverTimezone=UTC&amp;useUnicode=true&amp;character=utf8&amp;useSSL=true
```



#### 2.5.1 spring产生

```xml
<Context:property-placeholder location="classpath:jdbc.properties"/>
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${jdbc.driver}"/>
        <property name="jdbcUrl" value="${jdbc.url}"/>
        <property name="user" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>
```



#### 2.5.2 sql实现

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JdbcTemplateCRUDTTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testUpdate(){
        int u = jdbcTemplate.update("update test.account set money=? where name = ?", 230, "tom");
        System.out.println(u);
    }
}
```







```java
@Test
    public void testUpdate(){
        int u = jdbcTemplate.update("update test.account set money=? where name = ?", 230, "tom");
        System.out.println(u);
    }

    @Test
    public void testDelete(){
        String name = "fac1";
        int u = jdbcTemplate.update("delete from test.account where name=?",name);
        System.out.println(u);
    }

    @Test
    public void testQueryMore(){
        List<Account> accountList = jdbcTemplate.query("select * from test.account", new BeanPropertyRowMapper<Account>(Account.class));
        System.out.println(accountList);
    }

    @Test
    public void testQueryOne(){
        Account account = jdbcTemplate.queryForObject("select * from test.account where name=?", new BeanPropertyRowMapper<Account>(Account.class),"tom");
        System.out.println("one:"+account);
    }


    @Test
    public void testQueryCount(){
        Long aLong = jdbcTemplate.queryForObject("select count(*) from test.account", Long.class);
        System.out.println(aLong);
    }
```



### 2.6拦截器

```java
package com.item.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor1 implements HandlerInterceptor {


    //目标方法执行前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("pre...");
        return true;
    }
    //目标方法执行后，返回视图前
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("post...");
    }
    //整个流程执行后
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("after");
    }
}
```



```xml
<mvc:interceptors>
        <mvc:interceptor>
            <!--对哪些资源执行-->
            <mvc:mapping path="/**"/>
            <bean class="com.item.interceptor.MyInterceptor1"/>
        </mvc:interceptor>
    </mvc:interceptors>
```



如果返回false 则不放行 ture则放行



### 2.7 异常处理



![image-20220225174542646](C:\Users\kongzhihao\AppData\Roaming\Typora\typora-user-images\image-20220225174542646.png)



#### 1.SimpleMappingExceptionResolver

跳转页面



#### 2.接口 HandlerExceptionResovler



## 作业

