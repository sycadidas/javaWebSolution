package com.cc.pop.gg.vender.web.interceptor;

import com.jd.paipai.commons.jdcolor.JdColorResultBuilder;
import com.jd.paipai.commons.utils.JsonUtils;
import com.paipai.pop.jd.vender.common.domain.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

/**
 * 校验是否登陆
 *
 * @author wanghui323
 * @date 2019-2-26
 */
@Slf4j
public class SpecialRequestrInterceptor extends HandlerInterceptorAdapter {


    /**
     * 合法的referer
     */
    private Set<String> allowedReferers = new HashSet<String>();

    /**
     * 无需校验Referer的路径
     */
    private Set<String> noRequiredRefererPaths = new HashSet<String>();

    public Set<String> getAllowedReferers() {
        return allowedReferers;
    }

    /**
     * 无需登录校验的路径
     */
    private Set<String> noRequiredLoginPaths = new HashSet<String>();

    public Set<String> getNoRequiredLoginPaths() {
        return noRequiredLoginPaths;
    }

    public void setNoRequiredLoginPaths(Set<String> noRequiredLoginPaths) {
        this.noRequiredLoginPaths = noRequiredLoginPaths;
    }

    public void setAllowedReferers(Set<String> allowedReferers) {
        this.allowedReferers = allowedReferers;
    }

    public Set<String> getNoRequiredRefererPaths() {
        return noRequiredRefererPaths;
    }

    public void setNoRequiredRefererPaths(Set<String> noRequiredRefererPaths) {
        this.noRequiredRefererPaths = noRequiredRefererPaths;
    }



    /**
     * 重定向
     *
     * @param response
     * @param code
     * @param message
     */
    private void doRedirect(HttpServletResponse response, int code, String message) {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return;
        }
        Response<String> jsonResult = new Response<>();
        jsonResult.setCode(code);
        jsonResult.setMessage(message);
        out.println(JsonUtils.toJson(JdColorResultBuilder.build(Response.response(code, message))));
        out.flush();
        out.close();
    }
}
