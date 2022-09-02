package com.example.demo.model.dao;

import com.example.demo.model.dto.ArticleDTO;
import com.example.demo.model.pojo.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper {
    List<Article> selectArticleList();

    Article selectArticleById(Integer id);

    Integer addArticle(ArticleDTO articleDTO);

    Integer delArticle(Integer id);

    Integer batchDelArticle(@Param("ids") Integer[] ids);
}
