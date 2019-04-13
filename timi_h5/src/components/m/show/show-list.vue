<template>
  <div class="main">
    <div class="aui-tips aui-margin-b-15" id="tips-1" v-if="isShowMsg" style="position: fixed; top:0;">
      <i class="aui-iconfont aui-icon-info"></i>
      <router-link :to="{ name: 'chatroom'}" tag="div" class="aui-tips-title max-rows1" style="-webkit-box-orient: vertical; display: -webkit-box;" v-html="msg">
      </router-link>
      <i class="aui-iconfont aui-icon-close" tapmode @click="isShowMsg=false"></i>
    </div>
    <!--搜索栏开始-->
    <div style="height: 2.25rem"></div>
    <header class="aui-bar aui-bar-nav nav-title" v-if="isShowTitle">
      <a @click="goback" class="aui-pull-left aui-btn">
        <span class="aui-iconfont aui-icon-left"></span>返回
      </a>
      <div class="aui-title">卓依婷演出活动列表</div>
    </header>
    <header class="aui-bar aui-bar-nav" :style="searchStyle">
      <a class="aui-pull-left aui-btn" @click="showTitle">
        <span class="aui-iconfont aui-icon-left"></span>
      </a>

    </header>
    <!--搜索栏结束-->

    <!--演出活动开始-->
    <section class="aui-content aui-margin-b-15">
      <ul class="aui-list aui-media-list">
        <router-link v-for="timiShow in timiShows" :to="{ name: 'show', query: { id: timiShow.id }}" tag="li" class="aui-list-item">
          <div class="aui-media-list-item-inner">
            <div class="aui-list-item-media" style="width:7rem">
              <img :src="timiShow.imgSrc">
            </div>
            <div class="aui-list-item-inner">
              <div class="aui-list-item-text">
                <div class="aui-list-item-title max-rows1" style="-webkit-box-orient: vertical; display: -webkit-box;">{{ timiShow.showTitle }}</div>
                <div class="aui-list-item-right">{{ timiShow.showCity }}</div>
              </div>
              <div class="aui-list-item-text max-rows2" style="-webkit-box-orient: vertical; display: -webkit-box;">
                <span v-html="timiShow.showDetail"></span>
              </div>
              <div class="aui-info aui-margin-t-5" style="padding:0">
                <div class="aui-info-item">

                </div>
                <div class="aui-info-item">演出时间：{{ timiShow.showTime }}</div>
              </div>
            </div>
          </div>
        </router-link>
      </ul>
    </section>
    <!--开始活动结束-->
  </div>
</template>

<script>
  export default {
    data () {
      return {
        timiShows: [],
        isShowTitle: true,
        searchStyle: 'display: none',
        msg: '',
        isShowMsg: false
      }
    },
    mounted: function(){
      this.GLOBAL.showListApp = this;
      this.init();
    },
    methods: {
      init: function() {
        onload();
        var toast = new auiToast();
        toast.loading({
          title:"加载中",
          duration:2000
        },function(ret){
          console.log(ret);
        });
        this.loadShowData();
      },
      loadShowData: function() { // 加载演出数据
        var data = {
          pageNum: 1,
          pageSize: 9999
        }
        this.axios.post(this.GLOBAL.serviceHost + '/timizhuo/show/findAll',this.qs.stringify(data),{
        })
          .then(function(res){
            var timiShows = [];
            res.data.data.forEach((showDto)=> {
              var showImgArr = showDto.showImgs.split(';');
              var show = {
                id: showDto.id,
                imgSrc: showImgArr[0],
                showTitle: showDto.showTitle,
                showDetail: showDto.showDetail,
                showTime: showDto.showTime,
                showCity: showDto.showCity
              }
              timiShows.push(show);
            });
            this.timiShows = timiShows;
            var toast = new auiToast();
            toast.hide();
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
      showTitle: function () {
        this.isShowTitle = true;
        this.searchStyle = 'display: none';
      },
      showSearch: function () {
        this.isShowTitle = false;
        this.searchStyle = '';
      },
      goback: function () {
        this.$router.back(-1)
      }
    }
  }
  var onload = function () {
    // 顶部搜索栏开始

    // 顶部搜索栏结束
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .nav-title {
    position:fixed;
    top: 0;
    z-index:2;
    overflow:hidden;
  }

  .max-rows1, .max-rows2, .max-rows3 {
    text-overflow: -o-ellipsis-lastline;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  .max-rows1 {
    -webkit-line-clamp: 1;
  }

  .max-rows2 {
    -webkit-line-clamp: 2;
    text-align: left;
  }
</style>
