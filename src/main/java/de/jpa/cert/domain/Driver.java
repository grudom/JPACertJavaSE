package de.jpa.cert.domain;

import javax.persistence.*;

/**
 * Class to represent the entity 'Driver'
 *
 * Created by: gruppd, 04.02.13 18:11
 */
@Entity
public class Driver {

    /*
     * ~~~~ properties ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    @Id
    @TableGenerator(name = "DRIVER_GEN",
            table = "ID_DRIVER",
            pkColumnName = "GEN_PK",
            valueColumnName = "GEN_VAL")
    @GeneratedValue(strategy=GenerationType.TABLE, generator = "DRIVER_GEN")
    private Long id;

    @Column
    private String name;

    /*
     * ~~~~ constructors ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    public void Driver() {}

    /*
     * ~~~~ getters / setters ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
     * ~~~~ general overrides ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Driver driver = (Driver) o;

        if (id != null ? !id.equals(driver.id) : driver.id != null) return false;
        if (name != null ? !name.equals(driver.name) : driver.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
