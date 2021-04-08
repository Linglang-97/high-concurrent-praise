package cn.zheng.model;

import java.io.Serializable;

/**
 * @author Zheng Shaobo
 * @version 1.0   2021/3/25 16:37
 */
public class User implements Serializable {

    private String id;
    private String name;
    private String account;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
