package de.jpa.cert.client;

import de.jpa.cert.domain.Car;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by: gruppd, 14.03.13 18:42
 */
public class CriteriaAPITester {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("CarServiceEclipselink");
    EntityManager em = emf.createEntityManager();
    CriteriaAPITester criteriaAPITester = null;

    private List<Car> selector() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Car> c = cb.createQuery(Car.class);
        Root<Car> carRoot = c.from(Car.class);
        c.select(carRoot).where(cb.equal(carRoot.get("color"), "black"));
        TypedQuery<Car> query = em.createQuery(c);

        return query.getResultList();
    }

    private void executeQuery() {
        for (Car car : selector()) {
            System.out.println("\n\n" + car.toString() + "\n\n");
        }
    }

    public static void main(String [] args) {
        CriteriaAPITester criteriaAPITester = new CriteriaAPITester();
        criteriaAPITester.executeQuery();
    }
}
