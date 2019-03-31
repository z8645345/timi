package com.timi.timizhuo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.timi.timizhuo.dto.TimiUserDto;
import com.timi.timizhuo.dto.TimiVideoCommentDto;
import com.timi.timizhuo.entity.TimiUser;
import com.timi.timizhuo.entity.TimiVideoComment;
import com.timi.timizhuo.mapper.TimiUserMapper;
import com.timi.timizhuo.mapper.TimiVideoCommentMapper;
import com.timi.timizhuo.service.TimiVideoCommentService;
import com.timi.timizhuo.util.BeanConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Auther timi
 * @Date 2018/12/19 001907:28
 */
@Service
public class TimiVideoCommentServiceImpl extends ServiceImpl<TimiVideoCommentMapper, TimiVideoComment> implements TimiVideoCommentService {

    @Autowired
    private TimiVideoCommentMapper timiVideoCommentMapper;

    @Autowired
    private TimiUserMapper timiUserMapper;

    @Override
    public PageInfo<TimiVideoCommentDto> findByVideo(TimiVideoCommentDto timiVideoCommentDto) {
        PageHelper.startPage(timiVideoCommentDto.getPageNum(), timiVideoCommentDto.getPageSize());
//        List<TimiVideoComment> timiVideoComments = timiVideoCommentMapper.selectByVideoId(timiVideoCommentDto);
//        if (!CollectionUtils.isEmpty(timiVideoComments)) {
//            List<TimiVideoCommentDto> timiVideoCommentDtos = BeanConvertUtils.convertList(timiVideoComments, TimiVideoCommentDto.class);
//            timiVideoCommentDtos.forEach(timiVideoCommentDto1 -> {
//                setTimiUser(timiVideoCommentDto1);
//                List<TimiVideoComment> timiVideoComments1 = timiVideoCommentMapper.selectBySuperComment(timiVideoCommentDto1.getSuperComment());
//                if (!CollectionUtils.isEmpty(timiVideoComments1)) {
//                    List<TimiVideoCommentDto> timiVideoCommentDtos1 = BeanConvertUtils.convertList(timiVideoComments1, TimiVideoCommentDto.class);
//                    timiVideoCommentDtos1.forEach(timiVideoCommentDto2 -> {
//                        setTimiUser(timiVideoCommentDto2);
//                        timiVideoCommentDto1.setSubCommentDto(timiVideoCommentDto2);
//                    });
//                }
//            });
//            return new PageInfo<>(timiVideoCommentDtos);
//        }
        return new PageInfo<>();
    }

    private void setTimiUser(TimiVideoCommentDto timiVideoCommentDto) {
        TimiUser timiUser =timiUserMapper.selectByUsername(timiVideoCommentDto.getCommentBy());
        TimiUserDto timiUserDto = new TimiUserDto();
        BeanConvertUtils.convert(timiUser, timiUserDto);
        timiVideoCommentDto.setTimiUserDto(timiUserDto);
    }
}
