# CSA第八周JAVAweb



Tomcat文件目录
bin：可执行文件（启动文件startup.bat、关闭文件shutdown.bat）
conf：配置文件（修改端口号：server.xml，建议将server.xml备份）
lib：依赖的jar包
log：日志文件（记录出错等信息）
temp：临时文件
webapps：可执行的项目。默认作为存放开发项目的目录
work：存放由jsp翻译成的.java源文件,以及编译的.class字节码文件(jsp ->java ->class)

 **启动Tomcat**

方法一：在`.\apache-tomcat-9.0.43\bin`目录下，双击`startup.bat`打开窗口，双击`shutdown.bat`关闭窗口。

tomcat端口号默认8080，如果和其他服务的端口号冲突，则可对端口号进行修改

**访问**

更改端口

常见状态码

200：一切正常（一般看不到）
300/301：页面[重定向](https://so.csdn.net/so/search?q=重定向&spm=1001.2101.3001.7020)（跳转）
404 ：资源不存在
403 : 权限不足（比如访问a目录，a目录设置不可见）
500 ：服务器内部错误（代码有误

修改自身的端口号

\* conf/server.xml

\* <Connector port="8888" protocol="HTTP/1.1" connectionTimeout="20000"

redirectPort="8445" />

\* 一般会将tomcat的默认端口号修改为80。80端口号是http协议的默认端口号。

\* 好处：在访问时，就不用输入端口号



### 部署项目

部署项目的方式：

\1. 直接将项目放到webapps目录下即可。

\* /hello：项目的访问路径-->虚拟目录

\* 简化部署：将项目打成一个war包，再将war包放置到webapps目录下。

\* war包会自动解压缩

\2. 配置conf/server.xml文件

在<Host>标签体中配置

\* docBase:项目存放的路径

\* path：虚拟目录

\3. 在conf\Catalina\localhost创建任意名称的xml文件。在文件中编写



## Servlet概念：运行在服务器端的小程序

\* Servlet就是一个接口，定义了Java类被浏览器访问到(tomcat识别)的规则。

\* 将来我们自定义一个类，实现Servlet接口，复写方法。

### Servlet生命周期

Servlet 生命周期可被定义为从创建直到毁灭的整个过程。以下是 Servlet 遵循的过程：

- Servlet 初始化后调用 **init ()** 方法。

- Servlet 调用 **service()** 方法来处理客户端的请求。

- Servlet 销毁前调用 **destroy()** 方法。

- 最后，Servlet 是由 JVM 的垃圾回收器进行垃圾回收的。

  ## init() 方法

  init 方法被设计成只调用一次。它在第一次创建 Servlet 时被调用，在后续每次用户请求时不再调用。因此，它是用于一次性初始化，就像 Applet 的 init 方法一样。

  Servlet 创建于用户第一次调用对应于该 Servlet 的 URL 时，但是您也可以指定 Servlet 在服务器第一次启动时被加载。

  当用户调用一个 Servlet 时，就会创建一个 Servlet 实例，每一个用户请求都会产生一个新的线程，适当的时候移交给 doGet 或 doPost 方法。init() 方法简单地创建或加载一些数据，这些数据将被用于 Servlet 的整个生命周期。

  ```
  public void init() throws ServletException {
    // 初始化代码...
  }
  ```

  ## service() 方法

  service() 方法是执行实际任务的主要方法。Servlet 容器（即 Web 服务器）调用 service() 方法来处理来自客户端（浏览器）的请求，并把格式化的响应写回给客户端。

  每次服务器接收到一个 Servlet 请求时，服务器会产生一个新的线程并调用服务。service() 方法检查 HTTP 请求类型（GET、POST、PUT、DELETE 等），并在适当的时候调用 doGet、doPost、doPut，doDelete 等方法。

  ```
  public void service(ServletRequest request, 
                      ServletResponse response) 
        throws ServletException, IOException{
  }
  ```

  ## doGet() 方法

  GET 请求来自于一个 URL 的正常请求，或者来自于一个未指定 METHOD 的 HTML 表单，它由 doGet() 方法处理。

  ## doPost() 方法

  POST 请求来自于一个特别指定了 METHOD 为 POST 的 HTML 表单，它由 doPost() 方法处理

  ## destroy() 方法

  destroy() 方法只会被调用一次，在 Servlet 生命周期结束时被调用。destroy() 方法可以让您的 Servlet 关闭数据库连接、停止后台线程、把 Cookie 列表或点击计数器写入到磁盘，并执行其他类似的清理活动。

  ```java
  public void destroy() {
      // 终止化代码...
    }
  ```



## response request

#### request

1. request对象和response对象的原理
	1. request和response对象是由服务器创建的。我们来使用它们
	2. request对象是来获取请求消息，response对象是来设置响应消息

2. request对象继承体系结构：	
	ServletRequest		--	接口
		|	继承
	HttpServletRequest	-- 接口
		|	实现
	org.apache.catalina.connector.RequestFacade 类(tomcat)

3. request功能：
	1. 获取请求消息数据
		1. 获取请求行数据
			* GET /day14/demo1?name=zhangsan HTTP/1.1
			* 方法：
				1. 获取请求方式 ：GET
					* String getMethod()  
				2. (*)获取虚拟目录：/day14
					* String getContextPath()
				3. 获取Servlet路径: /demo1
					* String getServletPath()
				4. 获取get方式请求参数：name=zhangsan
					* String getQueryString()
				5. (*)获取请求URI：/day14/demo1
					* String getRequestURI():	
				
				6. 获取协议及版本：HTTP/1.1
									* String getProtocol()
				
					7. 获取客户机的IP地址：
								* String getRemoteAddr()

2. 获取请求头数据
			* 方法：
					* (*)String getHeader(String name):通过请求头的名称获取请求头的值
					* Enumeration<String> getHeaderNames():获取所有的请求头名称
				
		3. 获取请求体数据:
			* 请求体：只有POST请求方式，才有请求体，在请求体中封装了POST请求的请求参数
			* 步骤：
				1. 获取流对象
					*  BufferedReader getReader()：获取字符输入流，只能操作字符数据
					*  ServletInputStream getInputStream()：获取字节输入流，可以操作所有类型数据
						* 在文件上传知识点后讲解

		​	2. 再从流对象中拿数据

#### response

 功能：设置响应消息
	1. 设置响应行
		1. 格式：HTTP/1.1 200 ok
		2. 设置状态码：setStatus(int sc) 
		2. 设置响应头：setHeader(String name, String value) 

3. 设置响应体：
	* 使用步骤：
		1. 获取输出流
			* 字符输出流：PrintWriter getWriter()

			* 字节输出流：ServletOutputStream getOutputStream()

		2. 使用输出流，将数据输出到客户端浏览器

## 作业地址

