package com.example.demo.service;

import com.example.demo.exception.BlogException;
import com.example.demo.exception.BlogExceptionEnum;
import com.example.demo.model.dao.TagMapper;
import com.example.demo.model.dto.TagDTO;
import com.example.demo.model.pojo.Tag;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    TagMapper tagMapper;

    public PageInfo getTagList(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Tag> tagList = tagMapper.selectTagList();
        PageInfo pageInfo = new PageInfo(tagList);
        return pageInfo;
    }

    public Integer addTag(TagDTO tagDTO) {
        Tag tag = new Tag();
        BeanUtils.copyProperties(tagDTO, tag);
        System.out.println(tag.toString());
        Integer response = tagMapper.insertTag(tag);
        if (response == 0) {
            throw new BlogException(BlogExceptionEnum.CREATE_FAILED);
        }
        return response;
    }

}