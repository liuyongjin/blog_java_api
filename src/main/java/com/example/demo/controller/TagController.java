package com.example.demo.controller;

import com.example.demo.common.ApiResponse;
import com.example.demo.model.dto.GetArticleDTO;
import com.example.demo.service.TagService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/tag")
@Validated
public class TagController {

    @Autowired
    TagService tagService;

    /**
     * 查询标签列表
     */
    @RequestMapping("/list")
    public ApiResponse getTagList(@Valid GetArticleDTO getArticleDTO) {
        PageInfo tagList = tagService.getTagList(getArticleDTO.getPageIndex(), getArticleDTO.getPageSize());
        return ApiResponse.success(tagList);
    }

}
