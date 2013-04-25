package de.jpa.cert.client;

import de.jpa.cert.domain.BrandEnum;
import de.jpa.cert.domain.Car;
import de.jpa.cert.domain.ParkingSlot;
import de.jpa.cert.domain.SlotNameEnum;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class to create some (test) data for the application to use
 *
 * Created by: gruppd, 04.02.13 18:44
 */
public class BaseDataCreator {

    /**
     * Method to create parking slots on different levels of the parking house
     * @param em - the entity manager
     */
    public static void createSlots(EntityManager em) {
        // on each level of the 3 levels ...
        for (int i = 0; i < 3; i++) {
            // ... there are 60 slots ...
            for (int j = 0; j < 60; j++) {
                // ... and on the ground level there are 10 parking slots for the disabled
                if (i == 0 && j < 10) {
                    ParkingSlot ps = new ParkingSlot(SlotNameEnum.textValueOf(i).getSlotNu(), j, Boolean.TRUE);
                    ps.setOccupied(Boolean.FALSE);
                    em.getTransaction().begin();
                    em.persist(ps);
                    em.getTransaction().commit();
                } else {
                    ParkingSlot ps = new ParkingSlot(SlotNameEnum.textValueOf(i).getSlotNu(), j, Boolean.FALSE);
                    ps.setOccupied(Boolean.FALSE);
                    em.getTransaction().begin();
                    em.persist(ps);
                    em.getTransaction().commit();
                }
            }
        }
    }

    /**
     * @deprecated The data is created in the main class
     * Method to create some cars for testing
     * @param em - the entity manager
     */
    @Deprecated
    public static void createCars(EntityManager em) {

        List<Car> carList = new ArrayList<Car>();
        Car audiGreen = new Car(BrandEnum.AUDI, "green", "LA 5I ABC");
        carList.add(audiGreen);
        Car mercedesSilver = new Car(BrandEnum.MERCEDES, "silver", "M-AB-9232");
        carList.add(mercedesSilver);
        Car fiatRed = new Car(BrandEnum.FIAT, "red", "B-XD-1234");
        carList.add(fiatRed);
        Car volkswagenBlue = new Car(BrandEnum.VOLKSWAGEN, "blue", "YK 02 OML");
        carList.add(volkswagenBlue);
        Car ferrariRed = new Car(BrandEnum.FERRARI, "red", "920911");
        carList.add(ferrariRed);
        Car alphaWhite = new Car(BrandEnum.ALPHA_ROMEO, "white", "B-5236");
        carList.add(alphaWhite);
        Car skodaYellow = new Car(BrandEnum.SKODA, "yellow", "1-XD-1234");
        carList.add(skodaYellow);
        Car jeepBrown = new Car(BrandEnum.JEEP, "brown", "K-P-1000");
        carList.add(jeepBrown);

        Iterator<Car> iterator = carList.iterator();
        while (iterator.hasNext()) {
            Car car = iterator.next();
            em.getTransaction().begin();
            em.persist(car);
            em.getTransaction().commit();
            System.out.println(" => Initial setup " + car + "\n");
        }
    }

}
