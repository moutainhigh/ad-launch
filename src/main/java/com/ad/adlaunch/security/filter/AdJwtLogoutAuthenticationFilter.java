package com.ad.adlaunch.security.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.CompositeLogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
*
* @author : wezhyn
* @date : 2019/09/20
*
*/
public class AdJwtLogoutAuthenticationFilter extends GenericFilterBean {
    private RequestMatcher logoutRequestMatcher;
    private LogoutSuccessHandler logoutSuccessHandler;

    public AdJwtLogoutAuthenticationFilter(String filterProcessesUrl, LogoutSuccessHandler logoutSuccessHandler) {
        this.logoutSuccessHandler=logoutSuccessHandler;
        this.setFilterProcessesUrl(filterProcessesUrl);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse) res;
        if (this.requiresLogout(request, response)) {
            Authentication auth=SecurityContextHolder.getContext().getAuthentication();
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Logging out user '" + auth + "' and transferring to logout destination");
            }
//            this.logoutHandler.logout(request, response, auth);
            this.logoutSuccessHandler.onLogoutSuccess(request, response, auth);
        } else {
            chain.doFilter(req, res);
        }
    }

    private boolean requiresLogout(HttpServletRequest request, HttpServletResponse response) {
        return this.logoutRequestMatcher.matches(request);
    }

    private void setFilterProcessesUrl(String filterProcessesUrl) {
        this.logoutRequestMatcher=new AntPathRequestMatcher(filterProcessesUrl, null,false);
    }

    public LogoutSuccessHandler getLogoutSuccessHandler() {
        return this.logoutSuccessHandler;
    }

    public void setLogoutSuccessHandler(LogoutSuccessHandler logoutSuccessHandler) {
        this.logoutSuccessHandler=logoutSuccessHandler;
    }
}
