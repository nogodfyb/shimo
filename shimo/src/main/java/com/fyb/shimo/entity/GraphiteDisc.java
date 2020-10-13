package com.fyb.shimo.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fyb.shimo.converter.BoolConverter;
import com.fyb.shimo.converter.LocalDateTimeConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author fyb
 * @since 2020-10-12
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
    @ExcelIgnore
    private Integer id;

    /**
     * 编号
     */
    @ExcelProperty("石墨盘编号")
    private String code;

    /**
     * 封装类型
     */
    @ExcelProperty("封装")
    private String fengZhuang;
    /**
     * 长
     */
    @ExcelProperty("长")
    private String length;

    /**
     * 宽
     */
    @ExcelProperty("宽")
    private String width;

    /**
     * 厚
     */
    @ExcelProperty("厚")
    private String thickness;

    /**
     * (启用1，报废0)
     */
    @ExcelProperty(value = "启用状态",converter = BoolConverter.class)
    private Boolean isUsed;

    /**
     * 废弃原因
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    @ExcelProperty("报废原因")
    private String abandonedReason;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(value = "创建时间",converter = LocalDateTimeConverter.class)
    private LocalDateTime createdTime;

    /**
     * 废弃时间
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(value = "废弃时间",converter = LocalDateTimeConverter.class)
    private LocalDateTime abandonedTime;

    /**
     * ‌最近使用时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(value = "最近使用时间",converter = LocalDateTimeConverter.class)
    private LocalDateTime lastUsedTime;

    //不是数据库字段
    @TableField(exist = false)
    @ExcelIgnore
    private boolean isOverTime;



}
