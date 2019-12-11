# we-share

## Install axios  
```$xslt
npm install axios --save-dev  
```

## Install Mavon Editor  
```$xslt
npm install mavon-editor --save  
```
see  
https://www.jianshu.com/p/04376d0c9ff1  
https://www.jianshu.com/p/474197c1f7cc  

## Install iView  
```$xslt
npm install view-design --save  
```
see https://www.iviewui.com/  

## Commit History  
* 12/9  
  * 创建基于vue.js的WebStorm项目，部分文件未引入  
  * 使用iView组件库，需install  
  * 添加了导航栏  
  * 添加了首页  
  * 添加了部分本地素材供使用  
* 12/10  
  * 增加了一个简陋的编辑页面  
    增加路由实现页面跳转  
  * 完善导航栏搜索类型功能  
    首页增加了标签页
  * 增加帖子页面  
  * 帖子页面增加问答区域  
    更新了部分图标  
    优化了首页标签的逻辑  
    优化了帖子页面布局  
  * 优化了无限滚动的加载方式，改为按钮触发  
  * 引入了mavon-editor的markdown编辑器，需install  
    禁用了编辑器部分按钮  
* 12/11  
  * 编辑器不再遮挡导航栏登录的次级菜单了  
    优化和修正了搜索逻辑  
    修改了部分按钮样式  
  * 准备使用axios  
    将帖子页面的问题列表拆分成单独的组件  
    修改问题列表样式  
    新增回答组件  
    更正了部分路由逻辑  

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).
