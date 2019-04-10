<template>
  <div>
    <div v-if="isAt" style="position: fixed; top: 2.2rem; color: #0099FF;" class="aui-tips aui-margin-b-15" id="tips-1" @click="clickAt">
      <i class="aui-iconfont aui-icon-info"></i>
      <div class="aui-tips-title">有人@我</div>
      <i class="aui-iconfont aui-icon-close" tapmode @click="closeTips()"></i>
    </div>
    <div v-bind:class="{ displaydiv: viewType!=0 }">
      <!-- 遮罩层 -->
      <div class="container" v-show="panel">
        <div>
          <img id="image" :src="url" alt="Picture">
        </div>

        <div class="aui-bar-tab imgButton" style="height: 2rem; background-color: #000;">
          <!--<label><input class="aui-radio" type="radio" name="demo"> 原图</label>-->
          <div class="aui-btn aui-btn-info" @click="sendImg">发送</div>
        </div>

      </div>
      <header class="aui-bar aui-bar-nav nav-title">
        <a @click="goback" class="aui-pull-left aui-btn">
          <span class="aui-iconfont aui-icon-left"></span>返回
        </a>
        <div class="aui-title">婷迷聊天室({{onlineNumber}})</div>
        <a class="aui-pull-right" @click="showUserList(1)">
          <span class="aui-iconfont aui-icon-my"></span>
        </a>
      </header>
      <div style="position: fixed; top: 2.2rem; bottom: 2.2rem; width: 100%;" class="aui-refresh-content">
        <div class="aui-chat" style="overflow: auto"  :style="msgBgiStyle" id="msgContext">
          <div class="aui-chat-header" v-if="findMsgLog==0"><div class="xuanzhuan"><i class="aui-iconfont aui-icon-recovery"></i></div>加载中</div>
          <div class="aui-chat-header" v-else-if="findMsgLog==1" @click="loadMsgData(++pageNum)">点击加载更多聊天记录</div>
          <div class="aui-chat-header" v-else>无更多聊天记录</div>
          <div id="infos" @touchstart="moveStart" @touchend="moveEnd">
            <div v-for="info in infos">
              <div class="aui-chat-header" v-if="info.showTime" :id="info.id + 'date'">{{info.createTime}}</div>
              <div v-if="info.msgType=='3'" class="aui-chat-header">{{info.msg}}</div>
              <div v-else class="aui-chat-item" :class="info.lr" :id="info.id">
                <div class="aui-chat-media">
                  <img :src="info.pic" />
                </div>
                <div class="aui-chat-inner">
                  <div class="aui-chat-name">{{ info.nickname }} <span class="aui-label aui-label-warning">{{ info.title}}</span></div>
                  <div class="aui-chat-content" :class="{at: info.isAt}">
                    <div class="aui-chat-arrow"></div>
                    <span v-if="info.msgType == '1'" v-html="info.msg"></span>
                    <img v-else-if="info.msgType == '2'" :src="info.msg" @click="showAlbum(info.msg)" />
                  </div>
                  <div class="aui-chat-status aui-chat-status-refresh" :class="info.xuanzhuan" :id="info.msgId">
                    <i class="aui-iconfont" :class="info.sendStatus"></i>
                  </div>
                </div>
              </div>
            </div>

            <div>
              <div class="aui-chat-item">
                <div class="aui-chat-inner">
                  <div class="aui-chat-name" id="infoBottom"></div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="aui-searchbar aui-bar-tab" id="search" :style="inputStyle">

        <!--class="aui-searchbar-input aui-border-radius"-->
        <div class="aui-searchbar-input aui-border-radius">
          <!--<input type="text" id="sendContext" v-model="sendContext" @focus="inputFocus" @blur="inputBlur" @input="showAtList">-->
          <div  contenteditable="true" id="sendContext" v-model="sendContext" @focus="inputFocus" @blur="inputBlur" style="width: 100%" @input="showAtList">
          </div>
        </div>
        <div class="aui-btn aui-btn-info" @click="send()" :style="sendBtnStyle" tapmode>发送</div>
        <i class="aui-iconfont aui-icon-image aui-btn aui-btn-outlined" :style="sendBtnStyle" style="font-size: 1rem;" @click="selectImageMsg"></i>
        <input type="file" id="imageMsg" accept="image" @change="imgChange" style="display: none">
        <div v-if="isNewMsg" @click="newMsgClick" style="position: fixed; right:1rem; bottom:2.2rem; background-color:rgb(3, 169, 244); color: #fff; padding-left: 4px; padding-right: 4px; border-radius: 5px;">有{{msgNum}}条新消息</div>
      </div>

      <div class="album" v-if="album.isShowAlbum" @click="hideAlbum">
        <img :src="album.currentImg">
      </div>
    </div>
    <div v-bind:class="{ displaydiv: viewType==0 }">
      <header class="aui-bar aui-bar-nav">
        <a class="aui-pull-left aui-btn" @click="hideUserList">
        <span v-if="viewType == 1">
          <span class="aui-iconfont aui-icon-left"></span>返回
        </span>
          <span v-else>
          取消
        </span>
        </a>
        <div class="aui-title">
          <span v-if="viewType == 1">群聊成员</span>
          <span v-else>选择提醒成员</span>
          ({{onlineNumber}})
        </div>
      </header>
      <header class="aui-bar aui-bar-nav aui-bar-light">
        <div class="aui-title" style="left:0.2rem; right: 0.2rem;">
          <div class="aui-searchbar" id="search" style="background-color: #fff;">
            <div class="aui-searchbar-input aui-border-radius" style="background-color: rgb(243, 243, 243);">
              <i class="aui-iconfont aui-icon-search"></i>
              <input type="search" placeholder="请输入搜索成员昵称" id="search-input" @input="searchChange" v-model="searchNickName">
              <div class="aui-searchbar-clear-btn">
                <i class="aui-iconfont aui-icon-close"></i>
              </div>
            </div>
            <div class="aui-searchbar-btn" tapmode>取消</div>
          </div>
        </div>
      </header>

      <ul class="aui-list aui-media-list">
        <li class="aui-list-item" v-if="viewType == 2" @click="userListClick(null)">
          <div class="aui-card-list-header aui-card-list-user">
            <div class="aui-card-list-user-avatar">
              <img src="http://pkkwm1pvb.bkt.clouddn.com/@all.png" class="aui-img-round" style="vertical-align: middle">
            </div>
            <div style="height: 40px; line-height: 40px">全体成员</div>
          </div>
        </li>
        <li class="aui-list-item" v-for="timiUser in searchOnlineUserList" @click="userListClick(timiUser)">
          <div class="aui-card-list-header aui-card-list-user">
            <div class="aui-card-list-user-avatar">
              <img :src="timiUser.pic" class="aui-img-round" style="vertical-align: middle">
            </div>
            <div style="height: 40px; line-height: 40px"><span class="aui-label aui-label-warning">超级婷迷</span> {{timiUser.nickname}}</div>
          </div>
        </li>
      </ul>
    </div>

  </div>
