package de.jpa.cert.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Class to represent the entity 'ParkingSlot'
 *
 * Created by: gruppd, 04.02.13 18:11
 */
@Entity
@NamedQueries(value = {
        @NamedQuery(name = "findParkingSlotBySlotNu",
                query = " SELECT p FROM ParkingSlot p " +
                        " WHERE  p.slotNu = :slotNuParam " +
                        " AND    p.level = :levelParam "),
        @NamedQuery(name = "findFreeParkingSlots",
                query = " SELECT p FROM ParkingSlot p " +
                        " WHERE  p.isOccupied = false ")}
)
public class ParkingSlot implements Serializable {

    /*
     * ~~~~ properties ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String level;

    @Column(nullable = false)
    private Integer slotNu;

    @Column(name="DISABLED_PARKING_ALLOWED", nullable = false)
    private Boolean isDisabledParkingSlot;

    @Column
    private Boolean isOccupied;

    @OneToOne
    @JoinColumn(name="CAR_ON_SLOT")
    private Car car;

    /*
     * ~~~~ constructors ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    public ParkingSlot() {
    }

    /*
     * ~~~~ getters / setters ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */

    public ParkingSlot(String level, Integer slotNu, Boolean isDisabled) {
        this.level = level;
        this.slotNu = slotNu;
        this.isDisabledParkingSlot = isDisabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getSlotNu() {
        return slotNu;
    }

    public void setSlotNu(Integer slotNu) {
        this.slotNu = slotNu;
    }

    public Boolean getDisabledParkingSlot() {
        return isDisabledParkingSlot;
    }

    public void setDisabledParkingSlot(Boolean disabledParkingSlot) {
        isDisabledParkingSlot = disabledParkingSlot;
    }

    public Boolean getOccupied() {
        return isOccupied;
    }

    public void setOccupied(Boolean occupied) {
        isOccupied = occupied;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    /*
     * ~~~~ general overrides ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */

    @Override
    public String toString() {
        String carString = (car!=null) ? ", car(id)=" + car.getId() : "";
        return "ParkingSlot{" +
                "id=" + id +
                ", level=" + level +
                ", slotNu='" + slotNu + '\'' +
                ", isDisabledParkingSlot=" + isDisabledParkingSlot +
                ", isOccupied=" + isOccupied +
                carString + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParkingSlot that = (ParkingSlot) o;

        if (car != null ? !car.equals(that.car) : that.car != null) return false;
        if (!id.equals(that.id)) return false;
        if (!isDisabledParkingSlot.equals(that.isDisabledParkingSlot)) return false;
        if (isOccupied != null ? !isOccupied.equals(that.isOccupied) : that.isOccupied != null) return false;
        if (!level.equals(that.level)) return false;
        if (!slotNu.equals(that.slotNu)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + level.hashCode();
        result = 31 * result + slotNu.hashCode();
        result = 31 * result + isDisabledParkingSlot.hashCode();
        result = 31 * result + (isOccupied != null ? isOccupied.hashCode() : 0);
        return result;
    }

}
