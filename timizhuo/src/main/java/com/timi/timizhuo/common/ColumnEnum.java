package com.timi.timizhuo.common;

import lombok.Data;

/**
 * 卓依婷栏目枚举
 */
public enum ColumnEnum {
    MV("1", "MV"),
    SHOW("2", "演出"),
    IMAGE("3", "图片");

    private ColumnEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }

    private String type;

    private String name;

    public String getType() {
        return type;
    }
}
