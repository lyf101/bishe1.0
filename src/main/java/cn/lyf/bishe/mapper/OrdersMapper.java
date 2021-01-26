package cn.lyf.bishe.mapper;

import cn.lyf.bishe.domain.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Date:2021/1/26 15:00
 * Author:lyf
 */
@Mapper
@Component
public interface OrdersMapper {

    @Select("select * from orders where concat(user_name,store_name,staff_name,end_address,current_address,time)  like CONCAT('%',#{text},'%')")
    public List<Orders> findOrdersByText(String text);

}
