package io.anastasiou.repositories;

import io.anastasiou.models.Vehicle;

import java.util.List;

public interface VehicleDAO<T extends Vehicle> {
    T getById(int id);
    List<T> getAll();
    int insert(T entity);
    void update(T entity);
    void delete(T entity);
}
