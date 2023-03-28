package com.example.webdisplay.controller;


import com.example.webdisplay.config.WebSocket;
import com.example.webdisplay.constants.KafkaConsts;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/codeController")
public class MessageController {

    /** 消息发送工具对象 */
    @Autowired
    private WebSocket webSocket;



    /** 广播发送消息，将消息发送到指定的目标地址 */
    @PostMapping("/jinDuTiao")
    public void jinDuTiao() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            Thread.sleep(10000);
            webSocket.sendMessage(i + "");
        }
    }


    @KafkaListener(topics = KafkaConsts.TOPIC_TEST, containerFactory = "ackContainerFactory")
    public void handleMessage(ConsumerRecord record, Acknowledgment acknowledgment) {
        try {
            String message = (String) record.value();
            log.info("收到消息: {}", message);
            webSocket.sendMessage(message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 手动提交 offset
            acknowledgment.acknowledge();
        }
    }

}
