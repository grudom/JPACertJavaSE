package de.jpa.cert.client;

import de.jpa.cert.domain.BrandEnum;
import de.jpa.cert.domain.Car;
import de.jpa.cert.domain.ParkingSlot;
import de.jpa.cert.service.ParkingService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.Set;

/**
 * Class to run the program in batch mode.
 *
 * Created by: gruppd, 04.02.13 18:30
 */
public class CarParker {

    /**
     * method to test some persistence actions
     */
    public void doParking() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CarServiceEclipselink");
        EntityManager em = emf.createEntityManager();
        BaseDataCreator.createSlots(em);

        ParkingService parkingService = new ParkingService(em);

        // a new car is parking
        Set<String> detailsBmw = new HashSet<String>();
        detailsBmw.add("blister");
        detailsBmw.add("bully");
        Car bmw = parkingService.parkNewCar(BrandEnum.BMW, "black", "HH-PO-963", 12, "A", detailsBmw);
        System.out.println("|=| Car entered garage: " + bmw + "\n");

        Set<String> detailsMer = new HashSet<String>();
        detailsMer.add("old");
        detailsMer.add("crappy");
        Car mer = parkingService.parkNewCar(BrandEnum.MERCEDES, "silver", "M-AB-9232", 2, "A", detailsMer);
        System.out.println("|=| Car entered garage: " + mer + "\n");

        Car rom = parkingService.parkNewCar(BrandEnum.ALPHA_ROMEO, "white", "B-5236", 4, "B", null);
        System.out.println("|=| Car entered garage: " + rom + "\n");

        // find the car which is on slot e.g. A34
        ParkingSlot parkingSlot = parkingService.findSlotByNumber(12,"A");
        System.out.println("|=| Found car " + parkingSlot.getCar() + " parked in parking slot: " + parkingSlot + "\n");

        // a car is leaving the garage
        mer = parkingService.removeCar("M-AB-9232");
        System.out.println("|=| Car leaving garage: " + mer + "\n");

        // car which was previously parked returns to park again
        Car mercedes = parkingService.parkNewCar(BrandEnum.MERCEDES, "silver", "M-AB-9232", 2, "A", detailsMer);
        System.out.println("|=| Car entered garage: " + mercedes + "\n");

        // a car is leaving the garage
        mercedes = parkingService.removeCar("M-AB-9232");
        System.out.println("|=| Car leaving garage: " + mer + "\n");

        // close the EM and EMF
        em.close();
        emf.close();
    }


    public static void main(String[] args) {
        new CarParker().doParking();
    }
}
