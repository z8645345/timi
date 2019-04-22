<template>
  <div id="head-img">
    <!-- 遮罩层 -->
    <div class="container" v-show="panel">
      <div>
        <img id="image" :src="url" alt="Picture">
      </div>

      <button type="button" id="button" @click="crop" >确定</button>

    </div>
    <div class="aui-tips aui-margin-b-15" id="tips-1" v-if="isShowMsg" style="position: fixed; top:0;">
      <i class="aui-iconfont aui-icon-info"></i>
      <router-link :to="{ name: 'chatroom'}" tag="div" class="aui-tips-title max-rows1" style="-webkit-box-orient: vertical; display: -webkit-box;" v-html="msg">
      </router-link>
      <i class="aui-iconfont aui-icon-close" tapmode @click="isShowMsg=false"></i>
    </div>
    <div v-if="isShow">
      <section class="aui-content user-info aui-text-center aui-margin-b-15">
        <img @click="selectHead" :src="timiUser.pic" class="avatar aui-img-round">
        <input type="file" id="change" accept="image" @change="change" style="display: none">
        <input type="text" id="pic" style="display: none">
        <h2 class="aui-margin-t-15">{{ timiUser.nickname }}<small><i class="aui-iconfont aui-icon-cert aui-text-danger"></i></small></h2>
        <div class="aui-row aui-margin-t-15">
          <div class="aui-col-xs-12 aui-text-center aui-padded-r-10">
            爱婷宣言：<div id="loveTimiDeclaration" @blur="saveLoveTimiDeclaration" style="display: inline-block;" :contenteditable="loveTimiDeclarationEdit">{{ timiUser.loveTimiDeclaration}}</div>
            <i class="aui-iconfont aui-icon-edit" @click="editLoveTimiDeclaration"></i>
          </div>
        </div>
        <div class="aui-row aui-margin-t-15">
          <div class="aui-col-xs-12 aui-text-center aui-padded-r-10">
            个人简介：<div id="personalProfile" @blur="savePersonalProfile" style="display: inline-block;" :contenteditable="personalProfileEdit">{{ timiUser.personalProfile}}</div>
            <i class="aui-iconfont aui-icon-edit" @click="editPersonalProfile"></i>
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
          <router-link :to="{ name: 'userFollowList', query: { userId: timiUser.id, type: 1 }}" class="aui-col-xs-6 aui-text-right aui-border-r aui-padded-r-10">
            <span class="aui-text-danger aui-margin-r-5">{{timiUser.fansCount}}人</span>关注我
          </router-link>
          <router-link :to="{ name: 'userFollowList', query: { userId: timiUser.id, type: 2 }}" class="aui-col-xs-6 aui-text-left aui-padded-l-10">
            我关注<span class="aui-text-danger aui-margin-l-5">{{timiUser.followCount}}人</span>
          </router-link>
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
          <router-link :to="{ name: 'message'}" tag="li" class="aui-list-item" style="background-color: #fff;">
            <div class="aui-list-item-inner aui-list-item-arrow">
              <div class="aui-list-item-title">消息</div>
              <div class="aui-list-item-right">共{{timiUser.messageCount}}条未读消息</div>
            </div>
          </router-link>
          <router-link :to="{ name: 'userForumList', query: { userId: timiUser.id }}" tag="li" class="aui-list-item" style="background-color: #fff;">
            <div class="aui-list-item-inner aui-list-item-arrow">
              <div class="aui-list-item-title">我的帖子</div>
              <div class="aui-list-item-right">共{{timiUser.forumCount}}贴</div>
            </div>
          </router-link>
        </ul>
        <ul class="aui-list " style="position: fixed; bottom: 2.5rem; width: 100%">
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
          <div v-if="GLOBAL.msgNum > 0" class="aui-badge">{{GLOBAL.msgNum}}</div>
          <i class="aui-iconfont aui-icon-my"></i>
          <div class="aui-bar-tab-label">我的</div>
        </div>
      </footer>
      <!--底部工具类结束-->
    </div>
  </div>
