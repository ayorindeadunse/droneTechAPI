package com.ayorinde.dronetechapi.dronetechapi.responses;

public class ApiResponse {
    private Boolean success;
    private String message;
    // data
    private Object data;

    public ApiResponse() {
    }

    public ApiResponse(Boolean success, String message,Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}


