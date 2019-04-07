package booking.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;


@XmlRootElement(name="table")
@XmlAccessorType(XmlAccessType.FIELD)

public class Table {
    private int id;
    private int tableNumber;
    private int numOfSeats;
    private int placeId;
    private boolean isBooked;
    private Timestamp bookedTime;
    private int userId;

    public Table() {}

    public Table(int id, int tableNumber, int numOfSeats, int placeId, boolean isBooked, int userId) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.numOfSeats = numOfSeats;
        this.placeId = placeId;
        this.isBooked = isBooked;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public Timestamp getBookedTime() {
        return bookedTime;
    }

    public void setBookedTime(Timestamp bookedTime) {
        this.bookedTime = bookedTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
