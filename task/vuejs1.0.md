### 可复用的过渡

过渡可以通过Vue的组件系统实现复用. 要创建一个可复用过渡组件, 你需要做的及时将`<transiton>`或者`<transition-group>` 作为跟组件, 然后将任何子组件放置在其中就可以了.

使用template的简单例子:

```javascript
Vue.component('my-special-transtion', {
  template: ' \ 
  <transtion \
  name="very-special-transition" \
  mode="out-in" \
  v-on:before-enter="beforeEnter" \
  v-on:after-enter="afterEnter"> \
  <slot></slot> \
  </transtion> \
  '
  methods: {
    beforeEnter: function(el){
      // ...
    },
    afterEnter: function(el){
      // ...
    }
  }
})
```

函数组件更适合完成这个任务:

```javascript
Vue.component('my-special-transition', {
  functional: true,
  render: function(createElement, context){
    var data = {
      props: {
        name: 'very-special-transition',
        mode: 'out-in'
      },
      on:{
        beforeEnter: function(el){
          // 
        },
        afterEnter: function(el){
          //
        }
      }
    },
    return createElement('transition', data, context.children)
  }
})
```

### 动态过渡

在Vue中即使是过渡也是数据驱动的! 动态过渡的最基本例子是通过`name`特性来绑定动态值.

```javascript
<transition v-bind:name="transitionName">
  <!-- .... -->
</transition>
```

当你想用Vue的过渡系统来定义的css过渡/动画在不同的过渡间切换非常有用.

所有过渡特性都是动态绑定. 它不仅是简单的特性, 通过实践的钩子函数方法, 可以再获取到相应上下文数据. 这意味着, 可以根据组件的状态通过javascript过渡设置不同的过渡效果.

```HTMl
<script src="..../velocity.min.js"></script>
<div id="dynamic-fade-demo">
  Fade In: <input type="range" v-mode="fadeInDuration" min="0" v-bind:max="maxFadeDuration">
  Fade Out: <input type="range" v-mode="fadeOutDuration" min="0" v-bind:max="maxFadeDuration">
  <transition
        v-bind:css="false"
        v-on:before-enter="beforeEnter"
        v-on:enter="enter"
        v-on:leave="leave">
    <p v-if="show">hello</p>
  </transition>
  <button v-on:click="stop = true">stop it</button>
</div>
```

```javascript
new Vue(
  el: '#dynamic-fade-demo',
  data: {
    show: true,
    fadeInDuration: 1000,
    fadeOutDuration: 1000,
    maxFadeDuration: 1500,
    stop: false
  },
  mounted: function(){
    this.show = false
  },
  methods: {
    beforeEnter: function(el){
      el.style.opacity = 0
    },
    enter: function(el, done){
      var vm = this
      Velocity(el,
        {opacity: 1},
        {
          duration: this.fadeInduration,
          complete: function(){
            done()
            if(!vm.stop) vm.show = false
          }
        }
      )
    },
    leave: function(el, done){
      var vm = this
      Velocity(el,
         {opacity:0},
         {
           duration: this.fadeOutDuration,
           complete: function(){
             done()
             vm.show = true
           }
         }
      )
    }
  }
)
```

最后, 创建动态过渡, 的最终方案是组件通过接受props 来动态修改之前的过渡.

### 过渡状态

vue的过渡系统提供了非常多简单的方法设置进入、离开和列表的动效。 那么对于元数据本身的动效呢

* 数字和运算
* 颜色的显示
* svg节点的位置
* 元素的大小和其他的属性

所有的原始数字都被事先存储起来， 可以直接转换到数字。 做到这一步， 我们就可以结合vue的响应式和组件系统， 使用第三方库来实现切换元素的过渡状态。

---

### 状态动画与watcher

通过watcher我们能监听到任何数值属性的更新。 可能听起来很抽象， 所以让我们先来看看使用tweenjs一个列子：

```html
<script src=".....tween.js@16.3.4"></script>
<div id="aniated-number-demo">
  <input v-mode.number="number" type="number" step="20">
  <p>
    {{animatedNumber}}
  </p>
</div>
```