</template>

<script>
  import Cropper from 'cropperjs'
  export default {
    name: 'user-home',
    data() {
      return {
        isShow: true,
        timiUser: {},
        msg: '',
        isShowMsg: false,
        messageList: [],
        loveTimiDeclarationEdit: false,
        personalProfileEdit: false,
        cropper:'',
        croppable:false,
        panel:false,
        url:'',
        picValue:'',
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
          setTimeout(()=>app.init(), 10);
        } else {
          app.$router.push({name:'login'});
        }
      }, function (err) {
        toast.hide();
        app.errorAlert('系统异常');
      });
    },
    methods: {
      init: function() {

        //初始化这个裁剪框
        var self = this;
        var image = document.getElementById('image');
        this.cropper = new Cropper(image, {
          aspectRatio: 1,
          viewMode: 1,
          background:false,
          zoomable:false,
          ready: function () {
            self.croppable = true;
          }
        });
      },
      logout: function () {
        var app = this;
        this.post('/timizhuo/user/logout',{}, function (res) {
          if (res.data.code == '200') {;
            app.isShow = false;
            location.reload();
            // app.$router.push({name:'login'});
          }
        }, function (err) {
          app.errorAlert('系统异常');
        })
      },
      editLoveTimiDeclaration: function () {
        var self = this;
        this.loveTimiDeclarationEdit = true;
        setTimeout(()=>self.setFocus("loveTimiDeclaration"), 1);
      },
      editPersonalProfile: function () {
        var self = this;
        this.personalProfileEdit = true;
        setTimeout(()=>self.setFocus("personalProfile"), 1);
      },
      saveLoveTimiDeclaration: function () {
        var editData = {
          loveTimiDeclaration: document.getElementById("loveTimiDeclaration").innerHTML
        }
        this.updateTimiUser(editData);
      },
      savePersonalProfile: function () {
        var editData = {
          personalProfile: document.getElementById("personalProfile").innerHTML
        }
        this.updateTimiUser(editData);
      },
      updateTimiUser: function(editData) {
        this.post('/timizhuo/user/updateTimiUser', editData, function (res) {

        }, function (err) {
        });
      },
      setFocus: function (elementId) {
        setTimeout(()=>{
          var obj = document.getElementById(elementId);
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
      selectHead: function() {
        document.getElementById('change').click();
      },
      change (e) {
        let files = e.target.files || e.dataTransfer.files;
        if (!files.length) return;
        this.panel = true;
        this.picValue = files[0];
        this.url = this.getObjectURL(this.picValue);
        //每次替换图片要重新得到新的url
        if(this.cropper){
          this.cropper.replace(this.url);
        }
        this.panel = true;
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
      crop () {
        var toast = new auiToast();
        toast.loading({
          title:"头像裁剪中……",
          duration:2000
        },function(ret){
          console.log(ret);
        });
        this.panel = false;
        var croppedCanvas;
        var roundedCanvas;

        if (!this.croppable) {
          return;
        }
        // Crop
        croppedCanvas = this.cropper.getCroppedCanvas();
        console.log(this.cropper)
        // Round
        roundedCanvas = this.getRoundedCanvas(croppedCanvas);

        this.timiUser.headerImage = roundedCanvas.toDataURL();
        toast.hide();
        this.postImg()

      },
      getRoundedCanvas (sourceCanvas) {

        var canvas = document.createElement('canvas');
        var context = canvas.getContext('2d');
        var width = 300;
        var height = 300;

        canvas.width = width;
        canvas.height = height;

        context.imageSmoothingEnabled = true;
        context.drawImage(sourceCanvas, 0, 0, width, height);
        context.globalCompositeOperation = 'destination-in';
        context.beginPath();
        context.arc(width / 2, height / 2, Math.min(width, height) / 2, 0, 2 * Math.PI, true);
        context.fill();

        return canvas;
      },
      postImg () {
        var toast = new auiToast();
        toast.loading({
          title:"头像上传中……",
          duration:2000
        },function(ret){
          console.log(ret);
        });
        //这边写图片的上传
        var self = this;
        var data = {
          base64: this.timiUser.headerImage,
          namespaceType: 1
        }
        this.axios.post(this.GLOBAL.serviceHost + '/timizhuo/qiniu/uploadImg',this.qs.stringify(data),{
        })
          .then(function(res){
            if (res.data.code == '200') {
              var editData = {
                  pic: res.data.data
              }
              self.updateTimiUser(editData);
              self.timiUser.pic = res.data.data;
            } else {
              this.timiUser.headerImage = '';
              var dialog = new auiDialog();
              dialog.alert({
                title:"提示",
                msg:res.data.message,
                buttons:['确定']
              });
            }
            toast.hide();
          }.bind(this))
          .catch(function(err){
            if(err.response) {
              console.log(err.response)
              //控制台打印错误返回的内容
            }
            toast.hide();
            //bind(this)可以不用
          }.bind(this));
      },
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

  #head-img #button {
    position: absolute;
    right: 10px;
    top: 10px;
    width: 80px;
    height: 40px;
    border:none;
    border-radius: 5px;
    background:white;
  }
  #head-img .show {
    width: 100px;
    height: 100px;
    overflow: hidden;
    position: relative;
    border-radius: 50%;
    border: 1px solid #d5d5d5;
  }
  #head-img .picture {
    width: 100%;
    height: 100%;
    overflow: hidden;
    background-position: center center;
    background-repeat: no-repeat;
    background-size: cover;
  }
  #head-img .container {
    z-index: 99;
    position: fixed;
    padding-top: 60px;
    left: 0;
    top: 0;
    right: 0;
    bottom: 0;
    background:rgba(0,0,0,1);
  }

  #head-img #image {
    max-width: 100%;
  }

  .cropper-view-box,.cropper-face {
    border-radius: 50%;
  }

  .cropper-container {
    font-size: 0;
    line-height: 0;

    position: relative;

    -webkit-user-select: none;

    -moz-user-select: none;

    -ms-user-select: none;

    user-select: none;

    direction: ltr;
    -ms-touch-action: none;
    touch-action: none
  }

  .cropper-container img {
    /* Avoid margin top issue (Occur only when margin-top <= -height) */
    display: block;
    min-width: 0 !important;
    max-width: none !important;
    min-height: 0 !important;
    max-height: none !important;
    width: 100%;
    height: 100%;
    image-orientation: 0deg
  }

  .cropper-wrap-box,
  .cropper-canvas,
  .cropper-drag-box,
  .cropper-crop-box,
  .cropper-modal {
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
  }

  .cropper-wrap-box {
    overflow: hidden;
  }

  .cropper-drag-box {
    opacity: 0;
    background-color: #fff;
  }

  .cropper-modal {
    opacity: .5;
    background-color: #000;
  }

  .cropper-view-box {
    display: block;
    overflow: hidden;

    width: 100%;
    height: 100%;

    outline: 1px solid #39f;
    outline-color: rgba(51, 153, 255, 0.75);
  }

  .cropper-dashed {
    position: absolute;

    display: block;

    opacity: .5;
    border: 0 dashed #eee
  }

  .cropper-dashed.dashed-h {
    top: 33.33333%;
    left: 0;
    width: 100%;
    height: 33.33333%;
    border-top-width: 1px;
    border-bottom-width: 1px
  }

  .cropper-dashed.dashed-v {
    top: 0;
    left: 33.33333%;
    width: 33.33333%;
    height: 100%;
    border-right-width: 1px;
    border-left-width: 1px
  }

  .cropper-center {
    position: absolute;
    top: 50%;
    left: 50%;

    display: block;

    width: 0;
    height: 0;

    opacity: .75
  }

  .cropper-center:before,
  .cropper-center:after {
    position: absolute;
    display: block;
    content: ' ';
    background-color: #eee
  }

  .cropper-center:before {
    top: 0;
    left: -3px;
    width: 7px;
    height: 1px
  }

  .cropper-center:after {
    top: -3px;
    left: 0;
    width: 1px;
    height: 7px
  }

  .cropper-face,
  .cropper-line,
  .cropper-point {
    position: absolute;

    display: block;

    width: 100%;
    height: 100%;

    opacity: .1;
  }

  .cropper-face {
    top: 0;
    left: 0;

    background-color: #fff;
  }

  .cropper-line {
    background-color: #39f
  }

  .cropper-line.line-e {
    top: 0;
    right: -3px;
    width: 5px;
    cursor: e-resize
  }

  .cropper-line.line-n {
    top: -3px;
    left: 0;
    height: 5px;
    cursor: n-resize
  }

  .cropper-line.line-w {
    top: 0;
    left: -3px;
    width: 5px;
    cursor: w-resize
  }

  .cropper-line.line-s {
    bottom: -3px;
    left: 0;
    height: 5px;
    cursor: s-resize
  }

  .cropper-point {
    width: 5px;
    height: 5px;

    opacity: .75;
    background-color: #39f
  }

  .cropper-point.point-e {
    top: 50%;
    right: -3px;
    margin-top: -3px;
    cursor: e-resize
  }

  .cropper-point.point-n {
    top: -3px;
    left: 50%;
    margin-left: -3px;
    cursor: n-resize
  }

  .cropper-point.point-w {
    top: 50%;
    left: -3px;
    margin-top: -3px;
    cursor: w-resize
  }

  .cropper-point.point-s {
    bottom: -3px;
    left: 50%;
    margin-left: -3px;
    cursor: s-resize
  }

  .cropper-point.point-ne {
    top: -3px;
    right: -3px;
    cursor: ne-resize
  }

  .cropper-point.point-nw {
    top: -3px;
    left: -3px;
    cursor: nw-resize
  }

  .cropper-point.point-sw {
    bottom: -3px;
    left: -3px;
    cursor: sw-resize
  }

  .cropper-point.point-se {
    right: -3px;
    bottom: -3px;
    width: 20px;
    height: 20px;
    cursor: se-resize;
    opacity: 1
  }

  @media (min-width: 768px) {

    .cropper-point.point-se {
      width: 15px;
      height: 15px
    }
  }

  @media (min-width: 992px) {

    .cropper-point.point-se {
      width: 10px;
      height: 10px
    }
  }

  @media (min-width: 1200px) {

    .cropper-point.point-se {
      width: 5px;
      height: 5px;
      opacity: .75
    }
  }

  .cropper-point.point-se:before {
    position: absolute;
    right: -50%;
    bottom: -50%;
    display: block;
    width: 200%;
    height: 200%;
    content: ' ';
    opacity: 0;
    background-color: #39f
  }

  .cropper-invisible {
    opacity: 0;
  }

  .cropper-bg {
    background-image: url('data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQAQMAAAAlPW0iAAAAA3NCSVQICAjb4U/gAAAABlBMVEXMzMz////TjRV2AAAACXBIWXMAAArrAAAK6wGCiw1aAAAAHHRFWHRTb2Z0d2FyZQBBZG9iZSBGaXJld29ya3MgQ1M26LyyjAAAABFJREFUCJlj+M/AgBVhF/0PAH6/D/HkDxOGAAAAAElFTkSuQmCC');
  }

  .cropper-hide {
    position: absolute;

    display: block;

    width: 0;
    height: 0;
  }

  .cropper-hidden {
    display: none !important;
  }

  .cropper-move {
    cursor: move;
  }

  .cropper-crop {
    cursor: crosshair;
  }

  .cropper-disabled .cropper-drag-box,
  .cropper-disabled .cropper-face,
  .cropper-disabled .cropper-line,
  .cropper-disabled .cropper-point {
    cursor: not-allowed;
  }
</style>
