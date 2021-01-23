package cn.lyf.bishe.mapper;

import cn.lyf.bishe.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Date:2021/1/19 20:55
 * Author:lyf
 */
@Mapper
@Component
public interface UserMapper {

    @Select("select * from user")
    List<User> findUser();

    @Select("select * from user where username = #{username}")
    List<User> findUserByUsername(String username);

    @Select("select * from user where username = #{username} and password = #{password}")
    List<User> findUserByUsernamePassword(@Param("username")String username,@Param("password")String password);

    @Insert("insert into user values(#{id},#{username},#{password},#{realName},#{userType})")
    int insertUser(User user);


    @Delete("delete from user where id = #{id}")
    int deleteUserById(String id);

}
