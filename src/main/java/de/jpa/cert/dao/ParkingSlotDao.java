package de.jpa.cert.dao;

import de.jpa.cert.domain.BrandEnum;
import de.jpa.cert.domain.Car;
import de.jpa.cert.domain.ParkingSlot;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Class which serves as a DAO for entity ParkingSlot
 *
 * Created by: gruppd, 04.02.13 18:01
 */
public class ParkingSlotDao {

    /*
     * ~~~~ properties ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */

    protected EntityManager em;

    /*
     * ~~~~ constructors ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    public ParkingSlotDao(EntityManager em) {
        this.em = em;
    }

    /*
     * ~~~~ methods ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    public void persistParkingSlot(ParkingSlot parkingSlot) {
        em.persist(parkingSlot);
    }

    public ParkingSlot findParkingSlotBySlotNu(Integer slotNuParam, String levelParam) {
        ParkingSlot slot = (ParkingSlot) em.createNamedQuery("findParkingSlotBySlotNu", ParkingSlot.class).
                setParameter("slotNuParam", slotNuParam).setParameter("levelParam", levelParam).getSingleResult();

        return slot;
    }

    public List<ParkingSlot> findFreeParkingSlots() {
        List<ParkingSlot> parkingSlotList = em.createNamedQuery("findFreeParkingSlots", ParkingSlot.class).getResultList();

        return parkingSlotList;
    }


}
