package cn.lyf.bishe.mapper;

import cn.lyf.bishe.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Date:2021/1/19 20:55
 * Author:lyf
 */
@Mapper
@Component
public interface UserMapper {

    @Select("select * from user where username = #{username}")
    public List<User> findUserByUsername(String username);

    @Select("select * from user where username = #{username} and password = #{password}")
    public List<User> findUserByUsernamePassword(@Param("username")String username,@Param("password")String password);

    @Insert("insert into user values(#{id},#{username},#{password},#{realName},#{userType})")
    public int insertUser(User user);

}
