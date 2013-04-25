package de.jpa.cert.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Class to represent the entity 'Car'
 *
 * Created by: gruppd, 04.02.13 18:11
 */
@Entity
@NamedQuery(name = "findCarByPlate",
        query = "SELECT c FROM Car c WHERE c.plate = :plateParam")
public class Car implements Serializable {

    /*
     * ~~~~ properties ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */

    @Id
    //@TableGenerator(name = "CarIdGen",
    //        table = "ID_CAR",
    //        pkColumnName = "GEN_NAME",
    //        valueColumnName = "GEN_VAL")
    @SequenceGenerator(name="CarIdGen", sequenceName="CarIdSequence")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "CarIdGen")
    private Long id;

    @Column
    private String color;

    @Column(nullable = false)
    private String plate;

    @Column
    @Enumerated(EnumType.STRING)
    private BrandEnum brand;

    @OneToOne(mappedBy = "car")
    private ParkingSlot slot;

    @ElementCollection(targetClass = ParkingHistory.class)
    @CollectionTable(
            name = "PARKING_HISTORY",
            joinColumns = @JoinColumn(name = "CAR_ID"))
    @AttributeOverrides({
            @AttributeOverride(name = "startTime",
                    column = @Column(name = "START_PARKING_TIME")),
            @AttributeOverride(name = "endTime",
                    column = @Column(name = "END_PARKING_TIME"))
    })
    private List<ParkingHistory> parkingHistoryList;

    @ElementCollection
    private Set<String> descriptionalDetails;

    /*
     * ~~~~ constructors ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    public Car() {
    }

    public Car(BrandEnum brand, String color, String plate) {
        this.brand = brand;
        this.color = color;
        this.plate = plate;
    }

    /*
     * ~~~~ getters / setters ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public BrandEnum getBrand() {
        return brand;
    }

    public void setBrand(BrandEnum brand) {
        this.brand = brand;
    }

    public ParkingSlot getSlot() {
        return slot;
    }

    public void setSlot(ParkingSlot slot) {
        this.slot = slot;
    }

    public List<ParkingHistory> getParkingHistoryList() {
        if (parkingHistoryList == null) {
            parkingHistoryList = new ArrayList<ParkingHistory>();
        }
        return parkingHistoryList;
    }

    public void setParkingHistoryList(List<ParkingHistory> parkingHistoryList) {
        this.parkingHistoryList = parkingHistoryList;
    }

    public Set<String> getDescriptionalDetails() {
        return descriptionalDetails;
    }

    public void setDescriptionalDetails(Set<String> descriptionalDetails) {
        this.descriptionalDetails = descriptionalDetails;
    }

    /*
     * ~~~~ general overrides ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    @Override
    public String toString() {
        return "Car (id=" + getId() + ") - brand: " + getBrand() + " color: " + getColor() + " plate: " + getPlate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (brand != car.brand) return false;
        if (color != null ? !color.equals(car.color) : car.color != null) return false;
        if (!id.equals(car.id)) return false;
        if (!plate.equals(car.plate)) return false;
        if (slot != null ? !slot.equals(car.slot) : car.slot != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + plate.hashCode();
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        return result;
    }
}
