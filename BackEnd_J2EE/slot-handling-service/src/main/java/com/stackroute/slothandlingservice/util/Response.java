package com.stackroute.slothandlingservice.util;

public class Response<T> {
    private T data;
    private String message;
    private String code;

    public Response() {
        super();
    }

    public Response(T data, String message, String code) {
        super();
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public Response(String message, String code) {
        super();
        this.message = message;
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
