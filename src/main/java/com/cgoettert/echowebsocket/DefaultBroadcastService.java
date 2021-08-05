package com.cgoettert.echowebsocket;

import java.io.IOException;
import java.util.SplittableRandom;

import com.cgoettert.echowebsocket.message.OpenAddressMessage;
import com.cgoettert.echowebsocket.message.WSMessage;

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
            session.sendMessage(new TextMessage(generateOpenAddressMessage().toJson()));
        } catch (IOException e) {
            log.error(String.format("Erro ao enviar mensagem para %s", session.getId()), e);
        }
    }

    private WSMessage generateOpenAddressMessage() {
        return new OpenAddressMessage(generateRandomAddress(), generateRandomDurationTime());
    }

    private String generateRandomAddress() {
        return generateRandomLetter() + String.valueOf(generateRandomAddressNumber());
    }

    private int generateRandomDurationTime() {
        return new SplittableRandom().nextInt(30, 100);
    }

    private int generateRandomAddressNumber() {
        return new SplittableRandom().nextInt(1, 5);
    }

    private String generateRandomLetter() {
        int num = generateRandomAddressNumber();
        if (num <= 2) {
            return "A";
        }
        if (num == 3) {
            return "B";
        }
        if (num >= 4) {
            return "C";
        }
        return "A";
    }

}
