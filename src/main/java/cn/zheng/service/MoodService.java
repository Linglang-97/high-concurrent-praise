package cn.zheng.service;

import cn.zheng.dto.MoodDTO;
import cn.zheng.model.Mood;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Zheng Shaobo
 * @version 1.0   2021/3/26 18:53
 */
public interface MoodService {

    List<MoodDTO> findAll();
    boolean praiseMood(String userId, String moodId);
    boolean update(@Param("mood")Mood mood);
    Mood findById(String id);
    boolean praiseMoodForRedis(String userId, String moodId);
    List<MoodDTO> findAllForRedis();

}
