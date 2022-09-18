package com.example.demo.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

@Data
public class ArticleDTO {
    public interface Insert {

    }

    public interface Update {

    }

    @NotNull(message = "id不能为空", groups = Update.class)
    @Null(message = "id必须为空", groups = Insert.class)
    private Integer id;

    @NotNull(message = "文章标题不能为空", groups = {TagDTO.Insert.class, TagDTO.Update.class})
    private String title;

    //    @NotNull(message = "描述不能为空")
    private String des;

    @NotNull(message = "主图不能为空", groups = {TagDTO.Insert.class, TagDTO.Update.class})
    private String main_img;

    @NotNull(message = "内容不能为空", groups = {TagDTO.Insert.class, TagDTO.Update.class})
    private String content;

    private Integer status;

    private Integer comment_count;

    private Integer praise_count;

    private Integer browse_count;

    private List<Integer> tag_ids ;

}
