package com.example.demo.service;

import com.example.demo.exception.BlogException;
import com.example.demo.exception.BlogExceptionEnum;
import com.example.demo.model.dao.ArticleMapper;
import com.example.demo.model.dao.TagMapper;
import com.example.demo.model.dto.ArticleDTO;
import com.example.demo.model.pojo.Article;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    TagMapper tagMapper;

    public PageInfo getArticleList(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Article> articleList = articleMapper.selectArticleList();
        for (Article article : articleList) {
            List<String> idsArr = tagMapper.getArticleTagIds(article.getId());
//            StringBuffer ids = new StringBuffer();
////            for (String id : idsArr){
////                ids+= (","+id)
////            }
//            for (int i = 0; i < idsArr.size(); i++) {
//                if (i == 0) {
////                    ids += idsArr.get(i);
//                    ids.append(idsArr.get(i));
//                } else {
////                    ids += ("," + idsArr.get(i));
//                    ids.append(("," + idsArr.get(i)));
//                }
//            }
//            System.out.println(ids);
            article.setTags(tagMapper.selectTagsListByIds(idsArr));
        }
        PageInfo pageInfo = new PageInfo(articleList);
        return pageInfo;
    }


    public Integer addArticle(ArticleDTO articleDTO) {
//        Integer response = 0;
//        try {
//            response = articleMapper.addArticle(articleDTO);
//        } catch (Throwable e) {
//            if (response == 0) {
//                throw new BlogException(BlogExceptionEnum.CREATE_FAILED);
//            }
//        }
        Integer response = articleMapper.addArticle(articleDTO);
        if (response == 0) {
            throw new BlogException(BlogExceptionEnum.CREATE_FAILED);
        }
        return response;
    }

    public Integer delArticle(Integer id) {
        Integer response = articleMapper.delArticle(id);
        if (response == 0) {
            throw new BlogException(BlogExceptionEnum.DELETE_FAILED);
        }
        return response;
    }

    public Integer batchDelArticle(Integer[] ids) {
        Integer response = articleMapper.batchDelArticle(ids);
        if (response == 0) {
            throw new BlogException(BlogExceptionEnum.DELETE_FAILED);
        }
        return response;
    }

}