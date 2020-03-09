package com.cc.pop.gg.vender.web.filter;

import com.jd.paipai.commons.utils.XssEscapeUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Map;

/**
 * Xss过滤器
 */
public class XssFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = null;
        if (request instanceof HttpServletRequest) {
            httpServletRequest = (HttpServletRequest) request;
        }
        chain.doFilter(new XssHttpServletRequestWrapper(httpServletRequest), response);
    }

    @Override
    public void destroy() {
    }

    private class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

        /**
         * 构造方法
         *
         * @param request
         */
        public XssHttpServletRequestWrapper(HttpServletRequest request) {
            super(request);
        }

        @Override
        public String getHeader(String name) {
            return XssEscapeUtils.escape(super.getHeader(name));
        }

        @Override
        public String getQueryString() {
            return XssEscapeUtils.escape(super.getQueryString());
        }

        @Override
        public String getParameter(String name) {
            return XssEscapeUtils.escape(super.getParameter(name));
        }

        @Override
        public String[] getParameterValues(String name) {
            return XssEscapeUtils.escapeArray(super.getParameterValues(name));
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            return XssEscapeUtils.escapeMap(super.getParameterMap());
        }

    }

}
