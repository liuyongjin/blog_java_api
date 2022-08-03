package com.example.demo.service;

import com.example.demo.model.dao.ArticleMapper;
import com.example.demo.model.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    public List<Article> getArticleList(){
        return articleMapper.selectArticleList();
    }
}