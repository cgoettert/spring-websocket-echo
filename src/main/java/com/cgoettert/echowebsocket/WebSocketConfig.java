package com.cgoettert.echowebsocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(echoHandler(), "/echo")
                .setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler echoHandler() {
        return new EchoWebSocketHandler(echoService(), broadcastService());
    }

    @Bean
    public DefaultEchoService echoService() {
        return new DefaultEchoService("Echoing: \"%s\"");
    }

    @Bean
    public DefaultBroadcastService broadcastService() {
        return new DefaultBroadcastService();
    }

}
