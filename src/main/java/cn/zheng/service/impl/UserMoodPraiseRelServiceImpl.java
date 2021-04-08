package cn.zheng.service.impl;

import cn.zheng.dao.UserMoodPraiseRelDao;
import cn.zheng.model.UserMoodPraiseRel;
import cn.zheng.service.UserMoodPraiseRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zheng Shaobo
 * @version 1.0   2021/3/26 19:05
 */
@Service
public class UserMoodPraiseRelServiceImpl implements UserMoodPraiseRelService {

    @Autowired
    private UserMoodPraiseRelDao userMoodPraiseRelDao;

    @Override
    public boolean save(UserMoodPraiseRel userMoodPraiseRel) {
        return userMoodPraiseRelDao.save(userMoodPraiseRel);
    }
}
