package com.example.demo.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class ResPageInfoVO<T> {
    private Long total;
    private Integer pageIndex;
    private Integer pageSize;
    private List<T> list;
}
