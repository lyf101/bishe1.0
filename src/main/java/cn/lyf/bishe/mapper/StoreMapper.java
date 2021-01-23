package cn.lyf.bishe.mapper;

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
public interface StoreMapper {

    //查询所有库存
    @Select("select * from store")
    public List<Store> findStore();

    //添加库存
    @Insert("insert into store values(#{id},#{storeName},#{goodsName},#{goodsNum},#{goodsStatus})")
    public int addStore(Store store);

    //根据id删除库存
    @Delete("delete from store where id = #{id}")
    public int deleteStoreById(String id);

    //根据id查询库存
    @Select("select * from store where id = #{id}")
    public Store findStoreById(String id);

    //修改库存
    @Update("update store set store_name=#{storeName},goods_name=#{goodsName}," +
            "goods_num=#{goodsNum},goods_status=#{goodsStatus} where id = #{id}")
    public int updateStore(Store store);
}
