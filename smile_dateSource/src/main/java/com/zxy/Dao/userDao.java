package com.zxy.Dao;

import com.zxy.Model.User;
import org.springframework.stereotype.Repository;

/**
 * @Author smile_dip
 * @Date 2018/4/25 10:01
 * @Describe
 */

@Repository
public interface userDao {

    public void save(User user);
}
