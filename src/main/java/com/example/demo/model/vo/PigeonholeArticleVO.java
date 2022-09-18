package com.example.demo.model.vo;

import com.example.demo.model.pojo.Article;
import lombok.Data;

import java.util.List;

@Data
public class PigeonholeArticleVO {
    private String create_time;
    private List<Article> article_list;
}
