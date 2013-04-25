package de.jpa.cert.domain;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Class to represent the embeddable 'ParkingHistory'
 *
 * Created by: gruppd, 04.02.13 18:11
 */
@Embeddable
public class ParkingHistory {

    /*
     * ~~~~ properties ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    private Timestamp startTime;

    private Timestamp endTime;

    /*
     * ~~~~ constructors ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    public void ParkingHistory() {}

    /*
     * ~~~~ getters / setters ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
}
