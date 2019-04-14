package com.timi.timizhuo.listener;

import com.timi.timizhuo.websocket.ChatRoomServerStarter;
import com.timi.timizhuo.websocket.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Description 初始化监听器
 * @Auther timi
 * @Date 2018/11/1 000118:46
 */
@Component
public class InitListener {

    @Autowired
    StringRedisTemplate redisTemplate;

//    @PostConstruct
    public void init() {
        try {
            redisTemplate.delete(Const.ONLINE_USER);
            ChatRoomServerStarter.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("聊天室websocket启动失败");
        }

    }
}
