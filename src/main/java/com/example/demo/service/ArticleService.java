package com.example.demo.service;

import com.example.demo.exception.BlogException;
import com.example.demo.exception.BlogExceptionEnum;
import com.example.demo.model.dao.ArticleMapper;
import com.example.demo.model.dao.TagMapper;
import com.example.demo.model.dto.ArticleDTO;
import com.example.demo.model.pojo.Article;
import com.example.demo.util.RedisUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

//import java.io.Serializable;
// implements Serializable
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    TagMapper tagMapper;

    @Autowired
    RedisUtils redisUtils;


    //    @Cacheable(value = "getArticleList", key = "#pageIndex+#pageSize")
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
//            System.out.println(idsArr);
            if (idsArr != null && idsArr.size() > 0) {
                article.setTags(tagMapper.selectTagsListByIds(idsArr));
            }
        }
        System.out.println(redisUtils.get("article_list"));
        PageInfo pageInfo = new PageInfo(articleList);
        redisUtils.set("article_list", pageInfo.toString());
        return pageInfo;
    }

    public Article getArticleDetail(Integer id) {
        Article response = articleMapper.selectArticleById(id);
        if (response != null) {
            List<String> idsArr = tagMapper.getArticleTagIds(response.getId());
            response.setTags(tagMapper.selectTagsListByIds(idsArr));
        } else {
            throw new BlogException(BlogExceptionEnum.SELECT_FAILED);
        }
        return response;
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