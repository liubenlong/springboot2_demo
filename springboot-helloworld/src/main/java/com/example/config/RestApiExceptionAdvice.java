package com.example.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 参数校验统一处理类
 */
@ControllerAdvice
@Slf4j
public class RestApiExceptionAdvice {

    @ExceptionHandler({BindException.class})
    @ResponseBody
    public R handleValidationException(BindException e) {
        StringBuilder bindErrorBuilder = new StringBuilder();
        StringBuilder noBindErrorBuilder = new StringBuilder();
        List<FieldError> fieldErrors = e.getFieldErrors();
        fieldErrors.forEach(fieldError -> {
            if (fieldError.isBindingFailure()) {
                bindErrorBuilder.append(fieldError.getField()).append(",");
            } else {
                noBindErrorBuilder.append(fieldError.getDefaultMessage()).append(",");
            }
        });
        String bindError = bindErrorBuilder.toString();
        String bindErrorMsg = StringUtils.isBlank(bindError) ? null : "参数有误：" + bindError.substring(0, bindError.length() - 1);
        String noBindError = noBindErrorBuilder.toString();
        String noBindErrorMsg = StringUtils.isBlank(noBindError) ? null : noBindError.substring(0, noBindError.length() - 1);

        String msg = StringUtils.isBlank(bindErrorMsg) ?
                noBindErrorMsg :
                bindErrorMsg + (StringUtils.isBlank(noBindErrorMsg) ? "" : "," + noBindErrorMsg);
        log.warn("参数校验不通过,msg: {}", msg, e);
        return new R<>(ParamErrorCode.PARAM_ERROR, msg, null);
    }

    @ExceptionHandler({ConstraintViolationException.class,
            MethodArgumentNotValidException.class,
            ServletRequestBindingException.class,
            MethodArgumentTypeMismatchException.class,
            IllegalArgumentException.class,
            HttpMessageNotReadableException.class})
    @ResponseBody
    public R handleValidationException(Exception e) {
        String msg = "";
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException t = (MethodArgumentNotValidException) e;
            msg = t.getBindingResult().getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(","));
        } else if (e instanceof ConstraintViolationException) {
            ConstraintViolationException t = (ConstraintViolationException) e;
            msg = t.getConstraintViolations().stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(","));
        } else if (e instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException t = (MissingServletRequestParameterException) e;
            msg = t.getParameterName() + " 不能为空";
        } else if (e instanceof MissingPathVariableException) {
            MissingPathVariableException t = (MissingPathVariableException) e;
            msg = t.getVariableName() + " 不能为空";
        } else if (e instanceof IllegalArgumentException) {
            IllegalArgumentException t = (IllegalArgumentException) e;
            msg = t.getMessage();
        } else {
            msg = "参数有误";
        }

        log.warn("参数校验不通过,msg: {}", msg, e);

        return new R<>(ParamErrorCode.PARAM_ERROR, msg, null);
    }

    /**
     * 统一拦截所有服务端抛出的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public R handleException(Exception e) {
        log.error("服务器发生异常！", e);
        return new R<>(ParamErrorCode.ERROR, "服务器发生异常", e.getMessage());
    }

}
