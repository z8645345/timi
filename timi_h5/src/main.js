// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import Qs from 'qs'
import global_ from './components/common/Global'
// import VueTouch from 'vue-touch'
// Vue.use(VueTouch, {name: 'v-touch'})
// VueTouch.config.swipe = {
//   threshold: 100 //手指左右滑动距离
// }

import './assets/aui/css/aui.css'
import './assets/aui/css/aui-slide.css'
import './assets/aui/css/aui-pull-refresh.css'

import $ from 'jquery'
import './assets/aui/script/aui-slide'
import './assets/aui/script/api'
import './assets/aui/script/aui-toast'
import './assets/aui/script/aui-actionsheet'
import './assets/aui/script/aui-tab'
import './assets/aui/script/aui-dialog'
import './assets/aui/script/aui-pull-refresh'
import './assets/aui/script/aui-scroll'

import './assets/js/qiniu.min'
import './assets/js/tiows'

import '../node_modules/lrz/dist/lrz.all.bundle'

// import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
// import '../node_modules/bootstrap/dist/js/bootstrap.min.js'

Vue.config.productionTip = false
Vue.prototype.axios = axios
Vue.prototype.qs = Qs
Vue.prototype.GLOBAL = global_

/* eslint-disable no-new */
var app = new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})

Vue.prototype.post = function (url, data, successCall, errorCall){
  var usetToken =localStorage.getItem('userToken');
  this.axios.post(this.GLOBAL.serviceHost + url,this.qs.stringify(data),{
    headers: {
      'token': usetToken
    }
  })
    .then(function(res){
      successCall(res);
    }.bind(this))
    .catch(function(err){
      if(err.response) {
        console.log(err.response)
        //控制台打印错误返回的内容
      }
      errorCall(err);
    }.bind(this));
}

Vue.prototype.isLogin = function (){
  var timiUser = null;
  var userToken = localStorage.getItem('userToken');
  var app = this;
  $.ajax({
    url : app.GLOBAL.serviceHost + '/timizhuo/user/isLogin',
    type : 'post',
    async: false,//使用同步的方式,true为异步方式
    // data : {userToken: userToken},
    beforeSend: function(request) {
      request.setRequestHeader("token",userToken);
    },
    success : function(data){
      if (data.code == '200') {
        timiUser = data.data;
      }
    },
    fail:function(){
    }
  });
  return timiUser;
}
