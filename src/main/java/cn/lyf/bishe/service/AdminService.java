package cn.lyf.bishe.service;

import cn.lyf.bishe.domain.User;
import cn.lyf.bishe.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Date:2021/1/22 20:36
 * Author:lyf
 */
@Service
public class AdminService {
    @Autowired
    private UserMapper userMapper;

    //查看所有用户信息
    public List<User> findUser(){
        return userMapper.findUser();
    }
    //根据id删除用户信息
    public boolean deleteUserById(String id){
        int i = userMapper.deleteUserById(id);
        if (i==0){
            return false;
        }else return true;
    }
}
