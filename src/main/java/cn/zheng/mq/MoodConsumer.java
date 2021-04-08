package cn.zheng.mq;

import cn.zheng.dto.MoodDTO;
import cn.zheng.model.Mood;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * @author Zheng Shaobo
 * @version 1.0   2021/3/27 18:49
 */
//异步接收，需要对MessageListener进行实现，然后设置为Consumer实例的messageListener
@Component
public class MoodConsumer implements MessageListener {

    private static final String PRAISE_HASH_KEY = "springmv.mybatis.boot.mood.id.list.key";
    @Autowired
    private RedisTemplate redisTemplate;
    private Logger log = Logger.getLogger(this.getClass());

    @Override
    public void onMessage(Message message) {
        try {
            //从message对象中获取说说实体
            MoodDTO moodDTO = (MoodDTO) ((ActiveMQObjectMessage) message).getObject();
            redisTemplate.opsForSet().add(PRAISE_HASH_KEY, moodDTO.getId());
            redisTemplate.opsForSet().add(moodDTO.getId(), moodDTO.getUserId());
            log.info("消费者--->>>用户id：" + moodDTO.getUserId() + " 给说说id：" + moodDTO.getId() + " 点赞");
        } catch (JMSException e) {
            System.out.println(e);
        }
    }
}
