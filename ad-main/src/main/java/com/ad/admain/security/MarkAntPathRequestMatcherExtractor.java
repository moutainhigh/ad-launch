package com.ad.admain.security;

import com.ad.admain.config.web.JwtProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 通过组合模式实现
 *
 * @author : wezhyn
 * @date : 2019/09/24
 * <p>
 * Copyright (c) 2018-2019 All Rights Reserved.
 */
@Slf4j
public class MarkAntPathRequestMatcherExtractor implements RequestMatcher {

    /**
     * 储存当前登录用户是使用何种标识： requestMatchers.key
     */
    private static final ThreadLocal<String> MARK_CACHE=new ThreadLocal<>();
    private Map<String, RequestMatcher> requestMarkMatchers;

    /**
     * {@link JwtProperties#getLoginInterceptionInclude()}
     *
     * @param interceptMap map
     */
    public MarkAntPathRequestMatcherExtractor(Map<String, String> interceptMap) {
        if (interceptMap==null || interceptMap.size()==0) {
            throw new NullPointerException("无有效拦截列表");
        }
        requestMarkMatchers=new HashMap<>(7);
        for (String mark : interceptMap.keySet()) {
            log.info("mark: {} intercept : {}", mark, interceptMap.get(mark));
            requestMarkMatchers.put(mark,
                    new AntPathRequestMatcher(interceptMap.get(mark), null, false));
        }
    }

    /**
     * todo: 一个更好的解决方案
     * 在{@link com.ad.admain.security.LoginAuthenticationFailureHandler} 和
     * {@link com.ad.admain.security.LoginAuthenticationSuccessHandler} 中清除
     */
    public static void removeThreadLocal() {
        MARK_CACHE.remove();
    }

    public static ThreadLocal<String> getMarkCache() {
        return MARK_CACHE;
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        for (String mark : requestMarkMatchers.keySet()) {
            RequestMatcher matcher=requestMarkMatchers.get(mark);
            if (matcher.matches(request)) {
                MARK_CACHE.set(mark);
                return true;
            }
        }
        return false;
    }
}
