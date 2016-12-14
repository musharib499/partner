package com.gaadi.pratnerapps.models;

/**
 * Created by vinodtakhar on 6/6/16.
 */
public class BaseResponseModel {
    
    private boolean status;
    private String message;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess(){
        return getStatus();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BaseResponseModel{");
        sb.append("status=").append(status);
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
