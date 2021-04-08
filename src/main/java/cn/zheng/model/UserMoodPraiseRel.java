package cn.zheng.model;

import java.io.Serializable;

/**
 * @author Zheng Shaobo
 * @version 1.0   2021/3/26 16:46
 */
public class UserMoodPraiseRel implements Serializable {

    private String id;
    private String userId;
    private String moodId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMoodId() {
        return moodId;
    }

    public void setMoodId(String moodId) {
        this.moodId = moodId;
    }
}
