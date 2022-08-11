package com.example.demo.model.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Article {
    private Integer id;

    private String title;

    private Integer status;

    private String des;

    private String main_img;

    private String content;

    private Integer comment_count;

    private Integer praise_count;

    private Integer browse_count;

    private List<Tag> tags = new ArrayList<>();

    private Date create_time;

    private Date update_time;

    private Date delete_time;

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public Integer getStatus() {
//        return status;
//    }
//
//    public void setStatus(Integer status) {
//        this.status = status;
//    }
//
//    public String getDes() {
//        return des;
//    }
//
//    public void setDes(String des) {
//        this.des = des;
//    }
//
//    public String getMain_img() {
//        return main_img;
//    }
//
//    public void setMain_img(String main_img) {
//        this.main_img = main_img;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public Integer getCommentCount() {
//        return commentCount;
//    }
//
//    public void setCommentCount(Integer commentCount) {
//        this.commentCount = commentCount;
//    }
//
//    public Integer getPraiseCount() {
//        return praiseCount;
//    }
//
//    public void setPraiseCount(Integer praiseCount) {
//        this.praiseCount = praiseCount;
//    }
//
//    public Integer getBrowseCount() {
//        return browseCount;
//    }
//
//    public void setBrowseCount(Integer browseCount) {
//        this.browseCount = browseCount;
//    }
//
//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
//
//    public Date getUpdateTime() {
//        return updateTime;
//    }
//
//    public void setUpdateTime(Date updateTime) {
//        this.updateTime = updateTime;
//    }
//
//    public Date getDeleteTime() {
//        return deleteTime;
//    }
//
//    public void setDeleteTime(Date deleteTime) {
//        this.deleteTime = deleteTime;
//    }
//
//    public List<Tag> getTags() {
//        return tags;
//    }
//
//    public void setTags(List<Tag> tags) {
//        this.tags = tags;
//    }
}
