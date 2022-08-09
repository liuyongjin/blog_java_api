package com.example.demo.model.dto;

import lombok.Data;

@Data
public class GetArticleDTO {
    private Integer pageIndex = 1;
    private Integer pageSize = 10;
}
