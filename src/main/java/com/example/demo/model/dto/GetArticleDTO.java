package com.example.demo.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class GetArticleDTO {
    private Integer pageIndex = 1;
    private Integer pageSize = 10;
    private String title;
    private List<Integer> tag_ids;
}
