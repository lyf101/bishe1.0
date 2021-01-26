package cn.lyf.bishe.domain;

import lombok.Data;

/**
 * Date:2021/1/26 14:51
 * Author:lyf
 */
@Data
public class Orders {
    private String id;
    private String userName;
    private String StoreName;
    private String staffName;
    private String carName;
    private String endAddress;
    private String currentAddress;
    private String time;
}
