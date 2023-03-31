package com.example.webdisplay.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.webdisplay.config.WebSocket;
import com.example.webdisplay.constants.KafkaConsts;
import com.example.webdisplay.entity.StepOneMessage;
import com.example.webdisplay.entity.StepThreeMessage;
import com.example.webdisplay.entity.StepTwoMessage;
import com.example.webdisplay.entity.UserRequestMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/codeController")
public class MessageController {

    /** 消息发送工具对象 */
    @Autowired
    private WebSocket webSocket;


    @KafkaListener(topics = KafkaConsts.TOPIC_REQ, containerFactory = "ackContainerFactory")
    public void handleMessageStepOne(ConsumerRecord record, Acknowledgment acknowledgment) {
        try {
            String message = (String) record.value();
            UserRequestMessage userRequestMessage = JSON.parseObject(message, UserRequestMessage.class);
            String user = userRequestMessage.getIdentId();
            String task = userRequestMessage.getTerminalModel();
            String timeStamp = userRequestMessage.getTimeStamp();
            Date milliSecond = new Date(Long.parseLong(timeStamp));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = sdf.format(milliSecond);
            StepOneMessage stepOneMessage = new StepOneMessage().setUser(user).setTask(task).setTime(time).setStep(1).setRequestId(userRequestMessage.getRequestId());
            webSocket.sendMessage(JSON.toJSONString(stepOneMessage));
            log.info("接收到消息：{}", userRequestMessage);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 手动提交 offset
            acknowledgment.acknowledge();
        }
    }

    @KafkaListener(topics = KafkaConsts.TOPIC_COMMAND, containerFactory = "ackContainerFactory")
    public void handleMessageStepTwo(ConsumerRecord record, Acknowledgment acknowledgment) {
        try {
            String message = (String) record.value();
            JSONObject jsonObject = JSON.parseObject(message);
            String taskId = jsonObject.getString("taskId");
            String terminalModel = jsonObject.getString("terminalModel");
            String state = jsonObject.getString("state");
            String node = jsonObject.getString("node");
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            Double cpuSize = jsonObject.getDouble("cpuSize");
            Double memorySize = jsonObject.getDouble("memorySize");
            StepTwoMessage stepTwoMessage = new StepTwoMessage()
                    .setRequestId(taskId)
                    .setTerminalModel(terminalModel)
                    .setTimeStamp(time)
                    .setStep(2)
                    .setState(state)
                    .setNode(node)
                    .setCpuSize(String.valueOf(cpuSize))
                    .setMemorySize(String.valueOf(memorySize));
            webSocket.sendMessage(JSON.toJSONString(stepTwoMessage));
            log.info("接收到消息：{}", message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 手动提交 offset
            acknowledgment.acknowledge();
        }
    }

    @KafkaListener(topics = KafkaConsts.TOPIC_RESULT, containerFactory = "ackContainerFactory")
    public void handleMessageStepThree(ConsumerRecord record, Acknowledgment acknowledgment) {
        try {
            String message = (String) record.value();
            JSONObject jsonObject = JSON.parseObject(message);
            String url = jsonObject.getString("url");
            String taskId = jsonObject.getString("requestId");
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            StepThreeMessage stepThreeMessage = new StepThreeMessage().setRequestId(taskId).setUrl(url).setTimeStamp(time).setStep(3);
            webSocket.sendMessage(JSON.toJSONString(stepThreeMessage));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 手动提交 offset
            acknowledgment.acknowledge();
        }
    }

}
