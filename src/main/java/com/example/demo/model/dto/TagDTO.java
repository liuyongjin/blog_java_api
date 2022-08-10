package com.example.demo.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class TagDTO {
    public interface Update {
    }

    public interface Insert {
    }

    @Null(message = "id必须为空", groups = Insert.class)
    @NotNull(message = "id不能为空", groups = Update.class)
    private Integer id;

    @NotNull(message = "标签名称不能为空", groups = {Insert.class, Update.class})
    private String name;

    private String des;

    @NotNull(message = "标签颜色不能为空", groups = {Insert.class, Update.class})
    private String color;

    @NotNull(message = "标签背景颜色不能为空", groups = {Insert.class, Update.class})
    private String bg_color;
}
