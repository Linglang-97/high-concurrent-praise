package cn.zheng.dao;

import cn.zheng.model.UserMoodPraiseRel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Zheng Shaobo
 * @version 1.0   2021/3/26 18:42
 */
@Repository
public interface UserMoodPraiseRelDao {

    boolean save(@Param("userMoodPraiseRel")UserMoodPraiseRel userMoodPraiseRel);

}
