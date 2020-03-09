package com.cc.pop.gg.vender.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

/**
 */
public class RequestSolutionFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 如果请求url带XX 则设置参数
     * @param request
     * @param response
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if(isSpecial(httpServletRequest)){
            httpServletRequest.setAttribute("abc",true);
        }
    }

    @Override
    public void destroy() {

    }

    protected boolean isSpecial(HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        return getUrlHost(requestUrl).endsWith(".local");
    }

    public static String getUrlHost(String url) {
        try {
            return new URI(url).getHost();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
