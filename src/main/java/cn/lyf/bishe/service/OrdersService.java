package cn.lyf.bishe.service;

import cn.lyf.bishe.domain.Orders;
import cn.lyf.bishe.mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Date:2021/1/26 15:26
 * Author:lyf
 */
@Service
public class OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    public List<Orders> findOrdersByText(String text){
        /*String like = "%"+text+"%";*/
        List<Orders> orderList = ordersMapper.findOrdersByText(text);
        return orderList;

    }

}
