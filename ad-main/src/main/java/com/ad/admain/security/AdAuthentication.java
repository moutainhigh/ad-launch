package com.ad.admain.security;

import com.wezhyn.project.utils.StringUtils;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author : wezhyn
 * @date : 2019/09/19
 * <p>
 * Copyright (c) 2018-2019 All Rights Reserved.
 */
@Getter
public class AdAuthentication extends UsernamePasswordAuthenticationToken {

/*
    /**********************************************************
    /*  构造器
    /**********************************************************
*/

    public AdAuthentication(String userName, String password) {
        super(userName, password);
    }

    public AdAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public static AdAuthentication createByJwt(Authentication authentication, Collection<? extends GrantedAuthority> authorities) {
        AdAuthentication adAuthentication=(AdAuthentication) authentication;
        AdIdPrincipal adIdPrincipal=new AdIdPrincipal(adAuthentication.getId(), adAuthentication.getName());
        return new AdAuthentication(adIdPrincipal, "", authorities);
    }

    public static AdAuthentication createByJwt(Integer id, String username, Collection<? extends GrantedAuthority> authorities) {
        if (id==null || id <= 0 || StringUtils.isEmpty(username)) {
            throw new RuntimeException("jwt 解析错误");
        }
        AdIdPrincipal adIdPrincipal=new AdIdPrincipal(id, username);
        return new AdAuthentication(adIdPrincipal, "", authorities);
    }

    public Integer getId() {
        if (getPrincipal() instanceof AdUserDetails) {
            return ((AdUserDetails) getPrincipal()).getId();
        } else if (getPrincipal() instanceof AdIdPrincipal) {
            return ((AdIdPrincipal) getPrincipal()).getId();
        }
        throw new RuntimeException("不支持的操作");
    }
}
