package cn.zheng.service.impl;

import cn.zheng.dao.UserDao;
import cn.zheng.dto.UserDTO;
import cn.zheng.model.User;
import cn.zheng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zheng Shaobo
 * @version 1.0   2021/3/26 18:53
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDTO find(String id) {
        User user = userDao.find(id);
        return converModel2DTO(user);
    }

    private UserDTO converModel2DTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setAccount(user.getAccount());
        return userDTO;
    }

}
