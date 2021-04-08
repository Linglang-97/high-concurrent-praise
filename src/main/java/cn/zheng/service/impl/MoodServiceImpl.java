package cn.zheng.service.impl;

import cn.zheng.dao.MoodDao;
import cn.zheng.dao.UserDao;
import cn.zheng.dao.UserMoodPraiseRelDao;
import cn.zheng.dto.MoodDTO;
import cn.zheng.model.Mood;
import cn.zheng.model.User;
import cn.zheng.model.UserMoodPraiseRel;
import cn.zheng.mq.MoodProducer;
import cn.zheng.service.MoodService;
import cn.zheng.service.UserService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.jms.Destination;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Zheng Shaobo
 * @version 1.0   2021/3/26 18:57
 */
@Service
public class MoodServiceImpl implements MoodService {

    @Autowired
    private MoodDao moodDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserMoodPraiseRelDao userMoodPraiseRelDao;
    @Autowired
    private RedisTemplate redisTemplate;
    private static final String PRAISE_HASH_KEY = "springmv.mybatis.boot.mood.id.list.key";
    @Autowired
    private UserService userService;
    @Autowired
    private MoodProducer moodProducer;
    private static Destination destination = new ActiveMQQueue("zheng.queue.high.concurrency.praise");

    @Override
    public List<MoodDTO> findAll() {
        List<Mood> moodList = moodDao.findAll();
        return converModel2DTO(moodList);
    }

    @Override
    public boolean praiseMood(String userId, String moodId) {
        UserMoodPraiseRel userMoodPraiseRel = new UserMoodPraiseRel();
        userMoodPraiseRel.setUserId(userId);
        userMoodPraiseRel.setMoodId(moodId);
        userMoodPraiseRelDao.save(userMoodPraiseRel);
        Mood mood = this.findById(moodId);
        mood.setPraiseNum(mood.getPraiseNum() + 1);
        this.update(mood);
        return Boolean.TRUE;
    }

    @Override
    public boolean update(Mood mood) {
        return moodDao.update(mood);
    }

    @Override
    public Mood findById(String id) {
        return moodDao.findById(id);
    }

    @Override
    public boolean praiseMoodForRedis(String userId, String moodId) {
        MoodDTO moodDTO = new MoodDTO();
        moodDTO.setUserId(userId);
        moodDTO.setId(moodId);
        moodProducer.sendMessage(destination, moodDTO);
        return false;
    }

    @Override
    public List<MoodDTO> findAllForRedis() {
        List<Mood> moodList = moodDao.findAll();
        if(CollectionUtils.isEmpty(moodList)) {
            return Collections.EMPTY_LIST;
        }
        List<MoodDTO> moodDTOList = new ArrayList<>();
        for(Mood mood: moodList) {
            MoodDTO moodDTO = new MoodDTO();
            moodDTO.setId(mood.getId());
            moodDTO.setUserId(mood.getUserId());
            moodDTO.setPraiseNum(mood.getPraiseNum() + redisTemplate.opsForSet().size(mood.getId()).intValue());
            moodDTO.setPublishTime(mood.getPublishTime());
            moodDTO.setContent(mood.getContent());
            User user = userService.find(mood.getUserId());
            moodDTO.setUserName(user.getName());
            moodDTO.setUserAccount(user.getAccount());
            moodDTOList.add(moodDTO);
        }
        return moodDTOList;
    }

    private List<MoodDTO> converModel2DTO(List<Mood> moodList) {
        if(CollectionUtils.isEmpty(moodList))
            return Collections.EMPTY_LIST;
        List<MoodDTO> moodDTOList = new ArrayList<>();
        for(Mood mood: moodList) {
            MoodDTO moodDTO = new MoodDTO();
            moodDTO.setId(mood.getId());
            moodDTO.setContent(mood.getContent());
            moodDTO.setPraiseNum(mood.getPraiseNum());
            moodDTO.setPublishTime(mood.getPublishTime());
            moodDTO.setUserId(mood.getUserId());
            moodDTOList.add(moodDTO);
            User user = userDao.find(mood.getUserId());
            moodDTO.setUserName(user.getName());
            moodDTO.setUserAccount(user.getAccount());
        }
        return moodDTOList;
    }
}
