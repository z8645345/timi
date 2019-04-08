package com.timi.timizhuo.dto.response;

import com.timi.timizhuo.entity.TimiReply;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 帖子回复分页查询DTO
 */
@Data
public class ReplyFindPageDTO implements Serializable {

    /**
     * 楼层回复
     */
    private TimiReply timiReply;

    /**
     * 楼层下面的子回复
     */
    private List<TimiReply> subTimiReplyList;
}
