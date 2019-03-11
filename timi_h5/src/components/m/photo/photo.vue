<template>
  <div>
    <div class="aui-tips aui-margin-b-15" id="tips-1" v-if="isShowMsg" style="position: fixed; top:0;">
      <i class="aui-iconfont aui-icon-info"></i>
      <router-link :to="{ name: 'chatroom'}" tag="div" class="aui-tips-title max-rows1" style="-webkit-box-orient: vertical; display: -webkit-box;" v-html="msg">
      </router-link>
      <i class="aui-iconfont aui-icon-close" tapmode @click="isShowMsg=false"></i>
    </div>
    <div style="height: 2.25rem"></div>
    <header class="aui-bar aui-bar-nav nav-title">
      <a href="javascript:history.go(-1)" class="aui-pull-left aui-btn">
        <span class="aui-iconfont aui-icon-left"></span>返回
      </a>
      <div class="aui-title">卓依婷相册</div>
    </header>
    <section class="aui-content">
      <div class="aui-timeline">
        <div v-for="year in years">
          <div class="aui-timeline-item-header">{{ year.year }}</div>
          <div class="aui-timeline-item" v-for="date in year.dateData">
            <div class="aui-timeline-item-label aui-bg-warning text-light">{{date.data}}</div>
            <div class="aui-timeline-item-inner">
              <div class="aui-card-list">
                <div class="aui-card-list-header aui-border-b">{{date.title}}</div>
                <div class="aui-card-list-content-padded">
                  <div class="aui-row aui-row-padded">
                    <div class="aui-col-xs-6" v-for="img in date.imagesDtoList ">
                      <img :src="img.imgUrl" class="img-list" @click="showAlbum(img.imgUrl)" />
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

    </section>

    <!--相册开始-->
    <div class="album" v-if="album.isShowAlbum" @click="hideAlbum">
      <div class="imgNum">{{ album.imgIndex+1 }}/{{ albums[this.album.albumsIndex].length }}</div>
      <img :src="album.currentImg" :class="classObject" :style="styleObject" @touchstart="moveStart" @touchmove="moveing" @touchend="moveEnd">
      <div class="imgText">{{album.text}}</div>
    </div>
    <!--相册结束-->
  </div>
</template>

<script>
  export default {
    name: 'photo',
    data () {
      return {
        pageNum: 1,
        years: [],
        album: {
          isShowAlbum: false
        },
        albums: [],
        timiImgs: [],
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
        isShowMsg: false
      }
    },
    mounted: function(){
      this.GLOBAL.photoApp = this;
      this.init();
    },
    methods: {
      init: function () {
        var data = {
          pageNum: this.pageNum,
          pageSize: 100
        }
        this.axios.post(this.GLOBAL.serviceHost + '/timizhuo/images/findByColumnLimit',this.qs.stringify(data),{
        })
          .then(function(res){
            this.years = res.data.data;
            var albums = [];
            for (var i=0; i < this.years.length; i ++) {
              for (var j=0; j < this.years[i].dateData.length; j ++) {
                var album = [];
                for (var k=0; k < this.years[i].dateData[j].imagesDtoList.length; k ++) {
                  var img = {
                    imgUrl: this.years[i].dateData[j].imagesDtoList[k].imgUrl,
                    text: this.years[i].dateData[j].title
                  }
                  album.push(img);
                }
                albums.push(album);
              }
            }
            this.albums = albums;

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
      showAlbum: function(img) {
        for(var i = 0; i < this.albums.length; i ++) {
          var isBreak = false;
          for(var j = 0; j < this.albums[i].length; j ++) {
            if (this.albums[i][j].imgUrl == img) {
              this.album.albumsIndex = i;
              this.album.imgIndex = j;
              isBreak = true;
              break;
            }
          }
          if (isBreak) {
            break;
          }
        }
        this.showAlbumImg();
      },
      showAlbumImg: function() {
        this.album.isShowAlbum = true;
        var img = this.albums[this.album.albumsIndex][this.album.imgIndex];
        this.album.currentImg = img.imgUrl;
        this.album.text = img.text;
        this.album.countNum = this.albums[this.album.albumsIndex].length;
        this.album.currentNum = this.album.imgIndex+1;
      },
      hideAlbum: function () {
        this.isShowAuiBar = true;
        this.album.isShowAlbum = false;
      },
      nextImg: function() {
        var img;
        if (this.album.imgIndex == this.albums[this.album.albumsIndex].length -1) { // 当前相册最后一张图片
          if (this.album.albumsIndex == this.albums.length - 1) {
            return;
          }
          // 还有下一个相册
          this.album.imgIndex = 0;
          img = this.albums[++this.album.albumsIndex][this.album.imgIndex];
        } else {
          img = this.albums[this.album.albumsIndex][++this.album.imgIndex];
        }
        this.album.currentImg = img.imgUrl;
        this.album.text = img.text;
      },
      lastImg: function() {
        if (this.album.imgIndex == 0) {
          if (this.album.albumsIndex == 0) {
            return;
          }
          // 还有上一个相册
          --this.album.albumsIndex;
          this.album.imgIndex = this.albums[this.album.albumsIndex].length - 1;
          img = this.albums[this.album.albumsIndex][this.album.imgIndex];
        } else {
          var img = this.albums[this.album.albumsIndex][--this.album.imgIndex];
        }
        this.album.currentImg = img.imgUrl;
        this.album.text = img.text;
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
            if (dx < 0) {
              this.nextImg();
            } else {
              this.lastImg();
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
      }
    }
  }
</script>

<style scoped>
  .nav-title {
    position:fixed;
    top: 0;
    overflow:hidden;
  }

  .img-list {
    width:100%;
    height: 100%;
    object-fit: cover;
    height: 140px;
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
    position:absolute;
    top: 1%;
  }

  .imgText {
    color: #FFF;
    font-size: 0.8rem;
    position:absolute;
    bottom:0px;
    width:100%;
    height:auto;
    z-index:2;
    overflow:hidden;
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
