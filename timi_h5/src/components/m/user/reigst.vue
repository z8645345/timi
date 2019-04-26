<template>
  <div id="head-img">
    <!-- 遮罩层 -->
    <div class="container" v-show="panel">
      <div>
        <img id="image" :src="url" alt="Picture">
      </div>

      <button type="button" id="button" @click="crop" >确定</button>

    </div>
    <header class="aui-bar aui-bar-nav nav-title">
      <a href="javascript:history.go(-1)" class="aui-pull-left aui-btn">
        <span class="aui-iconfont aui-icon-left"></span>返回
      </a>
      <div class="aui-title">婷迷会员注册</div>
    </header>
    <div class="aui-content aui-margin-b-15">
      <div class="aui-text-center">
        <!--<div style="width:5rem; margin: 5px auto 0">-->
          <!--<input type="file">-->
          <!--<img src="../../../assets/images/timi.jpg" class="avatar aui-img-round">-->
          <!--请选择头像-->
        <!--</div>-->
        <div style="width:5rem; margin: 5px auto 0">
          <div class="show" @click="selectHead">
            <div class="picture" :style="'backgroundImage:url('+timiUser.headerImage+')'">
              <img id="img" src="" alt="">
            </div>
          </div>
          <div style="color: rgb(117, 117, 117)">请选择头像</div>
            <input type="file" id="change" accept="image" @change="change" style="display: none">

        </div>
      </div>
      <ul class="aui-list aui-form-list">
        <li class="aui-list-item">
          <div class="aui-list-item-inner">
            <div class="aui-list-item-label">
              手机号码
            </div>
            <div class="aui-list-item-input">
              <input type="text" id="pic" style="display: none">
              <input type="text" v-model="timiUser.username" placeholder="请输入手机号码" style="width: 60%; float: left;">
              <div class="aui-btn aui-btn-info aui-btn-block aui-btn-outlined aui-btn-sm" @click="sendCheckCode" style="width: 40%; float: right; margin-top: 4px;">{{sendButText}}</div>
            </div>
          </div>
        </li>
        <li class="aui-list-item">
          <div class="aui-list-item-inner">
            <div class="aui-list-item-label">
              验证码
            </div>
            <div class="aui-list-item-input">
              <input type="text" v-model="timiUser.checkCode" placeholder="请输入短信验证码">
            </div>
          </div>
        </li>
        <li class="aui-list-item">
          <div class="aui-list-item-inner">
            <div class="aui-list-item-label">
              密码
            </div>
            <div class="aui-list-item-input">
              <input type="password" v-model="timiUser.password" placeholder="请输入密码">
            </div>
          </div>
        </li>
        <li class="aui-list-item">
          <div class="aui-list-item-inner">
            <div class="aui-list-item-label">
              昵称
            </div>
            <div class="aui-list-item-input">
              <input type="text" v-model="timiUser.nickname" placeholder="请输入昵称">
            </div>
          </div>
        </li>
        <li class="aui-list-item">
          <div class="aui-list-item-inner">
            <div class="aui-list-item-label">
              微信号
            </div>
            <div class="aui-list-item-input">
              <input type="text" v-model="timiUser.wechartCode" placeholder="粉丝团更好找到你哦（选填）">
            </div>
          </div>
        </li>
        <li class="aui-list-item">
          <div class="aui-list-item-inner">
            <div class="aui-list-item-label">
              爱婷宣言
            </div>
            <div class="aui-list-item-input">
              <textarea placeholder="用言语表达你对依婷的爱吧（选填）" v-model="timiUser.loveTimiDeclaration"></textarea>
            </div>
          </div>
        </li>
        <li class="aui-list-item">
          <div class="aui-list-item-inner">
            <div class="aui-list-item-label">
              个人简介
            </div>
            <div class="aui-list-item-input">
              <textarea placeholder="让婷迷更好地认识你（选填）" v-model="timiUser.personalProfile"></textarea>
            </div>
          </div>
        </li>
        <li class="aui-list-item">
          <div class="aui-list-item-inner aui-list-item-center aui-list-item-btn">
            <div class="aui-btn aui-btn-info aui-margin-r-5 aui-btn-block" @click="reigst">注册</div>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
  import Cropper from 'cropperjs'
  export default {
    components: {

    },
    data () {
      return {
        picValue:'',
        cropper:'',
        croppable:false,
        panel:false,
        url:'',
        timiUser: {
          headerImage:'',
          username: '',
          password: '',
          nickname: '',
          wechartCode: '',
          loveTimiDeclaration: '',
          personalProfile: '',
          checkCode: ''
        },
        sendButText: '发送验证码',
        isSend: true
      }
    },
    mounted () {
      var winHeight = $(window).height();
      $(window).resize(function() {
        var thisHeight = $(this).height();
        var keyboardHeight = thisHeight - winHeight;
        $(".aui-bar-tab").css({ 'bottom': keyboardHeight + 'px' });
      });

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
    methods: {
      selectHead: function() {
        document.getElementById('change').click();
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
        // this.postImg()

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
        var data = {
          base64Str: this.timiUser.headerImage
        }
        this.axios.post(this.GLOBAL.serviceHost + '/timizhuo/user/fileUpload',this.qs.stringify(data),{
        })
          .then(function(res){
            debugger
            if (res.data.code == '200') {
              document.getElementById("pic").value=res.data.data;
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
      reigst: function () {
        if (this.timiUser.headerImage == null || this.timiUser.headerImage == '') {
          this.errorAlert("请选择头像!");
          return;
        }
        if (this.timiUser.username == null || this.timiUser.username == '') {
          this.errorAlert("请输入您的手机号码!");
          return;
        }
        // var myReg=/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
        // if(!myReg.test(this.timiUser.username)){
        //   this.errorAlert("请输入正确的邮箱格式!");
        //   return;
        // }
        if(!(/^1(3|4|5|7|8)\d{9}$/.test(this.timiUser.username))){
          this.errorAlert("请输入正确的手机号码!");
          return;
        }
        if (this.timiUser.checkCode == null || this.timiUser.checkCode == '') {
          this.errorAlert("请输入短信验证码!");
          return;
        }
        if (this.timiUser.checkCode.length != 6) {
          this.errorAlert("短信验证码有误!");
          return;
        }
        if (this.timiUser.password == null || this.timiUser.password == '') {
          this.errorAlert("请输入您的密码!");
          return;
        }
        if (this.timiUser.password.length < 6) {
          this.errorAlert("密码长度不能少于6位!");
          return;
        }
        if (this.timiUser.password.length > 20) {
          this.errorAlert("密码长度不能超过20位!");
          return;
        }
        if (this.timiUser.nickname == null || this.timiUser.nickname == "") {
          this.errorAlert("昵称不能为空!");
          return;
        }
        if (this.timiUser.nickname.leng > 10) {
          this.errorAlert("昵称不能超过10位!");
          return;
        }
        this.register();
      },
      register: function() {
        //this.loading("正在发送验证码……");
        var data = {
          username: this.timiUser.username,
          nickname: this.timiUser.nickname
        }
        var app = this;
        app.loading("正在注册……");
        var toast = new auiToast();
        app.axios.post(app.GLOBAL.serviceHost + '/timizhuo/user/register',app.qs.stringify(app.timiUser),{
        })
          .then(function(res){
            if (res.data.code == '200') {
              var dialog = new auiDialog();
              dialog.alert({
                title:"提示",
                msg:'恭喜你注册成功!',
                buttons:['确定']
              },function(ret){
                app.$router.push({name:'login'});
              })
            } else {
              app.errorAlert(res.data.message);
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

        // this.axios.post(this.GLOBAL.serviceHost + '/timizhuo/user/sendEmall',this.qs.stringify(data),{
        // })
        //   .then(function(res){
        //     if (res.data.code == '200') {
        //       var dialog = new auiDialog();
        //       dialog.prompt({
        //         title:"邮箱验证码发送成功!",
        //         text:'请输入你的邮箱验证码',
        //         type:'number',
        //         buttons:['取消','确定']
        //       },function(ret){
        //         if(ret.buttonIndex == 2){
        //           if (ret.text == null || ret.text == '') {
        //             app.errorAlert("验证码无效!");
        //             return false;
        //           }
        //           app.timiUser.checkCode = ret.text;
        //           app.loading("正在注册……");
        //           app.axios.post(app.GLOBAL.serviceHost + '/timizhuo/user/register',app.qs.stringify(app.timiUser),{
        //           })
        //             .then(function(res){
        //               if (res.data.code == '200') {
        //                 dialog.alert({
        //                   title:"提示",
        //                   msg:'恭喜你注册成功!',
        //                   buttons:['确定']
        //                 },function(ret){
        //                   if(ret){
        //                     app.$router.push({name:'login'});
        //                   }
        //                 })
        //               } else {
        //                 app.errorAlert(res.data.message);
        //               }
        //               toast.hide();
        //             }.bind(this))
        //             .catch(function(err){
        //               if(err.response) {
        //                 console.log(err.response)
        //                 //控制台打印错误返回的内容
        //               }
        //               toast.hide();
        //               //bind(this)可以不用
        //             }.bind(this));
        //         }
        //       })
        //     } else {
        //       this.errorAlert(res.data.message);
        //     }
        //     var toast = new auiToast();
        //     toast.hide();
        //   }.bind(this))
        //   .catch(function(err){
        //     if(err.response) {
        //       console.log(err.response)
        //       //控制台打印错误返回的内容
        //     }
        //     toast.hide();
        //     //bind(this)可以不用
        //   }.bind(this));
      },
      sendCheckCode: function() {
        if (!this.isSend) {
          return;
        }
        if (this.timiUser.username == null || this.timiUser.username == '') {
          this.errorAlert("请输入您的手机号码!");
          return;
        }
        if(!(/^1(3|4|5|7|8)\d{9}$/.test(this.timiUser.username))){
          this.errorAlert("请输入正确的手机号码!");
          return;
        }
        var data = {
          username: this.timiUser.username
        }
        var app = this;
        this.post('/timizhuo/user/sendSms', data, function (res) {
          if (res.data.code == '200') {;
            app.errorAlert("验证码发送成功!");
            app.isSend = false;
            app.countDown(60);
          } else {
            app.errorAlert(res.data.message);
          }
        }, function (err) {
          toast.hide();
          app.errorAlert('系统异常');
        });
      },
      countDown: function(i) {
        var app = this;
        setTimeout(function () {
          app.sendButText = (i--) + '秒';
          if (i >= 0) {
            app.countDown(i);
          } else {
            app.sendButText = '发送验证码',
            app.isSend = true;
          }
        }, 1000);
      },
      errorAlert: function (msg) {
        var dialog = new auiDialog();
        dialog.alert({
          title:"提示",
          msg:msg,
          buttons:['确定']
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
      }
    }
  }
</script>

<style>
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
