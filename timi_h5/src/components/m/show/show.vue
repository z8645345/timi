<template>
    <div>
      <div class="aui-tips aui-margin-b-15" id="tips-1" v-if="isShowMsg" style="position: fixed; top:0;">
        <i class="aui-iconfont aui-icon-info"></i>
        <router-link :to="{ name: 'chatroom'}" tag="div" class="aui-tips-title max-rows1" style="-webkit-box-orient: vertical; display: -webkit-box;" v-html="msg">
        </router-link>
        <i class="aui-iconfont aui-icon-close" tapmode @click="isShowMsg=false"></i>
      </div>
      <header class="aui-bar aui-bar-nav">
        <a @click="goback" class="aui-pull-left aui-btn">
          <span class="aui-iconfont aui-icon-left"></span>返回
        </a>
        <div class="aui-title">卓依婷{{timiShow.showCity}}演出</div>
        <a class="aui-pull-right aui-btn aui-btn-outlined" @click="openActionsheet">
          <span class="aui-iconfont aui-icon-share"></span>
        </a>
      </header>

      <section class="aui-content">
        <div class="aui-card-list aui-border-t aui-border-b">
            <img :src="timiShow.showImgs" v-if="timiShow.showVideo==''" />
            <!--<iframe style="width: 100%; height: 10rem" :src="timiShow.showVideo" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true" v-if="timiShow.showVideo!=''"> </iframe>-->
          <video v-if="timiShow.showVideo!=''" :src="timiShow.showVideo" controls preload="auto" width="100%">
          </video>
          <!--</div>-->
          <div class="aui-card-list-header aui-padded-t-5 aui-padded-b-5">
            {{timiShow.showTitle}}
          </div>
          <div class="aui-card-list-content-padded aui-padded-t-0 aui-padded-b-0" style="color:#7b7b7b;" v-html="timiShow.showDetail">

          </div>
          <div class="aui-card-list-footer aui-font-size-12" style="color:#999;">
            <div>
              <span>演出时间：{{timiShow.showTime}}</span><br> <span>演出地址：{{timiShow.showAddress}}</span>
            </div>
          </div>
        </div>
      </section>
    </div>
</template>

<script>
  export default {
    name: 'show',
    data () {
      return {
        timiShow: {},
        msg: '',
        isShowMsg: false
      }
    },
    mounted: function(){
      this.GLOBAL.showApp = this;
      this.init();
    },
    methods: {
      init: function() {
        var data = {
          id: this.$route.query.id
        }
        this.axios.post(this.GLOBAL.serviceHost + '/timizhuo/show/findById',this.qs.stringify(data),{
        })
          .then(function(res){
            var showDto = res.data.data;
            this.timiShow = showDto;
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
      openActionsheet: function () {
        actionsheet.init({
          frameBounces:true,//当前页面是否弹动，（主要针对安卓端）
          // title:"这里是标题",
          cancelTitle:'取消',
          buttons:['复制链接']
        },function(ret){
          if(ret){
            if (ret.buttonIndex == 1) {
              var oInput = document.createElement('input');
              oInput.value = window.location.href,
              document.body.appendChild(oInput);
              oInput.select(); // 选择对象
              document.execCommand("Copy"); // 执行浏览器复制命令 oInput.className = 'oInput';
              oInput.style.display='none';
              toast.success({
                title:"复制成功",
                duration:2000
              });
            }
          }
        })
      },
      goback: function () {
        this.$router.back(-1)
      }
    }
  }
  var actionsheet = new auiActionsheet();
  var toast = new auiToast();
</script>

<style scoped>

</style>
