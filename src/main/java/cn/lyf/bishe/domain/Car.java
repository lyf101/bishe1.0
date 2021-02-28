package cn.lyf.bishe.domain;

import lombok.Data;

/**
 * Date:2021/1/22 21:21
 * Author:lyf
 */
@Data
public class Car {
    //编号
    private String id;
    //车牌号
    private String carNum;
    //品牌
    private String carBrand;
    //载重
    private String carWeight;
    //公里数
    private String carMile;
    //状态
    private String carPress;
}
