package cn.lyf.bishe.service;

import cn.lyf.bishe.domain.Store;
import cn.lyf.bishe.mapper.StoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Date:2021/1/23 21:04
 * Author:lyf
 */
@Service
public class StoreService {

    @Autowired
    private StoreMapper storeMapper;

    //查询所有库存
    public List<Store> findStore() {
        return storeMapper.findStore();
    }

    //添加库存
    public boolean addStore(String storeName, String goodsName, String goodsNum, String goodsStatus) {
        Store store = new Store();
        store.setId(UUID.randomUUID().toString());
        store.setStoreName(storeName);
        store.setGoodsName(goodsName);
        store.setGoodsNum(goodsNum);
        store.setGoodsStatus(goodsStatus);

        int i = storeMapper.addStore(store);
        if (i == 0) {
            return false;
        } else return true;
    }



    //删除库存
    public boolean deleteStoreById(String id) {
        int i = storeMapper.deleteStoreById(id);
        if (i == 0) {
            return false;
        } else return true;
    }

    //根据id搜库存
    public Store findStoreById(String id) {
        Store store = storeMapper.findStoreById(id);
        return store;
    }


    //更新库存信息
    public boolean updateStore(String id,String storeName, String goodsName, String goodsNum, String goodsStatus) {
        Store store = new Store();
        store.setGoodsStatus(goodsStatus);
        store.setGoodsNum(goodsNum);
        store.setGoodsName(goodsName);
        store.setId(id);
        store.setStoreName(storeName);


        int i = storeMapper.updateStore(store);
        if (i == 0) {
            return false;
        } else return true;
    }

}
