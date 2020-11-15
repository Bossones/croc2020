package ru.croc.coder.controller;

import java.util.Map;

public class ExceptionDto {

    private String type;
    private String message;
    private Map<String, String> more;

    public ExceptionDto(String type, String message, Map<String, String> more) {
        this.type = type;
        this.message = message;
        this.more = more;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getMore() {
        return more;
    }

    public void setMore(Map<String, String> more) {
        this.more = more;
    }
}
