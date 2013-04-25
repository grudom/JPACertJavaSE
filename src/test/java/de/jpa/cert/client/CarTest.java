package de.jpa.cert.client;

import de.jpa.cert.domain.BrandEnum;
import de.jpa.cert.domain.Car;
import de.jpa.cert.service.ParkingService;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by: gruppd, 08.02.13 08:35
 */
public class CarTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeClass
    public static void classSetUp() {
        emf = Persistence.createEntityManagerFactory("DerbyEmbedded");
    }

    @AfterClass
    public static void classCleanUp() {
        if (emf != null)
            emf.close();
    }

    @Before
    public void setUp() {
        em = emf.createEntityManager();
        BaseDataCreator.createSlots(em);
    }

    @After
    public void cleanUp() {
        if (em != null)
            em.close();
        emf.getCache().evictAll();
    }

    @Test
    public void doParking() {
        ParkingService parkingService = new ParkingService(em);

        Set<String> detailsBmw = new HashSet<String>();
        detailsBmw.add("blister");
        detailsBmw.add("bully");
        Car bmw = parkingService.parkNewCar(BrandEnum.BMW, "black", "HH-PO-963", 12, "A", detailsBmw);
        Car foundCar = parkingService.findCarByPlate("HH-PO-963");
        assertEquals(bmw, foundCar);
    }
}
