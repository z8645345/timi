package com.timi.timizhuo.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;

/**
 * 回复贴枚举
 *
 * @author Cruisin
 */
public class ForumEnum {
    /**
     * 回复类型
     */
    @AllArgsConstructor
    public enum TypeEnum implements IEnum<Integer> {
        //1主贴回复  2楼层回复    3子回复回复
        LIKE(1), READ(2);
        private Integer type;

        @Override
        public Integer getValue() {
            return this.type;
        }}
}
