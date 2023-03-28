package com.example.webdisplay;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;


@SpringBootApplication
@EnableWebSocket
public class WebDisplayApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebDisplayApplication.class, args);
    }
}
