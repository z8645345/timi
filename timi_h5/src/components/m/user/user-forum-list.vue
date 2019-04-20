<template>
  <div>
    <section class="aui-content">
      <div class="aui-list-header">
        <div style="text-align: center; height: 2rem; font-size: 1rem; line-height: 2rem">
          <i class="aui-iconfont aui-icon-left aui-text-danger" style="float: left;font-size: 1rem; margin-left: 0.5rem" @click="goback"></i>
          发过的帖子
        </div>
      </div>
      <ul class="aui-list aui-list-noborder">
        <router-link v-for="timiForum in forumList" :to="{ name: 'detail', query: {id: timiForum.id}}" tag="li" class="aui-list-item" style="background-color: #fff;">
          <div class="aui-list-item-inner aui-list-item-arrow">
            <div v-if="timiForum.forumTitle != ''" class="aui-list-item-title" style="-webkit-box-orient: vertical;
                                                    display: -webkit-box;
                                                    -webkit-line-clamp: 1;
                                                    text-overflow: -o-ellipsis-lastline;
                                                    overflow: hidden;
                                                    text-overflow: ellipsis;;
                                                    width: 75%">{{timiForum.forumTitle}}</div>
            <div v-else class="aui-list-item-title" v-html="timiForum.forumContent" style="-webkit-box-orient: vertical;
                                                    display: -webkit-box;
                                                    -webkit-line-clamp: 1;
                                                    text-overflow: -o-ellipsis-lastline;
                                                    overflow: hidden;
                                                    text-overflow: ellipsis;;
                                                    width: 75%"></div>
            <div class="aui-list-item-right">{{timiForum.postedTime}}</div>
          </div>
        </router-link>
      </ul>

    </section>
  </div>
</template>

<script>
    export default {
        name: "user-forum-list",
      data() {
        return {
          forumList: []
        }
      },
      mounted: function() {
        this.loadForumList();
      },
      methods: {
        goback: function () {
          this.$router.go(-1);
        },
        loadForumList: function () {
          var app = this;
          var data = {
            userId: this.$route.query.userId
          }
          this.post('/timizhuo/forum/findForumByUserId', data, function (res) {
            if (res.data.code == '200') {
              app.forumList = res.data.data;
            } else {

            }
          }, function (err) {

          });
        }
      }
    }
</script>

<style scoped>

</style>
