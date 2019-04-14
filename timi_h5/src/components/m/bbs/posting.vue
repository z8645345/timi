<template>
  <div>
    <div v-if="!isShowAt">
      <div style="margin-left: 0.5rem; margin-right: 0.5rem; ">
        <header style="width: 100%; margin-top: 0.5rem; margin-bottom: 0.5rem">
          <i @click="back" class="aui-iconfont aui-icon-close" style="position: fixed; top: 0.5rem; left: 0.5rem; font-size: 20px"></i>
          <div style="text-align: center; font-size: 16px">发布帖子到婷迷论坛</div>
          <div @click="push" style="position: fixed; top: 0.5rem; right: 0.5rem; font-size: 16px">发布</div>
        </header>
        <div>
          <input id="forumTitle" v-if="isShowTitle" type="text" placeholder="加个标题哟~" v-model="forum.forumTitle">
          <div id="context" contenteditable="true" style="height: auto; min-height: 5rem" @focus="hideDefault" @blur="showDefauls">
            <span v-if="isShowDefauls" style="position: absolute ;color: rgb(217, 217, 217)">来吧，分享你与依婷的故事…</span>
          </div>
          <div class="aui-card-list-content">
            <div class="aui-row aui-row-padded">
              <div v-for="img in imgs" class="aui-col-xs-4" style="height: 115px">
                <img class="img-list" :src="img">
              </div>
              <div class="aui-col-xs-4" @click="addImg">
                <img src="http://timizhuo.cn/add_img.png">
              </div>
            </div>
          </div>
          <input type="file" id="change" accept="image" @change="change" style="display: none">

        </div>
      </div>
      <div class="aui-bar-tab" style="width: 100%; height: 2rem; line-height: 2rem">
      <span @click="showTitle" style="margin-left: 1rem; font-size: 1rem; padding-left: 0.5rem;padding-right: 0.5rem;">{{titleTip}}</span>
      <span style="margin-left: 1rem; font-size: 1rem; padding-left: 0.5rem;padding-right: 0.5rem">#</span>
      <span @click="at" style="margin-left: 1rem; font-size: 1rem; padding-left: 0.5rem;padding-right: 0.5rem">@</span>
    </div>
    </div>
    <div v-else>
      <header class="aui-bar aui-bar-nav">
        <a class="aui-pull-left aui-btn" @click="hideUserList">
          <span>
          取消
          </span>
        </a>
        <div class="aui-title">
          <span>选择提醒人</span>
        </div>
      </header>
      <header class="aui-bar aui-bar-nav aui-bar-light">
        <div class="aui-title" style="left:0.2rem; right: 0.2rem;">
          <div class="aui-searchbar" id="search" style="background-color: #fff;">
            <div class="aui-searchbar-input aui-border-radius" style="background-color: rgb(243, 243, 243);">
              <i class="aui-iconfont aui-icon-search"></i>
              <input type="search" placeholder="请输入搜索人昵称" id="search-input" @input="searchChange" v-model="searchNickName">
              <div class="aui-searchbar-clear-btn">
                <i class="aui-iconfont aui-icon-close"></i>
              </div>
            </div>
            <div class="aui-searchbar-btn" tapmode>取消</div>
          </div>
        </div>
      </header>

      <ul class="aui-list aui-media-list">
        <li class="aui-list-item" v-for="timiUser in userList">
          <div class="aui-card-list-header aui-card-list-user">
            <div class="aui-card-list-user-avatar">
              <img :src="timiUser.pic" class="aui-img-round" style="vertical-align: middle">
            </div>
            <div style="height: 40px; line-height: 40px"><span class="aui-label aui-label-warning">超级婷迷</span> {{timiUser.nickname}}</div>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'posting',
    data () {
      return {
        isShowDefauls: true,
        picValue: '',
        url: '',
        imgs: [
        ],
        isShowTitle: false,
        titleTip: '添加标题',
        forum: {
          forumTitle: '',
          forumContent: '',
          imageUrl: '',
          friendIds: ''
        },
        userList: [],
        isShowAt: false,
        searchNickName: '',
      }
    },
    methods: {
      hideDefault: function () {
        this.isShowDefauls = false;
      },
      showDefauls: function () {
        var context = document.getElementById("context").innerText;
        if (context == null || context == '') {
          this.isShowDefauls = true;
        }
      },
      addImg: function () {
        document.getElementById('change').click();
      },
      change (e) {
        let files = e.target.files || e.dataTransfer.files;
        if (!files.length) return;
        this.loading("上传中……");
        this.picValue = files[0];
        var a = new FileReader();
        this.uploadImg();
      },
      getObjectURL (file) {
        var url = null ;
        if (window.createObjectURL!=undefined) { // basic
          url = window.createObjectURL(file) ;
        } else if (window.URL!=undefined) { // mozilla(firefox)
          url = window.URL.createObjectURL(file) ;
        } else if (window.webkitURL!=undefined) { // webkit or chrome
          url = window.webkitURL.createObjectURL(file) ;
        }
        return url ;
      },
      showTitle: function () {
        if (this.isShowTitle) {
          this.isShowTitle = false;
          this.titleTip = '添加标题';
        } else {
          this.isShowTitle = true;
          this.titleTip = '取消标题';
          setTimeout(()=>document.getElementById("forumTitle").focus(), 10);
        }
      },
      push: function () {
        this.forum.forumContent = document.getElementById("context").innerHTML;
        var imageUrl = '';
        this.imgs.forEach((img)=> {
          imageUrl += img + ',';
        });
        this.forum.imageUrl = imageUrl.substring(0, imageUrl.length - 1);
        var app = this;
        this.loading("正在发布……")
        var toast = new auiToast();
        app.post('/timizhuo/forum/addForum', this.forum, function (res) {
          if (res.data.code == '200') {
            var dialog = new auiDialog();
            dialog.alert({
              title: "提示",
              msg: "发布成功",
              buttons:['确定']
            },function(ret){
              app.$router.push({name:'bbs'});
            })
          } else {
            var dialog = new auiDialog();
            dialog.alert({
              title: "提示",
              msg: res.data.message,
              buttons:['确定']
            },function(ret){
              app.$router.push({name:'login'});
            })
          }
          toast.hide();
        }, function (err) {
          toast.hide();
        });
      },
      uploadImg: function () {
        var app = this;
        lrz(this.picValue, {
          width: 800 //设置压缩参数
        }).then(function (rst) {
          /* 处理成功后执行 */
          rst.formData.append('base64img', rst.base64); // 添加额外参数
          var data = {
            base64: rst.base64,
            namespaceType: 2
          }
          var toast = new auiToast();
          app.post('/timizhuo/qiniu/uploadImg', data, function (res) {
            if (res.data.code == '200') {
              app.imgs.push(res.data.data);
            }
            toast.hide();
          }, function (err) {
            toast.hide();
          });
        }).catch(function (err) {
          /* 处理失败后执行 */
        }).always(function () {
          /* 必然执行 */
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
      back: function () {
        this.$router.back(-1);
      },
      at: function () {
        var app = this;
        app.post('/timizhuo/fans/followList', {}, function (res) {
          if (res.data.code == '200') {
            app.isShowAt = true;
            setTimeout(function(){
              app.initSearch();
            }, 1)
          } else {
            var dialog = new auiDialog();
            dialog.alert({
              title: "提示",
              msg: res.data.message,
              buttons:['确定']
            },function(ret){
              app.$router.push({name:'login'});
            })
          }
        }, function (err) {
        });
      },
      initSearch: function () {
        // 顶部搜索栏开始
        var apiready = function(){
          api.parseTapmode();
        }
        var searchBar = document.querySelector(".aui-searchbar");
        var searchBarInput = document.querySelector(".aui-searchbar input");
        var searchBarBtn = document.querySelector(".aui-searchbar .aui-searchbar-btn");
        var searchBarClearBtn = document.querySelector(".aui-searchbar .aui-searchbar-clear-btn");
        if(searchBar){
          searchBarInput.onclick = function(){
            searchBarBtn.style.marginRight = 0;
          }
        }
        searchBarClearBtn.onclick = function(){
          this.style.display = 'none';
          searchBarInput.value = '';
          searchBarBtn.classList.remove("aui-text-info");
          searchBarBtn.textContent = "取消";
        }
        searchBarBtn.onclick = function(){
          this.style.marginRight = "-"+this.offsetWidth+"px";
          searchBarInput.value = '';
          searchBarInput.blur();
        }
        // 顶部搜索栏结束
      },
      hideUserList: function () {
        this.isShowAt = false;
      },
      searchChange: function () {
        var searchBarBtn = document.querySelector(".aui-searchbar .aui-searchbar-btn");
        var searchBarClearBtn = document.querySelector(".aui-searchbar .aui-searchbar-clear-btn");
        searchBarClearBtn.style.display = 'none';
        searchBarBtn.classList.remove("aui-text-info");
        searchBarBtn.textContent = "取消";

        var users = [];
        for(var i = 0; i < this.userList.length; i ++) {
          var timiUser = this.onlineUserList[i];
          if (timiUser.nickname.indexOf(this.searchNickName) != -1) {
            users.push(timiUser);
          }
        }
        this.userList = users;
      },
    }
  }
</script>

<style scoped>
  .img-list {
    width:100%;
    height: 100%;
    object-fit: cover;
  }
</style>
