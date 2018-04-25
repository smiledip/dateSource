package com.zxy.util;

import java.lang.annotation.*;

/**
 * @Author smile_dip
 * @Date 2018/4/24 18:38
 * @Describe
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicSwitchDataSource {

    String dataSource() default "datasource0";


}

