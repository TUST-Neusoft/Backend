package edu.tust.neusoft.backend.response;

import lombok.Data;

import static edu.tust.neusoft.backend.config.BaseConfig.SUCCESS;
import static edu.tust.neusoft.backend.config.BaseConfig.FAIL;
@Data
public class Result {
    private int code;
    private String msg;
    private Object data;

    public static Result success(String msg, Object data) {
        Result result = new Result();
        result.code = SUCCESS;
        result.msg = msg;
        result.data = data;
        return result;
    }

    public static Result fail(String msg) {
        Result result = new Result();
        result.code = FAIL;
        result.msg = msg;
        return result;
    }
}
