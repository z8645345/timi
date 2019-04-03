package com.timi.timizhuo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRocordEnum {

    VIDOE_LOVE("1", "视频点赞"),
    VIDEO_COLLECTION("2", "视频收藏")
    ;
    private String type;
    private String name;

}