</template>

<script>
  var app;
  var wsIsConn = false;
  var DemoHandler = function () {
    this.onopen = function (event, ws) {
      var msg = app.timiUser.nickname + '加入群聊';
      var sendInfo = '{"msg":"'+ msg +'","type":'+ 3 +'}';
      tiows.send(sendInfo);
      app.loadOnlineUser();
    }

    /**
     * 收到服务器发来的消息
     * @param {*} event
     * @param {*} ws
     */
    this.onmessage = function (event, ws) {
      var data = event.data
      var timiMsgLog = JSON.parse(data);
      var lr = 'aui-chat-left';

      if(app.timiUser.nickname == timiMsgLog.nickname) {
        $("#" + timiMsgLog.msgId).html('');
        return;
      }

      var isImg = false;
      if (timiMsgLog.msgType == 2) {
        isImg = true;
      }
      var showTime = false;
      var lastInfo = app.infos[app.infos.length - 1];
      if(lastInfo.timestamp != 'undefined' && timiMsgLog.timestamp - lastInfo.timestamp > 60 * 1000) {
        showTime = true;
      }
      var isAt = false;
      var atAll = "<span contenteditable='false'>@所有人</span>";
      var atYou = "<span contenteditable='false'>@"+ app.timiUser.nickname +"</span>";
      if (timiMsgLog.msg.indexOf(atAll) != -1 || timiMsgLog.msg.indexOf(atYou) != -1) {
        app.showAt();
        isAt = true;
      }

      var info = {
        lr: lr,
        pic: timiMsgLog.pic,
        nickname: timiMsgLog.nickname,
        title: '超级婷迷',
        msg: timiMsgLog.msg,
        isImg: isImg,
        msgType: timiMsgLog.msgType,
        createTime: timiMsgLog.createTime,
        timestamp: timiMsgLog.timestamp,
        showTime: showTime,
        isAt: isAt
      }
      app.infos.push(info);

      if (timiMsgLog.msgType != '3') {
        var path = app.$route.path;
        if (path != '/chatroom') {
          app.GLOBAL.msgNum ++;
          if (path == '/home') {
            app.GLOBAL.homeApp.msg = timiMsgLog.nickname + ":" + timiMsgLog.msg;
            app.GLOBAL.homeApp.isShowMsg = true;
            setTimeout(()=>app.GLOBAL.homeApp.isShowMsg = false, 3000);
          } else if (path == '/user-home') {
            app.GLOBAL.userHomeApp.msg = timiMsgLog.nickname + ":" + timiMsgLog.msg;
            app.GLOBAL.userHomeApp.isShowMsg = true;
            setTimeout(()=>app.GLOBAL.userHomeApp.isShowMsg = false, 3000);
          } else if (path == '/photo') {
            app.GLOBAL.photoApp.msg = timiMsgLog.nickname + ":" + timiMsgLog.msg;
            app.GLOBAL.photoApp.isShowMsg = true;
            setTimeout(()=>app.GLOBAL.photoApp.isShowMsg = false, 3000);
          } else if (path == '/show') {
            app.GLOBAL.showApp.msg = timiMsgLog.nickname + ":" + timiMsgLog.msg;
            app.GLOBAL.showApp.isShowMsg = true;
            setTimeout(()=>app.GLOBAL.showApp.isShowMsg = false, 3000);
          } else if (path == '/show-list') {
            app.GLOBAL.showListApp.msg = timiMsgLog.nickname + ":" + timiMsgLog.msg;
            app.GLOBAL.showListApp.isShowMsg = true;
            setTimeout(()=>app.GLOBAL.showListApp.isShowMsg = false, 3000);
          } else if (path == '/video-list') {
            app.GLOBAL.videoListApp.msg = timiMsgLog.nickname + ":" + timiMsgLog.msg;
            app.GLOBAL.videoListApp.isShowMsg = true;
            setTimeout(()=>app.GLOBAL.videoListApp.isShowMsg = false, 3000);
          } else if (path == '/video') {
            app.GLOBAL.videoApp.msg = timiMsgLog.nickname + ":" + timiMsgLog.msg;
            app.GLOBAL.videoApp.isShowMsg = true;
            setTimeout(()=>app.GLOBAL.videoApp.isShowMsg = false, 3000);
          }
        }

      }


      if (jQuery(window).scrollTop()<(jQuery('#infoBottom').offset().top-jQuery(window).height())) {
        app.msgNum++;
        app.isNewMsg = true;
      } else {
        setTimeout(()=>document.getElementById("infoBottom").scrollIntoView(false), 100);
      }
    }

    this.onclose = function (e, ws) {
      // error(e, ws)
    }

    this.onerror = function (e, ws) {
      // error(e, ws)
    }

    /**
     * 发送心跳，本框架会自动定时调用该方法，请在该方法中发送心跳
     * @param {*} ws
     */
    this.ping = function (ws) {
      // log("发心跳了")
      ws.send('心跳内容')
    }
  }

  var ws_protocol = 'ws'; // ws 或 wss
  // var ip = '192.168.99.50'
  var ip = 'localhost'
  var port = 6789

  var heartbeatTimeout = 5000; // 心跳超时时间，单位：毫秒
  var reconnInterval = 1000; // 重连间隔时间，单位：毫秒

  var binaryType = 'blob'; // 'blob' or 'arraybuffer';//arraybuffer是字节
  var handler = new DemoHandler()

  var tiows

  export default {
    name: 'chatroom',
    data() {
      return {
        timiUser: {
        },
        infos: [
        ],
        cropper:'',
        croppable:false,
        panel:false,
        url:'',
        album: {
          isShowAlbum: false,
          currentImg: ''
        },
        pageNum: 1,
        startOffsetY: '',
        findMsgLog: 1,
        onlineNumber: 0,
        viewType: 0, // 视图模式，0：聊天页面 1：成员列表 2：@页面
        onlineUserList: [],
        searchOnlineUserList: [],
        searchNickName: '',
        inputStyle: 'background-color: rgb(240, 240, 240);',
        displayDiv: 'display-div',
        sendContext: '',
        sendContextLeng: 0,
        isAt: false,
        msgNum: 0,
        isNewMsg: false,
        sendBtnStyle: '',
        msgBgiStyles: [
          // 'background: url(http://pkkwm1pvb.bkt.clouddn.com/bgimg/b5.jpg) center center / auto 100% ',
          // 'background: url(http://pkkwm1pvb.bkt.clouddn.com/bgimg/b4.jpg) center center / auto 100% ',
          // 'background: url(http://pkkwm1pvb.bkt.clouddn.com/bgimg/b2.jpg) center center / auto 100% ',
          // 'background: url(http://pkkwm1pvb.bkt.clouddn.com/bgimg/b1.jpg) center center / auto 100% '
          'http://pkkwm1pvb.bkt.clouddn.com/bgimg/b5.jpg',
          'http://pkkwm1pvb.bkt.clouddn.com/bgimg/b4.jpg',
          'http://pkkwm1pvb.bkt.clouddn.com/bgimg/b2.jpg',
          'http://pkkwm1pvb.bkt.clouddn.com/bgimg/b1.jpg'
        ],
        msgBgiStyle: 'background: url(http://pkkwm1pvb.bkt.clouddn.com/bgimg/b5.jpg) center center / auto 100% ',
        msgBgiStyleIndex: 0,
        msgContextHeight: 0,
        isTop: false
      }
    },
    mounted () {
      this.GLOBAL.msgNum = 0;
      app = this;
      this.msgContextHeight = document.getElementById("msgContext").offsetHeight;
      app.msgBgiStyle = 'background: url('+ app.msgBgiStyles[0] +') top center / auto ' + app.msgContextHeight + 'px';
      window.setInterval(function () {
        if (app.msgBgiStyleIndex == app.msgBgiStyles.length - 1) {
          app.msgBgiStyleIndex = 0;
        } else {
          app.msgBgiStyleIndex ++;
        }
        app.msgBgiStyle = 'background: url('+ app.msgBgiStyles[app.msgBgiStyleIndex] +') top center / auto ' + app.msgContextHeight + 'px';
      }, 10000);

      var timiUser = this.isLogin();
      if (timiUser == null) {
        var dialog = new auiDialog();
        dialog.alert({
          title:"提示",
          msg:'请先登录再发送消息',
          buttons:['取消','去登录']
        },function(ret){
          if(ret){
            if (ret.buttonIndex==2) {
              app.$router.push({name:'login'});
            } else {
              history.go(-1);
            }
          }
        });
        return;
      } else {
        app.timiUser = timiUser;
      }

      app = this;
      this.pageNum = 1;
      this.wsInit();
      this.loadMsgData(this.pageNum);

      var nScrollTop = 0;   //滚动到的当前位置
      $("#msgContext").scroll(function(){
        nScrollTop = $(this)[0].scrollTop;
        if (nScrollTop <= 0) {
          app.isTop = true;
        } else {
          app.isTop = false;
        }
      });
    },
    methods: {
      wsInit: function() {

        if (!wsIsConn) {
          var userToken = localStorage.getItem('userToken');
          var queryString = "token=" + userToken;
          var param = "token=" + userToken;
          tiows = new tio.ws(ws_protocol, ip, port, queryString, param, handler, heartbeatTimeout, reconnInterval, binaryType)
          tiows.connect();
          wsIsConn = true;
        }
      },
      send: function () {
        var msg = document.getElementById("sendContext").innerHTML;
        msg = msg.replace(new RegExp('"','g'),"'");
        if (msg != null && msg != '') {
          app.clear();
          var dataTime = (new Date()).getTime();
          var id = app.timiUser.userId + dataTime;
          var lastInfo = app.infos[app.infos.length - 1];
          var showTime = false;
          if(dataTime - lastInfo.timestamp > 60 * 1000) {
            showTime = true;
          }
          var info = {
            lr: 'aui-chat-right',
            pic: app.timiUser.pic,
            nickname: app.timiUser.nickname,
            title: '超级婷迷',
            msg: msg,
            sendStatus: 'aui-icon-recovery',
            xuanzhuan: 'xuanzhuan',
            msgId: id,
            isImg: false,
            msgType: '1',
            showTime:  showTime,
            createTime: this.formatUnixtimestamp(dataTime),
          }
          app.infos.push(info);
          var sendInfo = '{"msgId":"' + id +'","msg":"'+ msg +'","type":'+ 1 +'}';
          tiows.send(sendInfo);
          setTimeout(()=>document.getElementById("infoBottom").scrollIntoView(false), 100);
        }
      },
      clear: function () {
        document.getElementById("sendContext").innerHTML=''
        this.sendContextLeng = 0;
      },
      selectImageMsg: function () {
        var timiUser = this.isLogin();
        if (timiUser == null) {
          var dialog = new auiDialog();
          dialog.alert({
            title:"提示",
            msg:'请先登录再发送消息',
            buttons:['取消','去登录']
          },function(ret){
            if(ret){
              if (ret.buttonIndex==2) {
                app.$router.push({name:'login'});
              }
            }
          })
        } else {
          this.timiUser = timiUser;
          document.getElementById("imageMsg").click();
        }
      },
      imgChange: function (e) {
        let files = e.target.files || e.dataTransfer.files;
        if (!files.length) return;
        this.panel = true;
        this.picValue = files[0];
        this.url = this.getObjectURL(this.picValue);
        //每次替换图片要重新得到新的url
        if(this.cropper){
          this.cropper.replace(this.url);
        }
        this.panel = true;
      },
      getObjectURL (file) {
        var url = null ;
        if (window.createObjectURL!=undefined) { // basic
          url = window.createObjectURL(file) ;
        } else if (window.URL!=undefined) { // mozilla(firefox)
          url = window.URL.createObjectURL(file) ;
        } else if (window.webkitURL!=undefined) { // webkit or chrome
          url = window.webkitURL.createObjectURL(file) ;
        }
        return url ;
      },
      sendImg: function () {
        lrz(this.picValue, {
          width: 500 //设置压缩参数
        }).then(function (rst) {
          /* 处理成功后执行 */
          rst.formData.append('base64img', rst.base64); // 添加额外参数
          var id = app.timiUser.userId + (new Date()).getTime();
          var info = {
            lr: 'aui-chat-right',
            pic: app.timiUser.pic,
            nickname: app.timiUser.nickname,
            title: '超级婷迷',
            msg: rst.base64,
            sendStatus: 'aui-icon-recovery',
            xuanzhuan: 'xuanzhuan',
            msgId: id,
            isImg: true,
            msgType: '2'
          }
          app.infos.push(info);
          ($('#infos').children("div:last-child")[0]).scrollIntoView();
          var sendInfo = '{"msgId":"' + id +'","msg":"'+ rst.base64 +'","type":'+ 2 +'}';
          tiows.send(sendInfo);
          document.getElementById("imageMsg").value='';
        }).catch(function (err) {
          /* 处理失败后执行 */
        }).always(function () {
          /* 必然执行 */
        });
        this.panel = false;
      },
      showAlbum: function (img) {
        this.album.currentImg = img;
        this.album.isShowAlbum = true;
      },
      hideAlbum: function () {
        this.album.isShowAlbum = false;
      },
      loadMsgData: function (pageNum) {
        this.findMsgLog = 0;
        if (wsIsConn) {
          var data = {
            pageNum: pageNum,
            pageSize: 20
          }
          app.post('/timizhuo/msgLog/findAll', data, function (res) {
            if (res.data.code == '200') {
              if (res.data.data.length > 0) {
                var sid = '';
                for(var i=0; i < res.data.data.length; i ++) {
                  var timiMsgLog = res.data.data[i];
                  var isImg = false;
                  if (timiMsgLog.msgType == '2') {
                    isImg = true;
                  }
                  var id = '';
                  if (i == 0) {
                    id = (new Date()).getTime() + '';
                    sid = id;
                  }
                  var lr = 'aui-chat-left';
                  if(app.timiUser.nickname == timiMsgLog.nickname) {
                    lr = 'aui-chat-right';
                  }
                  var showTime = false;
                  if (res.data.data.length - 1 > i) {
                    var lastInfo = res.data.data[i + 1];
                    if(lastInfo.timestamp != 'undefined' && timiMsgLog.timestamp - lastInfo.timestamp > 60 * 1000) {
                      showTime = true;
                    }
                  }
                  var info = {
                    lr: lr,
                    pic: timiMsgLog.pic,
                    nickname: timiMsgLog.nickname,
                    title: '超级婷迷',
                    msg: timiMsgLog.msg,
                    sendStatus: '',
                    xuanzhuan: '',
                    isImg: isImg,
                    id : id,
                    msgType: timiMsgLog.msgType,
                    createTime: timiMsgLog.createTime,
                    timestamp: timiMsgLog.timestamp,
                    showTime: showTime
                  }
                  app.infos.unshift(info);
                }
                app.findMsgLog = 1;
                setTimeout(function(){
                  document.getElementById(sid).scrollIntoView();
                }, 50)
              } else {
                app.findMsgLog = 2;
              }
            }
          }, function (err) {
          })
        }
      },
      moveStart: function (event) {
        //触摸开始时, 记住当前手指的位置
        this.startOffsetY = event.changedTouches[0].pageY;

      },
      moveEnd: function (event) {
        let dx = event.changedTouches[0].pageY - this.startOffsetY;
        if (app.isTop && dx > 150) {
          app.loadMsgData(++app.pageNum);
        }
      },
      loadOnlineUser: function () {
        app.post('/timizhuo/msgLog/getOnlineUser', {}, function (res) {
          app.onlineNumber = res.data.data.length;
          app.onlineUserList = res.data.data;
        }, function (err) {
        })
        window.setInterval(function () {
          app.post('/timizhuo/msgLog/getOnlineUser', {}, function (res) {
            app.onlineNumber = res.data.data.length;
            app.onlineUserList = res.data.data;;
          }, function (err) {
          })
        }, 5000);
      },
      showUserList: function (type) {
        this.viewType = type;
        app.searchOnlineUserList = app.onlineUserList;
        setTimeout(function(){
          app.initSearch();
        }, 1)
      },
      showAtList: function() {
        var o = document.getElementById("sendContext");
        var h = o.offsetHeight;
        if (h > 40) {
          this.sendBtnStyle = 'margin-top: '+ (h-36) +'px;'
        } else {
          this.sendBtnStyle = '';
        }
        var value = o.innerHTML;
        var len = value.length;
        if (len > this.sendContextLeng) {
          var lastStr = value.charAt(value.length - 1);
          if (lastStr == '@') {
            this.showUserList(2);
          }
        } else {
          this.inputDelete();
        }
        this.sendContextLeng = document.getElementById("sendContext").innerHTML.length;
      },
      hideUserList: function () {
        this.viewType = 0;
        setTimeout(function(){
          ($('#infos').children("div:last-child")[0]).scrollIntoView();
        }, 1)
      },
      initSearch: function () {
        // 顶部搜索栏开始
        var apiready = function(){
          api.parseTapmode();
        }
        var searchBar = document.querySelector(".aui-searchbar");
        var searchBarInput = document.querySelector(".aui-searchbar input");
        var searchBarBtn = document.querySelector(".aui-searchbar .aui-searchbar-btn");
        var searchBarClearBtn = document.querySelector(".aui-searchbar .aui-searchbar-clear-btn");
        if(searchBar){
          searchBarInput.onclick = function(){
            searchBarBtn.style.marginRight = 0;
          }
        }
        searchBarClearBtn.onclick = function(){
          this.style.display = 'none';
          searchBarInput.value = '';
          searchBarBtn.classList.remove("aui-text-info");
          searchBarBtn.textContent = "取消";
        }
        searchBarBtn.onclick = function(){
          this.style.marginRight = "-"+this.offsetWidth+"px";
          searchBarInput.value = '';
          searchBarInput.blur();
        }
        // 顶部搜索栏结束
      },
      searchChange: function () {
        var searchBarBtn = document.querySelector(".aui-searchbar .aui-searchbar-btn");
        var searchBarClearBtn = document.querySelector(".aui-searchbar .aui-searchbar-clear-btn");
        searchBarClearBtn.style.display = 'none';
        searchBarBtn.classList.remove("aui-text-info");
        searchBarBtn.textContent = "取消";

        var users = [];
        for(var i = 0; i < this.onlineUserList.length; i ++) {
          var timiUser = this.onlineUserList[i];
          if (timiUser.nickname.indexOf(this.searchNickName) != -1) {
            users.push(timiUser);
          }
        }
        this.searchOnlineUserList = users;
      },
      inputFocus: function () {
        this.inputStyle = 'background-color: rgb(240, 240, 240); top: 88%';
      },
      inputBlur: function () {
        this.inputStyle = 'background-color: rgb(240, 240, 240)';
      },
      userListClick: function (timiUser) {
        if (this.viewType == 1) {
          this.showUserInfo(timiUser);
        } else if (this.viewType == 2) {
          this.atUser(timiUser);
        }
      },
      showUserInfo: function (timiUser) {

      },
      atUser: function (timiUser) {
        this.hideUserList();
        var value = document.getElementById("sendContext").innerHTML;
        value = value.substring(0, value.length-1);

        var newValue = '';

        if (timiUser == null) {
          newValue = value +  '<span contenteditable="false">@所有人</span>&nbsp';
        } else {
          newValue = value + '<span contenteditable="false">@'+ timiUser.nickname +'</span>&nbsp';
        }
        $("#sendContext").focus().html(newValue);
        this.setFocus();
        this.sendContextLeng = document.getElementById("sendContext").innerHTML.length;
      },
      inputDelete: function () {
        var value = document.getElementById("sendContext").innerHTML;
        if (this.confirmEnding(value, "</span>")) {
          document.getElementById("sendContext").innerHTML = value.substring(0, value.lastIndexOf("<span"));
          this.setFocus();
        }
      },
      confirmEnding: function (str, target) {
        var start = str.length-target.length;
        var arr = str.substr(start,target.length);
        if(arr == target){
          return true;
        }
        return false;
      },
      setFocus: function () {
        setTimeout(()=>{
          var obj = document.getElementById("sendContext");
          if (window.getSelection) { //ie11 10 9 ff safari
            obj.focus(); //解决ff不获取焦点无法定位问题
            var range = window.getSelection(); //创建range
            range.selectAllChildren(obj); //range 选择obj下所有子内容
            range.collapseToEnd(); //光标移至最后
          } else if (document.selection) { //ie10 9 8 7 6 5
            var range = document.selection.createRange(); //创建选择对象
            //var range = document.body.createTextRange();
            range.moveToElementText(obj); //range定位到obj
            range.collapse(false); //光标移至最后
            range.select();
          }
        },5)
      },
      formatUnixtimestamp: function (inputTime) {
        var date = new Date(inputTime);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        var minute = date.getMinutes();
        var second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
      },
      showAt: function () {
        this.isAt = true;
      },
      closeTips: function () {
        this.isAt = false;
      },
      clickAt: function () {
        var atObjArray = $(".at");
        var atObj = atObjArray[atObjArray.length - 1];
        $(atObj).prev()[0].scrollIntoView();
        this.isAt = false;
      },
      newMsgClick: function () {
        this.isNewMsg = false;
        this.msgNum = 0;
        setTimeout(()=>document.getElementById("infoBottom").scrollIntoView(false), 100);
      },
      goback: function () {
        this.$router.back(-1)
      }
    }

  }

  var apiready = function(){
    api.parseTapmode();
  }

