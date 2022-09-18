package com.example.demo.model.dao;

import com.example.demo.model.dto.ArticleDTO;
import com.example.demo.model.pojo.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ArticleMapper {
    List<Article> selectArticleList();

    List<Article> selectArticleByTitleOrTags(@Param("title") String title, @Param("tag_ids") List<Integer> tag_ids);

    List<Article> selectShelvesArticleList();

    List<Article> selectArticleListByTimeDesc();

    List<Article> selectArticleListByTimeDesc(Date start_time, Date end_time);

    List<Article> selectRandomArticle();

    Article selectArticleById(Integer id);

    Integer addArticle(ArticleDTO articleDTO);

    Integer updateArticle(Article article);

    Integer delArticle(Integer id);

    Integer batchDelArticle(@Param("ids") Integer[] ids);
}
