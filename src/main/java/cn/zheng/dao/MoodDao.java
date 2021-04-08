package cn.zheng.dao;

import cn.zheng.model.Mood;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zheng Shaobo
 * @version 1.0   2021/3/26 18:36
 */
@Repository
public interface MoodDao {

    List<Mood> findAll();
    boolean update(@Param("mood")Mood mood);
    Mood findById(String id);

}
