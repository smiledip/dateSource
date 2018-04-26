# dateSource

springMVC+Mybatis+多数据源AbstractRoutingDataSource的demo
    1.mysql
    2.oracle
没有前端页面使用postman发测试请求
localhost：8080/test?id=1&username=zhangsan
测试不同的数据源，可以修改service中test方法上的注解@DynamicSwitchDataSource(dataSource="datasource1")
