package com.qijianguo.ad.advice;

import com.qijianguo.ad.exception.AdException;
import com.qijianguo.ad.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * 只处理AdException
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(value = AdException.class)
    public CommonResponse<String> handlerAdException(HttpServletRequest request, AdException ex) {

        CommonResponse response = new CommonResponse(-1, "business error");
        response.setData(ex.getMessage());
        return response;
    }

}