</script>

<style scoped>
  html,body {
    padding: 0!important;
    height: 100%;
  }

  .nav-title {
    position:fixed;
    top: 0;
    z-index:2;
    overflow:hidden;
  }

  .xuanzhuan {
    -webkit-transition-property: -webkit-transform;
    -webkit-transition-duration: 1s;
    -moz-transition-property: -moz-transform;
    -moz-transition-duration: 1s;
    -webkit-animation: rotate 1s linear infinite;
    -moz-animation: rotate 0.7s linear infinite;
    -o-animation: rotate 0.7s linear infinite;
    animation: rotate 0.7s linear infinite;}
  @-webkit-keyframes rotate{from{-webkit-transform: rotate(0deg)}
    to{-webkit-transform: rotate(360deg)}
  }
  @-moz-keyframes rotate{from{-moz-transform: rotate(0deg)}
    to{-moz-transform: rotate(359deg)}
  }
  @-o-keyframes rotate{from{-o-transform: rotate(0deg)}
    to{-o-transform: rotate(359deg)}
  }
  @keyframes rotate{from{transform: rotate(0deg)}
    to{transform: rotate(359deg)}
  }

  .container {
    z-index: 99;
    position: fixed;
    padding-top: 60px;
    left: 0;
    top: 0;
    right: 0;
    bottom: 0;
    background:rgba(0,0,0,1);
  }

  .imgButton {
    position: fixed;
    right: 0;
    bottom: 0;
  }

  .album {
    width: 100%;
    height: 100%;
    position: fixed;
    left:0;
    top:0;
    overflow: hidden;
    background-color: #000;
    z-index: 99999;
  }

  .album img {
    width: 100%;
    position: absolute;
    margin: auto;
    top: -9999px;
    right: -9999px;
    bottom: -9999px;
    left: -9999px;
  }

  .displaydiv {
    display: none
  }

  .at {
    border: #0099FF 2px solid;
  }
</style>
