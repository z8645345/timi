package com.timi.timizhuo.enums;

public enum UserRocordEnum {

    VIDOE_LOVE("1", "视频点赞"),
    VIDEO_COLLECTION("2", "视频收藏")
    ;

    private String type;
    private String name;

    UserRocordEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
