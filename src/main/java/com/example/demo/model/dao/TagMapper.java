package com.example.demo.model.dao;

import com.example.demo.model.pojo.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMapper {
    List<String> getArticleTagIds(Integer id);
    List<Tag> selectTagsListByIds(@Param("ids") List<String> ids);
    List<Tag> selectTagList();
    Integer insertTag(Tag tag);
}
