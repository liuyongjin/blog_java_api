package com.example.demo.model.dao;

import com.example.demo.model.dto.AddArticleDTO;
import com.example.demo.model.pojo.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper {
    List<Article> selectArticleList();
    Integer addArticle(AddArticleDTO addArticleDTO);
}
