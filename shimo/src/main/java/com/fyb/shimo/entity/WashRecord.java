package com.fyb.shimo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @since 2020-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WashRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 石墨盘编码
     */
    private Integer code;

    /**
     * 班别
     */
    @TableField("shift_group")
    private String shiftGroup;

    /**
     * 班次
     */
    private String shift;

    @TableField("dwzDiameter1")
    private String dwzDiameter1;

    @TableField("dwzDiameter2")
    private String dwzDiameter2;

    @TableField("dwzDiameter3")
    private String dwzDiameter3;

    @TableField("dwzDiameter4")
    private String dwzDiameter4;

    @TableField("dwzDiameter5")
    private String dwzDiameter5;

    @TableField("dwzDiameter6")
    private String dwzDiameter6;

    @TableField("dwzDiameter7")
    private String dwzDiameter7;

    @TableField("dwzDiameter8")
    private String dwzDiameter8;

    @TableField("dwzDiameter9")
    private String dwzDiameter9;

    @TableField("bmbrcDepth1")
    private String bmbrcDepth1;

    @TableField("bmbrcDepth2")
    private String bmbrcDepth2;

    @TableField("bmbrcDepth3")
    private String bmbrcDepth3;

    private String pzd1;

    private String pzd2;

    private String pzd3;

    @TableField("dwxkDiameter1")
    private String dwxkDiameter1;

    @TableField("dwxkDiameter2")
    private String dwxkDiameter2;

    @TableField("dwxkDiameter3")
    private String dwxkDiameter3;

    @TableField("dwxkDiameter4")
    private String dwxkDiameter4;

    @TableField("dwxkDepth1")
    private String dwxkDepth1;

    @TableField("dwxkDepth2")
    private String dwxkDepth2;

    @TableField("dwxkDepth3")
    private String dwxkDepth3;

    @TableField("dwxkDepth4")
    private String dwxkDepth4;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;


}
