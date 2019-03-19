<template>
  <div>

    <!--顶部栏开始-->
    <div class="top">
      <i class="aui-iconfont aui-icon-left aui-btn icon-left"></i>
      <i class="aui-iconfont aui-icon-search aui-btn icon-right"></i>
    </div>
    <!--顶部栏结束-->

    <!--横幅开始-->
    <div class="banner"></div>
    <!--横幅结束-->

    <!--tab栏开始-->
    <div class="aui-tab" id="tab">
      <div class="aui-tab-item aui-active">全部</div>
      <div class="aui-tab-item">精华</div>
      <div class="aui-tab-item"></div>
      <div class="aui-tab-item"></div>
      <div class="aui-tab-item"></div>
      <div class="aui-tab-item"></div>
      <div class="tabDiv"></div>
    </div>
    <!--tab栏结束-->

    <div class="aui-refresh-content">
      <!--置顶精华帖开始-->
      <div class="roof-placement aui-content aui-margin-b-10">
        <ul class="aui-list">
          <li class="aui-list-item" v-for="forum in stick">
            <div class="aui-list-item-inner aui-swipe-handle roof-placement-title">
            <span class="max-rows1" style="-webkit-box-orient: vertical; display: -webkit-box;">
              <span class="label">置顶</span>{{forum.forumTitle}}
            </span>
            </div>
          </li>
        </ul>
      </div>
      <!--置顶精华帖结束-->

      <section class="aui-content">
        <div class="aui-card-list">
          <div class="aui-card-list-header aui-card-list-user">
            <div class="aui-card-list-user-avatar">
              <img src="../../../assets/images/timi.jpg" class="aui-img-round">
            </div>
            <div class="aui-card-list-user-name">
              <div class="aui-text-info">丁小酷同学</div>
              <div class="aui-font-size-14 text-light">#比帅</div>
            </div>
            <div class="aui-card-list-user-info text-light">31分钟前</div>
          </div>
          <div class="aui-card-list-content-padded aui-padded-t-5 aui-padded-b-5 max-rows3" style="-webkit-box-orient: vertical; display: -webkit-box;">
            timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅
          </div>
          <div class="aui-card-list-content">
            <div class="aui-row aui-row-padded">
              <div class="aui-col-xs-4">
                <img src="../../../assets/images/timi.jpg">
              </div>
              <div class="aui-col-xs-4">
                <img src="../../../assets/images/timi.jpg">
              </div>
              <div class="aui-col-xs-4">
                <img src="../../../assets/images/timi.jpg">
              </div>
            </div>
          </div>
          <div class="aui-card-list-footer text-light aui-font-size-14">
            <div>
              <i class="aui-iconfont aui-icon-display"></i> 888
            </div>
            <div>
              <i class="aui-iconfont aui-icon-comment"></i> 666
            </div>
            <div>
              <i class="aui-iconfont aui-icon-laud"></i> 888
            </div>
          </div>
        </div>
        <div class="aui-card-list">
          <div class="aui-card-list-header aui-card-list-user">
            <div class="aui-card-list-user-avatar">
              <img src="../../../assets/images/timi.jpg" class="aui-img-round">
            </div>
            <div class="aui-card-list-user-name">
              <div class="aui-text-info">董小星</div>
              <div class="aui-font-size-14 text-light">#比帅</div>
            </div>
            <div class="aui-card-list-user-info text-light">31分钟前</div>
          </div>
          <div class="aui-card-list-content-padded aui-padded-t-5 aui-padded-b-5 max-rows3" style="-webkit-box-orient: vertical; display: -webkit-box;">
            timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅
          </div>
          <div class="aui-card-list-content">
            <div class="aui-row aui-row-padded">
              <div class="aui-col-xs-12">
                <img src="../../../assets/images/timi.jpg">
              </div>
            </div>
          </div>
          <div class="aui-card-list-footer text-light aui-font-size-14">
            <div>
              <i class="aui-iconfont aui-icon-display"></i> 888
            </div>
            <div>
              <i class="aui-iconfont aui-icon-comment"></i> 666
            </div>
            <div>
              <i class="aui-iconfont aui-icon-laud"></i> 888
            </div>
          </div>
        </div>
        <div class="aui-card-list">
          <div class="aui-card-list-header aui-card-list-user">
            <div class="aui-card-list-user-avatar">
              <img src="../../../assets/images/timi.jpg" class="aui-img-round">
            </div>
            <div class="aui-card-list-user-name">
              <div class="aui-text-info">蛋蛋的圈圈</div>
              <div class="aui-font-size-14 text-light">#最美的人</div>
            </div>
            <div class="aui-card-list-user-info text-light">31分钟前</div>
          </div>
          <div class="aui-card-list-content-padded aui-padded-t-5 aui-padded-b-5 max-rows3" style="-webkit-box-orient: vertical; display: -webkit-box;">
            依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美
          </div>
          <div class="aui-card-list-content">
            <div class="aui-row aui-row-padded">
              <div class="aui-col-xs-6">
                <img src="../../../assets/images/timi.jpg">
              </div>
              <div class="aui-col-xs-6">
                <img src="../../../assets/images/timi.jpg">
              </div>
            </div>
          </div>
          <div class="aui-card-list-footer text-light aui-font-size-14">
            <div>
              <i class="aui-iconfont aui-icon-display"></i> 888
            </div>
            <div>
              <i class="aui-iconfont aui-icon-comment"></i> 666
            </div>
            <div>
              <i class="aui-iconfont aui-icon-laud"></i> 888
            </div>
          </div>
        </div>
        <div class="aui-card-list">
          <div class="aui-card-list-header aui-card-list-user">
            <div class="aui-card-list-user-avatar">
              <img src="../../../assets/images/timi.jpg" class="aui-img-round">
            </div>
            <div class="aui-card-list-user-name">
              <div class="aui-text-info">丁小酷同学</div>
              <div class="aui-font-size-14 text-light">#比帅</div>
            </div>
            <div class="aui-card-list-user-info text-light">31分钟前</div>
          </div>
          <div class="aui-card-list-content-padded aui-padded-t-5 aui-padded-b-5 max-rows3" style="-webkit-box-orient: vertical; display: -webkit-box;">
            timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅
          </div>
          <div class="aui-card-list-content">
            <div class="aui-row aui-row-padded">
              <div class="aui-col-xs-4">
                <img src="../../../assets/images/timi.jpg">
              </div>
              <div class="aui-col-xs-4">
                <img src="../../../assets/images/timi.jpg">
              </div>
              <div class="aui-col-xs-4">
                <img src="../../../assets/images/timi.jpg">
              </div>
            </div>
          </div>
          <div class="aui-card-list-footer text-light aui-font-size-14">
            <div>
              <i class="aui-iconfont aui-icon-display"></i> 888
            </div>
            <div>
              <i class="aui-iconfont aui-icon-comment"></i> 666
            </div>
            <div>
              <i class="aui-iconfont aui-icon-laud"></i> 888
            </div>
          </div>
        </div>
        <div class="aui-card-list">
          <div class="aui-card-list-header aui-card-list-user">
            <div class="aui-card-list-user-avatar">
              <img src="../../../assets/images/timi.jpg" class="aui-img-round">
            </div>
            <div class="aui-card-list-user-name">
              <div class="aui-text-info">董小星</div>
              <div class="aui-font-size-14 text-light">#比帅</div>
            </div>
            <div class="aui-card-list-user-info text-light">31分钟前</div>
          </div>
          <div class="aui-card-list-content-padded aui-padded-t-5 aui-padded-b-5 max-rows3" style="-webkit-box-orient: vertical; display: -webkit-box;">
            timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅
          </div>
          <div class="aui-card-list-content">
            <div class="aui-row aui-row-padded">
              <div class="aui-col-xs-12">
                <img src="../../../assets/images/timi.jpg">
              </div>
            </div>
          </div>
          <div class="aui-card-list-footer text-light aui-font-size-14">
            <div>
              <i class="aui-iconfont aui-icon-display"></i> 888
            </div>
            <div>
              <i class="aui-iconfont aui-icon-comment"></i> 666
            </div>
            <div>
              <i class="aui-iconfont aui-icon-laud"></i> 888
            </div>
          </div>
        </div>
        <div class="aui-card-list">
          <div class="aui-card-list-header aui-card-list-user">
            <div class="aui-card-list-user-avatar">
              <img src="../../../assets/images/timi.jpg" class="aui-img-round">
            </div>
            <div class="aui-card-list-user-name">
              <div class="aui-text-info">蛋蛋的圈圈</div>
              <div class="aui-font-size-14 text-light">#最美的人</div>
            </div>
            <div class="aui-card-list-user-info text-light">31分钟前</div>
          </div>
          <div class="aui-card-list-content-padded aui-padded-t-5 aui-padded-b-5 max-rows3" style="-webkit-box-orient: vertical; display: -webkit-box;">
            依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美
          </div>
          <div class="aui-card-list-content">
            <div class="aui-row aui-row-padded">
              <div class="aui-col-xs-6">
                <img src="../../../assets/images/timi.jpg">
              </div>
              <div class="aui-col-xs-6">
                <img src="../../../assets/images/timi.jpg">
              </div>
            </div>
          </div>
          <div class="aui-card-list-footer text-light aui-font-size-14">
            <div>
              <i class="aui-iconfont aui-icon-display"></i> 888
            </div>
            <div>
              <i class="aui-iconfont aui-icon-comment"></i> 666
            </div>
            <div>
              <i class="aui-iconfont aui-icon-laud"></i> 888
            </div>
          </div>
        </div>
        <div class="aui-card-list">
          <div class="aui-card-list-header aui-card-list-user">
            <div class="aui-card-list-user-avatar">
              <img src="../../../assets/images/timi.jpg" class="aui-img-round">
            </div>
            <div class="aui-card-list-user-name">
              <div class="aui-text-info">丁小酷同学</div>
              <div class="aui-font-size-14 text-light">#比帅</div>
            </div>
            <div class="aui-card-list-user-info text-light">31分钟前</div>
          </div>
          <div class="aui-card-list-content-padded aui-padded-t-5 aui-padded-b-5 max-rows3" style="-webkit-box-orient: vertical; display: -webkit-box;">
            timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅
          </div>
          <div class="aui-card-list-content">
            <div class="aui-row aui-row-padded">
              <div class="aui-col-xs-4">
                <img src="../../../assets/images/timi.jpg">
              </div>
              <div class="aui-col-xs-4">
                <img src="../../../assets/images/timi.jpg">
              </div>
              <div class="aui-col-xs-4">
                <img src="../../../assets/images/timi.jpg">
              </div>
            </div>
          </div>
          <div class="aui-card-list-footer text-light aui-font-size-14">
            <div>
              <i class="aui-iconfont aui-icon-display"></i> 888
            </div>
            <div>
              <i class="aui-iconfont aui-icon-comment"></i> 666
            </div>
            <div>
              <i class="aui-iconfont aui-icon-laud"></i> 888
            </div>
          </div>
        </div>
        <div class="aui-card-list">
          <div class="aui-card-list-header aui-card-list-user">
            <div class="aui-card-list-user-avatar">
              <img src="../../../assets/images/timi.jpg" class="aui-img-round">
            </div>
            <div class="aui-card-list-user-name">
              <div class="aui-text-info">董小星</div>
              <div class="aui-font-size-14 text-light">#比帅</div>
            </div>
            <div class="aui-card-list-user-info text-light">31分钟前</div>
          </div>
          <div class="aui-card-list-content-padded aui-padded-t-5 aui-padded-b-5 max-rows3" style="-webkit-box-orient: vertical; display: -webkit-box;">
            timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅，timi曾真帅
          </div>
          <div class="aui-card-list-content">
            <div class="aui-row aui-row-padded">
              <div class="aui-col-xs-12">
                <img src="../../../assets/images/timi.jpg">
              </div>
            </div>
          </div>
          <div class="aui-card-list-footer text-light aui-font-size-14">
            <div>
              <i class="aui-iconfont aui-icon-display"></i> 888
            </div>
            <div>
              <i class="aui-iconfont aui-icon-comment"></i> 666
            </div>
            <div>
              <i class="aui-iconfont aui-icon-laud"></i> 888
            </div>
          </div>
        </div>
        <div class="aui-card-list">
          <div class="aui-card-list-header aui-card-list-user">
            <div class="aui-card-list-user-avatar">
              <img src="../../../assets/images/timi.jpg" class="aui-img-round">
            </div>
            <div class="aui-card-list-user-name">
              <div class="aui-text-info">蛋蛋的圈圈</div>
              <div class="aui-font-size-14 text-light">#最美的人</div>
            </div>
            <div class="aui-card-list-user-info text-light">31分钟前</div>
          </div>
          <div class="aui-card-list-content-padded aui-padded-t-5 aui-padded-b-5 max-rows3" style="-webkit-box-orient: vertical; display: -webkit-box;">
            依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美，依婷最美
          </div>
          <div class="aui-card-list-content">
            <div class="aui-row aui-row-padded">
              <div class="aui-col-xs-6">
                <img src="../../../assets/images/timi.jpg">
              </div>
              <div class="aui-col-xs-6">
                <img src="../../../assets/images/timi.jpg">
              </div>
            </div>
          </div>
          <div class="aui-card-list-footer text-light aui-font-size-14">
            <div>
              <i class="aui-iconfont aui-icon-display"></i> 888
            </div>
            <div>
              <i class="aui-iconfont aui-icon-comment"></i> 666
            </div>
            <div>
              <i class="aui-iconfont aui-icon-laud"></i> 888
            </div>
          </div>
        </div>
      </section>
    </div>

    <div style="height: 2.2rem"></div>
    <!--底部工具栏开始-->
    <footer class="aui-bar aui-bar-tab" id="footer">
      <router-link :to="{ name: 'home'}" tag="div" class="aui-bar-tab-item" tapmode>
        <i class="aui-iconfont aui-icon-home"></i>
        <div class="aui-bar-tab-label">首页</div>
      </router-link>
      <router-link :to="{ name: 'chatroom'}" tag="div" class="aui-bar-tab-item" tapmode>
        <div class="aui-badge">{{ GLOBAL.msgNum }}</div>
        <i class="aui-iconfont aui-icon-comment"></i>
        <div class="aui-bar-tab-label">婷迷聊天室</div>
      </router-link>
      <router-link :to="{ name: 'posting'}" tag="div" class="aui-bar-tab-item" tapmode>
        <div class="aui-bar-tab-label">
          <div class="aui-btn add-btn">
            +
          </div>
        </div>
      </router-link>
      <div class="aui-bar-tab-item aui-active" tapmode>
        <div class="aui-dot"></div>
        <i class="aui-iconfont aui-icon-comment"></i>
        <div class="aui-bar-tab-label">婷迷论坛</div>
      </div>
      <router-link :to="{ name: 'userHome'}" tag="div" class="aui-bar-tab-item" tapmode>
        <div class="aui-dot"></div>
        <i class="aui-iconfont aui-icon-my"></i>
        <div class="aui-bar-tab-label">我的</div>
      </router-link>
    </footer>
    <!--底部工具类结束-->
  </div>
