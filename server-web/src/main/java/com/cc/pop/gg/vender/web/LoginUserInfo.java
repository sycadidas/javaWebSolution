package com.cc.pop.gg.vender.web;

import lombok.Data;

/**
 * 登录用户信息
 *
 * @author sunyanchen3
 * @date 2020/2/26
 */
@Data
public class LoginUserInfo {

    /**
     * 已开店标记 true：可用 false：不可用
     */
    private Boolean alreadyOpenShop;
    /**
     * 登录pin
     */
    private String pin;

    /**
     * 构造方法
     * @param alreadyOpenShop
     * @param pin
     */
    public LoginUserInfo(Boolean alreadyOpenShop, String pin) {
        this.alreadyOpenShop = alreadyOpenShop;
        this.pin = pin;
    }
}
