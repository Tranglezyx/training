package com.example.mytraining.interceptor;

import com.example.mytraining.datasource.DataSourceContextHolder;
import com.example.mytraining.datasource.DynamicTableHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class DataSourceInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getHeader("userId");
        log.info("preHandler >>>> 用户Id:{}", userId);
        if (!StringUtils.hasText(userId)) {
            userId =  "1";
        }
        Integer firstValue = Integer.valueOf(userId.charAt(0) + "");
        Integer lastValue = Integer.valueOf(userId.charAt(userId.length() - 1) + "");
        if (firstValue % 2 == 0) {
            DataSourceContextHolder.setDataSource(2);
        } else {
            DataSourceContextHolder.setDataSource(1);
        }
        if (lastValue % 2 == 0) {
            DynamicTableHolder.set(2);
        } else {
            DynamicTableHolder.set(1);
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle >>>> ");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String userId = request.getHeader("userId");
        log.info("afterCompletion >>>> 用户Id:{}", userId);
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        DataSourceContextHolder.remove();
        DynamicTableHolder.remove();
    }
}
