package com.fyb.shimo.dto;

import lombok.Data;

@Data
public class GraphiteDiscPageParam {
    private Integer pageNum;
    private Integer pageSize;
    private String code;
    private String reason;
    private String fengZhuang;
    private Boolean overTimeMode;
}
