package com.timi.timizhuo.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;

/**
 * 回复贴枚举
 * @author Cruisin
 */
public class ReplyEnum {
    /**
     * 回复类型
     */
    @AllArgsConstructor
    public enum ReplyTypeEnum implements IEnum<Integer> {
        //1主贴回复  2楼层回复    3子回复回复
        MAIN(1), TIER(2), SON(3);
        private Integer type;

        @Override
        public Integer getValue() {
            return this.type;
        }}
}
