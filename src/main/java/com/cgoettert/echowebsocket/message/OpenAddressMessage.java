package com.cgoettert.echowebsocket.message;

import java.util.Objects;

public class OpenAddressMessage extends BaseMessage {

    private Event event;
    private String address;
    private Integer delay;
    private Integer duration;

    public OpenAddressMessage(String address, Integer duration) {
        if (Objects.isNull(address)) {
            throw new NullPointerException("Endereço nulo");
        }

        if (Objects.isNull(duration)) {
            throw new NullPointerException("Duração nula");
        }

        if (duration <= 0 || duration > 60) {
            throw new IllegalArgumentException("Duração deve ser maior que 0 e menor que 60");
        }

        this.event = Event.OPEN_ADDRESS;
        this.address = address;
        this.delay = 0;
        this.duration = duration;
    }

}
