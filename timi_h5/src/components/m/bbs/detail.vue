<template>
  <div>
    <div v-if="!isShowAt">
      <div v-if="!isReplyUI">
        <div style="height: 1.6rem;
      border-bottom: #F5F5F5 1px solid;
      background-color: #fff;
      color: #333333;
      padding-left: 0.5rem;
      padding-right: 0.5rem;
      line-height: 1.6rem;
      position: fixed;
      top: 0; width: 100%">
          <router-link :to="{ name: 'home'}" tag="i" v-if="source=='share'" class="aui-iconfont aui-icon-home" style="float: left; font-size: 1rem;"></router-link>
          <i v-else @click="goback()" class="aui-iconfont aui-icon-left" style="float: left; font-size: 1rem;"></i>
          <i @click="openActionsheet" class="aui-iconfont aui-icon-share" style="float: right; font-size: 1rem;"></i>
        </div>
        <div style="padding-left: 0.5rem;padding-right: 0.5rem; margin-top: 1.6rem;  background-color: #fff;"><span v-if="forum.forumType==1" style="color: #0894ec;background-color: #fff;">精·</span>{{forum.forumTitle}}</div>
        <div class="aui-card-list-header aui-card-list-user" style="background-color: #fff;">
          <router-link :to="{ name: 'orterUserHome', query: { userId: forum.userId }}" class="aui-card-list-user-avatar">
            <img :src="forum.userImageUrl" class="aui-img-round">
          </router-link>
          <div class="aui-card-list-user-name">
            <router-link :to="{ name: 'orterUserHome', query: { userId: forum.userId }}" class="aui-text-info">{{forum.userName}}</router-link>
            <div v-if="!isFollow" @click="follow(forum.userId)" class="aui-font-size-14 text-light aui-btn aui-btn-info">+关注</div>
            <div v-else class="aui-font-size-14 text-light" style="color: #0062cc;">已关注</div>
          </div>
          <div class="aui-card-list-user-info text-light">{{forum.pushTime}}</div>
        </div>
        <div style="padding-left: 1rem;padding-right: 1rem; background-color: #fff;">
          <div v-html="forum.forumContent"></div>
          <img v-for="imgUrl in forum.imagesUrl" :src="imgUrl" alt="" style="margin-top: 0.4rem; margin-bottom: 0.4rem">
        </div>
        <div class="aui-card-list-footer text-light aui-font-size-14" style="background-color: #fff;">
          <div @click="openActionsheet">
            <i class="aui-iconfont aui-icon-share"></i> 分享
          </div>
          <div>
            <i class="aui-iconfont aui-icon-comment"></i> {{forum.replyCount}}
          </div>
          <div @click="readAndLikeCountAdd(1)">
            <span :style="likeStyle"><i class="aui-iconfont aui-icon-laud"></i></span> {{forum.likeCount}}
          </div>
        </div>
        <div>
          <div class="aui-tab" id="tab" style="float: left; width: 40%;  margin-top: 0.5rem;">
            <div class="aui-tab-item aui-active">全部回复</div>
            <div class="aui-tab-item" >只看楼主</div>
          </div>
          <div style="float: right; width: 60%; height: 2.2rem; line-height: 2.2rem; margin-top: 0.5rem; background-color: #fff; text-align: right; padding-right: 0.5rem">
            <!--热门-->
            <!--<i class="aui-iconfont aui-icon-down"></i>-->
          </div>
          <div class="clear"></div>
        </div>

        <div v-for="reply in replyList" :id="reply.timiReply.id">
          <div class="aui-card-list-header aui-card-list-user" style="background-color: #fff; margin-top: 0.1rem">
            <router-link :to="{ name: 'orterUserHome', query: { userId: reply.timiReply.userId }}" class="aui-card-list-user-avatar">
              <img :src="reply.timiReply.userImageUrl" class="aui-img-round">
            </router-link>
            <div class="aui-card-list-user-name">
              <router-link :to="{ name: 'orterUserHome', query: { userId: reply.timiReply.userId }}" class="aui-text-info">{{reply.timiReply.userName}}</router-link>
            </div>
            <div class="aui-card-list-user-info text-light">第{{reply.timiReply.tierNum}}楼 | {{reply.timiReply.replyTime}}</div>
          </div>
          <div style="padding-left: 3rem;padding-right: 1rem; background-color: #fff;">
            <div @click="showcontext(2, reply.timiReply.id)">
              <div v-html="reply.timiReply.replyContent"></div>
              <img v-for="imageUrl in reply.timiReply.imagesUrl" :src="imageUrl" alt="" style="margin-top: 0.4rem; margin-bottom: 0.4rem">
            </div>
            <div v-if="reply.subTimiReplyList.length > 0" style="background-color: #FFECEC; padding: 0.5rem">
              <div v-for="subReply in reply.subTimiReplyList" :id="subReply.id">
                <router-link :to="{ name: 'orterUserHome', query: { userId: subReply.userId }}">{{subReply.userName}}</router-link>
                <span v-if="subReply.replyType==3">回复<a>{{subReply.parentName}}</a></span>
                : <span @click="showcontext(3, reply.timiReply.id)" v-html="subReply.replyContent"></span>
                <img v-for="imageUrl in subReply.imagesUrl" :src="imageUrl" alt="" style="margin-top: 0.4rem; margin-bottom: 0.4rem">
              </div>
            </div>

          </div>
          <div style="height: 1rem; background-color: #fff;"></div>
        </div>

        <div v-if="!isShowcontext" style="height: 1.6rem"></div>
        <div @click="showcontext(1, '')" v-if="!isShowcontext" style="position: fixed ;bottom:0; background-color: #fff; width: 100%; height: 2rem; line-height: 2rem; padding-left: 0.5rem">
          <span style="color: rgb(217, 217, 217)" v-html="contextTips"></span>
        </div>
        <!--<div class="aui-searchbar aui-bar-tab" id="search" :style="contextStyle">-->
        <!--<div class="aui-searchbar-input aui-border-radius">-->
        <!--<div contenteditable="true" id="context" style="background-color: #fff; line-height: 1.5rem" @blur="contextBlur" @input="contextInput" v-model="reply.replyCotent">-->
        <!--</div>-->
        <!--</div>-->
        <!--<div class="aui-btn aui-btn-info" tapmode>发送</div>-->
        <!--</div>-->
        <div :style="contextStyle" style="max-height: 6rem; overflow:auto; bottom: 0">
          <div contenteditable="true" id="replyContent" style="background-color: #fff;
        width: 80%;
        display: inline-block;
        max-height: 4.5rem;
        overflow:auto;
        vertical-align:top;
        border-radius: 6%; background-color: #FFCCFF;" @blur="contextBlur" @input="contextInput"></div>
          <div id="pushBtu" style="width: 18%; display: inline-block; height: 1.5rem; line-height: 1.5rem; vertical-align:top; text-align: center" @click="pushReply">发布</div>
          <div style="width: 100%; height: 1.5rem; line-height: 1.5rem">
          <span style="margin-left: 1rem; padding-left: 0.5rem;padding-right: 0.5rem" @click="selectImg">
            <i class="aui-iconfont aui-icon-image" style="font-size: 1.5rem"></i>
          </span>
            <span @click="at" style="margin-left: 1rem; font-size: 1.5rem; padding-left: 0.5rem;padding-right: 0.5rem">@</span>
          </div>
        </div>
      </div>

      <!--回复-->
      <div v-else>
        <div style="margin-left: 0.5rem; margin-right: 0.5rem; ">
          <header style="width: 100%; margin-top: 0.5rem; margin-bottom: 0.5rem">
            <i @click="hidePushUI" class="aui-iconfont aui-icon-close" style="position: fixed; top: 0.5rem; left: 0.5rem; font-size: 20px"></i>
            <div style="text-align: center; font-size: 16px">回复帖子</div>
            <div @click="push2" style="position: fixed; top: 0.5rem; right: 0.5rem; font-size: 16px">发布</div>
          </header>
          <div>
            <div id="replyContent" contenteditable="true" style="height: auto; min-height: 5rem"">

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
          <span @click="at" style="margin-left: 1rem; font-size: 1rem; padding-left: 0.5rem;padding-right: 0.5rem">@</span>
        </div>
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
          <div style="height: 40px; line-height: 40px; vertical-align: middle"> {{timiUser.nickname}}
            <input v-if="isCheck(timiUser)" checked class="aui-radio" type="checkbox" style="vertical-align: middle; float: right; margin-top: 8px" @click="atSelect(timiUser, $event)">
            <input v-else class="aui-radio" type="checkbox" style="vertical-align: middle; float: right; margin-top: 8px" @click="atSelect(timiUser, $event)">
          </div>
        </div>
      </li>
    </ul>
    <div style="height: 2.5rem"></div>
    <div style="bottom: 0; position: fixed; height: 2.5rem; width: 100%; padding-left: 1rem">
      <img v-for="timiUser in selectionUserList" :src="timiUser.pic" class="aui-img-round" style="vertical-align: middle; height: 2.5rem; display: inline-block">
      <div class="aui-btn" :class="btnStatus" @click="atComplete" style="float: right; margin-right: 0.5rem; margin-top: 0.6rem">{{selectionUserList.length}}/5完成</div>
    </div>
  </div>
  </div>
