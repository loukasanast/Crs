package io.anastasiou.services;

import io.anastasiou.models.Vehicle;

import java.util.List;

public interface VehicleService<T extends Vehicle> {
    T getById(int id);
    List<T> getAll();
    int add(T entity);
    void edit(T entity);
    void remove(T entity);
}
