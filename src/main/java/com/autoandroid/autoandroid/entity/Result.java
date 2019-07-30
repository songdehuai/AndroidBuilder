package com.autoandroid.autoandroid.entity;

public class Result {

    private int code = 400;
    private String message;
    private Object result;

    public Result() {

    }

    public Result(int code, Object result) {
        this.code = code;
        if (code == 200) {
            message = "成功";
        } else {
            message = "错误";
        }
        this.result = result;
    }

    public Result(int code, String result) {
        this.code = code;
        if (code == 200) {
            message = "成功";
        } else {
            message = "错误";
        }
        this.result = result;
    }

    public Result(int code) {
        this.code = code;
        if (code == 200) {
            message = "成功";
        } else {
            message = "错误";
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
