<template>
    <div class="content">
      <canvas id="tank" width="500" height="500"></canvas>
    </div>
</template>

<script>
  var ctx;
    export default {
      data () {
        return {
        }
      },
      mounted () {
        this.init();
        var app = this;
        document.onkeydown = function(ev){
          app.operation(ev);
        }
      },
      methods: {
        init: function() {
          var canvas = document.getElementById('tank');
          canvas.setAttribute("width", screen.availWidth);
          canvas.setAttribute("height", screen.availHeight);
          if(!canvas.getContext) return;
          ctx = canvas.getContext("2d");
          ctx.fillStyle = "rgb(200,0,0)";
          //绘制矩形
          ctx.fillRect (10, screen.availHeight-102-50, 50, 50);
          ctx.globalCompositeOperation = "source-over"; //全局合成操作
          ctx.fillStyle = "#9932CC"
          ctx.fillRect(30, screen.availHeight-102-50-10, 10, 10);
          ctx.save();
        },
        operation: function(ev) {
          if (ev.keyCode == 37) {
            this.moveLeft();
          } else if (ev.keyCode == 38) {
            this.moveTop();
          } else if (ev.keyCode == 39) {
            this.moveRight();
          } else if (ev.keyCode == 40) {
            this.moveBottomTop();
          }
        },
        moveTop: function () {
          // 上移动
          ctx.save(); //保存坐原点平移之前的状态
          ctx.beginPath();
          ctx.moveTo(10, 10); // 路径开始点坐标
          ctx.lineTo(20, 20); // 路径结束点坐标
          ctx.stroke(); // 绘制空心路径
          ctx.fill(); // 绘制实心路径
          ctx.closePath(); // 绘制路径结束
        },
        moveBottomTop: function () {
          // 下移动
        },
        moveLeft: function () {
          // 左移动
        },
        moveRight: function () {
          // 右移动
        }
      }
    }
</script>

<style scoped>
  .content {
    width: 100%;
    height:calc(100vh);
    background-color: #000;
  }

  #tank {
  }

  .tank .head {
    margin-left: 42%;
    width: 0.4rem;
    height: 0.7rem;
    background-color: #fff;
  }
  .tank .body {
    width: 2.4rem;
    height: 2.4rem;
    background-color: #fff;
  }

  .tank {
    position: absolute;
    bottom: 0;
    left: 30%;
  }
</style>
