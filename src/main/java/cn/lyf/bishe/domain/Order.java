package cn.lyf.bishe.domain;

import lombok.Data;

/**
 * Date:2021/1/26 14:51
 * Author:lyf
 */
@Data
public class Order {
    private String id;
    private String userId;
    private String StoreId;
    private String staffId;
    private String carId;
    private String time;
}
