package cn.lyf.bishe.service;

import cn.lyf.bishe.domain.Car;
import cn.lyf.bishe.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Date:2021/1/22 21:24
 * Author:lyf
 */
@Service
public class CarService {

    @Autowired
    private CarMapper carMapper;

    //查询所有车辆
    public List<Car> findCar() {
        return carMapper.findCar();
    }

    //添加车辆
    public boolean addCar(String carNum, String carBrand, String carWeight, String carMile, String carPress) {
        Car car = new Car();
        car.setId(UUID.randomUUID().toString());
        car.setCarBrand(carBrand);
        car.setCarMile(carMile);
        car.setCarNum(carNum);
        car.setCarWeight(carWeight);
        car.setCarPress(carPress);

        int i = carMapper.addCar(car);
        if (i == 0) {
            return false;
        } else return true;
    }

    //删除车辆
    public boolean deleteCarById(String id) {
        int i = carMapper.deleteCarById(id);
        if (i == 0) {
            return false;
        } else return true;
    }

    //更新车辆信息
    public boolean updateCar(String id, String carNum, String carBrand, String carWeight, String carMile, String carPress) {
        Car car = new Car();
        car.setId(id);
        car.setCarBrand(carBrand);
        car.setCarMile(carMile);
        car.setCarNum(carNum);
        car.setCarWeight(carWeight);
        car.setCarPress(carPress);


        int i = carMapper.updateCar(car);
        if (i == 0) {
            return false;
        } else return true;
    }

    //根据id搜车辆
    public Car findCarById(String id) {
        Car car = carMapper.findCarById(id);
        return car;
    }

    //查出空闲状态的车辆,并设为工作中
    public Car findCarByCarPress() {
        List<Car> cars = carMapper.findCarByCarPress("空闲");
        Car car = cars.get(0);
        carMapper.updateCarPress("工作中",car.getId());
        return car;
    }
}
