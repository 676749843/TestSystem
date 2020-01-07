package com.testSystem.shiro.filter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class ShiroPermissionsFilter extends FormAuthenticationFilter {

    private final static String X_REQUESTED_WITH_STRING = "X-Requested-With";
    private final static String XML_HTTP_REQUEST_STRING = "XMLHttpRequest";
    private final static String SESSION_OUT_STIRNG = "sessionOut";

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        if (this.isLoginRequest(servletRequest, servletResponse)) {
            if (this.isLoginSubmission(servletRequest, servletResponse)) {
                return this.executeLogin(servletRequest, servletResponse);
            } else {
                return true;
            }
        } else {
            if (isAjax((HttpServletRequest) servletRequest)) {
                servletResponse.getWriter().print(SESSION_OUT_STIRNG);
            } else {
                this.saveRequestAndRedirectToLogin(servletRequest, servletResponse);
            }
            return false;
        }
    }

    public boolean isAjax(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader(X_REQUESTED_WITH_STRING);
        if (XML_HTTP_REQUEST_STRING.equalsIgnoreCase(header)) {
            return true;
        }
        return false;
    }
}
