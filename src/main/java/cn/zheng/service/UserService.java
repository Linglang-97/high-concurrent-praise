package cn.zheng.service;

import cn.zheng.dto.UserDTO;

/**
 * @author Zheng Shaobo
 * @version 1.0   2021/3/26 18:52
 */
public interface UserService {

    UserDTO find(String id);

}
