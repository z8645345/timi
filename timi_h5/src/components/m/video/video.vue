<template>
  <div>
    <div class="aui-tips aui-margin-b-15" id="tips-1" v-if="isShowMsg" style="position: fixed; top:0;">
      <i class="aui-iconfont aui-icon-info"></i>
      <router-link :to="{ name: 'chatroom'}" tag="div" class="aui-tips-title max-rows1" style="-webkit-box-orient: vertical; display: -webkit-box;" v-html="msg">
      </router-link>
      <i class="aui-iconfont aui-icon-close" tapmode @click="isShowMsg=false"></i>
    </div>
    <section class="aui-content">
      <header class="aui-bar aui-bar-nav nav-title">
        <a href="javascript:history.go(-1)" class="aui-pull-left aui-btn">
          <span class="aui-iconfont aui-icon-left"></span>返回
        </a>
        <div class="aui-title">{{video.videoTitle}}</div>
      </header>
      <div class="aui-card-list aui-border-t aui-border-b">

        <video :src="video.videoContentUrl" controls preload="auto" width="100%">
        </video>
        <div class="aui-card-list-header aui-padded-t-5 aui-padded-b-5">
          {{video.videoTitle}}
        </div>
        <div class="aui-card-list-footer aui-font-size-12" style="color:#999;">
          <div>
            <!--<span>2048 赞</span> - <span>295 评论</span> - <span>去往栏目</span>-->
            光盘提取源文件较大，需要加载一定时间，请耐心等待
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
  export default {
    name: 'video',
    data () {
      return {
        video: {},
        msg: '',
        isShowMsg: false
      }
    },
    mounted: function(){
      this.GLOBAL.videoApp = this;
      this.init();
    },
    methods: {
      init: function () {
        var data = {
          id: this.$route.query.videoId
        }
        this.axios.post(this.GLOBAL.serviceHost + '/timizhuo/video/findById',this.qs.stringify(data),{
        })
          .then(function(res){
            this.video = res.data.data;
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

</style>
