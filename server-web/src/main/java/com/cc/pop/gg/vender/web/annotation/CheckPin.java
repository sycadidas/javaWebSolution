package com.cc.pop.gg.vender.web.annotation;

import java.lang.annotation.*;

/**
 * 校验pin是否关联了店铺 如果是则不允许访问
 *
 * @author sunyanchen3
 * @date 2020/2/27
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckPin {

    /**
     * 是否校验 默认false
     * @return
     */
    boolean checkFlag() default false;
}
