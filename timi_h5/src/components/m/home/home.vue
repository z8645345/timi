<template>
  <div class="main">
    <div class="aui-tips aui-margin-b-15" id="tips-1" v-if="isShowMsg" style="position: fixed; top:0;">
      <i class="aui-iconfont aui-icon-info"></i>
      <router-link :to="{ name: 'chatroom'}" tag="div" class="aui-tips-title max-rows1" style="-webkit-box-orient: vertical; display: -webkit-box;" v-html="msg">
      </router-link>
      <i class="aui-iconfont aui-icon-close" tapmode @click="isShowMsg=false"></i>
    </div>
    <!--搜索栏开始-->
    <header class="aui-bar aui-bar-nav nav-title" style="line-height: 45px; height: 45px">
      <img style="height: 45px; position: absolute; left: 0; top: 0" src="../../../assets/images/logo.png" alt="">
      <div class="aui-title">卓依婷官方粉丝团</div>
    </header>
    <!--搜索栏结束-->

    <!--轮播图开始-->
    <div id="aui-slide3">
      <div class="aui-slide-wrap" >
        <div class="aui-slide-node bg-dark">
          <img src="http://timizhuo.cn/hengfu11.png" />
        </div>
        <div class="aui-slide-node bg-dark">
          <img src="http://timizhuo.cn/hengfu12.png" />
        </div>
        <div class="aui-slide-node bg-dark">
          <img src="http://timizhuo.cn/hengfu13.png" />
        </div>
        <div class="aui-slide-node bg-dark">
          <img src="http://timizhuo.cn/4.jpg" />
        </div>
      </div>
      <div class="aui-slide-page-wrap"><!--分页容器--></div>
    </div>
    <!--轮播图结束-->

    <!--九宫格开始-->
    <div id="aui-slide">
      <div class="aui-slide-wrap" >
        <div class="aui-slide-node">
          <div class="aui-grid">
            <div class="aui-row">
              <router-link :to="{ name: 'showList'}" class="aui-col-xs-4">
                <div class="aui-dot"></div>
                <i class="aui-iconfont aui-icon-calendar"></i>
                <div class="aui-grid-label">演出活动</div>
              </router-link>
              <router-link :to="{ name: 'photo'}" class="aui-col-xs-4">
                <i class="aui-iconfont aui-icon-image"></i>
                <div class="aui-grid-label">依婷相册</div>
              </router-link>
              <router-link :to="{ name: 'videoList'}" class="aui-col-xs-4">
                <i class="aui-iconfont aui-icon-video"></i>
                <div class="aui-grid-label">依婷视频</div>
              </router-link>
              <a href="https://m.weibo.cn/u/1254461195">
              <div class="aui-col-xs-4">
                  <div class="aui-dot"></div>
                  <i class="aui-iconfont aui-icon-weibo" style="color: rgb(230, 22, 45)"></i>
                  <div class="aui-grid-label">依婷微博</div>
              </div>
              </a>
              <a href="https://m.weibo.cn/u/1801950494">
                <div class="aui-col-xs-4">
                  <i class="aui-iconfont aui-icon-weibo" style="color: rgb(230, 22, 45)"></i>
                  <div class="aui-grid-label">粉丝团微博</div>
                </div>
              </a>
              <div class="aui-col-xs-3" @click="showWechatQrcode">
                <i class="aui-iconfont aui-icon-wechat" style="color: rgb(9, 187, 9)"></i>
                <div class="aui-grid-label">粉丝团微信</div>
              </div>
            </div>
          </div>
        </div>

        <div class="aui-slide-node">
          <div class="aui-grid">
            <div class="aui-row">
              <div class="aui-col-xs-4">
                <i class="aui-iconfont aui-icon-date"></i>
                <div class="aui-grid-label">敬请期待</div>
              </div>
            </div>
          </div>
        </div>

      </div>
      <div class="aui-slide-page-wrap"><!--分页容器--></div>
    </div>
    <!--九宫格结束-->

    <!--演出活动开始-->
    <section class="aui-content aui-margin-b-15">
      <ul class="aui-list aui-media-list">
        <li class="aui-list-header">
          演出活动
          <router-link :to="{ name: 'showList'}" tag="div" class="aui-btn aui-btn-info">
            全部演出活动
          </router-link>
        </li>
        <router-link v-for="timiShow in timiShows" :to="{ name: 'show', query: { id: timiShow.id }}" tag="li" class="aui-list-item">
          <div class="aui-media-list-item-inner">
            <div class="aui-list-item-media" style="width:7rem">
              <img :src="timiShow.imgSrc">
            </div>
            <div class="aui-list-item-inner">
              <div class="aui-list-item-text">
                <div class="aui-list-item-title max-rows1" style="-webkit-box-orient: vertical; display: -webkit-box;">{{ timiShow.showTitle }}</div>
                <div class="aui-list-item-right">{{ timiShow.showCity }}</div>
              </div>
              <div class="aui-list-item-text max-rows2" style="-webkit-box-orient: vertical; display: -webkit-box;">
                <span>{{ timiShow.showDetail}}</span>
              </div>
              <div class="aui-info aui-margin-t-5" style="padding:0">
                <div class="aui-info-item">

                </div>
                <div class="aui-info-item">演出时间：{{ timiShow.showTime }}</div>
              </div>
            </div>
          </div>
        </router-link>
      </ul>
    </section>
    <!--开始活动结束-->

    <!--新浪微博开始-->
    <section class="aui-content">
      <ul class="aui-list aui-media-list">
        <li class="aui-list-header">
          依婷微博 <a href="https://m.weibo.cn/u/1254461195"><div class="aui-btn aui-btn-info">进入依婷微博</div></a>
        </li>
      </ul>
      <div class="aui-card-list" v-for="weibo in weiboData">
        <a :href="weibo.href">
          <div class="aui-card-list-header aui-card-list-user">
            <div class="aui-card-list-user-avatar">
              <img :src="weibo.timiPic" class="aui-img-round">
            </div>
            <div class="aui-card-list-user-name">
              <div class="aui-text-info">卓依婷</div>
              <div class="aui-font-size-14 text-light">#依婷微博</div>
            </div>
            <div class="aui-card-list-user-info text-light">{{ weibo.pushTime }}</div>
          </div>
          <div class="aui-card-list-content-padded aui-padded-t-5 aui-padded-b-5">
          {{ weibo.weiboText }}
        </div>
        </a>
        <div class="aui-card-list-content">
          <div class="aui-row aui-row-padded paddinglr" v-for="imgs in weibo.imgRows">
            <div :class="imgs.colsNum" v-for="img in imgs.cols" :style="img.imgStyle">
              <!--<div :style="img.imgStyle"></div>-->
              <img :src="img.src" class="img-list" @click="showAlbum(weibo.imgs, img.src)">
            </div>
          </div>
        </div>
      </div>
    </section>
    <!--新浪微博结束-->

    <!--依婷相册开始-->
    <section class="aui-content">
      <ul class="aui-list aui-media-list">
        <li class="aui-list-header">
          依婷相册
          <router-link :to="{ name: 'photo'}" class="aui-btn aui-btn-info">查看全部相册</router-link>
        </li>
      </ul>
      <div class="aui-card-list">
        <div class="aui-card-list-content">
          <div class="aui-row aui-row-padded paddinglr" v-for="imgs in timiImg.imgRows">
            <div :class="imgs.colsNum" v-for="img in imgs.cols" :style="img.imgStyle">
              <img :src="img.src" class="img-list" @click="showAlbum(timiImg.imgs, img.src)">
            </div>
          </div>
        </div>
      </div>
    </section>
    <!--依婷相册结束-->

    <!--依婷视频开始-->
    <section class="aui-content">
      <ul class="aui-list aui-media-list">
        <li class="aui-list-header">
          依婷视频
          <router-link :to="{ name: 'videoList'}" class="aui-btn aui-btn-info">查看全部视频</router-link>
        </li>
      </ul>
      <div class="aui-card-list video-list">
        <div class="aui-card-list-content">
          <div class="aui-row aui-row-padded paddinglr" v-for="videoRaw in videoRaws">
            <router-link v-for="col in videoRaw" :to="{ name: 'video', query: { videoId: col.video.videoId }}" :class="col.colsNum">
              <div class="video">
                <img :src="col.video.videoCover" class="img-list">
                <div class="video-time">{{ col.video.videoTime }}</div>
              </div>
              <div class="max-rows1">{{ col.video.videoTitle }}</div>
            </router-link>
          </div>
        </div>
      </div>
    </section>
    <!--依婷视频结束-->

    <!--婷迷论坛-->
    <section class="aui-content">
      <ul class="aui-list aui-media-list">
        <li class="aui-list-header">
          婷迷论坛<div class="aui-btn aui-btn-info">进入婷迷论坛</div>
        </li>
      </ul>
      <div class="aui-card-list" v-for="forum in forums">
        <router-link :to="{ name: 'orterUserHome', query: { userId: forum.userId }}" class="aui-card-list-header aui-card-list-user">
          <div class="aui-card-list-user-avatar">
            <img :src="forum.userImageUrl" class="aui-img-round">
          </div>
          <div class="aui-card-list-user-name">
            <div class="aui-text-info">{{forum.userName}}</div>
            <div class="aui-font-size-14 text-light"></div>
          </div>
          <div class="aui-card-list-user-info text-light">{{forum.pushTime}}</div>
        </router-link>
        <router-link :to="{ name: 'detail', query: {id: forum.id}}">
          <div class="aui-card-list-content-padded aui-padded-t-5 aui-padded-b-5 max-rows3" style="-webkit-box-orient: vertical; display: -webkit-box;" v-html="forum.forumContent">
          </div>
          <div class="aui-card-list-content">
            <div class="aui-row aui-row-padded" v-for="imgs in forum.imgRows">
              <div :class="imgs.colsNum" v-for="img in imgs.cols" :style="img.imgStyle">
                <!--<div :style="img.imgStyle"></div>-->
                <img :src="img.src" class="img-list">
              </div>
            </div>
          </div>
        </router-link>
        <div class="aui-card-list-footer text-light aui-font-size-14">
          <div>
            <i class="aui-iconfont aui-icon-display"></i> {{forum.readCount}}
          </div>
          <router-link :to="{ name: 'detail', query: {id: forum.id, isReply: true}}" style="color: #757575">
            <i class="aui-iconfont aui-icon-comment"></i> {{forum.replyCount}}
          </router-link>
          <div @click="likeCountAdd(forum)" :style="forum.likeStyle">
            <i class="aui-iconfont aui-icon-laud"></i> {{forum.likeCount}}
          </div>
        </div>
      </div>
    </section>
    <!--婷迷论坛-->

    <!--底部工具栏开始-->
    <div style="height: 2.5rem"></div>
    <footer class="aui-bar aui-bar-tab" id="footer" v-if="isShowAuiBar">
      <div class="aui-bar-tab-item aui-active" tapmode>
        <i class="aui-iconfont aui-icon-home"></i>
        <div class="aui-bar-tab-label">首页</div>
      </div>
      <router-link :to="{ name: 'showList'}" tag="div" class="aui-bar-tab-item" tapmode>
        <i class="aui-iconfont aui-icon-calendar"></i>
        <div class="aui-bar-tab-label">演出活动</div>
      </router-link>
      <router-link :to="{ name: 'bbs'}" tag="div" class="aui-bar-tab-item" tapmode>
        <div class="aui-dot"></div>
        <i class="aui-iconfont aui-icon-comment"></i>
        <div class="aui-bar-tab-label">婷迷论坛</div>
      </router-link>
      <router-link :to="{ name: 'userHome'}" tag="div" class="aui-bar-tab-item" tapmode>
        <div v-if="msgNum > 0" class="aui-badge">{{msgNum}}</div>
        <i class="aui-iconfont aui-icon-my"></i>
        <div class="aui-bar-tab-label">我的</div>
      </router-link>
    </footer>
    <!--底部工具类结束-->

    <div class="wechatQrcode" v-if="isShowWechatQrcode" @click="hideWechatQrcode">

      <div class="context">
        <img src="../../../assets/images/qrcode.jpg">
      </div>
    </div>

    <!--相册开始-->
    <!--<v-touch @swipeleft="swiperleft" @swiperight="swiperright">-->
      <div class="album" v-if="album.isShowAlbum" @click="hideAlbum">
        <div class="imgNum">{{ album.currentNum }}/{{ album.countNum }}</div>
        <img :src="album.currentImg" :class="classObject" :style="styleObject" @touchstart="moveStart" @touchmove="moveing" @touchend="moveEnd">
      </div>
    <!--</v-touch>-->
    <!--相册结束-->
  </div>
