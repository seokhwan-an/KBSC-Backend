package com.hanwul.kbscbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 클라이언트로 메시지 응답해줄 때 prefix 정의
        registry.enableSimpleBroker("/sub");
        // 클라이언트에서 메세지 송신시 붙일 prefix ㅈ어의
        registry.setApplicationDestinationPrefixes("/pub");
    }

    // 웹 소켓 연결해주는 설정
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp/chat") // handshake가 될 endPoint 지정
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }
}
