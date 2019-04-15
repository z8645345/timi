import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

// 手机端页面
const mHome = r => require(['../components/m/home/home.vue'],r);
const mShowList = r => require(['../components/m/show/show-list.vue'],r);
const mShow = r => require(['../components/m/show/show.vue'],r);
const mPhoto = r => require(['../components/m/photo/photo.vue'],r);
const mVideoList = r => require(['../components/m/video/video-list.vue'],r);
const mVideo = r => require(['../components/m/video/video.vue'],r);
const mReigst = r => require(['../components/m/user/reigst.vue'],r);
const mLogin = r => require(['../components/m/user/login.vue'],r);
const mUserHome = r => require(['../components/m/user/user-home.vue'],r);
const mMessage = r => require(['../components/m/user/message.vue'],r);
const mBbs = r => require(['../components/m/bbs/bbs.vue'],r);
const mPosting = r => require(['../components/m/bbs/posting.vue'],r);
const mDetail = r => require(['../components/m/bbs/detail.vue'],r);
const mChatroom = r => require(['../components/m/chatroom/chatroom.vue'],r);


// PC端页面
const pcHome = r => require(['../components/pc/home/home.vue'],r);

var mRoutes = [
  { path: '/', redirect: { name: 'home' } }, //重定向
  {path: '/home', name: 'home', component: mHome}, // 手机首页
  {path: '/show-list', name: 'showList', component: mShowList}, // 手机演出列表页
  {path: '/show', name: 'show', component: mShow}, // 手机演出详情页
  {path: '/photo', name: 'photo', component: mPhoto}, // 手机图片列表页
  {path: '/video-list', name: 'videoList', component: mVideoList}, // 手机视频列表页
  {path: '/video', name: 'video', component: mVideo}, // 手机视频播放页
  {path: '/reigst', name: 'reigst', component: mReigst}, // 手机用户注册页
  {path: '/login', name: 'login', component: mLogin}, // 手机用户登录页
  {path: '/user-home', name: 'userHome', component: mUserHome}, // 手机用户主页
  {path: '/message', name: 'message', component: mMessage}, // 消息列表页面
  {path: '/bbs', name: 'bbs', component: mBbs}, // 手机论坛页
  {path: '/posting', name: 'posting', component: mPosting}, // 手机论坛发帖页
  {path: '/detail', name: 'detail', component: mDetail}, // 手机论坛帖子详情页
  {path: '/chatroom', name: 'chatroom', component: mChatroom} // 手机聊天室页
];

var pcRoutes = [
  { path: '/', redirect: { name: 'home' } }, //重定向
  {path: '/home', name: 'home', component: pcHome}, // PC首页
]

var routes = mRoutes;

var index = new Vue({
  methods: {
    init: function f () {
      if (document.body.clientWidth < 768) {
        routes = mRoutes;
      } else {
        routes = pcRoutes;
      }
    }
  }
})
index.init();

export default new Router({
  routes: routes
})
