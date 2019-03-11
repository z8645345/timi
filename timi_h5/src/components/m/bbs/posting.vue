<template>
    <div style="margin-left: 0.5rem; margin-right: 0.5rem">
      <header style="width: 100%; margin-top: 0.5rem; margin-bottom: 0.5rem">
        <i class="aui-iconfont aui-icon-close" style="position: fixed; top: 0.5rem; left: 0.5rem; font-size: 20px"></i>
        <div style="text-align: center; font-size: 16px">发布帖子到婷迷论坛</div>
        <div style="position: fixed; top: 0.5rem; right: 0.5rem; font-size: 16px">发布</div>
      </header>
      <div>
        <div id="context" contenteditable="true" style="height: auto; min-height: 5rem" @focus="hideDefault" @blur="showDefauls">
          <div></div>
          <span v-if="isShowDefauls" style="position: absolute ;color: rgb(217, 217, 217)">来吧，分享你与依婷的故事…</span>
        </div><div style="border: rgb(217, 217, 217) dashed 1px;
                          width: 5rem;
                          height: 5rem;
                          line-height: 5rem;
                          text-align: center;
                          font-size: 5rem;
                          color: rgb(217, 217, 217);
                          font-weight: 200;">
        +
        </div>
      </div>
    </div>
</template>

<script>
  export default {
    name: 'posting',
    data () {
      return {
        isShowDefauls: true
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
      }
    }
  }
</script>

<style scoped>

</style>
