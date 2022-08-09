package com.example.demo.service;

import com.example.demo.model.dao.TagMapper;
import com.example.demo.model.pojo.Tag;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

}