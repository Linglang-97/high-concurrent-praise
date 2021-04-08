package cn.zheng.test;

import cn.zheng.dto.MoodDTO;
import cn.zheng.service.MoodService;
import cn.zheng.service.impl.MoodServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author Zheng Shaobo
 * @version 1.0   2021/3/26 19:07
 */
public class JDBCTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        MoodService service = applicationContext.getBean("moodServiceImpl", MoodService.class);
        List<MoodDTO> all = service.findAll();
        for(MoodDTO moodDTO : all)
            System.out.println(moodDTO.toString());
    }


}
