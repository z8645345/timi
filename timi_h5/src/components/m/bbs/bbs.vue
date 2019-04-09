<template>
  <div>

    <!--顶部栏开始-->
    <div class="top">
      <i class="aui-iconfont aui-icon-left aui-btn icon-left" style="position: absolute;
    font-size: 1.3rem;
    color: #fff;
    background-color: rgba(0, 0, 0, 0);
    top: 0.5rem;"></i>
      <i class="aui-iconfont aui-icon-search aui-btn icon-right" style="position: absolute;
    font-size: 1.3rem;
    color: #fff;
    background-color: rgba(0, 0, 0, 0);
    top: 0.5rem;"></i>
    </div>
    <!--顶部栏结束-->

    <!--横幅开始-->
    <div class="banner"></div>
    <!--横幅结束-->

    <!--tab栏开始-->
    <div class="aui-tab" id="tab" style="padding-right: 60%">
      <div class="aui-tab-item aui-active">全部</div>
      <div class="aui-tab-item" >精华</div>
    </div>
    <!--tab栏结束-->

    <div class="aui-refresh-content">
      <!--置顶精华帖开始-->
      <div class="roof-placement aui-content aui-margin-b-10">
        <ul class="aui-list">
          <router-link v-for="forum in stick" :to="{ name: 'detail', query: {id: forum.id}}" class="aui-list-item" tag="li">
            <div class="aui-list-item-inner aui-swipe-handle roof-placement-title">
            <span class="max-rows1" style="-webkit-box-orient: vertical; display: -webkit-box;">
              <span class="label">置顶</span>{{forum.forumTitle}}
            </span>
            </div>
          </router-link>
        </ul>
      </div>
      <!--置顶精华帖结束-->

      <section class="aui-content">
        <div class="aui-card-list" v-for="forum in list">
          <div class="aui-card-list-header aui-card-list-user">
            <div class="aui-card-list-user-avatar">
              <img :src="forum.userImageUrl" class="aui-img-round">
            </div>
            <div class="aui-card-list-user-name">
              <div class="aui-text-info">{{forum.userName}}</div>
              <div class="aui-font-size-14 text-light">#话题</div>
            </div>
            <div class="aui-card-list-user-info text-light">{{forum.pushTime}}</div>
          </div>
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
            <div>
              <i class="aui-iconfont aui-icon-comment"></i> {{forum.replyCount}}
            </div>
            <div>
              <i class="aui-iconfont aui-icon-laud"></i> {{forum.likeCount}}
            </div>
          </div>
        </div>
      </section>
    </div>

    <div style="height: 2.2rem"></div>
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
      <router-link :to="{ name: 'posting'}" tag="div" class="aui-bar-tab-item" tapmode>
        <div class="aui-bar-tab-label">
          <div class="aui-btn add-btn" style="border-radius: 50%;
    width: 2rem;
    height: 2rem;
    line-height: 2rem;
    font-size: 1.5rem;
    background-color: rgb(0, 185, 230);
    color: #fff;
    text-align: center;
    font-weight: 300;">
            +
          </div>
        </div>
      </router-link>
      <div class="aui-bar-tab-item aui-active" tapmode>
        <div class="aui-dot"></div>
        <i class="aui-iconfont aui-icon-comment"></i>
        <div class="aui-bar-tab-label">婷迷论坛</div>
      </div>
      <router-link :to="{ name: 'userHome'}" tag="div" class="aui-bar-tab-item" tapmode>
        <div class="aui-dot"></div>
        <i class="aui-iconfont aui-icon-my"></i>
        <div class="aui-bar-tab-label">我的</div>
      </router-link>
    </footer>
    <!--底部工具类结束-->
  </div>
</template>

