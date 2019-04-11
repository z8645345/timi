package com.timi.timizhuo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 基础entiry对象，所有entity全部继承这个类
 */
@Data
public class BaseEntity<T> implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    private Date createTime;
    private Date updateTime;

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

    /**
     * 获取分页对象
     * @return
     */
    public IPage<T> page() {
        return new Page<T>(this.pageNum, this.pageSize);
    }

    /**
     * 获取带升序排序的分页对象
     * @param sortColumn 排序字段
     * @return
     */
    public IPage<T> ascPage(String... sortColumn) {
        return new Page<T>(this.pageNum, this.pageSize).setAsc(sortColumn);
    }

    /**
     * 获取带多字段升序排序的分页对象
     * @param sortColumns 排序字段列表
     * @return
     */
    public IPage<T> ascPage(List<String> sortColumns) {
        return new Page<T>(this.pageNum, this.pageSize).setAscs(sortColumns);
    }

    /**
     * 获取带降序序排序的分页对象
     * @param sortColumn 排序字段
     * @return
     */
    public IPage<T> descPage(String... sortColumn) {
        return new Page<T>(this.pageNum, this.pageSize).setDesc(sortColumn);
    }

    /**
     * 获取带多字段降序排序的分页对象
     * @param sortColumns 排序字段列表
     * @return
     */
    public IPage<T> descPage(List<String> sortColumns) {
        return new Page<T>(this.pageNum, this.pageSize).setDescs(sortColumns);
    }

}
