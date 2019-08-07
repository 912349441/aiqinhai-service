#HTML+CSS

(红色标注为重点)

##一、HTML

###1，五大浏览器

​    IE、火狐、谷歌、safari、欧朋,

###2，内核：

> IE:Trident

> 火狐：Gecko

> safari:webkit

> goole:blink

> Opera：presto,

###3，web标准

​    结构标准:html

​    表现标准:css

​    行为标准：javascript	

​    原则：三层分离原则

###4，HTML的概念

​	超文本标签语言

###5，常用的编辑器

​        sublime，vscode,webstrom

###6，HTML基本结构中标签的作用

​       HTML：根节点

​	head：辅助   

​	title：页面标题

​	body：存放页面中所用的内容

​	HTML中lang=“en“设置页面语言

​	<meta  charset="utf-8">设置页面字符集、字符编码utf-8、GBK

eg:

![img](https://note.youdao.com/yws/public/resource/9d3c6ce25d321e80ca232f185229955f/xmlnote/76F5A3076D7F475E8C5E93ED8B5F1431/522)

![img](https://note.youdao.com/yws/public/resource/9d3c6ce25d321e80ca232f185229955f/xmlnote/700EE1F80CBE4A98A86CDA1820E4B9C8/524)

标题标签：

​	h1-h6:数字越大，字体越小一般一个页面只有一个h1标签，一般给logo使用。

​        p(段落),br(换行标签),hr(水平线标签)

###7，HTML中标签的分类

​	单标签,双标签（xhtml中单标签会报错）

###8，文本格式化标签

​        文本加粗(strong,b),文件斜体(em,i),文件下划线(ins,u),文本删除线(del,s)

###9，标签语义化

​	语义化：使用有意义的名称作为标签名

​	核心：在合适的地方使用合理的标签

​	两个重要没有语义的标签,主要帮助我们布局页面(div,span)

###10，标签 属性

​	图片标签:img

​		src：存放图片路径

​		alt：当图片丢失或者加载失败，提示用户

​		width：设置图片宽度

​		height：设置图片高度

​		title：设置图片标题

​		border：设置图片边框

​	  超链接:a标签(<a></a>)

​		href：存放要跳转页面的路径

​		作用:实现页面跳转,定位,下载

​		空链接:href=“#”

​		页面打开方式:target属性

​			_selt:在当前窗口打开

​			_blank：在新窗口打开

> > > ​    _parent：显示在上一层窗口中 (ifram结合使用)

> > > ​    _top     :显示在最上层窗口(ifram结合使用)

###11，列表

​	作用:用来管理一系列的数据

​	无序列表（用的最多的）:<ul><li></li></ul>

​		作用：用来管理一组没有先后顺序的数据

​	有序列表（用的比较少）:<ol><li></li></ol>

​		作用：用来管理一组有先后顺序的数据

​	自定义列表（基本不用）:dl><dt></dt><dd></dd></dl>

​		作用：用来管理多组数据（有小标题，有具体选项）

​		

###12，表格：用来管理多组数据

语法

​	<table>

> > <tr><th></th></tr>

> > <tr><td></td></tr>

​      </table>

​	

属性

​	cellspacing（设置单元格与单元格之间的间距）

​	cellpadding（设置单元格的边框与内容之间的间距）

合并单元格

​	rowspan：跨行合并		

​	colspan：跨列合并

###13,表单元素

input：type:

text:文本框,

password:密码框,

radio:单选框,默认选中:checked="checked"

checbox:多选框,默认选中:checked="checked"

button:按钮

file:文件上传框	

##二，CSS	

 ###1, 作用:

 设置页面元素的样式，美化页面

 ###2,css的语法:

 {属性：属性值；属性：属性值；…………}                                     

###3,  css的三种使用方式

行内样式:

<!--行内样式-->

<h1 style="color: #cad;">css例子</h1>

内嵌样式(内部样式):

<!--内嵌样式    -->

<style>

   /*例如*/

   .abc{

​      background: #cad;

   }

</style>

外联样式( 语法： <link rel="stylesheet" href="路径">)

<!--引入外部样式-->

<link rel="stylesheet" href="../assets/css/cbb/allstyle.css">

###4,字体相关属性

​    字体大小:  font-size

​    字体粗细

​      font-weight:normal,  bold, bolder,lighter

​        (100-900定义由粗到细的字符 400 等同于 normal，而 600 等同于 bold。

​    字体风格)

​      font-style:normal(正常), italic(斜体),oblique(倾斜)

​    字体类型

​      font-family

​        值的三种表示方式

​          使用中文

​          使用英文

​          使用Unicode编码的方式      escape();

​    字体简写：font: font-style  font-weight   font-size  font-family

​      注意： 1font-size和font-family一定不能少，位置不能变，而且是在 最后面，font-style和font-weight可选，位置可变

  color三种表示方式

​    使用表示颜色的单词

​    使用十六进制表示法

​      color: #nnnnnn    n的取值是：0-9 a-f

​      注意：当每每两位相同的时候可以简写；如：#aabbcc     #abc

​    使用rgb表示法

​      rgb(n,n,n);   n的取值是：0-255

###5,选择器

​    基础选择器

​      选择器名称 （标签名） { 属性：属性值；}

​    类选择器

​      .类名 { 属性：属性值}

​      特点： 1.一个类可以被多个元素使用 2.一个元素可以同时拥有多个类名，但是每个类名之间必须用空格隔开

​    id选择器

​      \#id名{属性：属性值；}

​      注意：在页面中只能有一个叫这个id名称的元素

​    通配符选择器

​      \* { 属性：属性值}

​      对所有标签起作用

​      注意：效率低，一般不推荐使用，而是在css初始化的时候使用

​    类和id 的命名规则

​      规则：1.可以有字母、数字、短横线-、下划线_ 

> > > 2.不能以数字开头 

> > > 3.不能使用单个的短横线 

> > > 4.不能使用短横线+数字开头

​      建议：1.使用有意义的名称 

> > > 2.使用驼峰命名法   驼峰命名法：当有多个单词组成的时候，第一个单词的首字母小写，后面每个单词的首字母大写

   ##复合选择器

### 1.交集选择器:

使用的方法是其中一个为HTML的标签,另外一个是类选择器: <p class="one" > 123</p>：p.one{}

![img](https://note.youdao.com/yws/public/resource/9d3c6ce25d321e80ca232f185229955f/xmlnote/D763C380B3A14B3082C41CEF633D43E8/534)

### 2.并集选择器

多个选择器用逗号分开,所有选择器都能实现后面的样式:a , p ,  .one , #id {}

![img](https://note.youdao.com/yws/public/resource/9d3c6ce25d321e80ca232f185229955f/xmlnote/C79F4B9FE558430D9789B4C51B437D2C/537)

### 3、后代选择器:

外层的标签写在前面,内层标签写在后面 :<div> <p> </p> </div>  ：div p {} （最常使用）

![img](https://note.youdao.com/yws/public/resource/9d3c6ce25d321e80ca232f185229955f/xmlnote/7B17321F61F24030B5A4E6F21EC64664/539)

### 4、子元素选择器

表示只选择第一层的子元素 ：语法:  .nav > li    大于号前后用空格分开

​    属性选择器：选择具有特殊属性的标签：（一般少用）

​       1)  a[title] {}  选择 a标签中有title属性的进行样式的改变

​      	 2)  input[type=text] {}  选择有特殊属性,并且特殊属性的值与给出值一样的进行样式改变（最常用）

​        3)  div[class^=font]  {}  ^= 表示以等号后面的值为开头的值的选择出来,进行css样式改变

​       4)  div[class$=font]   {}   $= 表示将等号后面的值作为结尾的标签选择出来,

​       5)  div[class*=tao]   {}   *= 表示将等号后面的值,有与之匹配的找出来,不管哪个位置

