<template>
  <div>
    <header class="aui-bar aui-bar-nav nav-title">
      <div class="aui-title">婷迷会员登录</div>
    </header>
    <div class="aui-content">
      <img id="img" src="http://pkkwm1pvb.bkt.clouddn.com/logo2.png" alt="" style="width: 100%">
      <ul class="aui-list aui-form-list">
        <li class="aui-list-item">
          <div class="aui-list-item-inner">
            <div class="aui-list-item-label">
              用户名
            </div>
            <div class="aui-list-item-input">
              <input type="text" id="pic" style="display: none">
              <input type="text" v-model="timiUser.username" placeholder="注册时候的邮箱" @focus="hidefooter" @blur="showfooter">
            </div>
          </div>
        </li>
        <li class="aui-list-item">
          <div class="aui-list-item-inner">
            <div class="aui-list-item-label">
              密码
            </div>
            <div class="aui-list-item-input">
              <input type="password" v-model="timiUser.password" placeholder="请输入密码" @focus="hidefooter" @blur="showfooter">
            </div>
          </div>
        </li>
        <li class="aui-list-item">
          <div class="aui-list-item-inner aui-list-item-center aui-list-item-btn">
            <div @click="login" class="aui-btn aui-btn-info aui-margin-r-5" style="width: 5rem;height: 2rem; line-height: 2rem">登录</div>
            <router-link :to="{ name: 'reigst'}" tag="div" class="aui-btn aui-btn-danger aui-margin-l-5" style="width: 5rem;height: 2rem; line-height: 2rem">注册</router-link>
          </div>
        </li>
      </ul>
    </div>

    <!--底部工具栏开始-->
    <footer class="aui-bar aui-bar-tab" :class="hidefooter" id="footer">
      <router-link :to="{ name: 'home'}" tag="div" class="aui-bar-tab-item" tapmode>
        <i class="aui-iconfont aui-icon-home"></i>
        <div class="aui-bar-tab-label">首页</div>
      </router-link>
      <router-link :to="{ name: 'showList'}" tag="div" class="aui-bar-tab-item" tapmode>
        <i class="aui-iconfont aui-icon-calendar"></i>
        <div class="aui-bar-tab-label">演出活动</div>
      </router-link>
      <router-link :to="{ name: 'bbs'}" tag="div" class="aui-bar-tab-item" tapmode>
        <div class="aui-dot"></div>
        <i class="aui-iconfont aui-icon-comment"></i>
        <div class="aui-bar-tab-label">婷迷论坛</div>
      </router-link>
      <div class="aui-bar-tab-item aui-active" tapmode>
        <div class="aui-dot"></div>
        <i class="aui-iconfont aui-icon-my"></i>
        <div class="aui-bar-tab-label">我的</div>
      </div>
    </footer>
    <!--底部工具类结束-->
  </div>
</template>

<script>
  export default {
    name: 'login',
    data () {
      return {
        timiUser: {
          username: '',
          password: ''
        },
        hidefooter: '',
        clientHeight:0
      }
    },
    mounted: function() {
      var winHeight = $(window).height();
      $(window).resize(function() {
        var thisHeight = $(this).height();
        var keyboardHeight = thisHeight - winHeight;
        $(".aui-bar-tab").css({ 'bottom': keyboardHeight + 'px' });
      });
    },
    methods: {
      login: function () {
        this.loading("登录中……")
        var toast = new auiToast();
        var app = this;
        this.post('/timizhuo/user/login', this.timiUser, function (res) {
          if (res.data.code == '200') {;
            localStorage.setItem('userToken', res.data.data.token);
            app.$router.push({name:'userHome'});
          } else {
            app.errorAlert(res.data.message);
          }
          toast.hide();
        }, function (err) {
          toast.hide();
          app.errorAlert('登录失败');
        })
        // this.loading("登录中……")
        // var toast = new auiToast();
        // this.axios.post(this.GLOBAL.serviceHost + '/timizhuo/user/login', this.qs.stringify(this.timiUser), {})
        //   .then(function (res) {
        //     if (res.data.code == '200') {;
        //       localStorage.setItem('userToken', res.data.data.token);
        //       alert(localStorage.getItem('userToken'));
        //       this.$router.push({name:'userHome'});
        //     } else {
        //       this.errorAlert(res.data.message);
        //     }
        //     toast.hide();
        //   }.bind(this))
        //   .catch(function (err) {
        //     if (err.response) {
        //       console.log(err.response)
        //       //控制台打印错误返回的内容
        //     }
        //     toast.hide();
        //     //bind(this)可以不用
        //   }.bind(this));
      },
      errorAlert: function (msg) {
        var dialog = new auiDialog();
        dialog.alert({
          title: "提示",
          msg: msg,
          buttons: ['确定']
        });
      },
      loading: function (msg) {
        var toast = new auiToast();
        toast.loading({
          title:msg,
          duration:2000
        },function(ret){
          console.log(ret);
        });
      },
      hidefooter: function () {
        this.hidefooter = 'hide-footer';
      },
      showfooter: function () {
        this.hidefooter = '';
      }
    }
  }
</script>

<style scoped>
  .hide-footer {
    position: static;
  }
</style>
