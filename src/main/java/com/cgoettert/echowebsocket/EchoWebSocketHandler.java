package com.cgoettert.echowebsocket;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EchoWebSocketHandler extends TextWebSocketHandler {

    private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    private final EchoService echoService;
    private final BroadcastService broadcastService;

    @Autowired
    public EchoWebSocketHandler(EchoService echoService, BroadcastService broadcastService) {
        this.echoService = echoService;
        this.broadcastService = broadcastService;
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.debug("RECEIVED: {}", message.getPayload());

        String reply = this.echoService.getMessage(message.getPayload());
        session.sendMessage(new TextMessage(reply));

        log.debug("SENT: {}", reply);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        log.debug("New session: {}", session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        sessions.remove(session);
        log.debug("Session {} removed - CloseStatus code {}", session.getId(), closeStatus.getCode());
    }

    @Scheduled(fixedDelayString = "${websocket.broadcast.fixeddelay}")
    public void scheduledBroadcast() {
        log.info("Broadcast to {} client(s)", sessions.size());
        for (WebSocketSession session : sessions) {
            broadcastService.broadcastMessage(session);
        }
    }

}
