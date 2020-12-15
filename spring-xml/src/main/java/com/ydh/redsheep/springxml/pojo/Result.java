package com.ydh.redsheep.springxml.pojo;

import lombok.Data;
import lombok.ToString;

/**
 * @author yangdehong
 */
public class Result {

    private String status;
    private String message;

    @Override
    public String toString() {
        return "Result{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
