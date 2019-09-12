package com.xyy.cache;


import com.xyy.cache.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CachedemoApplication.class)
public class CachedemoApplicationTests {

    @Autowired
    RedisUtil redisUtil;
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    JavaMailSenderImpl javaMailSender;



    /**
     * redisTemplate操作缓存
     */
    /*@Test
    public void context1(){
        String  s ="a::admin";
        boolean b = redisTemplate.delete(s);
        System.out.println("============"+b+"===========");
    }
*/
    /**
     * 消息生产者
     */
    @Test
    public void context2(){
        rabbitTemplate.convertAndSend("test.exchange","test","消息生产者发送消息到队列");
    }
    /**
     * 消息消费者
     */
    @Test
    public void context4(){
        Object test = rabbitTemplate.receiveAndConvert("test.queue");
        System.out.println(test.getClass());
        System.out.println(test.toString());
    }
    /*@Test
    public void context5(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(javaMailSender.getUsername());
        message.setTo("1562016984@qq.com"); //自己给自己发送邮件
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");
        javaMailSender.send(message);
    }*/

    /*@Test
    public void context6(){
        MimeMessage message = null;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            message.setFrom(javaMailSender.getUsername());
            helper.setTo("1562016984@qq.com");
            helper.setSubject("标题：发送Html内容");

            StringBuffer sb = new StringBuffer();
            sb.append("<h1>大标题-h1</h1>");
            sb.append("<p style='color:#F00'>红色字</p>");
            sb.append("<p style='text-align:right'>右对齐</p>");
            helper.setText(sb.toString(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        javaMailSender.send(message);
    }*/

    /*@Test
    public void sendAttachmentsMail() {
        MimeMessage message = null;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(javaMailSender.getUsername());
            helper.setTo("1562016984@qq.com");
            helper.setSubject("主题：带附件的邮件");
            helper.setText("带附件的邮件内容");
            //注意项目路径问题，自动补用项目路径
            FileSystemResource file = new FileSystemResource(new File("C:\\Users\\Administrator\\Desktop\\桌面.jpg"));
            //加入邮件
            helper.addAttachment("桌面.jpg", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        javaMailSender.send(message);
    }*/

}
