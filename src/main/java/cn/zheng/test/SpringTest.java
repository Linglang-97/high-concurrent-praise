package cn.zheng.test;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author Zheng Shaobo
 * @version 1.0   2021/3/25 15:43
 */
@Service
public class SpringTest {

    @Test
    public void testSpring() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringTest springTest = applicationContext.getBean("springTest", SpringTest.class);
        springTest.sayHello();
    }

    public void sayHello() {
        System.out.println("hello zheng!");
    }

}
