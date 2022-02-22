package io.anastasiou.services;

import io.anastasiou.models.Car;
import io.anastasiou.repositories.VehicleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService implements VehicleService<Car> {
    private VehicleDAO<Car> dao;

    @Autowired
    public CarService(VehicleDAO<Car> dao) {
        this.dao = dao;
    }

    @Override
    public Car getById(int id) {
        return dao.getById(id);
    }

    @Override
    public List<Car> getAll() {
        return dao.getAll();
    }

    @Override
    public int add(Car entity) {
        return dao.insert(entity);
    }

    @Override
    public void edit(Car entity) {
        dao.update(entity);
    }

    @Override
    public void remove(Car entity) {
        dao.delete(entity);
    }
}
