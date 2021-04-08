package cn.zheng.dto;

import cn.zheng.model.Mood;

import java.io.Serializable;

/**
 * @author Zheng Shaobo
 * @version 1.0   2021/3/26 18:50
 */
public class MoodDTO extends Mood implements Serializable {

    private String userName;
    private String userAccount;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public String toString() {
        return "MoodDTO{" +
                "userName='" + userName + '\'' +
                ", userAccount='" + userAccount + '\'' +
                '}';
    }
}
