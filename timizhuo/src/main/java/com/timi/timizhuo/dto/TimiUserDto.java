package com.timi.timizhuo.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description 用户实体传输类
 * @Auther timi
 * @Date 2018/12/6 000612:33
 */
@Data
public class TimiUserDto extends BaseDto {

    private String userId;

    private String username;

    private String password;

    private String nickname;

    private String wechartCode;

    private String pic;

    private String loveTimiDeclaration;

    private String personalProfile;

    private Date createTime;

    private Date updateTime;

    private String checkCode;

    private String token;

    private String headerImage;

    private String msg;

    private String msgId;

    private Integer msgType;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", nickname=").append(nickname);
        sb.append(", pic=").append(pic);
        sb.append(", loveTimiDeclaration=").append(loveTimiDeclaration);
        sb.append(", personalProfile=").append(personalProfile);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
