package com.qijianguo.ad.advice;

import com.qijianguo.ad.annotation.IgnoreResponseAdvice;
import com.qijianguo.ad.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

public class CommonResponseDataAdvice implements ResponseBodyAdvice {

    @Override
    /**
     * 判断是否拦截
     */
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        // 如果方法或类中标注了 IgnoreResponseAdvice 则不进行拦截
        if (Objects.requireNonNull(methodParameter.getMethod()).isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }

        return true;
    }

    @Override
    @Nullable
    @SuppressWarnings("all")
    /**
     * 响应前拦截
     */
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {

        CommonResponse commonResponse = new CommonResponse(0, "");
        if (null == o) {
            return commonResponse;
        } else if (o instanceof CommonResponse){
            commonResponse = (CommonResponse<Objects>) o;
        } else {
            commonResponse.setData(0);
        }
        return commonResponse;
    }
}
