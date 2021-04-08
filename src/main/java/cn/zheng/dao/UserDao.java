package cn.zheng.dao;

import cn.zheng.model.User;
import org.springframework.stereotype.Repository;

/**
 * @author Zheng Shaobo
 * @version 1.0   2021/3/26 18:33
 */
@Repository
public interface UserDao {

    User find(String id);

}
