package com.timi.timizhuo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 卓依婷栏目枚举
 */
@Getter
@AllArgsConstructor
public enum ColumnEnum  {
    MV("1", "MV"),
    SHOW("2", "演出"),
    IMAGE("3", "图片");


    private String type;

    private String name;

}
