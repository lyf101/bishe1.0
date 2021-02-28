package cn.lyf.bishe.mapper;

import cn.lyf.bishe.domain.Orders;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Date:2021/1/26 15:00
 * Author:lyf
 */
@Mapper
@Component
public interface OrdersMapper {

    @Select("select * from orders where id = #{id}")
    public Orders findOrderById(String id);

    @Select("select * from orders where concat(user_name,store_name,staff_name,end_address,current_address,time,car_num)  like CONCAT('%',#{text},'%')")
    public List<Orders> findOrdersByText(String text);

     @Select("select * from orders ")
    public List<Orders> findAllOrders();

     @Insert("insert into orders values(#{id},#{userName},#{storeName},#{staffName},#{endAddress},#{currentAddress},#{time},#{carNum})")
    public int addOrders(Orders orders);

     //根据id删除配送信息
     @Delete("delete from orders where id = #{id}")
    public int deleteOrdersById(String id);

     @Update("update orders set user_name=#{userName},store_name=#{storeName}, " +
             "staff_name=#{staffName},end_address=#{endAddress},current_address=#{currentAddress}," +
             "time=#{time},car_num=#{carNum} where id = #{id}")
    public int updateOrders(Orders orders);
}
