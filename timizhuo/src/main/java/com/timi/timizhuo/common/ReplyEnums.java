package com.timi.timizhuo.common;

/**
 * 回复贴枚举
 */
public class ReplyEnums {
    /**
     * 回复类型
     */
    public enum ReplyTypeEnum {
        MAIN(1), TIER(2), SON(3);

        private ReplyTypeEnum(Integer type) {
            this.type = type;
        }
        private Integer type;

        public Integer getType() {
            return type;
        }
    }
}
