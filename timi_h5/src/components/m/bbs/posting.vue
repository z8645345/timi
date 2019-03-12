<template>
    <div style="margin-left: 0.5rem; margin-right: 0.5rem; ">
      <header style="width: 100%; margin-top: 0.5rem; margin-bottom: 0.5rem">
        <i class="aui-iconfont aui-icon-close" style="position: fixed; top: 0.5rem; left: 0.5rem; font-size: 20px"></i>
        <div style="text-align: center; font-size: 16px">发布帖子到婷迷论坛</div>
        <div style="position: fixed; top: 0.5rem; right: 0.5rem; font-size: 16px">发布</div>
      </header>
      <div>
        <div id="context" contenteditable="true" style="height: auto; min-height: 5rem" @focus="hideDefault" @blur="showDefauls">
          <div></div>
          <span v-if="isShowDefauls" style="position: absolute ;color: rgb(217, 217, 217)">来吧，分享你与依婷的故事…</span>
        </div>
        <div class="aui-card-list-content">
          <div class="aui-row aui-row-padded">
            <div v-for="img in imgs" class="aui-col-xs-4" style="height: 115px">
              <img class="img-list" :src="img">
            </div>
            <div class="aui-col-xs-4" @click="addImg">
              <img src="http://timizhuo.cn/add_img.png">
            </div>
            <!--<div @click="addImg" class="aui-col-xs-4" style="border: rgb(217, 217, 217) dashed 1px;-->
                          <!--width: 10rem;-->
                          <!--height: 10rem;-->
                          <!--line-height: 10rem;-->
                          <!--text-align: center;-->
                          <!--font-size: 10rem;-->
                          <!--color: rgb(217, 217, 217);-->
                          <!--font-weight: 100;">-->
              <!--+-->
            <!--</div>-->
          </div>
        </div>
        <input type="file" id="change" accept="image" @change="change" style="display: none">
      </div>
    </div>
</template>

<script>
  export default {
    name: 'posting',
    data () {
      return {
        isShowDefauls: true,
        picValue: '',
        url: '',
        imgs: [
        ]
      }
    },
    methods: {
      hideDefault: function () {
        this.isShowDefauls = false;
      },
      showDefauls: function () {
        var context = document.getElementById("context").innerText;
        if (context == null || context == '') {
          this.isShowDefauls = true;
        }
      },
      addImg: function () {
        document.getElementById('change').click();
      },
      change (e) {
        let files = e.target.files || e.dataTransfer.files;
        if (!files.length) return;
        this.picValue = files[0];
        this.imgs.push(this.getObjectURL(this.picValue))
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
      }
    }
  }
</script>

<style scoped>
  .img-list {
    width:100%;
    height: 100%;
    object-fit: cover;
  }
</style>
