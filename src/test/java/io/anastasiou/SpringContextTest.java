package io.anastasiou;

import io.anastasiou.models.Car;
import io.anastasiou.services.VehicleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringJUnitConfig(AppConfig.class)
public class SpringContextTest {
    @Autowired
    private VehicleService<Car> service;

    @Test
    public void testRemove() {
        int id = service.add(new Car(-1, "Toyota", "Prius", 2020));
        Car c1 = service.getById(id);

        service.remove(c1);

        Car c2 = service.getById(id);

        assertNull(c2);
    }

    @Test
    public void testAdd() {
        int id = service.add(new Car(-1, "Mercedes", "C50", 2010));
        Car c = service.getById(id);

        service.remove(c);

        assertEquals("Mercedes", c.getMake());
    }

    @Test
    public void testEdit() {
        int id = service.add(new Car(-1, "Toyota", "Prius", 2020));
        Car c1 = service.getById(id);

        c1.setMake("Audi");
        c1.setModel("A5");

        service.edit(c1);

        Car c2 = service.getById(c1.getId());

        service.remove(c2);

        assertEquals("Audi", c2.getMake());
    }

    @Test
    public void testGetById() {
        int id = service.add(new Car(-1, "Toyota", "Prius", 2020));
        Car c = service.getById(id);

        service.remove(c);

        assertEquals(id, c.getId());
    }

    @Test
    public void testGetAll() {
        service.add(new Car(-1, "Toyota", "Prius", 2020));
        service.add(new Car(-1, "BMW", "M3", 2019));

        List<Car> cars = service.getAll();

        for(Car c : cars) {
            service.remove(c);
        }

        assertEquals(2, cars.size());
    }
}
