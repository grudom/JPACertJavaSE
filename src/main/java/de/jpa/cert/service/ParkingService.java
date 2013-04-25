package de.jpa.cert.service;


import de.jpa.cert.dao.CarDao;
import de.jpa.cert.dao.ParkingSlotDao;
import de.jpa.cert.domain.BrandEnum;
import de.jpa.cert.domain.Car;
import de.jpa.cert.domain.ParkingHistory;
import de.jpa.cert.domain.ParkingSlot;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Session facade for actions mainly on entity car
 *
 * Created by: gruppd, 04.02.13 18:11
 */
public class ParkingService {

    /*
     * ~~~~ Properties ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    EntityManager em;
    CarDao cardao;
    ParkingSlotDao parkingSlotDao;

    public ParkingService(EntityManager em) {
        this.em = em;
        this.cardao = new CarDao(em);
        this.parkingSlotDao = new ParkingSlotDao(em);
    }

    // a new car is being parked
    public Car parkNewCar(BrandEnum brand, String color, String plate, Integer slot, String level, Set<String> detSet) {
        Car newCar = findCarByPlate(plate);
        if (newCar == null) {
            System.out.println("|=| Car is a new car");
            em.getTransaction().begin();
            newCar = cardao.createCar(brand, color, plate);
            ParkingSlot parkingSlot = parkingSlotDao.findParkingSlotBySlotNu(slot, level);
            newCar.setSlot(parkingSlot);
            newCar.setDescriptionalDetails(detSet);
            ParkingHistory hist = new ParkingHistory();
            hist.setStartTime(new Timestamp(new Date().getTime()));
            newCar.getParkingHistoryList().add(hist);
            parkingSlot.setCar(newCar);
            parkingSlot.setOccupied(Boolean.TRUE);
            cardao.persist(newCar);
            parkingSlotDao.persistParkingSlot(parkingSlot);
            em.getTransaction().commit();
        } else {
            System.out.println("|=| Car was already parked before");
            em.getTransaction().begin();
            ParkingHistory hist = new ParkingHistory();
            hist.setStartTime(new Timestamp(new Date().getTime()));
            List<ParkingHistory> histList = newCar.getParkingHistoryList();
            histList.add(hist);
            ParkingSlot parkingSlot = parkingSlotDao.findParkingSlotBySlotNu(slot, level);
            newCar.setSlot(parkingSlot);
            parkingSlot.setCar(newCar);
            parkingSlot.setOccupied(Boolean.TRUE);
            cardao.persist(newCar);
            parkingSlotDao.persistParkingSlot(parkingSlot);
            em.getTransaction().commit();
        }

        return newCar;
    }

    // car is leaving the garage
    public Car removeCar(String plate) {
        em.getTransaction().begin();
        Car carLeaving = cardao.findCarByPlate(plate);
        List<ParkingHistory> hist = carLeaving.getParkingHistoryList();
        hist.get(hist.size() - 1).setEndTime(new Timestamp(new Date().getTime()));
        ParkingSlot parkingSlot = carLeaving.getSlot();
        parkingSlot.setCar(null);
        parkingSlot.setOccupied(Boolean.FALSE);
        carLeaving.setSlot(null);
        cardao.persist(carLeaving);
        parkingSlotDao.persistParkingSlot(parkingSlot);
        em.getTransaction().commit();
        return carLeaving;
    }

    // cleanup after a certain amount of time
    public void cleanupCars(String plate) {
        // e.g. run daily (e.g. JEE TimerService) and clean up all cars with a last history endTime > 6 months
    }

    // customer cannot find the car anymore
    public Car findCarByPlate(String plateParam) {
        Car carFound = cardao.findCarByPlate(plateParam);
        return carFound;
    }

    // which cars a left overnight
    public List<Car> findAllCars() {
        List<Car> carList = cardao.findAllCars();
        return carList;
    }

    // search free slots in busy times
    public List<ParkingSlot> findAllFreeParkingSlots() {
        List<ParkingSlot> parkingSlotList = parkingSlotDao.findFreeParkingSlots();
        return parkingSlotList;
    }

    // find slot by number
    public ParkingSlot findSlotByNumber(Integer slotNu, String level) {
        ParkingSlot parkingSlot = parkingSlotDao.findParkingSlotBySlotNu(slotNu, level);
        return parkingSlot;
    }

}
