package cn.lyf.bishe.service;

import cn.lyf.bishe.domain.Staff;
import cn.lyf.bishe.mapper.StaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Date:2021/1/23 21:04
 * Author:lyf
 */
@Service
public class StaffService {

    @Autowired
    private StaffMapper staffMapper;

    //查询所有员工
    public List<Staff> findStaff() {
        return staffMapper.findStaff();
    }

    //添加库存
    public boolean addStaff(String staffId, String staffName, String staffSex, int staffAge,String staffTel,String staffAddress) {
        Staff staff = new Staff();
        staff.setId(UUID.randomUUID().toString());
        staff.setStaffId(staffId);
        staff.setStaffName(staffName);
        staff.setStaffSex(staffSex);
        staff.setStaffAge(staffAge);
        staff.setStaffTel(staffTel);
        staff.setStaffAddress(staffAddress);

        int i = staffMapper.addStaff(staff);
        if (i == 0) {
            return false;
        } else return true;
    }



    //删除员工信息
    public boolean deleteStaffById(String id) {
        int i = staffMapper.deleteStaffById(id);
        if (i == 0) {
            return false;
        } else return true;
    }

    //根据id搜员工
    public Staff findStaffById(String id) {
        Staff staff = staffMapper.findStaffById(id);
        return staff;
    }


    //更新员工信息
    public boolean updateStaff(String id,String staffId, String staffName, String staffSex, int staffAge,String staffTel,String staffAddress) {
        Staff staff = new Staff();
        staff.setId(id);
        staff.setStaffId(staffId);
        staff.setStaffName(staffName);
        staff.setStaffSex(staffSex);
        staff.setStaffAge(staffAge);
        staff.setStaffTel(staffTel);
        staff.setStaffAddress(staffAddress);


        int i = staffMapper.updateStaff(staff);
        if (i == 0) {
            return false;
        } else return true;
    }

}
