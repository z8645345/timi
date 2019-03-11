package com.timi.timizhuo.service;

import com.github.pagehelper.PageInfo;
import com.timi.timizhuo.dto.TimiVideoCommentDto;

/**
 * @Description TODO
 * @Auther timi
 * @Date 2018/12/19 0019 07:28
 */
public interface TimiVideoCommentService {
    PageInfo<TimiVideoCommentDto> findByVideo(TimiVideoCommentDto timiVideoCommentDto);
}
