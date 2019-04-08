package com.timi.timizhuo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author timi
 * @since 2019-04-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TimiFans extends BaseEntity<TimiFans> {

    private static final long serialVersionUID = 1L;

    /**
     * 被关注的用户id
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 该用户id
     */
    @TableField("user_id")
    private Integer userId;

}
