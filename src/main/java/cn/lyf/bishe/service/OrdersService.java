package cn.lyf.bishe.service;

import cn.lyf.bishe.domain.Orders;
import cn.lyf.bishe.mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Date:2021/1/26 15:26
 * Author:lyf
 */
@Service
public class OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    //根据id查配送信息
    public Orders findOrderById(String id){
        Orders orderById = ordersMapper.findOrderById(id);
        return orderById;
    }


    //根据关键字查询配送信息
    public List<Orders> findOrdersByText(String text){
        /*String like = "%"+text+"%";*/
        List<Orders> orderList = ordersMapper.findOrdersByText(text);
        return orderList;

    }

    //查询全部配送信息
    public List<Orders> findAllOrders(){
        List<Orders> allOrders = ordersMapper.findAllOrders();
        return allOrders;
    }

    //添加配送信息
    public boolean addOrders(String userName, String storeName, String staffName, String endAddress,String currentAddress) {
        Orders orders = new Orders();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);

        orders.setId(UUID.randomUUID().toString());
        orders.setTime(format);
        orders.setUserName(userName);
        orders.setStoreName(storeName);
        orders.setStaffName(staffName);
        orders.setEndAddress(endAddress);
        orders.setCurrentAddress(currentAddress);

        int i = ordersMapper.addOrders(orders);
        if (i>0){
            return true;
        }return false;
    }

    //根据id删除配送信息
    public boolean deleteOrdersById(String id){
        int i = ordersMapper.deleteOrdersById(id);
        if (i>0){
            return true;
        }return false;
    }

    //修改信息
    public boolean updateOrders(Orders orders){
        int i = ordersMapper.updateOrders(orders);
        if (i>0){
            return true;
        }return false;
    }

    public boolean addOrdersForUser(Orders orders){
        int i = ordersMapper.addOrders(orders);
        if (i>0){
            return true;
        }return false;
    }

}
