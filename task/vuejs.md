---
layout: post
title: vuejs笔记记录
categories: js
---

### 动态Prop

类似于用`v-bind`绑定HTML特性到一个表达式, 也可以用`v-bind`动态绑定props的值到父组件的数据中. 每当父组件数据变化时, 该变化也会传到给子组件:



### 动态组件

多个组件可以使用同一个挂载点, 然后动态地在他们之间切换,. 使用保留的`<component>`元素, 动态地绑定到它的`is`特性:

```javascript
var vm = new Vue({
  el: '#app',
  data: {
    currentView: 'home'
  },
  components: {
    home: {},
    posts: {}
  }
})
```

```html
<component :is="currentView">
  <!-- 组件在vm.currentview变化时变化 -->
</component>
```

如果把切换出去的组件保留在内存中, 可以保留它的状态或避免被重新渲染. 为此可以添加一个`keep-alive`指令参数:

```html
<keep-alive>
  <component :is="currentView">
    <!-- 非活动组件将被缓存 -->
  </component>
</keep-alive>
```

### 编写可复用组件

在编写组件是, 记住是否要复用组件有好处. 一次性组件跟其他组件紧密耦合没关系, 但是可复用组件应当定义一个清晰的公开接口.

vue组件的api来自三部分`props, events和slots`:

* props允许外部环境传递数据给组件
* Events允许组件触发外部环境的副作用
* slots允许外部环境将额外的内容组合在组件中.

使用`v-bind`和`v-on`的简写语法, 模板的缩进清楚且简介:

```html
<my-component
              :foo="baz"
              :bar="quz"
              @event-a="doThis"
              @event-b="doThat">
  <img slot="icon" src=""/>
  <p slot="main-text">hello!</p>
</my-component>
```

### 子组件索引

尽管有props和events, 但是有时任然需要在JavaScript中直接访问子组件. 为此可以使用ref为子组件制定一个索引Id. 列如:

```html
<div id="parent">
  <user-profile ref="profile"></user-profile>
</div>
```

```javascript
var parent = new Vue({el: "#parent"})
var child = parent.$refs.profile
```

当`ref`和`v-for`一起使用时, ref是一个数组或对象, 包含相应的子组件.

> `$refs`只在组件渲染完成后才填充, 并且它是非相应式的.它仅仅作为一个直接访问子组件的应急方案,-- 应当避免在模板或计算属性中使用`$refs`

### 异步组件

在大型应用中, 我们可能需要将应用拆分为多个小模块, 如需从服务器下载. 为了让事情更简单, vue.js允许将组件定义为一个工厂函数, 动态地解析组件的定义. vuejs 只在组件需要渲染时触发工厂函数, 并且把结果缓存起来, 用于后面的再次渲染.列如:

```javascript
Vue.vomponent('async-example', function(resolve, reject){
  setTimeOut(function(){
    resolve({
      template: "<div>I am async</div>"
    })
  }, 1000)
});
```

工厂函数接受一个`resolve`回调, 在收到从服务器下载的组件定义时调用. 也可以调用`reject(reason)`指示加载失败. 这里`setTimeout` 只是为了演示, 怎么获取组件完全由你决定. 推荐配合使用: webpack的代码分割功能:

```javascript
Vue.component('async-webpack-example', function(resolve){
  // 这个特殊的require 语法告诉webpack
  // ....
  require(['./my-async-componet'], resolve)
})
```

[参考](https://cn.vuejs.org/v2/guide/components.html#异步组件)

### 组件名约定

当注册组件(或者props)时, 可以使用kebab-case, camelCase, 或TitleCase. Vue不关心这个

```javascript
// 在组件定义中
compoents: {
  // 使用kebab-case形式注册
  'kebab-cased-compoent':{},
  'camelCasedComponent':{}
  // .....
}
```

在HTMl中模板中, 请使用kebab-case形式:

```html
<!-- 在HTMl模板中始终使用 kebab-case -->
<kebab-cased-component></kebab-cased-component>
<camel-cased-component></camel-cased-component>
```

当使用字符串模板是, 可以不受html的caseInsenstive限制. 这意味着在实际上的模板中, 你可以使用camleCase, TitleCase或者kebab-case来应用. 如果组件未经`slot`元素传递内容, 你甚至可以再组件名后使用`/`使其自闭合.当然, 这只在字符串模板中有效. 因为自闭的自定义元素是无效的HTML, 浏览器原生的解析器也无法识别它.

### 递归组件

组件在他的模板内可以递归地调用自己, 不过, 只当它有name 选项是才可以. 当你利用`Vue.component`全局注册了一个组件, 全局的id作为组件的`name`选项, 被自动设置. 如果你不谨慎, 递归组件可能导致死循环:

```javascript
name: 'stack-overflow',
template: '<div><stack-overflow></stack-overflow></div>'
```

上面组件会导致一个错误'max stack size exceeded', 所以要确保递归调用有终止条件(比如递归调用时使用`v-if`并让他最终返回`false`)

### 内联模板

如果子组件有inline-template特性, 组件将把它的内容当做它的模板, 而不是把它当做分发内容. 这让模板更灵活.

```html
<my-component inline-template>
  <div>
    <p>hteljsdlfjlsdjf</p>
  </div>
</my-component>
```

但是inline-template让模板的作用域难以理解. 最佳时间是使用template 选项在组件内定义模板或者在`.vue`文件中使用`template`元素.

### X-Templates

另一种定义模板的方式是在JavaScript标签里使用`text/x-template`类型, 并且指定一个id. 列如:

```html
<script type="text/x-template" id="hello-world-template">
	<p> Heloooo</p>
</script>
```

```javascript
Vue.component('hello-world', {
  template: '#hello-world-template'
})
```

这在又很多模板或者小的应用中有应用, 否则应该避免使用, 因为它将模板和组件的其他定义隔离了.

### 使用`v-once`的低级静态组件

尽管在vue中渲染html很快, 不过当组件包含大量静态内容时, 可以考虑使用`v-once`将渲染结果缓存起来, 就像这样

```javascript
Vue.component('terms-of-service', {
  template: '\
	<div v-once>\
		<h1>Terms of service</h1> \
		sldfjlsdjlfjlsjfldjslf..... \
	</div>
  '
})
```

