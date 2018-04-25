package com.zxy.Controller;

import com.zxy.Model.User;
import com.zxy.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author smile_dip
 * @Date 2018/4/25 10:09
 * @Describe
 */
@Controller
public class sysController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 测试
     * @return
     */
    @ResponseBody
    @RequestMapping("/test")
    public User test(String id ,String username){
       User user= new User(id,username);
        userInfoService.save(user);
        return user;
    }



    @ResponseBody
    @RequestMapping("/find")
    public String find(){
        return "index";
    }
}
