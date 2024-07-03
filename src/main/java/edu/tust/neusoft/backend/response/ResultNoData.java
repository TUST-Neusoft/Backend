package edu.tust.neusoft.backend.response;

import lombok.Data;

import static edu.tust.neusoft.backend.config.BaseConfig.FAIL;
import static edu.tust.neusoft.backend.config.BaseConfig.SUCCESS;

@Data
public class ResultNoData {
    private int code;
    private String msg;

    public static ResultNoData success(String msg) {
        ResultNoData result = new ResultNoData();
        result.code = SUCCESS;
        result.msg = msg;
        return result;
    }

    public static ResultNoData fail(String msg) {
        ResultNoData result = new ResultNoData();
        result.code = FAIL;
        result.msg = msg;
        return result;
    }
}