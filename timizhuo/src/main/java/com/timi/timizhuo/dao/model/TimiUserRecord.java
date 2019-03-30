package com.timi.timizhuo.dao.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TimiUserRecord implements Serializable {
    private String id;

    private String username;

    private String recordId;

    private String type;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", recordId=").append(recordId);
        sb.append(", type=").append(type);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}