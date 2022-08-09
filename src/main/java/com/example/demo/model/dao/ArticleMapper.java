package com.example.demo.model.dao;

import com.example.demo.model.dto.ArticleDTO;
import com.example.demo.model.pojo.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper {
    List<Article> selectArticleList();
    Integer addArticle(ArticleDTO articleDTO);
}
