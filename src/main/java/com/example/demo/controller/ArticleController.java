package com.example.demo.controller;

import com.example.demo.common.ApiResponse;
import com.example.demo.common.Constant.CommonEnum;
import com.example.demo.model.dto.ArticleDTO;
import com.example.demo.model.dto.GetArticleDTO;
import com.example.demo.service.ArticleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/article")
@Validated
public class ArticleController {

    @Autowired
    ArticleService articleService;

    /**
     * 查询文章列表
     */
    @RequestMapping("/list")
    public ApiResponse getArticleList(@Valid GetArticleDTO getArticleDTO) {
        PageInfo articleList = articleService.getArticleList(getArticleDTO.getPageIndex(), getArticleDTO.getPageSize());
        return ApiResponse.success(articleList);
    }

    @PostMapping("/add")
    public ApiResponse addArticle(@Validated(ArticleDTO.Insert.class) ArticleDTO articleDTO) {
        Integer response = articleService.addArticle(articleDTO);
        return ApiResponse.success(response);
    }

    @PostMapping("/del")
    public ApiResponse delArticle(@NotNull(message = "id不能为空") Integer id) {
        return ApiResponse.success(CommonEnum.CREATE_SUCCESS.getStatus(), CommonEnum.CREATE_SUCCESS.getMsg());
    }

}
