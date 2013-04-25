package de.jpa.cert.dao;

import de.jpa.cert.domain.BrandEnum;
import de.jpa.cert.domain.Car;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

/**
 * Class which serves as a DAO for entity Car
 *
 * Created by: gruppd, 04.02.13 18:11
 */
public class CarDao {

    /*
     * ~~~~ properties ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    protected EntityManager em;

    /*
     * ~~~~ constructors ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    public CarDao(EntityManager em) {
        this.em = em;
    }

    /*
     * ~~~~ methods ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    public Car createCar(BrandEnum brand, String color, String plate) {
        Car car = new Car();
        car.setBrand(brand);
        car.setColor(color);
        car.setPlate(plate);

        return car;
    }

    public void persist(Car car) {
        em.persist(car);
    }

    public void delete(Car car) {
        em.remove(car);
    }

    public Car findCar(Long id) {
        return em.find(Car.class, id);
    }

    public List<Car> findAllCars() {
        Query query = em.createQuery("SELECT e FROM Car e");
        return query.getResultList();
    }

    public Car findCarByPlate(String plateParam) {
        Car car = null;
        try {
            car = (Car) em.createNamedQuery("findCarByPlate", Car.class).setParameter("plateParam", plateParam).getSingleResult();
        } catch (NoResultException e) {
            // car remains null
        }

        return car;
    }
}
