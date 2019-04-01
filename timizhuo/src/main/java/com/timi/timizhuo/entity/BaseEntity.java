package com.timi.timizhuo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

/**
 * 基础entiry对象，所有entity全部继承这个类
 */
@Data
public class BaseEntity<T> {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 当前页数
     */
    @TableField(exist = false)
    private int pageNum;

    /**
     * 每条条数
     */
    @TableField(exist = false)
    private int pageSize;

    /**
     * 排序字段，升序
     */
    @TableField(exist = false)
    private String asc;

    /**
     * 排序字段，降序
     */
    @TableField(exist = false)
    private String desc;

    /**
     * 排序字段列表，升序
     */
    @TableField(exist = false)
    private List<String> ascs;

    /**
     * 排序字段列表，降序
     */
    @TableField(exist = false)
    private List<String> descs;

}
