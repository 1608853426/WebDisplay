package com.example.webdisplay.handler;


import com.example.webdisplay.config.WebSocket;
import com.example.webdisplay.constants.KafkaConsts;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@Slf4j
public class MessageHandler {


//    @KafkaListener(topics = KafkaConsts.TOPIC_TEST, containerFactory = "ackContainerFactory")
//    public void handleMessage(ConsumerRecord record, Acknowledgment acknowledgment) {
//        try {
//            String message = (String) record.value();
//            log.info("收到消息: {}", message);
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        } finally {
//            // 手动提交 offset
//            acknowledgment.acknowledge();
//        }
//    }


}
