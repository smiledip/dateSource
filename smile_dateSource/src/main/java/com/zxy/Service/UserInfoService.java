package com.zxy.Service;

import com.zxy.Dao.userDao;
import com.zxy.Model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zxy.util.DynamicSwitchDataSource;

/**
 * @Author smile_dip
 * @Date 2018/4/24 18:44
 * @Describe
 */

@Slf4j
@Service
@DynamicSwitchDataSource(dataSource = "datasource1")
public class UserInfoService {

    @Autowired
    private userDao userDao;

    //测试可以datasource1 --是oracle  ,datasource0是存入到mysql
    //@DynamicSwitchDataSource(dataSource="datasource1")
   @DynamicSwitchDataSource(dataSource = "datasource0")
    public void save(User user) {
            userDao.save(user);
    }

}
