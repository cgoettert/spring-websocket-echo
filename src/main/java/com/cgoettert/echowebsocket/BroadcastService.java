package com.cgoettert.echowebsocket;

import org.springframework.web.socket.WebSocketSession;

public interface BroadcastService {

    public void broadcastMessage(WebSocketSession sessions);

}
