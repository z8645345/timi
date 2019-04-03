package com.timi.timizhuo.enums;

/**
 * 回复贴枚举
 * @author Cruisin
 */
public class ReplyEnum {
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