</template>

<script>
  export default {
    components: {

    },
    data () {
      return {
        stick: []
      }
    },
    mounted () {
      var pullRefresh = new auiPullToRefresh({
        container: document.querySelector('.aui-refresh-content'),
        triggerDistance: 100
      },function(ret){
        if(ret.status=="success"){
          setTimeout(function(){
            pullRefresh.cancelLoading(); //刷新成功后调用此方法隐藏
          },1500)
        }
      });
      this.loadStickData();
    },
    methods: {
      loadStickData: function () {
        var app = this;
        app.post('/timizhuo/forum/findForumByStick', {}, function (res) {
          if (res.data.code == '200') {
            app.stick = res.data.data;
          }
        }, function (err) {

        });
      }
    }
  }
</script>

<style>
  body {
    background-color:  rgb(245, 245, 245);
  }

  .icon-left, .icon-right {
    position: absolute;
    font-size: 1.3rem;
    color: #fff;
    background-color: rgba(0, 0, 0, 0);
    top: 0.5rem;
  }

  .icon-left {

  }

  .icon-right {
    right: 0.8rem;
  }

  .icon-left:hover, .icon-right:hover {
    background-color: rgba(0, 0, 0, 0);
  }

  .banner {
    height: 7rem;
    background: url("http://timizhuo.cn/tiebabgi.jpg") center center no-repeat;
    background-size: auto 100%;
  }
  .aui-tab, .roof-placement {
    background-color: #fff;
  }

  .aui-tab {
    margin-top: 0.5rem;
    padding-left: 0.5rem;
    padding-right: 0.5rem;
  }

  .roof-placement .label {
    color: rgb(58, 103, 168);
    padding-right: 0.5rem;
  }
  .roof-placement-title {
    font-size: 0.6rem;
  }

  .max-rows1{
    text-overflow: -o-ellipsis-lastline;
    overflow: hidden;
    text-overflow: ellipsis;
    -webkit-line-clamp: 1;
  }

  .aui-card-list {
    margin-bottom: 0.2rem;
  }

  .add-btn {
    border-radius: 50%;
    width: 2rem;
    height: 2rem;
    line-height: 2rem;
    font-size: 1.5rem;
    background-color: rgb(0, 185, 230);
    color: #fff;
    text-align: center;
    font-weight: 300;
  }

  .tabDiv {
    width: 1.2rem;
    height: 1.2rem;
    position: absolute;
    background-color: #fff;
    left: 50%;
    top: 50%;
    visibility: visible;
    margin-left: -0.6rem;
    margin-top: -0.6rem;
    z-index: 11;
  }
</style>
