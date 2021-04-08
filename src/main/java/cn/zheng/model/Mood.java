package cn.zheng.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author Zheng Shaobo
 * @version 1.0   2021/3/26 16:42
 */
public class Mood implements Serializable {

    private String id;
    private String content;
    private Integer praiseNum;
    private String userId;
    private Date publishTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
}
