package com.fyb.shimo.dto;

import lombok.Data;

@Data
public class GraphiteDiscPageParam {
    private Integer pageNum;
    private Integer pageSize;
    private Integer code;
    private String reason;
    private String fengZhuang;
    private Boolean overTimeMode;
}
