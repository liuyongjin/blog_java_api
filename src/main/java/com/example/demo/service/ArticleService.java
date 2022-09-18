package com.example.demo.service;

import com.alibaba.fastjson2.JSONObject;
import com.example.demo.common.Constant;
import com.example.demo.exception.BlogException;
import com.example.demo.exception.BlogExceptionEnum;
import com.example.demo.model.dao.ArticleMapper;
import com.example.demo.model.dao.TagMapper;
import com.example.demo.model.dto.ArticleDTO;
import com.example.demo.model.dto.GetArticleDTO;
import com.example.demo.model.pojo.Article;
import com.example.demo.model.vo.PigeonholeArticleVO;
import com.example.demo.util.RedisUtils;
import com.example.demo.util.Toolkit;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
//import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

//import java.io.Serializable;
// implements Serializable
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    TagMapper tagMapper;

    @Autowired
    RedisUtils redisUtils;
    @Autowired
    ObjectMapper objectMapper;

    // @Cacheable(value = "getArticleList", key = "#pageIndex+#pageSize")
    public PageInfo getArticleList(Integer pageIndex, Integer pageSize) {
        String jsonData = redisUtils.get(Constant.ARTICLE_LIST);
        if (jsonData != null) {
            // jsonData = StringEscapeUtils.unescapeHtml(jsonData);
            return JSONObject.parseObject(jsonData, PageInfo.class);
        }

        PageHelper.startPage(pageIndex, pageSize);
        List<Article> articleList = articleMapper.selectShelvesArticleList();
        if (articleList != null) {
            for (Article article : articleList) {
                List<String> idsArr = tagMapper.getArticleTagIds(article.getId());
                // StringBuffer ids = new StringBuffer();
                //// for (String id : idsArr){
                //// ids+= (","+id)
                //// }
                // for (int i = 0; i < idsArr.size(); i++) {
                // if (i == 0) {
                //// ids += idsArr.get(i);
                // ids.append(idsArr.get(i));
                // } else {
                //// ids += ("," + idsArr.get(i));
                // ids.append(("," + idsArr.get(i)));
                // }
                // }
                // System.out.println(idsArr);
                if (idsArr != null && idsArr.size() > 0) {
                    article.setTags(tagMapper.selectTagsListByIds(idsArr));
                }
            }
        }
        PageInfo pageInfo = new PageInfo(articleList);
        redisUtils.set(Constant.ARTICLE_LIST, JSONObject.toJSONString(pageInfo));
        return pageInfo;
    }

    public PageInfo getSearchArticleList(GetArticleDTO getArticleDTO) {
        String title = "%" + getArticleDTO.getTitle() + "%";
        List<Integer> tag_ids = getArticleDTO.getTag_ids();
        List<Article> articleList = getArticleTags(articleMapper.selectArticleByTitleOrTags(title, tag_ids));
        PageHelper.startPage(getArticleDTO.getPageIndex(), getArticleDTO.getPageSize());
        PageInfo pageInfo = new PageInfo(articleList);
        return pageInfo;
    }

    private List<Article> getArticleTags(List<Article> articleList) {
        if (articleList.size() != 0) {
            for (Article article : articleList) {
                List<String> idsArr = tagMapper.getArticleTagIds(article.getId());
                if (idsArr != null && idsArr.size() > 0) {
                    article.setTags(tagMapper.selectTagsListByIds(idsArr));
                }
            }
        }
        return articleList;
    }

    public PageInfo getPigeonholeArticleList() {
        List<Article> articleList = articleMapper.selectArticleListByTimeDesc();
        List<PigeonholeArticleVO> pigeonholeArticleVOList = new ArrayList<>();
        List<String> timeList = new ArrayList<>();
        if (articleList.size() != 0) {
            for (Article article : articleList) {
                Date create_time = article.getCreate_time();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                String formatTime = sdf.format(create_time);
                Date start_time = Toolkit.getFirstMonthDay(create_time);
                Date end_time = Toolkit.getLastMonthDay(create_time);
                if (!timeList.contains(formatTime)) {
                    timeList.add(formatTime);
                    PigeonholeArticleVO pigeonholeArticleVO = new PigeonholeArticleVO();
                    pigeonholeArticleVO.setCreate_time(formatTime);
                    List<Article> articleListByTimeRange = getArticleTags(articleMapper.selectArticleListByTimeDesc(start_time, end_time));
                    pigeonholeArticleVO.setArticle_list(articleListByTimeRange);
                    pigeonholeArticleVOList.add(pigeonholeArticleVO);
                }
            }
        }
        PageInfo pageInfo = new PageInfo(pigeonholeArticleVOList);
        return pageInfo;
    }

    public PageInfo getRandomArticleList(GetArticleDTO getArticleDTO) {
        List<Article> articleList = articleMapper.selectRandomArticle();
        PageHelper.startPage(getArticleDTO.getPageIndex(), getArticleDTO.getPageSize());
        PageInfo pageInfo = new PageInfo(articleList);
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
        // Integer response = 0;
        // try {
        // response = articleMapper.addArticle(articleDTO);
        // } catch (Throwable e) {
        // if (response == 0) {
        // throw new BlogException(BlogExceptionEnum.CREATE_FAILED);
        // }
        // }
        Integer response = articleMapper.addArticle(articleDTO);
        if (response == 0) {
            throw new BlogException(BlogExceptionEnum.CREATE_FAILED);
        }
        return response;
    }

    // TODO: 更新标签关系
    public Integer updateArticle(ArticleDTO articleDTO) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDTO, article);
        Integer response = articleMapper.updateArticle(article);
        if (response == 0) {
            throw new BlogException(BlogExceptionEnum.UPDATE_FAILED);
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