</template>

<script>
  var homeApp;
  export default {
    data () {
      return {
        timiShows: [],
        weiboData: [],
        timiImg: [],
        videoRaws: [],
        forums: [],
        isShowWechatQrcode: false,
        isShowAuiBar: true,
        album: {},
        classObject: {
          gallery: true,
          preview: false,
          animation: false,
        },
        styleObject: {
          width: '100vw',
          transform: '',
        },
        startOffsetX: '',
        isTouchStart: false,
        screenWidth: 0,//获取当前页面尺寸
        msg: '',
        isShowMsg: false,
        msgNum: 0
      }
    },
    mounted: function(){
      this.GLOBAL.homeApp = this;
      this.init();
    },
    methods: {
      init: function() {
        onload();
        var toast = new auiToast();
        toast.loading({
          title:"加载中",
          duration:2000
        },function(ret){
          console.log(ret);
        });
        this.loadShowData();
        this.loadWeiboData();
        this.loadTimiImgData();
        this.loadTimiVideoData();
        this.loadForumData();
        this.message();
      },
      loadShowData: function() { // 加载演出数据
        var data = {
          pageNum: 1,
          pageSize: 3
        }
        this.axios.post(this.GLOBAL.serviceHost + '/timizhuo/show/findAll',this.qs.stringify(data),{
        })
          .then(function(res){
            var timiShows = [];
            res.data.data.forEach((showDto)=> {
              var showImgArr = showDto.showImgs.split(';');
              var show = {
                id: showDto.id,
                imgSrc: showImgArr[0],
                showTitle: showDto.showTitle,
                showDetail: showDto.showDetail,
                showTime: showDto.showTime,
                showCity: showDto.showCity
              }
              timiShows.push(show);
            });
            this.timiShows = timiShows;
            var toast = new auiToast();
            toast.hide();
          }.bind(this))
          .catch(function(err){
            if(err.response) {
              console.log(err.response)
              //控制台打印错误返回的内容
            }
            var toast = new auiToast();
            toast.hide();
            //bind(this)可以不用
          }.bind(this));
      },
      loadWeiboData: function() { // 加载微博数据
        var data = {
          pageNum: 1,
          pageSize: 3,
          desc: 'created_at'
        }
        this.axios.post(this.GLOBAL.serviceHost + '/timizhuo/weibo/findAll',this.qs.stringify(data),{
          // headers: {
          //   'Content-Type': 'application/x-www-form-urlencoded'
          // }
        })
          .then(function(res){
            var weiboData = [];
            res.data.data.records.forEach((weiboDto)=> {
              var imgs = [];
              if (weiboDto.originalPics != null) {
                imgs = weiboDto.originalPics.split(';');
              }
              var weibo = {
                headImgStyle: {
                  background: 'url(' + weiboDto.timiPic + ') center center / 100% no-repeat'
                },
                timiPic: weiboDto.timiPic,
                href: weiboDto.originalTextUrl,
                pushTime: weiboDto.createdAt,
                weiboText: weiboDto.weiboText,
                imgRows: this.getImgRows(imgs),
                imgs: imgs
              }
              weiboData.push(weibo);
            });
            this.weiboData = weiboData;
            //控制台打印请求成功时返回的数据
            //bind(this)可以不用
          }.bind(this))
          .catch(function(err){
            if(err.response) {
              console.log(err.response)
              //控制台打印错误返回的内容
            }
            //bind(this)可以不用
          }.bind(this));
      },
      loadTimiImgData: function() { // 加载图库数据
        var data = {
          pageNum: 1,
          pageSize: 9
        }
        this.axios.post(this.GLOBAL.serviceHost + '/timizhuo/images/findByColumn',this.qs.stringify(data),{
        })
          .then(function(res){
            if (res.data.code == '200') {
              var timiImagesDtos = res.data.data.records;
              var imgs = [];
              for (var i = 0; i < timiImagesDtos.length; i ++) {
                imgs.push(timiImagesDtos[i].imgUrl);
              }
              this.timiImg.imgs = imgs;
              this.timiImg.imgRows = this.getImgRows(imgs);
            } else {
              alert(res.data.message);
            }
          }.bind(this))
          .catch(function(err){
            if(err.response) {
              console.log(err.response)
              //控制台打印错误返回的内容
            }
            var toast = new auiToast();
            toast.hide();
            //bind(this)可以不用
          }.bind(this));
      },
      loadTimiVideoData: function() {
        var data = {
          pageNum: 1,
          pageSize: 4
        }
        this.axios.post(this.GLOBAL.serviceHost + '/timizhuo/video/randomVideo',this.qs.stringify(data),{
        })
          .then(function(res){
            if (res.data.code == '200') {
              var randomVideos = res.data.data;
              var raws = [];
              var cols = [];
              for (var i = 0; i < randomVideos.length; i ++) {
                var timiVideo = randomVideos[i];
                var video = {
                  videoId: timiVideo.id,
                  videoTitle: timiVideo.videoTitle,
                  videoTime: timiVideo.videoTime,
                  videoCover: timiVideo.videoCover
                }
                var col = {
                  colsNum: 'aui-col-xs-6',
                  video: video
                }
                cols.push(col);
                if (cols.length % 2 == 0) {
                  raws.push(cols);
                  cols = [];
                }
              }
              this.videoRaws = raws;
            } else {
              alert(res.data.message);
            }
          }.bind(this))
          .catch(function(err){
            if(err.response) {
              console.log(err.response)
              //控制台打印错误返回的内容
            }
            var toast = new auiToast();
            toast.hide();
            //bind(this)可以不用
          }.bind(this));
      },
      loadForumData: function () {
        var self = this;
        var data = {
          forumType: null,
          pageNum: 1,
          pageSize: 3
        }
        this.post('/timizhuo/forum/findForum', data, function (res) {
          if (res.data.code == '200') {
            res.data.data.records.forEach((forumDTO)=> {
              if (forumDTO.imageUrl != null && forumDTO.imageUrl != '') {
                forumDTO.imagesUrl = forumDTO.imageUrl.split(",");
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
                forumDTO.imgRows = self.getImgRows(forumDTO.imagesUrl);

              }
              self.forums.push(forumDTO);
            });
          } else {
          }
        }, function (err) {

        });
      },
      likeCountAdd: function(forum) {
        var app = this;
        var data = {
          id : forum.id,
          type: 1
        }
        this.post('/timizhuo/forum/updateLikeAndRead',data, function (res) {
          var toast = new auiToast();
          if (res.data.code == '200') {
            toast.custom({
              title:"点赞成功",
              html:'<i class="aui-iconfont aui-icon-laud"></i>',
              duration:1000
            });
            forum.likeCount ++;
            forum.likeStyle = 'color: red; font-weight: 800'
          } else {
            toast.fail({
              title:"登陆后才能点赞哦！",
              duration:2000
            });
          }
        }, function (err) {

        });
      },
      getImgRows: function(imgs) {
        var num;
        var imagesHeight;
        var colsNum;
        if (imgs.length == 1) {
          num = 1;
          imagesHeight = 500;
          colsNum = 12;
        } else if (imgs.length == 2 || imgs.length == 4) {
          num = 2;
          imagesHeight = 170;
          colsNum = 6;
        } else {
          num = 3;
          imagesHeight = 120;
          colsNum = 4
        }
        var imgRows = [];
        var cols = [];
        for (var i = 1; i <= imgs.length; i ++) {
          var img = {
            imgStyle: {
              height: imagesHeight + 'px'
            },
            src: imgs[i-1]
          }
          cols.push(img);
          if (i % num == 0) {
            var imgs1 = {
              colsNum: 'aui-col-xs-' + colsNum,
              cols: cols
            }
            imgRows.push(imgs1);
            cols = [];
          }
        }
        return imgRows;
      },
      showAlbum: function (images, img) {
        this.isShowAuiBar = false;
        this.album.isShowAlbum = true;
        this.album.currentImg = img;
        if (images != null) {
          this.album.images = images;
          for(var i = 0; i <= images.length; i ++) {
            if (images[i] == img) {
              this.album.currentNum = i + 1;
              this.album.index = i;
              break;
            }
          }
          this.album.countNum = images.length;
        }
      },
      hideAlbum: function () {
        this.isShowAuiBar = true;
        this.album.isShowAlbum = false;
        // this.swiperleft();
      },
      showWechatQrcode: function() {
        this.isShowWechatQrcode = true;
      },
      hideWechatQrcode: function() {
        this.isShowWechatQrcode = false;
      },
      nextImg: function() {
        if (this.album.index == this.album.images.length-1) {
          return;
        }
        this.album.index = this.album.index + 1;
        this.showAlbum(this.album.images, this.album.images[this.album.index]);
      },
      lastImg: function() {
        if (this.album.index == 0) {
          return;
        }
        this.album.index = this.album.index - 1;
        this.showAlbum(this.album.images, this.album.images[this.album.index]);
      },
      preview: function (event) {
        if (!this.classObject.preview) {//查看状态
          this.classObject.preview = true;
          this.styleObject.width = `${this.imgs.length * 100}vw`;
          this.index = event.target.dataset.index-1;
          this.moveBy();
        } else {//预览状态
          this.classObject.preview = false;
          this.styleObject.width = `100vw`;
          this.styleObject.transform = `translate(0, 0)`;
        }
      },
      moveStart: function (event) {
        //触摸开始时, 记住当前手指的位置
        this.startOffsetX = event.changedTouches[0].pageX;
        //手指滑动的时候, 禁止动画
        this.classObject.animation = false;

      },
      moveing: function (event) {
        this.isTouchStart = true;
        //计算手指的水平移动量
        let dx = event.changedTouches[0].pageX - this.startOffsetX;
        //调用move方法, 设置gallery元素的transform, 移动图片
        this.move(dx);
      },
      moveEnd: function (event) {
        if (this.isTouchStart) {
          //移动图片的时候, 需要动画, 动画采用css3的transition实现
          this.classObject.animation = true;
          let dx = event.changedTouches[0].pageX - this.startOffsetX;
          //console.log(event);
          if (Math.abs(dx) > this.screenWidth / 2) {
            //处理临界值
            if (this.album.index == 0) {//第一张图片
              if (dx < 0) {//右滑
                this.nextImg();
              }
            } else if (this.album.index > 0 && this.album.index < this.album.images.length-1) {//在中间部分
              if (dx < 0) {
                this.nextImg();
              } else {
                this.lastImg();
              }
            } else if (this.album.index == this.album.images.length-1){//在最后
              if (dx > 0) {
                this.lastImg();
              }
            }
          }
          //不超过临界值不处理
          this.moveBy();
        }
        this.isTouchStart = false;
      },
      move: function (dx) {//移动动画
        // let offset = -(this.index * this.screenWidth) + dx;
        // this.styleObject.transform = `translate(${offset}px, 0)`;
      },
      moveBy: function () {
        this.styleObject.transform = `translate(${-this.index * 100}vw, 0)`;
        console.log(this.index)
      },
      message: function () {
        var app = this;
        app.post('/timizhuo/userMessage/findUserMessage',{}, function (res) {
          if (res.data.code == '200') {
            app.GLOBAL.msgNum = res.data.data.length;
            app.msgNum = app.GLOBAL.msgNum;
          }
        }, function (err) {

        })
        setInterval(function () {
          app.post('/timizhuo/userMessage/findUserMessage',{}, function (res) {
            if (res.data.code == '200') {
              app.GLOBAL.msgNum = res.data.data.length;
              app.msgNum = app.GLOBAL.msgNum;
            }
          }, function (err) {

          })
        },10000);
      }
    }
  }
  var onload = function () {
    // 顶部搜索栏开始
    // var apiready = function(){
    //   api.parseTapmode();
    // }
    // var searchBar = document.querySelector(".aui-searchbar");
    // var searchBarInput = document.querySelector(".aui-searchbar input");
    // var searchBarBtn = document.querySelector(".aui-searchbar .aui-searchbar-btn");
    // var searchBarClearBtn = document.querySelector(".aui-searchbar .aui-searchbar-clear-btn");
    // if(searchBar){
    //   searchBarInput.onclick = function(){
    //     searchBarBtn.style.marginRight = 0;
    //   }
    //   searchBarInput.oninput = function(){
    //     if(this.value.length){
    //       searchBarClearBtn.style.display = 'block';
    //       searchBarBtn.classList.add("aui-text-info");
    //       searchBarBtn.textContent = "搜索";
    //     }else{
    //       searchBarClearBtn.style.display = 'none';
    //       searchBarBtn.classList.remove("aui-text-info");
    //       searchBarBtn.textContent = "取消";
    //     }
    //   }
    // }
    // searchBarClearBtn.onclick = function(){
    //   this.style.display = 'none';
    //   searchBarInput.value = '';
    //   searchBarBtn.classList.remove("aui-text-info");
    //   searchBarBtn.textContent = "取消";
    // }
    // searchBarBtn.onclick = function(){
    //   var keywords = searchBarInput.value;
    //   if(keywords.length){
    //     searchBarInput.blur();
    //     document.getElementById("search-keywords").textContent = keywords;
    //   }else{
    //     this.style.marginRight = "-"+this.offsetWidth+"px";
    //     searchBarInput.value = '';
    //     searchBarInput.blur();
    //   }
    // }
    // 顶部搜索栏结束

    var slide = new auiSlide({
      container:document.getElementById("aui-slide"),
      // "width":300,
      "height":220,
      "speed":300,
      "pageShow":true,
      "pageStyle":'dot',
      "loop":false,
      'dotPosition':'center',
      currentPage:currentFun
    })

    function currentFun(index) {
      console.log(index);
    }

    var slide3 = new auiSlide({
      container:document.getElementById("aui-slide3"),
      // "width":300,
      "height":200,
      "speed":500,
      "autoPlay": 3000, //自动播放
      "loop":true,
      "pageShow":true,
      "pageStyle":'line',
      'dotPosition':'center'
    })
    console.log(slide3.pageCount());
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

  /*.bgc {*/
    /*background-color: #FFCCFF;*/
  /*}*/
  .aui-grid, .aui-active, a {
    color: rgb(65, 160, 254);
  }

  .paddinglr {
    padding-left: 0.5rem;
    padding-right: 0.5rem;
  }

  /*搜索栏开始*/
  .logo {

  }

  .logo img {
    height: 44px;
  }

  /*搜索栏结束*/

  /*轮播图开始*/
  .bg-dark {
    background: #333333 !important;
  }
  .aui-slide-node img {
    width: 100%;
    height: 100%;
  }
  /*轮播图结束*/

  .max-rows1, .max-rows2, .max-rows3 {
    text-overflow: -o-ellipsis-lastline;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  .max-rows1 {
    -webkit-line-clamp: 1;
  }

  .max-rows2 {
    -webkit-line-clamp: 2;
    text-align: left;
  }

  .max-rows3 {
    -webkit-line-clamp: 3;
  }
  .max-rows2 span{    display: block;
    text-align: left;
    width: 100%;}

  .img-list {
    width:100%;
    height: 100%;
    object-fit: cover;
  }

  .video-list .video img {
    height: 120px;
  }
  .video-list .video {
    position: relative;
  }

  .video-time {
    display: inline-block;
    padding: 3px;
    color: #fff;
    font-style: 10px;
    position: absolute ;
    right: 5px;
    bottom: 5px;
  }

  .wechatQrcode {
    width: 100%;
    height: 100%;
    position: fixed;
    left:0;
    top:0;
    z-index: 99999;
  }

  .album {
    width: 100%;
    height: 100%;
    position: fixed;
    left:0;
    top:0;
    overflow: hidden;
    background-color: #000;
    z-index: 99999;
  }

  .album .imgNum {
    color: #FFF;
    font-size: 1rem;
  }
  .wechatQrcode img {
    width: 80%;
    position: absolute;
    margin: auto;
    top: -9999px;
    right: -9999px;
    bottom: -9999px;
    left: -9999px;
    z-index: 99999;
  }

  .album img {
    width: 100%;
    position: absolute;
    margin: auto;
    top: -9999px;
    right: -9999px;
    bottom: -9999px;
    left: -9999px;
  }
</style>
