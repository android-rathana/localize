package com.kshrd.kd.models.utilities;

public class Response<T> {

    private String code;
    private boolean status;
    private String msg;

    public Response() {
    }

    public Response(String code, boolean status, String msg) {
        this.code = code;
        this.status = status;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code='" + code + '\'' +
                ", status=" + status +
                ", msg='" + msg + '\'' +
                '}';
    }
}
