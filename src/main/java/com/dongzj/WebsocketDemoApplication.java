package com.dongzj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
@EnableWebSocket
public class WebsocketDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsocketDemoApplication.class, args);
    }

    /**
     * 会自动注册使用了@ServerEndpoint注解声明的WebSocket endpoint
     * 要注意，如果使用独立的servlet容器，
     * 而不是直接使用springboot的内置容器
     * 就不要注入ServerEndpointExporter，因为它将由容器自己提供和管理
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}

