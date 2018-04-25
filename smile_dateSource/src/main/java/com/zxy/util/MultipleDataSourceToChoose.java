package com.zxy.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Author smile_dip
 * @Date 2018/4/24 18:33
 * @Describe
 */
public class MultipleDataSourceToChoose extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return HandlerDataSource.getDataSource();
    }

}