<script>
  var app;
  export default {
    components: {

    },
    data () {
      return {
        page: {
          forumType: null,
          pageNum: 0,
          pageSize: 10
        },
        stick: [],
        list: []
      }
    },
    mounted () {
      this.init();
    },
    methods: {
      init: function() {
        app = this;
        this.auiPullToRefresh();
        this.loadAuiTab();
        this.loadStickData();
        this.loadListData(this.scroll());
      },
      auiPullToRefresh: function() {
        var app = this;
        var pullRefresh = new auiPullToRefresh({
          container: document.querySelector('.aui-refresh-content'),
          triggerDistance: 100
        },function(ret){
          if(ret.status=="success"){
            app.loadStickData();
            app.page.pageNum = 0;
            app.list = [];
            app.loadListData(function () {
              pullRefresh.cancelLoading(); //刷新成功后调用此方法隐藏
            });
          }
        });
      },
      loadAuiTab: function() {
        var apiready = function(){
          api.parseTapmode();
        }
        var tab = new auiTab({
          element:document.getElementById("tab"),
        },function(ret){
          if (ret.index == 1) {
            app.loaddAll();
          } else if (ret.index == 2) {
            app.loadJin();
          }
        });
      },
      loadStickData: function () {
        var app = this;
        app.post('/timizhuo/forum/findForumByStick', {}, function (res) {
          if (res.data.code == '200') {
            app.stick = res.data.data;
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
      loaddAll: function() {
        this.page.forumType = null;
        this.list = [];
        this.page.pageNum = 0;
        this.loadListData();
      },
      loadJin: function() {
        this.page.forumType = 1;
        this.list = [];
        this.page.pageNum = 0;
        this.loadListData();
      },
      loadListData: function (cellBack) {
        var self = this;
        app.page.pageNum++;
        app.post('/timizhuo/forum/findForum', app.page, function (res) {
          if (res.data.code == '200') {
            res.data.data.list.forEach((forumDTO)=> {
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
                forumDTO.imgRows = app.getImgRows(forumDTO.imagesUrl);

              }

              self.list.push(forumDTO);
            });
            setTimeout(()=>cellBack(), 100);
          } else {
            var dialog = new auiDialog();
            dialog.alert({
              title: "提示",
              msg: "请先登录！",
              buttons:['确定']
            },function(ret){ƒ
              self.$router.push({name:'login'});
            })
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
        if (imgs.length > 1) {
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
        } else {
          var img = {
            imgStyle: {
            },
            src: imgs[0]
          }
          cols.push(img);
          var imgs1 = {
            colsNum: 'aui-col-xs-' + colsNum,
            cols: cols
          }
          imgRows.push(imgs1);
        }

        return imgRows;
      },
      scroll: function () {
        var app = this;
        var scroll = new auiScroll({
          listen:true,
          distance:0 //判断到达底部的距离，isToBottom为true
        },function(ret){
          if(ret.isToBottom){
            app.loadListData();
          }
        });
      }
    }
  }
</script>

<style>
  body {
    background-color:  rgb(245, 245, 245);
  }

  .icon-right {
    right: 0.8rem;
  }

  .icon-left:hover, .icon-right:hover {
    background-color: rgba(0, 0, 0, 0);
  }

  .banner {
    height: 7rem;
    background: url("http://timizhuo.cn/tiebabgi.jpg") center center no-repeat;
    background-size: auto 100%;
  }
  .aui-tab, .roof-placement {
    background-color: #fff;
  }

  .aui-tab {
    margin-top: 0.5rem;
    padding-left: 0.5rem;
    padding-right: 0.5rem;
    z-index: 11;
  }

  .roof-placement .label {
    color: rgb(58, 103, 168);
    padding-right: 0.5rem;
  }
  .roof-placement-title {
    font-size: 0.6rem;
  }

  .max-rows1{
    text-overflow: -o-ellipsis-lastline;
    overflow: hidden;
    text-overflow: ellipsis;
    -webkit-line-clamp: 1;
  }

  .aui-card-list {
    margin-bottom: 0.2rem;
  }

  .tabDiv {
    width: 1.2rem;
    height: 1.2rem;
    position: absolute;
    background-color: #fff;
    left: 50%;
    top: 50%;
    visibility: visible;
    margin-left: -0.6rem;
    margin-top: -0.6rem;
    z-index: 11;
  }

  .img-list {
    width:100%;
    height: 100%;
    object-fit: cover;
  }
</style>
