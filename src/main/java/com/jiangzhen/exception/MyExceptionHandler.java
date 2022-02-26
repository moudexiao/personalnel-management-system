package com.jiangzhen.exception;

import com.jiangzhen.enums.ResultEnum;
import com.jiangzhen.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class MyExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResultVo handlerException(BaseException e) {
        return ResultVo.fail(e.getResultEnum());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVo notValidExceptionHandle(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        Objects.requireNonNull(bindingResult.getFieldError());
        log.error(bindingResult.getFieldError().getField() + " " + bindingResult.getFieldError().getDefaultMessage());
        return ResultVo.fail(ResultEnum.PARAM_ERROR,
                bindingResult.getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public ResultVo AuthenticationException(AuthenticationException e) {
        log.error("AuthenticationException:{}", e.getMessage());
        return ResultVo.fail(ResultEnum.AUTHENTICATE_FAIL, e.getMessage());
    }

    @ExceptionHandler(value = AuthorizationException.class)
    public ResultVo AuthorizationException(AuthorizationException e) {
        log.error("AuthorizationException:{}", e.getMessage());
        return ResultVo.fail(ResultEnum.AUTHORIZATION_FAIL);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResultVo SQLIntegrity(SQLIntegrityConstraintViolationException e) {
        log.error(e.getMessage());
        log.error(e.getSQLState());
        return ResultVo.fail(ResultEnum.PARAM_ERROR, "请先删除已有数据!");
    }

//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public ResultVo Exception(Exception e) {
//        log.error(e.getMessage());
//        return ResultVo.fail(ResultEnum.PARAM_ERROR,"服务器异常!");
//    }
}
