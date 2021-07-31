package com.cgoettert.echowebsocket.extensions;

import com.google.gson.Gson;

public class JsonExtension {

    public static <T> String toJson(T source) {
        return new Gson().toJson(source);
    }

}