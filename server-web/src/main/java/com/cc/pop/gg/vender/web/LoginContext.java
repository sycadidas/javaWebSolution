package com.cc.pop.gg.vender.web;

import org.springframework.core.NamedThreadLocal;

/**
 * 将pin值绑定到当前线程中
 *
 * @author wanghui59@jd.com
 * @date 2017-12-19
 */
public class LoginContext {

    /**
     * 线程变量
     */
    private static ThreadLocal<LoginUserInfo> threadLocal = new NamedThreadLocal<>("loginUserInfo");

    /**
     * set
     *
     * @param loginUserInfo
     */
    public static void set(LoginUserInfo loginUserInfo) {
        threadLocal.set(loginUserInfo);
    }

    /**
     * get
     *
     * @return
     */
    public static LoginUserInfo get() {
        return threadLocal.get();
    }


    /**
     * remove
     */
    public static void remove() {
        threadLocal.remove();
    }

}