```javascript
new Vue({
 el:'#animated-number-demo',
  data:{
    number: 0,
    animatedNumber: 0
  },
  watch:{
    number:function(newValue, oldValue){
      var vm = this
      function animate(time){
        requestAnimationFrame(animate)
        TWEEN.update(time)
      }
      new TWEEN.Tween({TweeningNumber:oldValue})
        .easing(TWEEN.EASing.Quadratic.Out)
        .to({tweeningNumber:newValue}, 500)
        .onUpdate(function(){
          vm.animatedNumber= this.tweeningNumber.toFixed(0)
        }).start()
      animate()
    }
  }
})
```

### 动态状态转换

就像vue的过渡组件一样， 数据背后状态转换会实时更新， 这对于原型设计十分有用。 当你修改一些变量， 即使是一个简单的svg多边形也可以事先很多难以想象的效果。[demo](https://jsfiddle.net/chrisvfritz/65gLu2b6/)

### 钩子函数

指令定义函数提供了几个钩子函数(可选):

* `bind`: 只调用一次, 指令第一次绑定好元素时调用, 用这个钩子函数可以定义一个在绑定时执行一次的初始化操作.
* `inserted`: 被绑定元素插入父节点时调用(父节点存在即可调用, 不必存在于document中).
* `update`: 被绑定元素所在的模板更新时调用, 而不论绑定值是否变化. 通过比较更新前后的绑定值, 可以忽略不必要的模板更新(详细的钩子函数参数见下)
* `componentUpdated`: 被绑定元素所在模板完成一次更新周期时调用
* `unbind`: 只调用一次, 指令与元素解绑时调用

### 钩子函数参数

钩子函数被赋予一下参数:

* **el**: 指令绑定的元素, 可以用来直接操作DOM.
* **binding**: 一个对象, 包含一下属性:
  * **name**: 指令名, 不包括`v-`前缀
  * **value**: 指令的绑定值, 例如`v-my-directiv="1 + 1"`, value的值是`2`
  * **oldValue**: 指令绑定的前一个值, 尽在`update`和`componentUpdate`钩子中可以用. 无论值是否改变都可用
  * **expression**: 绑定值得字符串形式. 例如`v-my-directive= "1 + 1"`, expression的值是`1 + 1`
  * **arg**: 传给指令的参数. 列如`v-my-directive:foo`, arg的值是"foo"
  * **modifiers**: 一个包含修饰符的对象. 例如: `v-my-directive.foo.bar`, 修饰符对象modifiers的值是`{foo:true, bar:true}`
  * **vnode**: Vue编译生成的虚拟节点, [Vnode接口](https://cn.vuejs.org/v2/api/#VNode接口)
  * **oldVnode**: 上一个虚拟节点, 仅在`update`和`componentUpdate`钩子中可用

> 除了`el`之外, 其他参数都应该是只读的, 尽量不要修改他们. 如果需要在钩子之间共享数据, 建议通过元素的dataset来进行.

一个使用了这些参数的自定义钩子样例:

```html
<div id="hook-arguments-example" v-demo:hello.a.b="message"></div>
```

```javascript
Vue.directive('demo', {
  bind: function(el, binding, vnode){
    vars = JSON.stringify
    el.innerHTML = 
      'name: ' + s(binding.name) + '<br>' +
      'value: ' + s(binding.value) + '<br>' +
      'expression: ' + s(binding.arg) + '<br>' +
      'modifiers: ' + s(binding.modifiers) + '<br>' +
      'vnode keys: ' + Object.keys(vnode).join(' ,')
  }
})
new Vue({
  el: '#hook-arguments-example',
  data: {
    message: 'hello'
  }
})
```

### 函数简写

大多数情况下, 我们可能想在`bind`和`update`钩子上做重复动作, 并且不关心其他的钩子函数.

```javascript
Vue.directive('color-swatch', function(el, binding){
  el.style.backgroundColor = binding.value
})
```

### 对象字面量

如果指令需要多个值, 可以传入一个JavaScript对象字面量. 记住指令函数能够接受所有合法类型的JavaScript表达式.

```HTML
<div v-demo="{color: 'white', text: 'hello'}">
</div>
```

```javascript
Vue.directive('demo',function(el, binding){
  console.log(binding.value.color)
  console.log(binding.value.text)
})
```

### 混合

> 混合是一种灵活的分布式复用Vue组件的方式. 混合对象可以包含任意组件选项. 以组件使用混合对象时, 所有混合对象的选项将被混入该组件本身的选项.

列子:

```javascript
//定义一个混合对象
var myMixin = {
  created: function(){
    this.hello()
  },
  methods: {
    hello: function(){
      console.log('hello from mixin!')
    }
  }
}

//定义一个使用混合对象的组件
var Component = Vue.extend({
  mixins: [myMixin]
})
var component = new Component(); // hello from mixin

```

#### 选项合并

当组件和混合对象含有同名选项时, 这些选项将以恰当的方式混合. 比如, 同名钩子函数将混合为一个数组, 因此都将被调用. 另外, 混合对象的钩子将在组件自身钩子**之前**调用:

值为对象的选项时, 例如`methods`, `components`和`directives`, 将被混合为同一个对象. 两个对象键名冲突时, 取组件对象的键值对.

```javascript
var mixin = {
  methods: {
    foo: function(){
      console.log('foo')
    },
    conflicting: function(){
      console.log('from mixin')
    }
  }
}
var vm = new Vue({
  mixins: [mixin],
  methods: {
    bar: function(){
      console.log('bar')
    },
    confliction: function(){
      console.log('from self')
    }
  }
})
vm.foo()
vm.bar()
vm.conflicting() //from self
```

### 全局混合

也可以全局注册混合对象。请注意使用！一旦使用全局混合对象， 将会影响到所有之后创建的Vue实例。 使用恰当是， 可以为自定对象注入处理逻辑

```javascript
Vue.mixin({
  created:function(){
    var myOption = this.$options.myOption
    if(myOption){
      console.log(myOption);
    }
  }
})
```

### 自定义选项混合策略

自定义选项将使用默认策略，既简单地覆盖已有值。如果想让自定义选项以自定义逻辑混合， 可以想`Vue.config.optionMergeStrategies`添加一个函数：

```javascript
Vue.config.optionMergeStrategies.myOption = function (toVal, fromVal){
  // return mergedVal
}
```

 对于大多数对象选项， 可以使用`methods`的合并策略：

```javascript
var strategies = Vue.config.optionMergeStrategies
strategies.myOption = strategies.methods
```

### 插件

插件通常会Vue添加全局功能。 插件的范围没有限制， 一般有以下几种：

1. 添加全局方法或者属性， 如vue-element
2. 添加全局资源： 指令/过滤器/过渡等， 如vue-touch
3. 通过全局mixin添加一些组件选项， 如：vuex
4. 添加Vue实例方法， 通过把他们添加到Vue.prototype上实现。
5. 一个库， 提供自己的API， 同时提供上面提到的一个或多个功能， 如vue-router

Vue.js的插件应当有一个公开方法`install`. 这个方法的第一个参数是Vue构造器， 第二个参数是一个可选的选项对象：

```javascript
MyPlugin.install = function(Vue, options){
  //1. 添加全局方法或属性
  Vue.myGlobalMethod = function(){
    
  }
  // 2. 添加全局资源
  Vue.directive('my-directive', {
    bind(el, binding, vnode, oldVnode){}
  })
  Vue.mixin({
    created: function(){
      
    }
  })
  // 添加实例方法
  Vue.prototype.$myMethod = function(options){}
}
```

### 使用插件

通过全局方法Vue.use()使用插件

```javascript
Vue.use(MyPlugin)
//传入一个选项对象
Vue.use(MyPlugin, {someOption:true})
```

`Vue.use`会自动组织注册相同插件多次， 届时只会注册一次该插件。

一些插件， 如`vue-router`, 如果Vue是全局变量则自动调用`Vue.use`. 不过在模块环境中应当始终显示调用`Vue.use`

### 单文件组件