###5、伪元素选择器：

​    语法:E::first-letter    解释: E表示标签 ,主要有以下几种:

​    1) E::first-letter  表示第一个单词 

​    2) E::first-line   表示第一行

​    3) E::selection    选中文字后,文字的变换, 比如字体颜色的改变

​    4) E::before   E::after     花括号中必须有 content属性 .在盒子的内部的前面或后面放内容（这些添加不会出现在DOM中，不会改变文档内容，不可复制，仅仅是在css渲染层加入。比如，加入图标  ，清除浮动的时候也会用到，）

![img](https://note.youdao.com/yws/public/resource/9d3c6ce25d321e80ca232f185229955f/xmlnote/3F98C290F1014F9F99AC5F2F706ACA4A/550)

伪类选择器

>   #####链接伪类选择器

> ​    选择器:link:未访问的链接,只对a标签有效

> ​      语法选择器：link { }

> ​    选择器:visited:   已访问的链接,只对a标签有效

> ​      语法选择器：visited {}

> ​    选择器:hover: 鼠标停留,对所有元素都有效，适用于鼠标移入移出的样式变化问题

> ​      语法选择器：hover { }

> ​    选择器:active:点击时,选定的链接

> ​      语法选择器：active {  }

> ​    注意：如果四个同时使用是有顺序的。  :link   :visited  :hover   :active

>   #####结构伪类选择器

> ​    （儿子）选择器:first-child :(选择第一个孩子元素)

> ​    选择器:last-child :(  选择最后一个孩子元素)

> ​    (选择器要选择儿子的标签的选择器) 选择器:nth-child(n) n:表示选择的孩子元素的位置序号   从前往后算

> ​    （儿子）选择器:nth-last-child(n); n:表示选择的孩子元素的位置序号   从后往前算

> ​    常见写法

> ​      body div:nth-child(4){ color: red;  }

> ​      div.one:nth-child(4){ color: pink; }

###6，文本相关属性

​    text-align:设置文本水平对齐方式：left、center、right

​    text-indent：设置文本首行缩进

​      单位：em

​      首行缩进2em

​    text-decoration：设置文本修饰线

​      none：去掉修饰线

​      underline：下划线

​      overline：上划线

​      line-through：中划线

###7、元素常见的三种显示方式

  行内元素:display:inline

​    特点：  1.一行显示多个 

​    2.大小由内容决定

​    3.不能设置有效宽高 

​    常见的行内元素有:<a>、<strong>、 <b>、<em>、<i>、<del>、<s>、 <ins>、<u>、<span>等，其中<span> 标签最典型的行内元素。

  块级元素:display:block

​    

​    特点： 1.独占一行

​             2.能设置有效宽高

​    常见块级元素: <h1>~<h6>、<p>、<div>、<ul>、<ol>、<li>

  行内块级元素: display:inline-block

​    行内块级元素居中要针对父代标签

​    特点： 1.一行可以显示多个

​             2.可以设置有效宽高

###8，行高属性:line-height：像素

  	注意：如果盒子中是当行文本，当盒子的高度和行高相等的时候，文本垂直居中

###9，css三大特性

  层叠性

​    同一个元素的同一个属性，后面的样式会覆盖前面的样式，优先级高的会覆盖优先级低

  继承性

​    特殊情况： 1.a标签不能继承颜色 2.h系列标签不能继承font-size和font-weight

  优先级

​    继承<通配符选择器<标签选择器<类选择器<id选择器<行内样式<！important

###10，背景相关属性

  背景颜色：background-color

  背景图片： background-image: url();

  背景是否平铺：background-repeat

​      repeat

​      no-repeat

​      repeat-x

​      repeat-y

  背景附着 background-attachment

​    scroll:滚动

​    fixed：固定

  背景位置 ：background-position

​    使用表示方位的单词

​      一个值的时候：设置的值就是表示方位，另一个方向默认居中

​      两个值的时候：和设置的值有关，与顺序无关

​    使用具体数字

​      一个值的时候：这个值表示水平方向，垂直方向默认居中

​      两个值：第一个表示水平方向，第二个表示垂直方向

  背景透明度： rgba(n,n,n.m)     a:alpha透明度  m的取值：0-1

   

  背景简写：background：background-image  background-repeat   background-position

###11，盒子模型

  组成

​    内容content

​    边框：border

​    内边距：padding

​    外边距：margin

  border：边框

​    border-width：边框宽度

​    border-color:边框颜色

  注意：border必须给颜色样式大小才能显示

  border-style：边框样式

​    solid：实线边框

​    dashed：虚线边框

​    dotted：点线边框

​    double：双实线边框

​    边框简写：border：border-width  border-style  border-color 

​    注意：border-style一定要写，其他可选，与顺序无关

   上边框：  border-top

   下边框：border-bottom

​    左边框： border-left

​    右边框： border-right

​    表格细线边框（合并表格边框）： border-collapse

​      值：collapse

​    圆角边框 border-radius(值为像素或者百分比)

内边距：padding

  padding的值： 

> 一个值：表示上下左右都是这内边距值  

>  两个值：第一个表示上下内边距，第二个表示左右内边距 

> 三个值：第一个表示上内边距，第二个表示左右内边距，第三个表示下内边距 四个值：依次表示上右下左内边距

padding能改变盒子的大小

 	注意内边距不能填颜色跟图片

padding有四个方向：       

> padding-top:上内边距       

> padding-left: 左内边距       

> padding-bottom:下内边距      

>  padding-right:由内边距

外边距margin

  margin的取值： 

> 一个值：表示上下左右都是这外边距值 

> 两个值：第一个表示上下外边距，第二个表示左右外边距    

> 三个值：第一个值表示上外边距，第二个值表示左右外边距，第三个值表示下外边距 

> 四个值：依次表示上右下左外边距

  margin的四个方向：

​       margin-top:上外边距 

​       margin-left:左外边距      

>  margin-bottom:下外边距      

>  margin-right:右外边距

  margin塌陷

​    标准流margin会出现塌陷，浮动就不会

​    垂直塌陷

​      解决方案：没有，布局的时候尽可能的避免

​    包含垂直塌陷

​      解决方案：

​        给父盒子加border

​        给父盒子加overflow:hidden

影响盒子大小的因素： padding，border

  	盒子的大小计算 盒子的真实宽度： width + 左右border + 左右padding 

​       盒子的真实高度： height+ 上下border+ 上下padding

盒子布局稳定性

  先使用width或者height，然后使用padding，最后使用margin

盒子阴影

 	box-shadow： 水平阴影位置   垂直阴影位置  模糊距离   阴影大小  阴影颜色 （内外阴影）

  注意：默认是外阴影，不需要设置。

###12，页面常见的三种布局机制

  标准流

  浮动：float

  浮动的特点

​    1) 浮动元素脱标，不占标准流中的位置

