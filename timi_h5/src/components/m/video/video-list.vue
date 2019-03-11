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
                    <router-link class="aui-col-xs-12" v-for="video in date.videoDtoList" :to="{ name: 'video', query: { videoId: video.id }}">
                      <div class="video">
                        <img :src="video.videoCover" class="video-list" />
                        <div class="video-time">{{ video.videoTime }}</div>
                      </div>
                      <div>{{video.videoTitle}}</div>
                    </router-link>
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
        isShowMsg: false
      }
    },
    mounted: function(){
      this.GLOBAL.videoListApp = this;
      this.init();
    },
    methods: {
      init: function () {
        this.initTab();
        this.loadVideoList("('1','2')");
      },
      initTab: function () {
        var app = this;
        var tab = new auiTab({
          element:document.getElementById("tab")
        },function(ret){
          if(ret.index == 2) {
            app.loadVideoList("('1')");
          } else if (ret.index == 3) {
            app.loadVideoList("('2')");
          }
        });
      },
      loadVideoList: function (columnType) {
        var data = {
          pageNum: this.pageNum,
          pageSize: 9999,
          columnType: columnType
        }
        this.axios.post(this.GLOBAL.serviceHost + '/timizhuo/video/findByColumnLimit',this.qs.stringify(data),{
        })
          .then(function(res){
            this.years = res.data.data;
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
    height: 100%;
    object-fit: cover;
    height: 140px;
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
