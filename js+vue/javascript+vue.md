# javascript+vue



1.行内js

2.内部js

3.外部js





js放置位置

1.放在head标签

2.放在body

3.放在body标签后（推荐）



js代码从上往下依次执行，最好等到html元素执行完毕后再执行js代码



行内

```html
<button type="button" onclick="alert('hello')">你好</button>
```

内部

```html
<script type="text/javascript">
    alert('你们好');
</script>
```



一行代表一个语句

语句以分号结尾

多个语句写在一行，语句后必须加分号





## 标识符和关键字

标识符就是一个名字

关键字也称保留字





## 变量

js是弱类型语言

声明变量时不需要指明变量类型

直接只有war修饰符



变量的声明：

1.声明并赋值

```html
<script type="text/javascript">
	var str="hello,world";
	console.log(str);
</script>
```

2.先声明后赋值

变量的注意点：当变量之声明而未赋值时，则变量的返回为undefined

变量未定义就去用，则报错

可以在一个var中声明多个变量

用 var生成一个已有的变量是无效的

重新生成一个已有的变量并赋值会覆盖。



变量提升：

所有变量的声明语句会最先执行。

只有用war声明的才会变量提升



## 数据类型

### 数值

整数型，浮点型

1.所有数字都是以64位浮点类型存储。在js中1==1.0

1+1.0=2；

浮点类型最高精度17位，运算可能不准确

在存储数值时会自动将整型的浮点数值1.0转换成整型1



### 字符串

用单引号或双引号包括起来

### 布尔

true false



### undefined

表示值不存在

1.声明后未赋值

2.调用方法函数时，需要参数但未传递时，则参数为undefined

3.函数没有返回值，默认返回undefined



### null

表示空值



### 对象

{}



## 类型转换

### 函数转换

parseInt()

找到所有数字为止（字母，小数点）



parseFloat（）

可以找到第一个小数点



### 显示转换

toString



toFixed

```html
<script type="text/javascript">

	var data=10.123;
	console.log(data);
	console.log(data.toFixed(1));
	
</script>
```



### 构造方法

console.log(Number("5.5"));

console.log(Number("5.5.5"));NaN



### String

转换成字符串（包括Null，undefined）



### 最后

往后面+“ ”；



## 运算

### 全等和相等

全等：比较值和类型是否相等

相等：比较值是否相等



### 控制语句



switch使用全等



## 数组

### 数组定义

1.长度可变

2.类型任意

3.索引从0开始

```html
	<script type="text/javascript">

		var arr=[1,'a',true];
		console.log(arr);
		
		var arr2 = new Array(1,2,3);
		console.log(arr2)
		
		var arr3 = new Array(5);
		
		console.log(arr2.length);
		arr2.length=5;
		console.log(arr2.length);
	</script>
```

4.可以越界



获取长度

arr.length

设置长度

arr.length=

设置元素：

arr[?]=

可以使用任意字符当作数组下标索引

### 遍历数组

1.for

```html
for(var i=0;i<arr2.length;i++){
			console.log(arr2[i]);
		}
		
```





2.for in

for(var in arr){

}

3.foreach

element表示当前元素

index 表示索引

## 数组方法

push

unshift

pop

shift

reverse

join

indexof

slice

splice

concat

## 函数

### 1.函数声明语句

```html
<script type="text/javascript">

	function test(){
		console.log('hanshushengming1');
	}
	test()
	function test2(a){
		console.log(a)
	}
	test2(1)
	
</script>
```

### 2.函数定义表达式

```html
	var test3 = function(){
		console.log('hanshushengming1');
	}
	test3()
	var test4 = function(a){
		console.log(a);
	}
	test4(1);	
```

### 3.function构造函数



使用函数声明语句，具有函数名提升的效果



### 函数参数

实参可以省略，对应形参为undefined

若函数形参和实参相同名字，则以最后一个为准

可以给函数默认值

a=a||"a";

如果没给参数a，则默认字符串a

参数为值传递时，传递副本

###  函数调用

常用：函数名（参数列表）;

函数调用模式:

对象调用模式

### 函数的返回

通过return返回

return 结束方法、



## 函数的作用域

### 全部变量，局部变量

在js中只有函数有作用域

函数内部的叫做局部变量

同名时函数内部就近原则

没有var修饰符，则为全局变量



## 内置对象

### String

charAt

indexOf

substr

substring

toLowerCase

toUpperCase

length



### Math

random 随机数

ceil向上取整

floor向下取整



### Date



## 对象

### 创建

1.字面量

