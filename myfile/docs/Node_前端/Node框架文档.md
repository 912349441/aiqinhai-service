> ​	文档是使用有道云笔记的MD编辑器编写。建议使用有道云笔记查看


## 一、框架简介

### 1.框架介绍

基于Node的搭建的Web服务器，Node中用Express做HTTP请求处理和接口转发,提供服务器渲染环境。是MVVM设计模式的单页面应用。react做页面渲染,react-router管理前端路口控制,redux管理应用状态

### 3.框架流程图
![image](https://note.youdao.com/yws/public/resource/dfad45cdc88fc626761e42eb7cfbda4e/xmlnote/2974EAE1955F4987B25E86646866F820/13903)

### 4.NPM
NPM是JavaScript的的包管理工具，并且是Node.js默认的管理工具，NPM可以轻松的管理包的依赖和版本。还提供了编写脚本的功能

#### 1.NPM命令

```
npm install或者 npm i 安装package文件里面的依赖包
npm install xxx 安装指定依赖，默认是最新版本
npm install xxx@1.0.0 安装指定依赖，指定版本
npm uninstall xxx 删除指定依赖包
npm update xxx 更新指定依赖
```
#### 2.脚本命令

```
npm build 打包程序
npm start-prod 启动打包程序
npm dev 开发启动
npm clean 清除打包文件
```

#### 3.NPM命令参数
```
-g 全局安装
--save-dev 安装开发用依赖并写入package.
--save安装生产用依赖并写入package.
```

### 4.文件目录

- 



## 三、开发

### 1.新建路由（页面路由）

#### 1.1引入组件
**src/client/routes.js**

```
import TestCom from './componets/TestCom'
<!--组件名字开头大写-->

<!--引用组件-->
<Route path="/channel_look/:channelId" title="网站标题" component={TestCom}  />

```
- path 路由参数,访问地址,必填
- title 页面标题,等同于<title></title>,必填
- component 调用的组件,必填
- bodyClass 用于页面body的样式名,非比填

#### 1.2新建页面组件

**src/client/component**下面新增文件夹，文件夹里面新增 
**index.js**(建议命名为index.js,导入模块时默认索引该文件夹下面的index.js文件)，

index.js页面入口组件代码

```
<!--导入React,必须-->
import React, { Component} from 'react'
<!--导入类型参数,有参数传入时必须导入-->
import PropTypes from 'prop-types'
<!--创建名为TestComponent的组件-->
export default class TestComponent extends Component{
    constructor(props){
        super(props)
    }
    static propTypes = {
        localtion:PropTypes.object,
        params:PropTypes.object
    }
    render(){
        <div>Hello, world!</div>
    }
}
```
- ==constructor== 构造函数，初始化这个组件（或类）的属性，初始化时执行
- ==propTypes== 对象，定义这个组件需要的数据、数据类型、是否必须。（localtion对象和params对象入口文件这两个prop是react-router组件传进来）
  - ==localtion==对象，由React-router创建的localtion对象，和浏览器的localtion对象不一样，里面没有跳转等方法调用，多了query的参数对象
  - ==params==对象，路由地址参数
- ==render== 渲染组件方法

---

#### 1.2请求数据
```
<!--导入React框架-->
import React, { Component} from 'react'
<!--导入类型参数,有参数传入时必须导入-->
import PropTypes from 'prop-types'
<!--新增导入方法对象，需要请求数据时导入-->
import * as Actions from '../../action'

<!--创建名为TestComponent的组件-->
export default class TestComponent extends Component{
    constructor(props){
        super(props)
    }
    static propTypes = {
        localtion:PropTypes.object,
        params:PropTypes.object
    }
    <!--新增fetchData方法-->
    static fetchData(params){
        return [
          Actions.getSession(params),
          Actions.advertList({...params,code:'01'})
        ]
    }
    render(){
        <div>Hello, world!</div>
    }
}
```
- ==fetchData== 静态方法，直接通过类来调用，不会被继承，这个函数返回请求数组，组件被调用时会触发这个函数里面的请求方法。
  - params参数，里面包含了请求的参数列表
    - ==session==
    - ==cookie==
    - ==query== (get请求参数)
    - 路由地址参数,比如:/order/:id -> /oder/123  -> {id:'123'}
- ==...params==，把这个params参数对象的内容==解构==出来。


  #### 1.3组件内调用组件

新建一个文件(组件)**testHello.js**

```
<!--导入React框架-->
import React, { Component} from 'react'
<!--导入类型参数,有参数传入时必须导入-->
import PropTypes from 'prop-types'

<!--创建名为TestHello的组件-->
export default class TestHello extends Component{
    constructor(props){
        super(props)
    }
    static propTypes = {
        text:PropTypes.string.isRequire
    }
    render(){
        const {text} = this.props
        <div>Hello, {text}</div>
    }
}
```
引用组件

```
<!--导入React框架-->
import React, { Component} from 'react'
<!--导入类型参数,有参数传入时必须导入-->
import PropTypes from 'prop-types'
<!--导入方法对象，需要请求数据时导入-->
import * as Actions from '../../action'
<!--导入组件,导入组件时可以忽略掉.js，但不建议，会导致编译变慢，特别是生产环境打包-->
    import * as TestHello from './testHello.js'

<!--创建名为TestComponent的组件-->
export default class TestComponent extends Component{
        constructor(props){
        super(props)
    }
    static propTypes = {
        localtion:PropTypes.object,
        params:PropTypes.object
    }
    static fetchData(params){
        return [
          Actions.getSession(params),
          Actions.advertList({...params,code:'01'})
        ]
    }
    render(){
        <TestHello text={'world!'} />
    }
}
```

#### 1.4组件绑定数据

当我们在入口组件调用fetchData时只是触发了请求，把数据存储到reducer里面，组件并没有获取到数据，需要我们手动把reducer里面的数据绑定到组件

**testHello.js**

```
<!--导入React框架-->
import React, { Component} from 'react'
<!--导入类型参数,有参数传入时必须导入-->
import PropTypes from 'prop-types'
<!--导入绑定数据函数-->
import { connect } from 'react-redux'

<!--绑定数据的函数-->
const mapStateToProps = state =>{
  return {
    advertList:state.advertList.toJS()
  }
}

<!--把数据绑定到组件-->
@connect(mapStateToProps)
<!--创建名为TestHello的组件-->
export default class TestHello extends Component{
    constructor(props){
        super(props)
    }
    static propTypes = {
        text:PropTypes.string.isRequire，
        advertList:PropTypes.object.isRequire
    }
    render(){
        const {text,advertList} = this.props
        console.log(advertList)
        <div>Hello, {text}</div>
    }
}
```
- ==mapStateToProps==这个方法返回的是一个对象，redux会把这个对象下面的每个属性传给组件
  - state 存储着所有的数据
  - 传给组件的属性名和state里面的属性名可以不一致,(建议一致,方便查找)

#### 1.5组件绑定action

（==mapDispatchToProps==）

通过redux提供bindActionCreators方法来批量绑定action方法

**testHello.js**

```
<!--导入React框架-->
import React, { Component} from 'react'
<!--导入类型参数,有参数传入时必须导入-->
import PropTypes from 'prop-types'
<!--导入绑定数据函数-->
import { connect } from 'react-redux'
import * as Actions from '../../action'
<!--引入绑定方法-->
import {bindActionCreators} from 'redux'

<!--绑定数据的方法-->
const mapStateToProps = state =>{
  return {
    advertList:state.advertList.toJS()
  }
}
<!--绑定请求的方法-->
const mapDispatchToProps = dispatch =>{
  return {
    actions: bindActionCreators(Actions, dispatch)
  }
}

<!--把数据和action绑定到组件-->
@connect(mapStateToProps,mapDispatchToProps)
<!--创建名为TestHello的组件-->
    <!--redux会把重新生成的action传递到组件-->
export default class TestHello extends Component{
    constructor(props){
        super(props)
    }
    static propTypes = {
            text:PropTypes.string.isRequire，
        advertList:PropTypes.object.isRequire,
        acttions:PropTypes.object.isRequire
    }
    render(){
        const {text,advertList} = this.props
        console.log(advertList)
        <div>Hello, {text}</div>
    }
}
```

绑定数据尽可能的不要绑定在入口文件，最好是绑定到具体的某个组件，这样做有如下好处：
1. 提高组件的复用度
2. 提高性能，所有数据绑定在入口文件，数据一旦发生变化，会导致不需要渲染的子组件触发渲染，造成不必要性能的损失
3. 提高开发效率，假设有一个层级很深的组件，如果从入口文件一层一层传递参数，无疑会加大调试难度，还会造成代码冗余
4. 加大代码的复杂度，导致某些功能实现起来很困难

### 2.框架的整体流程

框架大致分为如下步骤：

1. 接收HTTP请求
2. 中间件处理
3. 匹配路由
4. 获取数据（一般是调用接口）
5. 页面渲染处理

##### 2.1 启动监听端口

Node.js服务启动需要一个入口文件，这个入口文件一般用于监听端口（处理请求），处理中间件（后面的路由和获取数据和渲染其实也是中间件）。

我们项目的入口文件命名都是**server.js**，一般都放在项目的根目录下

**server.js**
```javascript
<!-- 引入Express框架 -->
var express = require('express')
<!-- 初始化 -->
var app = express()


<!-- 监听80端口 -->
app.listen(80,'0.0.0.0',function (err) {
  if (err) {
    console.error(err)
  } else {
    console.info('==> Listening on port %s. Open up http://localhost:%s/ in your browser.', config.port, config.port)
  }
})

```

#### 2.2中间件处理

> Express 是一个自身功能极简，完全是由路由和中间件构成一个的 web 开发框架：从本质上来说，一个 Express 应用就是在调用各种中间件。
中间件（Middleware） 是一个函数，它可以访问请求对象（request object (req)）, 响应对象（response object (res)）, 和 web 应用中处于请求-响应循环流程中的中间件，一般被命名为 next 的变量。

中间件的功能包括：

- 执行任何代码。
- 修改请求和响应对象。
- 终结请求-响应循环。
- 调用堆栈中的下一个中间件。

如果当前中间件没有终结请求-响应循环，则必须调用 next() 方法将控制权交给下一个中间件，否则请求就会挂起。

Express 应用可使用如下几种中间件：

- 应用级中间件。
- 路由级中间件。
- 错误处理中间件。
- 内置中间件。
- 第三方中间件


###### 应用级中间件

应用级中间件绑定到 app 对象 使用 app.use() 和 app.METHOD()， 其中， METHOD 是需要处理的 HTTP 请求的方法，例如 GET, PUT, POST 等等，全部小写。


**server.js**

```javascript
<!-- 引入Express框架 -->
var express = require('express')
<!-- 初始化 -->
var app = express()

// 没有挂载路径的中间件，应用的每个请求都会执行该中间件
app.use(function (req, res, next) {
  console.log('Time:', Date.now());
  next();
});

// 处理get请求
app.get('/', function (req, res, next) {
  cosole.log('http get')
})

<!-- 监听80端口 -->
app.listen(80,'0.0.0.0',function (err) {
  if (err) {
    console.error(err)
  } else {
    console.info('==> Listening on port %s. Open up http://localhost:%s/ in your browser.', config.port, config.port)
  }
})

```
###### 路由级中间件

路由级中间件和应用级中间件一样，只是它绑定的对象为 express.==Router()==。

**server.js**

```javascript
var routes = require('./src/server/routes/index')
// 将路由挂载至应用 
routes(app)
```
```javascript
<!-- 引入Express框架 -->
var express = require('express')
<!-- 初始化 -->
var app = express()
var router = express.Router();
<!-- 引用路由 -->
var routes = require('./src/server/routes/index')

// 没有挂载路径的中间件，应用的每个请求都会执行该中间件
app.use(function (req, res, next) {
  console.log('Time:', Date.now());
  next();
});

// 处理get请求
app.get('/', function (req, res, next) {
  cosole.log('http get')
})

// 将路由挂载至应用 
routes(app)

<!-- 监听80端口 -->
app.listen(80,'0.0.0.0',function (err) {
  if (err) {
    console.error(err)
  } else {
    console.info('==> Listening on port %s. Open up http://localhost:%s/ in your browser.', config.port, config.port)
  }
})

```
**src/server/routes/index.js**
```javascript
module.exports = function (app) {
  app.use('/hello',require('./hello'))
}
```

**src/server/routes/hello.js**
```javascript
var express = require('express')
var router = express.Router()

router.get('/',function(req,res,next){
  res.send('hello world!')
})
```
###### 错误处理中间件

错误处理中间件有 4 个参数，定义错误处理中间件时必须使用这 4 个参数。即使不需要 next 对象，也必须在参数中声明它，否则中间件会被识别为一个常规中间件，不能处理错误。


**server.js**
```javascript
<!-- 引入Express框架 -->
var express = require('express')
<!-- 初始化 -->
var app = express()
var router = express.Router();
<!-- 引用路由 -->
var routes = require('./src/server/routes/index')

// 没有挂载路径的中间件，应用的每个请求都会执行该中间件
app.use(function (req, res, next) {
  console.log('Time:', Date.now());
  next();
});

// 处理get请求
app.get('/', function (req, res, next) {
  cosole.log('http get')
})

// 将路由挂载至应用 
routes(app)

//报错
app.use(function(err,req, res, next) {
  res.status(404).send('请求错误,404');
})


<!-- 监听80端口 -->
app.listen(80,'0.0.0.0',function (err) {
  if (err) {
    console.error(err)
  } else {
    console.info('==> Listening on port %s. Open up http://localhost:%s/ in your browser.', config.port, config.port)
  }
})

```
###### 内置中间件 

express.static 是 Express 唯一内置的中间件,负责在 Express 应用中提托管静态资源。

**server.js**
```javascript
<!-- 引入Express框架 -->
var express = require('express')
<!-- 初始化 -->
var app = express()
var router = express.Router();
<!-- 引用路由 -->
var routes = require('./src/server/routes/index')

//配置静态资源地址
app.use(config.path.public,express.static(path.join(__dirname, 'dist')))
app.use(config.path.static,express.static(path.join(__dirname,'/src/client/assets/images')))
app.use(config.path.upload,express.static(path.resolve(config.path.file)))


// 没有挂载路径的中间件，应用的每个请求都会执行该中间件
app.use(function (req, res, next) {
  console.log('Time:', Date.now());
  next();
});

// 处理get请求
app.get('/', function (req, res, next) {
  cosole.log('http get')
})

// 将路由挂载至应用 
routes(app)

//报错
app.use(function(err,req, res, next) {
  res.status(404).send('请求错误,404');
})


<!-- 监听80端口 -->
app.listen(80,'0.0.0.0',function (err) {
  if (err) {
    console.error(err)
  } else {
    console.info('==> Listening on port %s. Open up http://localhost:%s/ in your browser.', config.port, config.port)
  }
})

```

###### 第三方中间

安装所需功能的 node 模块，并在应用中加载，可以在应用级加载，也可以在路由级加载。

**server.js**
```javascript
<!-- 引入Express框架 -->
var express = require('express')
<!-- 初始化 -->
var app = express()
var router = express.Router();
<!-- 引用路由 -->
var routes = require('./src/server/routes/index')
// 引入第三方中间件
var session = require('express-session')
var RedisStore = require('connect-redis')(session)
var cookieParser = require('cookie-parser')
var bodyParser = require('body-parser')


//配置静态资源地址
app.use(config.path.public,express.static(path.join(__dirname, 'dist')))
app.use(config.path.static,express.static(path.join(__dirname,'/src/client/assets/images')))
app.use(config.path.upload,express.static(path.resolve(config.path.file)))
<!--新增代码-->
//解析cookie和post请求参数
app.use(bodyParser.json())
app.use(bodyParser.urlencoded({extended: false}))
app.use(cookieParser())

//session配置
app.use(session({
  name: config.session.id,
  secret: config.session.secret,
  store: new RedisStore({client:redis}),
  resave:true,
  saveUninitialized:false,
  cookie:{maxAge:config.session.maxAge}
}))
<!-- 新增代码结束 -->


// 没有挂载路径的中间件，应用的每个请求都会执行该中间件
app.use(function (req, res, next) {
  console.log('Time:', Date.now());
  next();
});

// 处理get请求
app.get('/', function (req, res, next) {
  cosole.log('http get')
})

// 将路由挂载至应用 
routes(app)

//报错
app.use(function(err,req, res, next) {
  res.status(404).send('请求错误,404');
})



<!-- 监听80端口 -->
app.listen(80,'0.0.0.0',function (err) {
  if (err) {
    console.error(err)
  } else {
    console.info('==> Listening on port %s. Open up http://localhost:%s/ in your browser.', config.port, config.port)
  }
})
```
#### 3.3匹配路由(Node请求路由)

框架有两个路由，一个是用于匹配页面的路由，另一个是用于匹配Node的请求，Node的路由文件放在**src/routes**

**src/server/routes/index.js**
```javascript
module.exports = function (app) {
  app.use('/hello',require('./hello'))
}
```

**src/server/routes/hello.js**
```javascript
var express = require('express')
var router = express.Router()

router.get('/',function(req,res,next){
  res.send('hello world!')
})
```

Node路由还出现下面这种写法
```javascript
// src/server/routes/index.js
var hello = require('./hello')
module.exports = function (app) {
  hello(app)
}

// hello.js
module.exports = function (app) {
    app.get('/hello', function (req, res, next) {
        res.send('hello world!')
    })
}
```
两种写法都可以，主要区别是第一种调用路由级中间件，第二种方法调用应用级中间件，请求路由更推荐调用路由级中间件。

第二种写法目前只有材巴巴PC版项目中才有，实际开发中，按项目的情况下添加路由。

ajax请求的方法，统一放在 **/ajax**这个路由下面。

凡是调用JAVA接口的方法，统一添加前缀 **/api**，例如：
```javascript
app.use('/api/purchaser',require('./purchaser'))
```

#### 4.3请求数据，服务端渲染

当HTTP请求过来，中间件处理完之后，下一步进入渲染中间件（服务器渲染）中匹配页面路由，请求接口

**server.js**
```javascript
<!-- 引入Express框架 -->
var express = require('express')
<!-- 初始化 -->
var app = express()
var router = express.Router();
<!-- 引用路由 -->
var routes = require('./src/server/routes/index')
// 引入第三方中间件
var session = require('express-session')
var RedisStore = require('connect-redis')(session)
var cookieParser = require('cookie-parser')
var bodyParser = require('body-parser')

<!-- 新增代码 -->
// 引入服务器渲染方法
var severSide = require('./dist/server')
<!-- 新增结束 -->

//配置静态资源地址
app.use(config.path.public,express.static(path.join(__dirname, 'dist')))
app.use(config.path.static,express.static(path.join(__dirname,'/src/client/assets/images')))
app.use(config.path.upload,express.static(path.resolve(config.path.file)))
<!--新增代码-->
//解析cookie和表单
app.use(bodyParser.json())
app.use(bodyParser.urlencoded({extended: false}))
app.use(cookieParser())

//session配置
app.use(session({
  name: config.session.id,
  secret: config.session.secret,
  store: new RedisStore({client:redis}),
  resave:true,
  saveUninitialized:false,
  cookie:{maxAge:config.session.maxAge}
}))
<!-- 新增代码结束 -->


// 没有挂载路径的中间件，应用的每个请求都会执行该中间件
app.use(function (req, res, next) {
  console.log('Time:', Date.now());
  next();
});

// 处理get请求
app.get('/', function (req, res, next) {
  cosole.log('http get')
})

// 将路由挂载至应用 
routes(app)

<!-- 新增代码 -->
//react服务器渲染方法
app.get('*', function (req, res, next) {
  severSide.default(req, res,next)
})
<!-- 新增结束 -->



//报错
app.use(function(err,req, res, next) {
  res.status(404).send('请求错误,404');
})



<!-- 监听80端口 -->
app.listen(80,'0.0.0.0',function (err) {
  if (err) {
    console.error(err)
  } else {
    console.info('==> Listening on port %s. Open up http://localhost:%s/ in your browser.', config.port, config.port)
  }
})
```
**PS：引入的服务器渲染的方法是编译过后的代码，路径是/dist/server.js，如果要调试或者查看源码，入口文件的路径/src/server/server.js**

**src/server/server.js**文件做了匹配路由，循环匹配路由的请求数组，创建页面模板字符串，响应渲染好的页面字符串
```javascript
import React from 'react'
import { renderToString } from 'react-dom/server'
import { RouterContext, match, createMemoryHistory } from 'react-router'
import { Provider } from 'react-redux'
import configureStore from './../client/store/configureStore'
import routes from './../client/routes/index'
import config from '../../config/default'

async function fetchAllData(dispatch, components, params) {}
function renderFullPage(renderedContent, initialState,attributes) {}
export default function render(req, res,next) {}
```
**render方法**

匹配路由，发送请求，创建页面和响应请求都在这个方法里面


```
export default function render(req, res,next) {
  const history = createMemoryHistory()
  const store = configureStore({}, history)
  <!-- 页面路由和请求地址进行匹配 -->
  match({ routes:routes(), location: req.url }, (error, redirectLocation, renderProps) => {
    if (error) {
      res.status(500).send(error.message)
    } else if (redirectLocation) {
      res.redirect(302, redirectLocation.pathname + redirectLocation.search)
    } else if (renderProps) {
      <!-- session,query,cookie,路由地址参数放到params -->
      renderProps.params = {...renderProps.params,...renderProps.location.query}
      renderProps.params.req = true
      renderProps.params.session = req.session
      <!--调用匹配组件的fetchData方法-->
      return fetchAllData(store.dispatch, renderProps.components, renderProps.params)
        .then(html=>{
          const InitialView = (
            <Provider store={store}>
              <RouterContext {...renderProps} />
            </Provider>)
          const componentHTML = renderToString(InitialView)
          const initialState = store.getState()
          const attributes = getTitle(renderProps.routes)
          res.set('Content-Type', 'text/html')
          <!-- renderFullPage创建页面字符串,res.send响应回客户端 -->
          return res.status(200).send(renderFullPage(componentHTML, initialState,attributes))
        }).catch(err => {
          console.log(err)
          next()
        })
    } else {
      next()
    }
  })
}
```

## 常见问题

## 常用库介绍

## 学习资源和文档

## 笔记

1.前端URL->src/client/routers.js->根据URL找到匹配的组件

```xml
 import TestCom from './componets/TzxTest' 
 <Route path="/test" title="测试-材巴巴" component={TestCom} />
```

2.根据from进入组件中,引入框架、组件、对象、方法等

```java
import React, { Component} from 'react'/* 导入React框架 */
import PropTypes from 'prop-types'     /* 导入类型参数,有参数传入时必须导入 */
import { connect } from 'react-redux'   /* 导入绑定数据函数  */
import * as Actions from '../../action'  /* 导入方法对象，需要请求数据时导入 */
import TestHello from './testHello'        /* 引入组件*/
import {bindActionCreators} from 'redux'     /* 引入绑定方法*/
```

3.A引用B页面，如果B页面有需要调用者提供的数据，A要传递给它

```JAVA
A: <TestHello text={'HelloWord!'}/>
B: const {text}=this.props
        return (
            <div>testHello : {text}</div>
        );
```

4.调用Service,向接口前进

```javascript
import * as Actions from '../../action'  /* 导入方法对象，需要请求数据时导入 */	
```

 ①要能够通过Actions调用方法，需要在==src/client/action/index==下配置==testList()==所在的==src/client/action==路径

```java
export * from './testAction'
```

```java
// 调用对象方法
static fetchData(params){
        return [
            Actions.getSession(params),
            Actions.testList()
        ]
    }
```

 ②testAction里的==testList()== ,

```java
import * as types from '../constants/ActionType'
import testTzx from '../api/testTzx'

// 测试活动的list
export const testList = params =>{
    return (dispatch,getState) =>{
        return dispatch({
            type:types.TEST_LIST,
            promise:testTzx.list(params)
        })
    }
}
```

+ Action.方法名->进入方法体->调用service下方法->找到==api/==路径下的==service==的方法体
+ Action.方法名->找到==api/==路径下的==service==的方法体

5.调用接口，取出数据（==不知道为什么调用的不是这里，而是src/client/reducers下的，而且还要配置里面的index==）

```java
import {ProjectResource} from './resouce2'
export default {
    list: function (params) {
        return ProjectResource('get', '/api/activity/list', {
          params: params
        })
    }
}
```

6.配置type（==type:types.TEST_LIST==） ==src/client/constants/ActionType.js== 路径下配置

```java
//测试
export const TEST_LIST = 'TEST_LIST'
export const TEST_LIST_FAILURE = 'TEST_LIST_FAILURE'
export const TEST_LIST_REQUEST = 'TEST_LIST_REQUEST'
export const TEST_LIST_SUCCESS = 'TEST_LIST_SUCCESS'
```

7. ==src/client/reducers/testImpl.js== 配置

    

8. 新闻模块前后台结构

   Actions.newsList(）->src/client/action/news.js/newsList/(==感觉有点想from找到Controller==)      

   ```javascript
   /**
    * Created by Administrator on 2018/8/16.
    */
   import * as types from '../constants/ActionType'
   import testTzx from '../api/testTzx'
   
   // 测试活动的list
   export const testList = params =>{
       console.log("进入到第一层testList:进入到第一层testList:进入到第一层testList")
       return (dispatch,getState) =>{
           return dispatch({
               type:types.TEST_LIST, // 根据type去ActionType找匹配的方法
               promise:testTzx.list(params)
           })
       }
   }
   ```

    ->src/client/api/news.js/list(==调另一个中间层？？？应该也是一个service==) 

   ```javascript
   /**
    * Created by Administrator on 2018/8/16.
    */
   import {ProjectResource} from './resouce2'
   import testService from '../../server/api/testService'
   // 调用接口，取到数据
   export default {
       list: function (params) {
           console.log("进入到第二层list")
           if(true){
               console.log("返回第二层之后拿到的数据"+JSON.stringify(testService.list()))
               return testService.list()
           }else{
               return ProjectResource('get','/api/news/list',{
                   params: params
               })
           }
       }
   }
   ```

   

   ->src/server/api/newService.js/list (==有点Service层的feel==,调用接口处理数据信息，再返回给上一层)

   ->数据回调之后是会根据==type==对应的值找到==src/client/	reducere/testImpl.js/方法保存数据==

   ```java
   import {
       TEST_LIST_FAILURE,
       TEST_LIST_REQUEST,
       TEST_LIST_SUCCESS,
   } from '../constants/ActionType'  /*所有的type都放在ActionType里*/
   
   //新闻
   export const testList = createReducer(newsListState, {
       [TEST_LIST_REQUEST]: (state, action) => state.set('isFetching', true),
       [TEST_LIST_SUCCESS]: (state, action) => {
           return state.merge({
               isFetching: false,
               list: action.json.response.list,
               total: action.json.response.totalCount,
               retCode:action.json.retCode
           })
       },
       [TEST_LIST_FAILURE]: (state, action) => state.set('isFetching', false)
   })
   ```

9. 整个一后台过程走完之后，TzxTest/index.js里，设置==mapStateToProps== 以及==propTypes==

   ```javascript
    	const mapStateToProps = state =>{
       return {
           nodeSession:state.nodeSession.toJS(),
           testList:state.testList.toJS()
       }
   }
   ```

   ```java
       // propTypes 告诉是什么类型的数据
       static propTypes = {
           nodeSession:PropTypes.object.isRequired,
           actions: PropTypes.object.isRequired,
           testList:PropTypes.object.isRequired
       }
   ```

   ```javascript
   const {nodeSession,testList,actions} = this.props //把super以及propTypes的数据放进去
   // 引入组件，传值
   <Header session={nodeSession.session} hotFlag={'activeProA'} menuType={'purOrder'} />
                   <TestHello
                       text={'HelloWord_01 !'}
                       list={testList.list }
                   />
   ```

   

