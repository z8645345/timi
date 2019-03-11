package com.timi.timizhuo.listener;

import com.timi.timizhuo.dto.TimiUserDto;
import com.timi.timizhuo.websocket.ChatRoomServerStarter;
import com.timi.timizhuo.websocket.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Description 初始化监听器
 * @Auther timi
 * @Date 2018/11/1 000118:46
 */
@Component
public class InitListener {

    @Autowired
    StringRedisTemplate redisTemplate;

    @PostConstruct
    public void init() {
//        EventLoopGroup bossGroup = new NioEventLoopGroup();
//        EventLoopGroup workGroup = new NioEventLoopGroup();
//        try{
//            ServerBootstrap b = new ServerBootstrap();
//            b.group(bossGroup, workGroup);
//            b.channel(NioServerSocketChannel.class);
//            b.childHandler(new MyWebSocketChannelHandler());
//            System.out.println("服务端开启等待客户端连接...");
//            Channel ch = b.bind(NettyConfig.port).sync().channel();
//            ch.closeFuture().sync();
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{
//            //优雅的退出程序
//            bossGroup.shutdownGracefully();
//            workGroup.shutdownGracefully();
//        }
        try {
            redisTemplate.delete(Const.ONLINE_USER);
            ChatRoomServerStarter.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("聊天室websocket启动失败");
        }

    }
}
