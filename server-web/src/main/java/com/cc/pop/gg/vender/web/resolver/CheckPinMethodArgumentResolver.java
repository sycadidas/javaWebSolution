package com.cc.pop.gg.vender.web.resolver;

import com.cc.pop.gg.vender.web.LoginContext;
import com.cc.pop.gg.vender.web.LoginUserInfo;
import com.cc.pop.gg.vender.web.annotation.CheckPin;
import com.jd.paipai.commons.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * pin参数解析器
 * 功能：根据校验标识 检验pin是否关联了其他店铺 是则抛异常，否则映射pin参数
 *
 * @author sunyanchen3
 * @date 2020/2/25
 */
@Slf4j
public class CheckPinMethodArgumentResolver implements HandlerMethodArgumentResolver {
    /**
     * 判断参数
     *
     * @param parameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CheckPin.class) && parameter.getParameterType() == LoginUserInfo.class;
    }

    /**
     * 参数处理
     *
     * @param parameter
     * @param mavContainer
     * @param webRequest
     * @param binderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        if (httpServletRequest == null) {
            log.error("入参httpServletRequest信息不合法");
            return null;
        }
        CheckPin annotation = parameter.getParameterAnnotation(CheckPin.class);
        LoginUserInfo loginUserInfo = LoginContext.get();
        log.info("：{}", annotation.checkFlag());
        log.info("：{}", JsonUtils.toJson(loginUserInfo));
        if (annotation.checkFlag() && !loginUserInfo.getAlreadyOpenShop()) {// 提示错误
            log.info("！", loginUserInfo.getPin());
            throw new Exception("！");
        } else {
            return loginUserInfo;
        }
    }
}
