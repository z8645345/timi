<template>
  <div>
    <div style="padding-left: 0.5rem;padding-right: 0.5rem; background-color: #fff;"><span v-if="forum.forumType==1" style="color: #0894ec;background-color: #fff;">精·</span>{{forum.forumTitle}}</div>
    <div class="aui-card-list-header aui-card-list-user" style="background-color: #fff;">
      <div class="aui-card-list-user-avatar">
        <img :src="forum.userImageUrl" class="aui-img-round">
      </div>
      <div class="aui-card-list-user-name">
        <div class="aui-text-info">{{forum.userName}}</div>
        <div class="aui-font-size-14 text-light aui-btn aui-btn-info">+关注</div>
      </div>
      <div class="aui-card-list-user-info text-light">{{forum.pushTime}}</div>
    </div>
    <div style="padding-left: 1rem;padding-right: 1rem; background-color: #fff;">
      <div v-html="forum.forumContent"></div>
      <img v-for="imgUrl in forum.imagesUrl" :src="imgUrl" alt="" style="margin-top: 0.4rem; margin-bottom: 0.4rem">
    </div>
    <div class="aui-card-list-footer text-light aui-font-size-14" style="background-color: #fff;">
      <div>
        <i class="aui-iconfont aui-icon-share"></i> 分享
      </div>
      <div>
        <i class="aui-iconfont aui-icon-comment"></i> {{forum.replyCount}}
      </div>
      <div>
        <i class="aui-iconfont aui-icon-laud"></i> {{forum.likeCount}}
      </div>
    </div>
    <div>
      <div class="aui-tab" id="tab" style="float: left; width: 40%">
        <div class="aui-tab-item aui-active">全部回复</div>
        <div class="aui-tab-item" >只看楼主</div>
      </div>
      <div style="float: right; width: 60%; height: 2.2rem; line-height: 2.2rem; margin-top: 0.5rem; background-color: #fff; text-align: right; padding-right: 0.5rem">
        热门
        <i class="aui-iconfont aui-icon-down"></i>
      </div>
      <div class="clear"></div>
    </div>

    <div class="aui-card-list-header aui-card-list-user" style="background-color: #fff; margin-top: 0.1rem">
      <div class="aui-card-list-user-avatar">
        <img :src="forum.userImageUrl" class="aui-img-round">
      </div>
      <div class="aui-card-list-user-name">
        <div class="aui-text-info">{{forum.userName}}</div>
      </div>
      <div class="aui-card-list-user-info text-light">第1楼 | 4:31</div>
    </div>
    <div style="padding-left: 3rem;padding-right: 1rem; background-color: #fff;">
      <div>依婷真美，依婷真美，依婷真美，依婷真美，依婷真美，依婷真美，依婷真美，依婷真美，依婷真美，依婷真美</div>
      <div style="background-color: #FFECEC; padding: 0.5rem"><a href="#">婷迷1</a> : 那还用说，我最喜欢了</div>
    </div>
    <div style="height: 1rem; background-color: #fff;"></div>

    <div class="aui-card-list-header aui-card-list-user" style="background-color: #fff; margin-top: 0.1rem">
      <div class="aui-card-list-user-avatar">
        <img :src="forum.userImageUrl" class="aui-img-round">
      </div>
      <div class="aui-card-list-user-name">
        <div class="aui-text-info">{{forum.userName}}</div>
      </div>
      <div class="aui-card-list-user-info text-light">第2楼 | 4:31</div>
    </div>
    <div style="padding-left: 3rem;padding-right: 1rem; background-color: #fff;">
      <div>我爱依婷，我爱依婷，我爱依婷，我爱依婷，我爱依婷，我爱依婷，我爱依婷，我爱依婷，我爱依婷，我爱依婷</div>
      <img src="http://timizhuo.cn/timi.jpg" alt="" style="margin-top: 0.4rem; margin-bottom: 0.4rem">
    </div>
  </div>
</template>

<script>
    export default {
      name: "detail",
      data () {
        return {
          forum: {}
        }
      },
      mounted: function(){
        this.init();
      },
      methods: {
        init: function () {
          this.forum = this.$route.query;
          this.loadAuiTab();
        },
        loadAuiTab: function() {
          var app = this;
          var apiready = function(){
            api.parseTapmode();
          }
          var tab = new auiTab({
            element:document.getElementById("tab"),
          },function(ret){

          });
        },
      }
    }
</script>

<style scoped>
  .clear{ clear:both}
</style>