```html
<script type="text/javascript">
	
var obj={}

console.log(obj)
obj.uname="zhangsan"
obj.pwd = "123"
	console.log(obj)
	
var obj2={
	uname:"zhangsan",
	age:15,
	sayhello:function(){
		console.log("hello")
	}
}
console.log(obj2.uname)
obj2.sayhello()
</script>
```



2.create

以模板创建对象

var 对象名 = Object.create（模板）





### 序列化



	var str = JSON.stringify(obj)
	console.log(str)
	
	var obj3=JSON.parse(str)
	console.log(obj3)







### this

在函数中使用是全局变量

在对象中使用是这个对象

谁调用的函数就是谁



## 事件

load事件：当页面加载完毕后执行的事件

```html
	<body>
		<body onload="loawd()"></body>
	</body>
	
	<script type="text/javascript">
		function loawd(){
			console.log("jiazai")
		}

	</script>
```



### 事件流和事件模型

事件流：接受事件的顺序

​	事件冒泡：从小到大

​	事件捕获：从大到小



DOM事件流的三个阶段：事件捕获阶段，处于目标阶段，



## 事件处理程序

事件处理程序以on开头

html事件

```html
<body>
	<button type="button" onclick="alert('hello')" onmouseout="test()">按钮</button>、
</body>

<script type="text/javascript">
	function test(){
		console.log("鼠标走了")
	}
	
</script>
```



DOM0级事件

只能为同一个元素的同一个事件设定一个程序

```html
		<button type="button" id="btn"> DOM0</button>
var btn = document.getElementById("btn")
	console.log(btn)
	btn.onclick=function(){
		console.log("DOM0")
	}
```



DOM2级事件

可以绑定多次

addEventListener()

移除事件

removeEventListener();



## BOM 对象

### 系统对话框

消息框：alert

输入框：prompt

参数1.对话框的文本

参数2.默认的输入文本

确认框：confirm 

 ### 打开窗口

window.open



### 打开指定页面

window.open("")



## 时间函数

setTimeout（）

参数一：执行的函数

参数二：时间，毫秒 



setInteval（）

定间隔执行



## history对象



back（）

加载history列表 





##location对象

reload（）重新加载

replace（）不可后退



## DOM对象

 



## 创建节点

createElement（）





### 插入节点

write()

insertbefore()向指定节点前插入新节点



```html
<body>
	<ul>
		<li id="red">红色</li>
		<li>green</li>
	</ul>
</body>

<script type="text/javascript">
	document.write("<h2>你好</h2>")
	
	var li = document.createElement("li")
	var text = document.createTextNode("fense")
	li.appendChild(text)
	document.getElementById("red").parentElement.insertBefore(li,document.getElementById("red"))
</script>
```

### 下拉框

```html
var opt = document.createElemnet("option")

opt.value="2"
opt.text="xxx"
console.log(music.options)
music.options.add(opt)
```



## 间接查找节点

```html
<script type="text/javascript">
		var ul = document.getElementsByTagName("ul")[0];
		console.log(ul.childNodes);
		console.log(ul.children);
		
		console.log(ul.firstChild)
		console.log(ul.lastChild)
</script>
```



## 删除节点

<body>
		<span id="programmer">程序员</span>
		<a href="javascript:void(0)" onclick="delNode();">删除</a>
	</body>
	

```html
<script type="text/javascript">
	function delNode(){
		var span = document.getElementById("programmer");
		
		var parent = span.parentElement;
		
		parent.removeChild(span);
	}
```



## 表单

### 获取表单



1.document.表单名称

2.document.getElementById

3.document.forms

	<body>
		<form id="form1" name="form1" method="get" action="">


​			
```html
	</form>
	<form id="form2" name="form2" method="get" action="">
		
	</form>
	.
	
</body>

<script type="text/javascript">

	var form1 = document.form1;
	console.log(form1)
	
	var form2 = document.getElementById("form2");
	console.log(form2);
	
	var forms = document.forms;
	console.log(forms);
	
	var form3 = forms[0];
	console.log(form3);
	var form4 = forms['form1']
	console.log(form4);
</script>
```



### 获取input元素

```html
	<script type="text/javascript">
		var uname = document.getElementById("uname").value;
		console.log(uname);
		
		var uno = document.getElementsByName("uno")[0].value;
		console.log(uno)
		
		
		

	</script>
```



### 获取单选按钮

1.获取单选按钮组

