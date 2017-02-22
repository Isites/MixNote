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