​    2) 浮动的元素以顶部对齐

​    3)  如果浮动的元素前面是标准流的元素，那么浮动的元素跟在标准流的元素的下面

​    4) 如果浮动的元素前面是浮动的元素，那么浮动的元素跟在前面浮动的元素后面

​    5) 如果有包含关系，子元素浮动不会跑出父元素，而且不会占据父元素的padding和border的距离

  定位:position

- [static](https://www.runoob.com/css/css-positioning.html#position-static)  (HTML元素的默认值，即没有定位，元素出现在正常的流中，静态定位的元素不会受到 top, bottom, left, right影响。)
- [relative](https://www.runoob.com/css/css-positioning.html#position-relative) （相对定位元素的定位是相对其正常位置。）
- [fixed](https://www.runoob.com/css/css-positioning.html#position-fixed) (元素的位置相对于浏览器窗口是固定位置，即使窗口是滚动的它也不会移动)
- [absolute](https://www.runoob.com/css/css-positioning.html#position-absolute) （绝对定位的元素的位置相对于最近的已定位父元素，如果元素没有已定位的父元素，那么它的位置相对于<html>）
- [sticky](https://www.runoob.com/css/css-positioning.html#position-sticky) （没用过。。）

  偏移量

​    top  left   right bottom

  定位模式

​       相对定位（relative）

​      相对定位的特点：

​       1.相对定位的元素不脱标，虽然偏移了，但是占原来的位置，

​       2.可以设置有效偏移量 

​       3.相对定位不能改变元素的显示方式  （就是原来的display是什么就是什么）

​       4.相对定位是参照自身原来的位置来做位移

​       绝对定位  ( absolute)

​      1.元素脱标，浮起来，不占位置，后面的元素会跟上

>  2.改变元素的显示方式，也就是改变display属性为block 

>  3.子元素绝对定位，如果父辈元素都是静态定位，那么这个绝对定位的子元素参照浏览器来做位移； 子元素绝对定位，参照离自身最近的非静态的父辈元素来做定位,脱标浮起来 改变显示方式 定位参照最近非静态或者浏览器

​    固定定位 ( fixed)

​      固定定位的特点：

​    	1.不管父辈元素的定位方式，只要是固定定位的元素都是参照浏览器来做位置移动 

​       2.固定定位的元素脱标，不占位置 

​       3.改变元素的显示方式

​    子绝父相

定位盒子居中

  偏移量50% 水平垂直各移动宽高的一半

  偏移量设置都是50% 居中的盒子需要设置水平和垂直反方向移动宽高的一半

​    left:50% top:50% margin-top:-25px;(子盒子高度的一半) margin-left:-25px;(子盒子宽度的一半)  如果宽高给定百分值的情况下适用，水平居中则改两个left

层级

###13，清除浮动

  清除浮动的方式

​    1）给父盒子加overflow：hidden

​    额外标签法

​    2）伪元素清除浮动

​      .clearfix::after {

> >  content: ".";

> > display: blocl; 

> > visibility:hidden; 

> > height:0; 

> > line-height:0; 

> } 

.clearfix {zoom: 1; }

​    3）双伪元素清除浮动

​      .clearfix::before,

​     .clearfix::after {

​           content: "";

​           display: table; 

​    } 

​    .clearfix::after {

​          clear: both 

​    }

###14，显示和隐藏属性

  显示： display：block

​      元素显示并且显示方式被改变: visibility:visible

  隐藏:display:none 隐藏元素，不占位置

​         visibility:hidden 隐藏元素，占位置

鼠标样式: cursor

  default :跟随操作系统鼠标样式

  auto：跟随浏览器鼠标样式

  text：文本光标

  move：移动关闭

  pointer：小手

外轮廓线 :outline

  常用：outline：none；

  和border的使用是一样的

防止文本域拖拽:resize：none；

垂直对齐方式:vertical-align:top/ middle

  	只对行内元素和行内块级元素有效，一般都是用来设置行内块级元素的垂直对齐方式

精灵图:将很多的小图做成的一张大图

  好处： 	1.减少服务器的压力 

> > > 2.提高用户的访问效率 

> > > 3.减少网络请求，节约网络资源

  z-index

  注意： 	1.层级只对非静态定位的元素有效 

> > > 2.如果都是非静态定位，后面的元素的层级比前面的元素的层级高 

> > > 3.非静态定位的元素的层级默认都是0

文本溢出处理  overflow

  visible：不做任何处理

  hidden：隐藏溢出

  auto：有溢出加滚动条，没有溢出不加

  scroll：始终都有滚动条

溢出使用省略号替代 

  white-space：nowrap   让文本不换行

  text-overflow：ellipsis  ellipsis：省略 让溢出部分被省略号替代

  overflow：hidden   隐藏溢出

文字 字间距

  letter-spacing：像素 字符间距（中文适用）

  word-spacing：像素 单词间距（英文适用）

###15，calc()函数：计算 元素的宽度:

​      eg: div{ width:  calc(100% - 10px) }

###16,[display：table的几个用法](https://www.cnblogs.com/stephen666/p/6995388.html)

1)、父元素宽度固定，想让若干个子元素平分宽度:

.parent{display: table;  width: 1000px;} .son{display: table-cell;}

2),块级子元素垂直居中

.parent {display: table;} .son {display: table-cell; vertical-align: middle;}

注意：虽然display：table解决了避免使用表格的问题，但有几个需要注意的：

（1）display: table时padding会失效

（2）display: table-row时margin、padding同时失效 （3）display: table-cell时margin会失效

##三、CSS3

  （css3,用的不多不详解说，此处只说常用的几点，详情去看文档：<https://www.runoob.com/css3/css3-tutorial.html>）

###1,圆角： border-radius 

###2，背景：

- background-image
- background-size
- background-origin
- background-clip

###3，渐变：

- 线性渐变（Linear Gradients）- 向下/向上/向左/向右/对角方向 

（默认由上到下，设置从左到右写法：

- \#grad {   background: -webkit-linear-gradient(left, red , blue); /* Safari 5.1 - 6.0 */   background: -o-linear-gradient(right, red, blue); /* Opera 11.1 - 12.0 */   background: -moz-linear-gradient(right, red, blue); /* Firefox 3.6 - 15 */   background: linear-gradient(to right, red , blue); /* 标准的语法 */ }

）

- 径向渐变（Radial Gradients）- 由它们的中心定义

注意： Internet Explorer 9 及之前的版本不支持渐变。

###4，文本效果：

- text-shadow：文本阴影
- box-shadow：盒子阴影
- text-overflow：文本溢出属性指定应向用户如何显示溢出内容
- word-wrap：（break-word）允许长单词换行到下一行
- word-break：规定自动换行的处理方法

###5，过渡：transition

###6, 多媒体查询:

@media screen and (min-width: 720px) and (max-width: 1000px) {

​    body {

​        background-color: red;

​    }

}

//浏览器宽度 720px到1000px的设备上,div背景颜色为red

###7,伸缩布局   

<https://www.runoob.com/css3/css3-flexbox.html>

​	伸缩布局：主轴  侧轴 

​		主轴：Flex容器的主轴主要用来配置Flex项目，默认是水平方向

​		侧轴：与主轴垂直的轴称作侧轴，默认是垂直方向的

​		方向：默认主轴从左向右，侧轴默认从上到下

​		主轴和侧轴并不是固定不变的，通过flex-direction可以互换。

​	1、必要元素：

​		a、指定一个盒子为伸缩盒子 display: flex  

​		b、设置属性来调整此盒的子元素的布局方式 例如 flex-direction

​		c、明确主侧轴及方向

​		d、可互换主侧轴，也可改变方向

​	2、各属性详解

​		a、flex-direction:调整主轴方向（默认为水平方向）

​			该属性通过定义flex容器的主轴方向来决定felx子项在flex容器中的位置

​			row 水平方向

​			Row-reverse反转

​			column 垂直方向

​			column-reverse 反转列

​		b、justify-content :设置或检索弹性盒子元素在主轴（横轴）方向上的对齐方式。 

​			flex-start: 起点对齐

​			flex-end:  终点对齐

​			center    中间对齐

​			space-around、 环绕

​			space-between  两端对齐

​		c、flex :控制子项目的缩放比例

​			不指定flex 属性，则不参与分配

​		d、align-items : 设置或检索弹性盒子元素在侧轴（竖轴）方向上的对齐方式。 

​			flex-start: 起点对齐

​			flex-end:  终点对齐

​			center    中间对齐

​			Stretch:  拉伸

​		E、flex-wrap 控制是否换行

​			Wrap：换行

​			Nowrap：不换行

###8,rem 与px之间的转换方法：

1），先用js监测屏幕分辨率的大小来更改 html的font-size：

> (function (doc, win) {   var docEl = doc.documentElement,     resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',     recalc = function () {       var clientWidth = docEl.clientWidth;       if (!clientWidth) return;       docEl.style.fontSize = 20 * (clientWidth / 320) + 'px';

> //分辨率以320px为基础的情况下     };    if (!doc.addEventListener) return;   win.addEventListener(resizeEvt, recalc, false);   doc.addEventListener('DOMContentLoaded', recalc, false); })(document, window);

2），然后根据font-size的值换算rem，比如我们通常设计稿根据720px的分辨率作为基础大小，那么font-size 就是48px，那么假如div的宽为100px，则此换算为：100px/48 = 2.0833rem (余数一般取后四位),详情可查看：

（1，如果用sublime软件的话，此换算可借用插件来实现：<https://blog.csdn.net/qq_16559905/article/details/51712589>

2，可借用sass,less通过函数来计算：<https://www.w3cplus.com/preprocessor/sass-px-to-rem-with-mixin-and-function.html>）

兼容性问题：<https://www.cnblogs.com/ahao68/p/5431250.html>

一般兼容性处理的常见方法是为属性添加私有前缀，如不能解决，应避免使用，无需刻意去处理CSS3的兼容性问题。

私有前缀:

​    -o-: opear   欧朋

​    -ms-:  IE  微软

​    -moz-: 火狐

​    -webkit- : 谷歌 苹果

<!DOCTYPE html> 这个标注是每个页面都需要添加的，不可忽略，不写的话可能出现样式错误的问题。详情可查阅：<https://www.cnblogs.com/Eton/p/6063450.html>



# 笔记

## 文字溢出

```css
overflow:hidden;
white-space: nowrap;
text-overflow: ellipsis;
```

## Css画三角形

```css
  i{
    position: absolute;
    height: 0px;
    width: 0px;
    top:0;
    margin-left: 15%;
    border-left: 0.4rem solid transparent;
    border-top: 0.4rem solid #999999;
    border-right: 0.4rem solid transparent;
    }
    .check{
        top:-1px;
        border-top: 0.4rem solid #FFFFFF;
    }
```

## 垂直居中

```css
.publicityList{
    width: 100%;
    .px2rem(height,182);
    span{
        display: inline-block;
        text-align: center;
        color: #666666;
        .padding(12,0,12,0);
        position: relative;
        top: 50%;
        transform: translateY(-50%);
    }
```

##Slide滑动监控

```js
import Slide from 'clientPath/componets/Common/Slide'

   up() {
      console.log("上");
   }

   right() {
      console.log("右");
   }

   down() {
      console.log("下");
   }

   left() {
      console.log("左");
   }

componentDidMount=()=>{
   /**
    * 监听触摸的方向
    * @param target            要绑定监听的目标元素
    * @param isPreventDefault  是否屏蔽掉触摸滑动的默认行为（例如页面的上下滚动，缩放等）
    * @param upCallback        向上滑动的监听回调（若不关心，可以不传，或传false）
    * @param rightCallback     向右滑动的监听回调（若不关心，可以不传，或传false）
    * @param downCallback      向下滑动的监听回调（若不关心，可以不传，或传false）
    * @param leftCallback      向左滑动的监听回调（若不关心，可以不传，或传false）
    */
   Slide.listenTouchDirection(
      document,
      true,
      false,
      this.right,
      false,
      this.left
   )
}
```
##Confirm确认弹框

```js
import Confirm from 'clientPath/componets/Common/confirm'
	  
      confirmContent:'确定删除吗？',//弹框内容
      confirmVisible:false,
      btns:[{title:'确定',func:() => this.removeAttach()},{title:'取消',func:() => this.closeShow()}],
      
  //删除附件
  removeAttach = (i) =>{
    const {files} = this.state
    files.splice(i,1);
    this.setState({
      files:files,
      confirmVisible:false
    })
    this.showTip('删除成功！')
  }
  
  //关闭提示框
  closeShow =()=>{
    const {confirmVisible} = this.state
    this.setState({
      confirmVisible:false
    })
  }
  
  confirmVisible,btns,confirmContent
  
  <Confirm visible={confirmVisible} btns={btns} content={confirmContent} title={'提示'}/>
```

##conTip提示框

```js
import ConTip from 'clientPath/componets/Common/confirmTip'

      TipVisible: false,
      Tipcont:'',

         showTip = (Tipcont) => {
            this.setState({
               TipVisible: true,
               Tipcont: Tipcont
            })
            let that = this
            setTimeout(function () {
               that.setState({
                  TipVisible: false
               })
            }, 3000)
         }

      TipVisible, Tipcont
      < ConTip visible = {TipVisible} content = {Tipcont} />
```

## react阻止冒泡事件，绝对干货

```
https://www.jianshu.com/p/e0894bd588f4?tdsourcetag=s_pctim_aiomsg
```

