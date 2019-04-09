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
      <div @click="readAndLikeCountAdd(1)">
        <i class="aui-iconfont aui-icon-laud"></i> {{forum.likeCount}}
      </div>
    </div>
    <div>
      <div class="aui-tab" id="tab" style="float: left; width: 40%;  margin-top: 0.5rem;">
        <div class="aui-tab-item aui-active">全部回复</div>
        <div class="aui-tab-item" >只看楼主</div>
      </div>
      <div style="float: right; width: 60%; height: 2.2rem; line-height: 2.2rem; margin-top: 0.5rem; background-color: #fff; text-align: right; padding-right: 0.5rem">
        热门
        <i class="aui-iconfont aui-icon-down"></i>
      </div>
      <div class="clear"></div>
    </div>

    <div v-for="reply in replyList">
      <div class="aui-card-list-header aui-card-list-user" style="background-color: #fff; margin-top: 0.1rem">
        <div class="aui-card-list-user-avatar">
          <img :src="reply.timiReply.userImageUrl" class="aui-img-round">
        </div>
        <div class="aui-card-list-user-name">
          <div class="aui-text-info">{{reply.timiReply.userName}}</div>
        </div>
        <div class="aui-card-list-user-info text-light">第{{reply.timiReply.tierNum}}楼 | {{reply.timiReply.replyTime}}</div>
      </div>
      <div style="padding-left: 3rem;padding-right: 1rem; background-color: #fff;">
        <div @click="showcontext(2, reply.timiReply.id)">
          <div v-html="reply.timiReply.replyContent"></div>
        </div>
        <div style="background-color: #FFECEC; padding: 0.5rem">
          <div v-for="subReply in reply.subTimiReplyList">
            <a href="#">{{subReply.userName}}</a>
            <span v-if="subReply.replyType==3">回复<a href="#">{{subReply.parentName}}</a></span>
            : <span @click="showcontext(3, subReply.id)" v-html="subReply.replyContent"></span>
          </div>
          <div><a href="#">婷迷1</a> : 那还用说，我最喜欢了</div>
          <div><a href="#">婷迷2</a>回复<a href="#">婷迷1</a> : 我也喜欢</div>
          <div><a href="#">婷迷3</a> : 依婷的笑容让我每天都心情很好，永远支持依婷。</div>
        </div>
      </div>
      <div style="height: 1rem; background-color: #fff;"></div>
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
      <div style="background-color: #FFECEC; padding: 0.5rem">
        <div><a href="#">婷迷1</a> : 那还用说，我最喜欢了</div>
        <div><a href="#">婷迷2</a>回复<a href="#">婷迷1</a> : 我也喜欢</div>
        <div><a href="#">婷迷3</a> : 依婷的笑容让我每天都心情很好，永远支持依婷。</div>
      </div>
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
      <div style="background-color: #FFECEC; padding: 0.5rem">
        <div><a href="#">婷迷1</a> : 那还用说，我最喜欢了</div>
        <div><a href="#">婷迷2</a>回复<a href="#">婷迷1</a> : 我也喜欢</div>
        <div><a href="#">婷迷3</a> : 依婷的笑容让我每天都心情很好，永远支持依婷。</div>
      </div>
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
      <div style="background-color: #FFECEC; padding: 0.5rem">
        <div><a href="#">婷迷1</a> : 那还用说，我最喜欢了</div>
        <div><a href="#">婷迷2</a>回复<a href="#">婷迷1</a> : 我也喜欢</div>
        <div><a href="#">婷迷3</a> : 依婷的笑容让我每天都心情很好，永远支持依婷。</div>
      </div>
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
      <div style="background-color: #FFECEC; padding: 0.5rem">
        <div><a href="#">婷迷1</a> : 那还用说，我最喜欢了</div>
        <div><a href="#">婷迷2</a>回复<a href="#">婷迷1</a> : 我也喜欢</div>
        <div><a href="#">婷迷3</a> : 依婷的笑容让我每天都心情很好，永远支持依婷。</div>
      </div>
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
      <div style="background-color: #FFECEC; padding: 0.5rem">
        <div><a href="#">婷迷1</a> : 那还用说，我最喜欢了</div>
        <div><a href="#">婷迷2</a>回复<a href="#">婷迷1</a> : 我也喜欢</div>
        <div><a href="#">婷迷3</a> : 依婷的笑容让我每天都心情很好，永远支持依婷。</div>
      </div>
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
      <div style="background-color: #FFECEC; padding: 0.5rem">
        <div><a href="#">婷迷1</a> : 那还用说，我最喜欢了</div>
        <div><a href="#">婷迷2</a>回复<a href="#">婷迷1</a> : 我也喜欢</div>
        <div><a href="#">婷迷3</a> : 依婷的笑容让我每天都心情很好，永远支持依婷。</div>
      </div>
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
      <img src="http://timizhuo.cn/timi.jpg" alt="" style="margin-top: 0.4rem;">
    </div>

    <div v-if="!isShowcontext" style="height: 1.6rem"></div>
    <div @click="showcontext(1, '')" v-if="!isShowcontext" style="position: fixed ;bottom:0; background-color: #fff; width: 100%; height: 2rem; line-height: 2rem; padding-left: 0.5rem">
      <span style="color: rgb(217, 217, 217)" v-html="contextTips"></span>
    </div>
    <!--<div class="aui-searchbar aui-bar-tab" id="search" :style="contextStyle">-->
    <!--<div class="aui-searchbar-input aui-border-radius">-->
      <!--<div contenteditable="true" id="context" style="background-color: #fff; line-height: 1.5rem" @blur="contextBlur" @input="contextInput" v-model="reply.replyCotent">-->
      <!--</div>-->
    <!--</div>-->
    <!--<div class="aui-btn aui-btn-info" tapmode>发送</div>-->
    <!--</div>-->
    <div :style="contextStyle" style="max-height: 4.5rem; overflow:auto;">
      <div contenteditable="true" id="replyContent" style="background-color: #fff; width: 80%; display: inline-block; max-height: 4.5rem; overflow:auto; vertical-align:top" @blur="contextBlur" @input="contextInput"></div>
      <div id="pushBtu" style="width: 18%; display: inline-block; height: 1.5rem; line-height: 1.5rem; vertical-align:top; text-align: center" @click="pushReply">发布</div>
    </div>
  </div>
