package com.zxy.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author smile_dip
 * @Date 2018/4/25 10:05
 * @Describe
 */
@Setter
@Getter
@ToString
public class User {


    private String id;
    private String username;

    public User(String id, String username) {
        this.id = id;
        this.username = username;
    }
}
