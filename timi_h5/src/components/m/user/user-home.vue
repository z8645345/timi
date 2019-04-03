<template>
  <div>
    <div class="aui-tips aui-margin-b-15" id="tips-1" v-if="isShowMsg" style="position: fixed; top:0;">
      <i class="aui-iconfont aui-icon-info"></i>
      <router-link :to="{ name: 'chatroom'}" tag="div" class="aui-tips-title max-rows1" style="-webkit-box-orient: vertical; display: -webkit-box;" v-html="msg">
      </router-link>
      <i class="aui-iconfont aui-icon-close" tapmode @click="isShowMsg=false"></i>
    </div>
    <div v-if="isShow">
      <section class="aui-content user-info aui-text-center aui-margin-b-15">
        <img :src="timiUser.pic" class="avatar aui-img-round">
        <h2 class="aui-margin-t-15">{{ timiUser.nickname }}<small><i class="aui-iconfont aui-icon-cert aui-text-danger"></i></small></h2>
        <div class="aui-row aui-margin-t-15">
          <div class="aui-col-xs-12 aui-text-center aui-padded-r-10">
            爱婷宣言：{{ timiUser.loveTimiDeclaration}}
          </div>
        </div>
        <div class="aui-row aui-margin-t-15">
          <div class="aui-col-xs-12 aui-text-center aui-padded-r-10">
            个人简介：{{ timiUser.personalProfile}}
          </div>
        </div>
        <div class="aui-row aui-margin-t-15">
          <div class="aui-col-xs-6 aui-text-right aui-border-r aui-padded-r-10">
            <div class="aui-label aui-label-info">LV 6</div>
          </div>
          <div class="aui-col-xs-6 aui-text-left aui-padded-l-10">
            积分:0
          </div>
        </div>
        <div class="aui-row aui-margin-t-15">
          <div class="aui-col-xs-6 aui-text-right aui-border-r aui-padded-r-10">
            <span class="aui-text-danger aui-margin-r-5">0人</span>关注我
          </div>
          <div class="aui-col-xs-6 aui-text-left aui-padded-l-10">
            我关注<span class="aui-text-danger aui-margin-l-5">100人</span>
          </div>
        </div>
      </section>
      <section class="aui-content">
        <ul class="aui-list aui-list-noborder">
          <div class="aui-list-header">
            <div>
              <i class="aui-iconfont aui-icon-my aui-text-danger"></i>
              个人中心
            </div>
          </div>
          <li class="aui-list-item" style="background-color: #fff;">
            <div class="aui-list-item-inner aui-list-item-arrow">
              <div class="aui-list-item-title">个人资料</div>
              <div class="aui-list-item-right"><img :src="timiUser.pic" class="avatar aui-img-round" style="float: right"></div>
            </div>
          </li>
          <li class="aui-list-item" style="background-color: #fff;">
            <div class="aui-list-item-inner aui-list-item-arrow">
              <div class="aui-list-item-title">我的帖子</div>
              <div class="aui-list-item-right">共888贴</div>
            </div>
          </li>
          <li class="aui-list-item" style="background-color: #fff;">
            <div class="aui-list-item-inner aui-list-item-arrow">
              <div class="aui-list-item-title">我收藏的视频</div>
              <div class="aui-list-item-right">共888个</div>
            </div>
          </li>
        </ul>
        <ul class="aui-list ">
          <div class="aui-list-header aui-btn aui-btn-block aui-btn-danger">
            <div class="aui-col-xs-12 aui-text-center aui-padded-r-10 aui-list-item-title" @click="logout">
              退出登录
            </div>
          </div>
        </ul>
      </section>

      <!--底部工具栏开始-->
      <footer class="aui-bar aui-bar-tab" id="footer">
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
  </div>
</template>

<script>
  export default {
    name: 'user-home',
    data() {
      return {
        isShow: true,
        timiUser: {},
        msg: '',
        isShowMsg: false
      }
    },
    mounted: function(){
      this.GLOBAL.userHomeApp = this;
      var userToken = localStorage.getItem('userToken');
      var app = this;
      this.post('/timizhuo/user/isLogin', {userToken: userToken}, function (res) {
        if (res.data.code == '200') {;
          app.timiUser = res.data.data;
          app.isShow = true;
        } else {
          app.$router.push({name:'login'});
        }
      }, function (err) {
        toast.hide();
        app.errorAlert('系统异常');
      })
    },
    methods: {
      logout: function () {
        var app = this;
        this.post('/timizhuo/user/logout',{}, function (res) {
          debugger;
          if (res.data.code == '200') {;
            app.isShow = false;
            location.reload();
            // app.$router.push({name:'login'});
          }
        }, function (err) {
          app.errorAlert('系统异常');
        })
      }
    }
  }
</script>

<style scoped>
  .user-info {
    background-color: #ffffff;
    padding: 0.75rem 0;
  }
  img.avatar {
    display: block;
    margin: 0 auto;
    width: 20%;
  }
  .aui-list .aui-list-header {
    background-color: #ffffff;
    line-height: 1.2rem;
  }
  .aui-list .aui-list-item {
    background-color: #f8f6ef;
  }
</style>
