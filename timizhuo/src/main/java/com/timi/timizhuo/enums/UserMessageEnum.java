package com.timi.timizhuo.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;

/**
 * 回复贴枚举
 *
 * @author Cruisin
 */
public class UserMessageEnum {
    /**
     * 回复类型
     */
    @AllArgsConstructor
    public enum ContentTypeEnum implements IEnum<Integer> {
        //主回复1 楼层回复2 子回复3 帖子 4
        MAIN(1),TIER(2),SON(3),POST(4);
        private Integer type;

        @Override
        public Integer getValue() {
            return this.type;
        }}

    /**
     * 回复类型
     */
    @AllArgsConstructor
    public enum MessageStateEnum implements IEnum<Integer> {
        //1消息状态 1 未读 2已读  3失效
        UNREAD(1),READ(2),INVALID(3);
        private Integer type;

        @Override
        public Integer getValue() {
            return this.type;
        }}
}
