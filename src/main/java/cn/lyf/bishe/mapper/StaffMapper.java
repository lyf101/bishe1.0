package cn.lyf.bishe.mapper;

import cn.lyf.bishe.domain.Staff;
import cn.lyf.bishe.domain.Store;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Date:2021/1/23 21:03
 * Author:lyf
 */
@Mapper
@Component
public interface StaffMapper {

    //查询所有员工信息
    @Select("select * from staff")
    public List<Staff> findStaff();

    //添员工信息
    @Insert("insert into staff values(#{id},#{staffId},#{staffName},#{staffSex},#{staffState},#{staffTel},#{staffAddress})")
    public int addStaff(Staff staff);

    //根据id删除员工信息
    @Delete("delete from staff where id = #{id}")
    public int deleteStaffById(String id);

    //查询空闲状态的员工
    @Select("select * from staff where staff_state=#{staffState}")
    public List<Staff> findStaffByState(int staffState);

    //更改员工状态
    @Update("update staff set staff_state=#{staffStare} where id = #{id}")
    public int updateStaffState(@Param("staffStare") int staffStare,@Param("id") String id);

    //根据id查询员工
    @Select("select * from staff where id = #{id}")
    public Staff findStaffById(String id);

    //修改员工信息
    @Update("update staff set staff_id=#{staffId},staff_name=#{staffName}," +
            "staff_sex=#{staffSex},staff_state=#{staffState},staff_tel=#{staffTel}," +
            "staff_address=#{staffAddress} where id = #{id}")
    public int updateStaff(Staff staff);
}