</template>

<script>
  export default {
    name: "detail",
    data () {
      return {
        forum: {},
        replyList: [],
        isShowcontext: false,
        havaContexg: false,
        contextStyle : 'display: none',
        contextTips : '说说你的看法……',
        reply: {},
        findReply: {
          pageNum : 1,
          pageSize : 50
        },
        likeStyle: '',
        source: '',
        imgs: [
        ],
        isReplyUI: false,
        isFollow: false,
        userList: [],
        userListAll: [],
        isShowAt: false,
        searchNickName: '',
        selectionUserList: [],
        btnStatus: 'aui-btn-success',
        tempContext: '',
        showcontextObj: {
          type: 1,
          parentId: ''
        }
      }
    },
    mounted: function(){
      window.scrollTo(0,0);
      this.init();
    },
    methods: {
      init: function () {
        var app = this;
        this.findReply.forumId = this.$route.query.id;
        this.reply.forumId = this.$route.query.id;
        this.source = this.$route.query.source;
        this.loadAuiTab();
        this.loadDetail();
        this.loadReplys(false);
      },
      readAndLikeCountAdd: function(type) {
        var app = this;
        var data = {
          id : this.$route.query.id,
          type: type
        }
        this.post('/timizhuo/forum/updateLikeAndRead',data, function (res) {
          var toast = new auiToast();
          if (res.data.code == '200') {
            if (type == 1) {
              toast.custom({
                title:"点赞成功",
                html:'<i class="aui-iconfont aui-icon-laud"></i>',
                duration:1000
              });
              app.forum.likeCount ++;
              app.likeStyle = 'color: red; font-weight: 800'
            }
          } else {
            toast.fail({
              title:"登陆后才能点赞哦！",
              duration:2000
            });
          }
        }, function (err) {

        });
      },
      initReply: function() {
        this.reply = {}
        this.reply.forumId = this.$route.query.id;
      },
      loadAuiTab: function() {
        var app = this;
        var apiready = function(){
          api.parseTapmode();
        }
        var tab = new auiTab({
          element:document.getElementById("tab"),
        },function(ret){
          if (ret.index == 1) {
            app.loadReplys(false);
          } else if (ret.index == 2) {
            app.loadReplys(true);
          }
        });
      },
      loadDetail: function() {
        var app = this;
        var data = {
          id : this.$route.query.id
        }
        this.post('/timizhuo/forum/findForumById',data, function (res) {
          if (res.data.code == '200') {
            var forumDTO = res.data.data;
            var timestamp = (new Date()).getTime();
            if (timestamp - forumDTO.postedTimeLong < 60 * 1000) {
              // 1分钟以内
              forumDTO.pushTime = "刚刚"
            } else if (timestamp - forumDTO.postedTimeLong < 60 * 1000 * 60) {
              // 1小时以内
              forumDTO.pushTime = parseInt(((timestamp - forumDTO.postedTimeLong) / 1000 / 60)) + '分钟前';
            } else if (timestamp - forumDTO.postedTimeLong < 60 * 1000 * 60 * 24) {
              forumDTO.pushTime = parseInt(((timestamp - forumDTO.postedTimeLong) / 1000 / 60 / 60)) + '小时前';
            } else if (timestamp - forumDTO.postedTimeLong < 60 * 1000 * 60 * 24 * 7) {
              forumDTO.pushTime = parseInt(((timestamp - forumDTO.postedTimeLong) / 1000 / 60 / 60 / 24)) + '天前';
            } else {
              forumDTO.pushTime = forumDTO.postedTime;
            }
            app.forum = forumDTO;
            var imgs = app.forum.imageUrl.split(",");
            var imagesUrl = [];
            for (var i = 0; i < imgs.length; i ++) {
              imagesUrl.push(imgs[i]);
            }
            app.forum.imagesUrl = imagesUrl;
            app.readAndLikeCountAdd(2);
            app.isFollowFun(app.forum.userId);
          } else {
            var dialog = new auiDialog();
            dialog.alert({
              title: "提示",
              msg: res.data.message,
              buttons:['确定']
            },function(ret){
            })
          }
        }, function (err) {

        });
      },
      loadReplys: function(lookFloorHost) {
        this.selectionUserList = [];
        this.findReply.lookFloorHost = lookFloorHost;
        var app = this;
        this.post('/timizhuo/reply/findReply',this.findReply, function (res) {
          if (res.data.code == '200') {
            app.replyList = res.data.data.list;
            app.forum.replyCount = app.replyList.length;
            var replyId = app.$route.query.replyId;
            if (replyId != undefined) {
              setTimeout(()=>document.getElementById(replyId).scrollIntoView(true), 10);
            } else {
              var isReply = app.$route.query.isReply;
              if (isReply != undefined && isReply) {
                setTimeout(()=>{
                  app.isReplyUI = true;
                  app.setFocus();
                }, 10);
              }
            }
          } else {
            var dialog = new auiDialog();
            dialog.alert({
              title: "提示",
              msg: res.data.message,
              buttons:['确定']
            },function(ret){
            })
          }
        }, function (err) {

        });
      },
      showcontext: function (replyType, parentId) {
        this.showcontextObj.type = replyType;
        this.showcontextObj.parentId = parentId;
        this.initReply();
        this.isShowcontext=true;
        this.contextStyle = '';
        this.reply.replyType = replyType;
        if (parentId != null && parentId != '') {
          this.reply.parentId = parentId;
        }
        setTimeout(()=>document.getElementById("replyContent").focus(), 10);
      },
      contextBlur: function () {
        setTimeout(()=>{
          this.contextStyle = 'display: none';
          this.isShowcontext=false;
          if (!this.havaContexg) {
            this.contextTips ='说说你的看法';
          } else {
            this.contextTips ='[草稿内容待发送]';
          }
        }, 200);

      },
      contextInput: function () {

      },
      pushReply: function () {
        var app = this;
        var friendIds = [];
        for (var i = 0; i < this.selectionUserList.length; i ++) {
          friendIds.push(this.selectionUserList[i].id);
        }
        this.reply.friendIds = friendIds;
        this.reply.replyContent = document.getElementById("replyContent").innerHTML;
        this.post('/timizhuo/reply/addReply', this.reply, function (res) {
          if (res.data.code == '200') {
            var toast = new auiToast();
            toast.success({
              title:"发布成功",
              duration:500
            });
            document.getElementById("replyContent").innerHTML = '';
            app.loadReplys();
            window.scrollTo(0,0);
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
      push2: function() {
        var app = this;
        var friendIds = [];
        for (var i = 0; i < this.selectionUserList.length; i ++) {
          friendIds.push(this.selectionUserList[i].id);
        }
        this.reply.friendIds = friendIds;
        this.reply.replyContent = document.getElementById("replyContent").innerHTML;
        var imageUrl = '';
        this.imgs.forEach((img)=> {
          imageUrl += img + ',';
        });
        this.reply.imageUrl = imageUrl.substring(0, imageUrl.length - 1);
        this.loading("正在发布……")
        var toast = new auiToast();
        this.post('/timizhuo/reply/addReply', this.reply, function (res) {
          if (res.data.code == '200') {
            app.hidePushUI();
            setTimeout(()=>{
              toast.success({
                title:"发布成功",
                duration:500
              });
              app.loadReplys();
              window.scrollTo(0,0);
            }, 10);
          } else {
            var dialog = new auiDialog();
            dialog.alert({
              title: "提示",
              msg: res.data.message,
              buttons:['确定']
            },function(ret){
              app.$router.push({name:'login'});
            })
          };
          toast.hide();
        }, function (err) {
          toast.hide();
        });
      },
      openActionsheet: function () {
        var actionsheet = new auiActionsheet();
        actionsheet.init({
          frameBounces:true,//当前页面是否弹动，（主要针对安卓端）
          // title:"这里是标题",
          cancelTitle:'取消',
          buttons:['复制链接']
        },function(ret){
          if(ret){
            if (ret.buttonIndex == 1) {
              var oInput = document.createElement('input');
              oInput.value = window.location.href + '&source=share',
                document.body.appendChild(oInput);
              oInput.select(); // 选择对象
              document.execCommand("Copy"); // 执行浏览器复制命令 oInput.className = 'oInput';
              oInput.style.display='none';
              var toast = new auiToast();
              toast.success({
                title:"复制成功",
                duration:2000
              });
            }
          }
        })
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
      goback: function () {
        this.$router.go(-1);
      },
      selectImg: function () {
        var app = this;
        var html = document.getElementById("replyContent").innerHTML;
        this.isReplyUI = true;
        setTimeout(()=>{
          document.getElementById("replyContent").innerHTML = html;
          app.addImg();
        }, 10);
      },
      hidePushUI: function () {
        this.imgs = [];
        this.isReplyUI = false;
      },
      follow: function (parentId) {
        var data = {
          parentId: parentId
        }
        var app = this;
        this.post('/timizhuo/fans/follow', data, function (res) {
          if (res.data.code == '200') {
            app.isFollow = true;
            var toast = new auiToast();
            toast.success({
              title:"关注成功",
              duration:2000
            });
          } else {
            var toast = new auiToast();
            toast.fail({
              title:"登录后才能关注哦！",
              duration:2000
            });
          }
        }, function (err) {
        });
      },
      isFollowFun: function (parentId) {
        var data = {
          parentId: parentId
        }
        var app = this;
        this.post('/timizhuo/fans/isFollow', data, function (res) {
          if (res.data.code == '200') {
            app.isFollow = res.data.data;
          }
        }, function (err) {
        });
      },
      at: function () {
        var app = this;
        setTimeout(()=> {app.tempContext = document.getElementById("replyContent").innerHTML }, 10);
        var app = this;
        app.post('/timizhuo/fans/followList', {}, function (res) {
          if (res.data.code == '200') {
            app.userList = res.data.data;
            app.userListAll = res.data.data;
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
        var app = this;
        // 顶部搜索栏开始
        var apiready = function(){
          api.parseTapmode();
        }
        var searchBar = document.querySelector(".aui-searchbar");
        var searchBarInput = document.querySelector(".aui-searchbar input");
        var searchBarBtn = document.querySelector(".aui-searchbar .aui-searchbar-btn");
        var searchBarClearBtn = document.querySelector(".aui-searchbar .aui-searchbar-clear-btn");
        // if(searchBar){
        //   searchBarInput.onclick = function(){
        //     searchBarBtn.style.marginRight = 0;
        //   }
        // }
        // searchBarClearBtn.onclick = function(){
        //   this.style.display = 'none';
        //   searchBarInput.value = '';
        //   searchBarBtn.classList.remove("aui-text-info");
        //   searchBarBtn.textContent = "取消";
        // }
        // searchBarBtn.onclick = function(){
        //   this.style.marginRight = "-"+this.offsetWidth+"px";
        //   searchBarInput.value = '';
        //   searchBarInput.blur();
        // }
        // 顶部搜索栏结束
      },
      hideUserList: function () {
        var app = this;
        this.selectionUserList = [];
        this.isShowAt = false;
        setTimeout(()=> {document.getElementById("replyContent").innerHTML = app.tempContext;
          app.setFocus();
        }, 10);
      },
      searchChange: function () {
        var searchBarBtn = document.querySelector(".aui-searchbar .aui-searchbar-btn");
        var searchBarClearBtn = document.querySelector(".aui-searchbar .aui-searchbar-clear-btn");
        searchBarClearBtn.style.display = 'none';
        searchBarBtn.classList.remove("aui-text-info");
        searchBarBtn.textContent = "取消";

        var users = [];
        for(var i = 0; i < this.userListAll.length; i ++) {
          var timiUser = this.userListAll[i];
          if (timiUser.nickname.indexOf(this.searchNickName) != -1) {
            users.push(timiUser);
          }
        }
        this.userList = users;
      },
      atSelect: function (timiUser, event) {
        if (event.target.checked) {
          this.btnStatus = 'aui-btn-primary';
          // 选中
          if (this.selectionUserList.length >= 5) {
            event.target.checked = false;
            var toast = new auiToast();
            toast.success({
              title:"选中不能超过5个",
              duration:2000
            });
          } else {
            this.selectionUserList.push(timiUser);
          }
        } else {
          // 取消选中
          this.unSelect(timiUser);
          if (this.selectionUserList.length == 0) {
            this.btnStatus = 'aui-btn-success';
          }
        }
      },
      unSelect: function (timiUser) {
        for(var i = 0; i < this.selectionUserList.length; i ++) {
          if (this.selectionUserList[i].id == timiUser.id) {
            this.selectionUserList.splice(i, 1);
          }
        }
      },
      atComplete: function () {
        var app = this;
        if (this.btnStatus == 'aui-btn-success') {
          return;
        }
        this.isShowAt = false;
        var appendHtml = '';
        for (var i = 0; i < this.selectionUserList.length; i ++) {
          appendHtml += '<span>@' + this.selectionUserList[i].nickname + '</span>&nbsp;';
        }
        setTimeout(()=>{
          app.showcontext(this.showcontextObj.type, this.showcontextObj.parentId);
          document.getElementById("replyContent").innerHTML = this.tempContext;
          document.getElementById("replyContent").innerHTML += appendHtml;
          app.setFocus();
        }, 10);

      },
      isCheck: function (timiUser) {
        for (var i = 0; i < this.selectionUserList.length; i ++) {
          if (this.selectionUserList[i].id == timiUser.id) {
            return true;
          }
        }
        return false
      },
      setFocus: function () {
        setTimeout(()=>{
          var obj = document.getElementById("replyContent");
          if (window.getSelection) { //ie11 10 9 ff safari
            obj.focus(); //解决ff不获取焦点无法定位问题
            var range = window.getSelection(); //创建range
            range.selectAllChildren(obj); //range 选择obj下所有子内容
            range.collapseToEnd(); //光标移至最后
          } else if (document.selection) { //ie10 9 8 7 6 5
            var range = document.selection.createRange(); //创建选择对象
            //var range = document.body.createTextRange();
            range.moveToElementText(obj); //range定位到obj
            range.collapse(false); //光标移至最后
            range.select();
          }
        },5)
      },
    }
  }
</script>

<style scoped>
  .clear{ clear:both}
</style>
