package com.qijianguo.ad.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PreRequestFilter extends ZuulFilter{


    @Override
    /**
     *
     */
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    /**
     * 顺序，数字越小越先执行
     */
    public int filterOrder() {
        return 0;
    }

    @Override
    /**
     * 条件执行
     */
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        context.set("startTime", System.currentTimeMillis());
        return null;
    }
}
