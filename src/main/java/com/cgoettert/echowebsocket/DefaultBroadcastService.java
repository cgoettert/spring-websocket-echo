package com.cgoettert.echowebsocket;

import java.io.IOException;
import java.util.SplittableRandom;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultBroadcastService implements BroadcastService {

    public DefaultBroadcastService() {
    }

    @Override
    public void broadcastMessage(WebSocketSession session) {
        try {
            session.sendMessage(new TextMessage(generateRandomIntegerMessage()));
        } catch (IOException e) {
            log.error(String.format("Erro ao enviar mensagem para %s", session.getId()), e);
        }
    }

    private String generateRandomIntegerMessage() {
        return String.valueOf(new SplittableRandom().nextInt(0, 9));
    }

}
