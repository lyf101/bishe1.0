package cn.lyf.bishe.service;

import cn.lyf.bishe.domain.User;
import cn.lyf.bishe.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Date:2021/1/19 21:12
 * Author:lyf
 */
@Service
public class LoginRegisterService {
    @Autowired
    private UserMapper userMapper;

    //用户名密码登录
    public User isExistUserByUsernamePassword(String username, String password) {
        List<User> userByUsernamePassword = userMapper.findUserByUsernamePassword(username, password);
        if(userByUsernamePassword.size() == 0){
            return null;
        }
        return userByUsernamePassword.get(0);
    }

    //根据用户名查询用户是否存在
    public boolean isExistUserByUsername(String username){
        List<User> userByUsername = userMapper.findUserByUsername(username);
        if(userByUsername.size()==0){
            return false;
        }return true;
    }


    public int addUser(String username, String password, String realName) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername(username);
        user.setPassword(password);
        user.setRealName(realName);
        user.setUserType("普通用户");
        int i = userMapper.insertUser(user);
        return i;
    }
}
