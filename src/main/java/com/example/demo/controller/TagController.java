package com.example.demo.controller;

import com.example.demo.common.ApiResponse;
import com.example.demo.common.Constant;
import com.example.demo.model.dto.GetArticleDTO;
import com.example.demo.model.dto.TagDTO;
import com.example.demo.service.TagService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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

    @PostMapping("/add")
    public ApiResponse addTag(@Validated(TagDTO.Insert.class) TagDTO tagDTO) {
        Integer response = tagService.addTag(tagDTO);
        return ApiResponse.success(response);
    }

    @PostMapping("/edit")
    public ApiResponse editTag(@Validated(TagDTO.Update.class) TagDTO tagDTO) {
        Integer response = tagService.editTag(tagDTO);
        return ApiResponse.success(response);
    }

    @PostMapping("/del")
    public ApiResponse delTag(@NotNull(message = "id不能为空") Integer id) {
        Integer response = tagService.delTag(id);
        return ApiResponse.success(Constant.CommonEnum.CREATE_SUCCESS.getStatus(), Constant.CommonEnum.CREATE_SUCCESS.getMsg(), response);
    }


    @PostMapping("/bdel")
    public ApiResponse batchDelTag(@NotNull(message = "ids不能为空") Integer[] ids) {
        Integer response = tagService.batchDelTag(ids);
        return ApiResponse.success(Constant.CommonEnum.CREATE_SUCCESS.getStatus(), Constant.CommonEnum.CREATE_SUCCESS.getMsg(), response);
    }
}
