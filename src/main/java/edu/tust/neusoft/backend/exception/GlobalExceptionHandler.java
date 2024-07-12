package edu.tust.neusoft.backend.exception;

import edu.tust.neusoft.backend.response.Result;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleException(Exception e) {
        // 记录异常信息
        logger.error("Exception caught in handleException : {}", e.getMessage());
        e.printStackTrace();

        // 返回通用错误响应
        return Result.fail("服务器内部错误: " + e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Result handleConstraintViolationException(ConstraintViolationException e) {
        // 记录异常信息
        logger.error("ConstraintViolationException caught in handleConstraintViolationException : {}", e.getMessage());
        e.printStackTrace();

        // 返回具体的错误响应
        return Result.fail("违反数据库约束条件: " + e.getSQLException().getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public Result handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        // 记录异常信息
        logger.error("DataIntegrityViolationException caught in handleDataIntegrityViolationException : {}", e.getMessage());
        e.printStackTrace();

        // 返回具体的错误响应
        return Result.fail("数据完整性违规: " + e.getMessage());
    }
}
