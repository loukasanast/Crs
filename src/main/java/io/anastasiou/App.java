package io.anastasiou;

import io.anastasiou.repositories.CarDAO;
import io.anastasiou.services.CarService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CarDAO carDAO = context.getBean(CarDAO.class);
        CarService service = context.getBean(CarService.class);

        System.out.println("Hello Spring!");
    }
}
