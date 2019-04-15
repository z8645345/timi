<template>
    <div>
      <section class="aui-content">
        <div class="aui-list-header">
          <div style="text-align: center; height: 2rem; font-size: 1rem; line-height: 2rem">
            <i class="aui-iconfont aui-icon-left aui-text-danger" style="float: left;font-size: 1rem; margin-left: 0.5rem" @click="goback"></i>
            未读消息列表
          </div>
        </div>
        <ul class="aui-list aui-list-noborder">
          <li v-for="message in messageList" class="aui-list-item" style="background-color: #fff;" @click="readMessage(message)">
            <div class="aui-list-item-inner aui-list-item-arrow">
              <div class="aui-list-item-title">{{message.text}}</div>
              <div class="aui-list-item-right"></div>
            </div>
          </li>
        </ul>

      </section>
    </div>
</template>

<script>
    export default {
        name: "message",
      data() {
        return {
          messageList: []
        }
      },
      mounted: function() {
        var app = this;
        this.post('/timizhuo/userMessage/findUserMessage',{}, function (res) {
          if (res.data.code == '200') {
            app.messageList = res.data.data;
            for (var i = 0; i < app.messageList.length; i ++) {
              if (app.messageList[i].contentType == 1) {
                app.messageList[i].text = '有人回复你发的帖子'
              } else if (app.messageList[i].contentType == 2) {
                app.messageList[i].text = '有人回复你发表的评论'
              } else if (app.messageList[i].contentType == 3) {
                app.messageList[i].text = '有人回复你发表的评论'
              } else if (app.messageList[i].contentType == 4) {
                app.messageList[i].text = '有人发帖时@了你'
              }
            }
          }
        }, function (err) {

        })
      },
      methods: {
        goback: function () {
          this.$router.go(-1);
        },
        readMessage: function (message) {
          if (message.contentType == 1) {

          } else if (message.contentType == 2) {

          } else if (message.contentType == 3) {

          } else if (message.contentType == 4) { // 主贴消息
            this.$router.push({name:'detail', query: {id: message.forumId}});
          }
        }
      }
    }
</script>

<style scoped>

</style>
