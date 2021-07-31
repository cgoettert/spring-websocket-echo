package com.cgoettert.echowebsocket.message;

import com.cgoettert.echowebsocket.extensions.JsonExtension;

import lombok.experimental.ExtensionMethod;

@ExtensionMethod(JsonExtension.class)
abstract class BaseMessage implements WSMessage {

    @Override
    public String toJson() {
        return this.toJson();
    };

}
