package cn.zheng.job;

import cn.zheng.model.Mood;
import cn.zheng.model.UserMoodPraiseRel;
import cn.zheng.service.MoodService;
import cn.zheng.service.UserMoodPraiseRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Set;

/**
 * @author Zheng Shaobo
 * @version 1.0   2021/3/26 21:09
 */
@Component
@Configurable
@EnableScheduling
public class PraiseDataSaveDBJob {

    @Autowired
    private RedisTemplate redisTemplate;
    private static final String PRAISE_HASH_KEY = "springmv.mybatis.boot.mood.id.list.key";
    @Autowired
    private UserMoodPraiseRelService userMoodPraiseRelService;
    @Autowired
    private MoodService moodService;

    //每5s执行一次
    @Scheduled(cron = "*/20 * * * * * ")
    public void savePraiseDataToDB2() {
        //step1:取出Redis缓存中所有被点赞的说说id
        Set<String> moods = redisTemplate.opsForSet().members(PRAISE_HASH_KEY);
        if(CollectionUtils.isEmpty(moods)) {
            return;
        }
        for(String moodId: moods) {
            if(redisTemplate.opsForSet().members(moodId) == null) {
                continue;
            } else {
                //step2:从Redis缓存中通过说说id获取所有点赞的用户id列表
                Set<String> userIds = redisTemplate.opsForSet().members(moodId);
                if(CollectionUtils.isEmpty(userIds)) {
                    continue;
                } else {
                    //step3:循环保存mood_id和user_id的关联关系到MySQL数据库
                    for(String userId: userIds) {
                        UserMoodPraiseRel userMoodPraiseRel = new UserMoodPraiseRel();
                        userMoodPraiseRel.setMoodId(moodId);
                        userMoodPraiseRel.setUserId(userId);
                        userMoodPraiseRelService.save(userMoodPraiseRel);
                    }
                    Mood mood = moodService.findById(moodId);
                    //step4:更新说说点赞数量
                    //说说的总点赞数量=Redis点赞数量+数据库的点赞数量
                    mood.setPraiseNum(mood.getPraiseNum() +
                            redisTemplate.opsForSet().size(moodId).intValue());
                    moodService.update(mood);
                    redisTemplate.delete(moodId);
                }
            }
        }
        redisTemplate.delete(PRAISE_HASH_KEY);
    }

}
