package cn.zheng.mq;

import cn.zheng.dto.MoodDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

import javax.jms.Destination;


/**
 * @author Zheng Shaobo
 * @version 1.0   2021/3/27 18:49
 */
@Component
public class MoodProducer {

    @Autowired
    private JmsTemplate jmsTemplate;
    private Logger log = Logger.getLogger(this.getClass());

    public void sendMessage(Destination destination, final MoodDTO mood) {
        log.info("生产者--->>>用户id：" + mood.getUserId() + " 给说说id：" + mood.getId() + " 点赞");
        jmsTemplate.convertAndSend(destination, mood);
    }

}
