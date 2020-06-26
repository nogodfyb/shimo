package com.fyb.shimo.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author fyb
 * @since 2020-06-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GraphiteDisc implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 编号
     */
    private Integer code;

    /**
     * (启用1，报废0)
     */
    private Boolean isUsed;

    /**
     * 废弃原因
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String abandonedReason;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 废弃时间
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDateTime abandonedTime;


}
