<template>
  <div>
    <div style="margin-left: 0.5rem;margin-right: 0.5rem">{{forum.forumTitle}}</div>
    <div class="aui-card-list-header aui-card-list-user">
      <div class="aui-card-list-user-avatar">
        <img :src="forum.userImageUrl" class="aui-img-round">
      </div>
      <div class="aui-card-list-user-name">
        <div class="aui-text-info">{{forum.userName}}</div>
        <div class="aui-font-size-14 text-light">关注</div>
      </div>
      <div class="aui-card-list-user-info text-light">{{forum.pushTime}}</div>
    </div>
    <div style="margin-left: 0.5rem;margin-right: 0.5rem">
      <div v-html="forum.forumContent"></div>
      <img v-for="imgUrl in forum.imagesUrl" :src="imgUrl" alt="" style="margin-top: 0.4rem; margin-bottom: 0.4rem">
    </div>
    <div class="aui-card-list-footer text-light aui-font-size-14">
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
        }
      }
    }
</script>

<style scoped>

</style>
