package com.example.demo.model.dao;

import com.example.demo.model.pojo.Article;
import com.example.demo.model.pojo.Tag;
import com.example.demo.model.vo.TagArticleVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper {
    List<Article> selectArticleList();
    List<String> getArticleTagIds(Integer id);
    List<Tag> getTagsList(@Param("ids") List<String> ids);
}
