package com.zxy.util;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @Author smile_dip
 * @Date 2018/4/25 17:55
 * @Describe
 */
public class DataSourceAspect  implements MethodBeforeAdvice,AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method,
                               Object[] args, Object target) throws Throwable {
        // TODO Auto-generated method stub
        HandlerDataSource.clear();
    }

    @Override
    public void before(Method method, Object[] args, Object target)
            throws Throwable {
        if (method.isAnnotationPresent(DynamicSwitchDataSource.class))
        {
            DynamicSwitchDataSource datasource = method.getAnnotation(DynamicSwitchDataSource.class);
            HandlerDataSource.putDataSource(datasource.dataSource());
        }
        else
        {
            HandlerDataSource.putDataSource("testDataSource1");
        }

    }
}
