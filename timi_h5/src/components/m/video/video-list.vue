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
      <div class="aui-title">卓依婷视频</div>
    </header>
    <div class="aui-tab" id="tab">
      <div class="aui-tab-item aui-active">全部视频</div>
      <div class="aui-tab-item"><div></div>MV视频</div>
      <div class="aui-tab-item">演出视频</div>
    </div>
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
                    <div class="aui-col-xs-12" v-for="video in date.videoDtoList">
                      <a :href="video.videoContentUrl">
                        <div class="video">
                          <img :src="video.videoCover" class="video-list" />
                          <div class="video-time">{{ video.videoTime }}</div>
                        </div>
                        <div>{{video.videoTitle}}</div>
                      </a>
                    </div>
                  </div>

                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

    </section>
  </div>
</template>

<script>
  export default {
    name: 'video-list',
    data () {
      return {
        pageNum: 1,
        years: [],
        msg: '',
        isShowMsg: false,
        columnType: "1,2"
      }
    },
    mounted: function(){
      this.GLOBAL.videoListApp = this;
      this.init();
    },
    methods: {
      init: function () {
        this.initTab();
        this.loadVideoList(this.columnType);
        this.scroll();
      },
      initTab: function () {
        var app = this;
        var tab = new auiTab({
          element:document.getElementById("tab")
        },function(ret){
          app.resert();
          if(ret.index == 1) {
            app.columnType = "1,2";
            app.loadVideoList(app.columnType);
          } else if(ret.index == 2) {
            app.columnType = "1";
            app.loadVideoList(app.columnType);
          } else if (ret.index == 3) {
            app.columnType = "2";
            app.loadVideoList(app.columnType);
          }
        });
      },
      loadVideoList: function (columnType) {
        var app = this;
        var data = {
          pageNum: this.pageNum++,
          pageSize: 1,
          columnType: columnType
        }
        this.axios.post(this.GLOBAL.serviceHost + '/timizhuo/video/findByColumnLimit',this.qs.stringify(data),{
        })
          .then(function(res){
            for (var i = 0; i < res.data.data.length; i ++) {
              this.years.push(res.data.data[i])
            }
            setTimeout(()=>{
              if (!this.hasScrollbar()) {
                app.loadVideoList(app.columnType);
              }
            }, 100);
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
      hasScrollbar: function() {
        return document.body.scrollHeight > (window.innerHeight || document.documentElement.clientHeight);
      },
      scroll: function () {
        var app = this;
        var scroll = new auiScroll({
          listen:true,
          distance:0 //判断到达底部的距离，isToBottom为true
        },function(ret){
          if(ret.isToBottom){
            app.loadVideoList(app.columnType);
          }
        });
      },
      resert: function () {
        this.years = [];
        this.pageNum = 1;
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

  .video-list {
    width:100%;
  }

  .video {
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
</style>
