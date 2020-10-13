package com.fyb.shimo.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class WashRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ExcelIgnore
    private Integer id;

    /**
     * 石墨盘编码
     */
    @ExcelProperty("石墨盘编号")
    private String code;

    /**
     * 班别
     */
    @TableField("shift_group")
    @ExcelProperty("班组")
    private String shiftGroup;

    /**
     * 班次
     */
    @ExcelProperty("班次")
    private String shift;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(value = "创建时间",converter = LocalDateTimeConverter.class)
    private LocalDateTime createdTime;

    @TableField("dwzDiameter1")
    @ExcelProperty("定位针直径1")
    private String dwzDiameter1;

    @TableField("dwzDiameter2")
    @ExcelProperty("定位针直径2")
    private String dwzDiameter2;

    @TableField("dwzDiameter3")
    @ExcelProperty("定位针直径3")
    private String dwzDiameter3;

    @TableField("dwzDiameter4")
    @ExcelProperty("定位针直径4")
    private String dwzDiameter4;

    @TableField("dwzDiameter5")
    @ExcelProperty("定位针直径5")
    private String dwzDiameter5;

    @TableField("dwzDiameter6")
    @ExcelProperty("定位针直径6")
    private String dwzDiameter6;

    @TableField("dwzDiameter7")
    @ExcelProperty("定位针直径7")
    private String dwzDiameter7;

    @TableField("dwzDiameter8")
    @ExcelProperty("定位针直径8")
    private String dwzDiameter8;

    @TableField("dwzDiameter9")
    @ExcelProperty("定位针直径9")
    private String dwzDiameter9;

    @TableField("bmbrcDepth1")
    @ExcelProperty("背面避让槽深度1")
    private String bmbrcDepth1;

    @TableField("bmbrcDepth2")
    @ExcelProperty("背面避让槽深度2")
    private String bmbrcDepth2;

    @TableField("bmbrcDepth3")
    @ExcelProperty("背面避让槽深度3")
    private String bmbrcDepth3;

    @ExcelProperty("平整度1")
    private String pzd1;

    @ExcelProperty("平整度2")
    private String pzd2;

    @ExcelProperty("平整度3")
    private String pzd3;

    @TableField("dwxkDiameter1")
    @ExcelProperty("定位销孔直径1")
    private String dwxkDiameter1;

    @TableField("dwxkDiameter2")
    @ExcelProperty("定位销孔直径2")
    private String dwxkDiameter2;

    @TableField("dwxkDiameter3")
    @ExcelProperty("定位销孔直径3")
    private String dwxkDiameter3;

    @TableField("dwxkDiameter4")
    @ExcelProperty("定位销孔直径4")
    private String dwxkDiameter4;

    @TableField("dwxkDepth1")
    @ExcelProperty("定位销孔深度1")
    private String dwxkDepth1;

    @TableField("dwxkDepth2")
    @ExcelProperty("定位销孔深度2")
    private String dwxkDepth2;

    @TableField("dwxkDepth3")
    @ExcelProperty("定位销孔深度3")
    private String dwxkDepth3;

    @TableField("dwxkDepth4")
    @ExcelProperty("定位销孔深度4")
    private String dwxkDepth4;



    /**
     * 定位针高度
     */
    @TableField("dwzHeight1")
    @ExcelProperty("定位针高度1")
    private String dwzHeight1;

    @TableField("dwzHeight2")
    @ExcelProperty("定位针高度2")
    private String dwzHeight2;

    @TableField("dwzHeight3")
    @ExcelProperty("定位针高度3")
    private String dwzHeight3;

    @TableField("dwzHeight4")
    @ExcelProperty("定位针高度4")
    private String dwzHeight4;

    @TableField("dwzHeight5")
    @ExcelProperty("定位针高度5")
    private String dwzHeight5;

    @TableField("dwzHeight6")
    @ExcelProperty("定位针高度6")
    private String dwzHeight6;

    @TableField("dwzHeight7")
    @ExcelProperty("定位针高度7")
    private String dwzHeight7;

    @TableField("dwzHeight8")
    @ExcelProperty("定位针高度8")
    private String dwzHeight8;

    @TableField("dwzHeight9")
    @ExcelProperty("定位针高度9")
    private String dwzHeight9;

    /**
     * 定位销钉高度
     */
    @TableField("dwxdHeight1")
    @ExcelProperty("定位销钉高度1")
    private String dwxdHeight1;

    /**
     * 定位销钉高度
     */
    @TableField("dwxdHeight2")
    @ExcelProperty("定位销钉高度2")
    private String dwxdHeight2;

    /**
     * 定位销钉高度
     */
    @TableField("dwxdHeight3")
    @ExcelProperty("定位销钉高度3")
    private String dwxdHeight3;

    /**
     * 定位销钉高度
     */
    @TableField("dwxdHeight4")
    @ExcelProperty("定位销钉高度4")
    private String dwxdHeight4;


}
