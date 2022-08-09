package com.example.demo.model.dto;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddAritcleDTO {

    @NotNull(message = "文章标题不能为空")
    private String title;

    private Integer status;

    @NotNull(message = "描述不能为空")
    private String des;

    @NotNull(message = "主图不能为空")
    private String main_img;

    @NotNull(message = "内容不能为空")
    private String content;

    private Integer commentCount;

    private Integer praiseCount;

    private Integer browseCount;

}
