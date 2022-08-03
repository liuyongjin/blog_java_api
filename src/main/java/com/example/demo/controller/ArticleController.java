package com.example.demo.controller;


import com.example.demo.common.ApiResponse;
import com.example.demo.model.pojo.Article;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @RequestMapping("/list")
    public ApiResponse getArticleList() {
        List<Article> articleList = articleService.getArticleList();
        return ApiResponse.success(articleList);
    }
}