2.遍历，查看checked属性



	<body>
		<input type="radio" name="rad" value="1" />1
		<input type="radio" name="rad" value="2" />2
		<button type="button" onclick="getRadio()">获取</button>
	</body>
	
	<script type="text/javascript">
		function getRadio(){
			var radios = document.getElementsByName("rad");
			
			for (var i =0;i<radios.length;i++){
				console.log(radios[i].checked);
				if(radios[i].checked){
	
				}
			}
		}
	
	</script>



### 获取多选

```html
<body>
	<input type="checkbox" name="rad" value="1" />1
	<input type="checkbox" name="rad" value="2" />2
	<input type="checkbox" name="rad" value="3" />3
	<input type="checkbox" name="rad" value="4" />4
	<button type="button" onclick="getRadio()">获取</button>
</body>

<script type="text/javascript">
	function getRadio(){
		var radios = document.getElementsByName("rad");
		var str='';
		for (var i =0;i<radios.length;i++){

			if(radios[i].checked){
				str +=radios[i].value+"--";
			}
		}
		console.log(str);
	}
</script>
```



### 获取下拉

1.获取下拉框

var ufrom = document.getElementById("ufrom");

2.获取下拉框的所有选项

var sel = ufrom.options

3.获取下拉框所有被选中的索引

var index = ufrom.selectedIndex

4.获取被选中的值

var val = sel[index].value

5.获取文本

var text =sel[index].text



## 提交表单

### 方法1

	<body>
		<form id="myform1" name="myform2" action="http://www.baidu.com" method="get">
			姓名：<input name="test" id="uname" />
			<input type="button" value="提交表单" onclick="sub()"/>
		</form>
	</body>
	
	<script type="text/javascript">
		function sub(){
			
			var uname = document.getElementById("uname").value;
			
			if(uname ==null || uname.trim()==""){
				return
			}
			
			document.getElementById("myform1").submit();
		}


	</script>



### 方式二

```html
<body>
	<form id="myform1" name="myform2" action="http://www.baidu.com" method="get">
		姓名：<input name="test" id="uname" />
		<input type="submit" value="提交表单" onclick="return sub()"/>
	</form>
</body>

<script type="text/javascript">
	function sub(){
			
	}
</script>
```



## 表单验证

 ```html
 document.getxxx.reset() //重置表单
 ```



通过id返回dom对象

```html
function $(id){
	return document.getxxxById(id)
}
```



# VUE

javascript框架

```html
	<body>
		<div id="app">
		  {{ message }}
		</div>
		<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
		
		
	</body>
	
	<script type="text/javascript">
var app = new Vue({
  el: '#app',
  data: {
    message: 'nihao!'
  }
})
	</script>
```



作用域：可以设置在双标签，不能挂载在body



vue中用到的数据放在data中

data可以写复杂数据



## 本地应用

### v-text

```html
	<body>
		<div id="app">
			<h2 v-text="message+'!!!'"></h2>
			<h2 v-text="info"></h2>
			<h2 v-text="info"></h2>
		</div>
		<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
		
		
	</body>
	
	<script type="text/javascript">
var app = new Vue({
  el: '#app',
  data: {
    message: 'Hello Vue!',
	info: "前端"
  }
})
```



### v-html

```html
		<div id="app">
			<h2 v-html="message+'!!!'"></h2>
			<h2 v-html="info"></h2>
			<h2 v-text="info"></h2>
		</div>
		<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
		
		
	</body>
	
	<script type="text/javascript">
var app = new Vue({
  el: '#app',
  data: {
    message: 'Hello Vue!',
	info: "<a href='http://www.baidu.com'>前端"
  }
})
	</script>
```

v-html解析连接



### v-on

为元素绑定事件

方法定义在methods中

方法内部可以用this访问定义在data中的数据





### v-show

根据指令真假选择元素是否隐藏



### v-if

根据表达式真假，切换元素的显示和隐藏

	<body>
		<div id="app">
			<input type="button" value="切换显示" @click="isshow"/>
			<p v-if="ish">我是傻逼</p>
		</div>


​		
​		

var app = new Vue({
  el: '#app',
  data: {
	  ish: false
  },
  methods: {
	  isshow:function(){
		  this.ish=!this.ish;
	  }
  },
})
	</script>

### v-bind

设置元素的属性

v-bind:属性名





### v-for

```html
	<div id="app">
		<ul>
			<li v-for="(it,index) in arr">
				{{index+1}} 名字是:{{it}}
			</li>
		</ul>
	</div>
```



### v-model

设置获取表单中的值





## vue网络应用



### axios

axios必须先导入再使用

使用get和post方法发送对应的请求

then方法中的回调函数会在请求成功或失败的时候触发

通过回调函数的形参可以获取响应内容，或错误信息。

axios回调函数的this已经改变没无法访问到data。

