package cn.lyf.bishe.mapper;

import cn.lyf.bishe.domain.Car;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Date:2021/1/22 21:24
 * Author:lyf
 */
@Mapper
@Component
public interface CarMapper {

    //查所有车辆
    @Select("select * from car")
    public List<Car> findCar();

    //查出空闲车辆
    @Select("select * from car where car_press = #{carPress}")
    public List<Car> findCarByCarPress(String carPress);

    //添加车辆
    @Insert("insert into car values(#{id},#{carNum},#{carBrand},#{carWeight},#{carMile},#{carPress})")
    public int addCar(Car car);

    //根据id删除车辆
    @Delete("delete from car where id = #{id}")
    public int deleteCarById(String id);

    //根据id查询车辆
    @Select("select * from car where id = #{id}")
    public Car findCarById(String id);

    //修改车辆
    @Update("update car set car_num=#{carNum},car_brand=#{carBrand},car_weight=#{carWeight}," +
            "car_mile=#{carMile},car_press=#{carPress} where id = #{id}")
    public int updateCar(Car car);

    //更新车辆状态
    @Update("update car set car_press =#{carPress} where id = #{id}")
    public int updateCarPress(@Param("carPress")String carPress,@Param("id") String id);
}