</template>

<script>
    export default {
      name: "detail",
      data () {
        return {
          forum: {},
          replyList: [],
          isShowcontext: false,
          havaContexg: false,
          contextStyle : 'display: none',
          contextTips : '说说你的看法……',
          reply: {},
          findReply: {
            pageNum : 1,
            pageSize : 50
          }
        }
      },
      mounted: function(){
        window.scrollTo(0,0);
        this.init();
      },
      methods: {
        init: function () {
          var app = this;
          this.findReply.forumId = this.$route.query.id;
          this.reply.forumId = this.$route.query.id;
          this.loadAuiTab();
          this.loadDetail();
          this.loadReplys();
        },
        readAndLikeCountAdd: function(type) {
          var app = this;
          var data = {
            id : this.$route.query.id,
            type: type
          }
          this.post('/timizhuo/forum/updateLikeAndRead',data, function (res) {
            if (res.data.code == '200') {
              if (type == 1) {
                app.forum.likeCount ++;
              }
            } else {
              console.log(res.data.message);
            }
          }, function (err) {

          });
        },
        initReply: function() {
          this.reply = {}
          this.reply.forumId = this.$route.query.id;
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
        loadDetail: function() {
          var app = this;
          var data = {
            id : this.$route.query.id
          }
          this.post('/timizhuo/forum/findForumById',data, function (res) {
            if (res.data.code == '200') {
              app.forum = res.data.data;
              app.readAndLikeCountAdd(2);
            } else {
              var dialog = new auiDialog();
              dialog.alert({
                title: "提示",
                msg: res.data.message,
                buttons:['确定']
              },function(ret){
              })
            }
          }, function (err) {

          });
        },
        loadReplys: function() {
          var app = this;
          this.post('/timizhuo/reply/findReply',this.findReply, function (res) {
            if (res.data.code == '200') {
              app.replyList = res.data.data.list;
            } else {
              var dialog = new auiDialog();
              dialog.alert({
                title: "提示",
                msg: res.data.message,
                buttons:['确定']
              },function(ret){
              })
            }
          }, function (err) {

          });
        },
        showcontext: function (replyType, parentId) {
          this.initReply();
          this.isShowcontext=true;
          this.contextStyle = '';
          this.reply.replyType = replyType;
          if (parentId != null && parentId != '') {
            this.reply.parentId = parentId;
          }
          setTimeout(()=>document.getElementById("replyContent").focus(), 10);
        },
        contextBlur: function () {
          setTimeout(()=>{
            this.contextStyle = 'display: none';
            this.isShowcontext=false;
            if (!this.havaContexg) {
              this.contextTips ='说说你的看法';
            } else {
              this.contextTips ='[草稿内容待发送]';
            }
          }, 200);

        },
        contextInput: function () {

        },
        pushReply: function () {
          var app = this;
          this.reply.replyContent = document.getElementById("replyContent").innerHTML;
          this.post('/timizhuo/reply/addReply', this.reply, function (res) {
            if (res.data.code == '200') {
              var toast = new auiToast();
              toast.success({
                title:"发布成功",
                duration:500
              });
              document.getElementById("replyContent").innerHTML = '';
              app.loadReplys();
              window.scrollTo(0,0);
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
        }
      }
    }
</script>

<style scoped>
  .clear{ clear:both}
</style>
