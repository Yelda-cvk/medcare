package com.medcare.medcare.util;

public class Response<T> {

    private T data;
    private String mesaj;

    public Response(T data, String mesaj) {
        this.data = data;
        this.mesaj = mesaj;
    }

    public T getData() {
        return data;
    }

    public String getMesaj() {
        return mesaj;
    }
}
