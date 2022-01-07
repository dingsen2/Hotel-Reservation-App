package model;

import java.util.Date;
import java.util.Objects;

public class Reservation {
    Customer customer;
    IRoom room;
    Date checkIndate;
    Date checkOutdate;

    public Reservation(Customer customer, IRoom room, Date checkIndate, Date checkOutdate){
        this.customer = customer;
        this.room = room;
        this.checkIndate = checkIndate;
        this.checkOutdate = checkOutdate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getCheckIndate() {
        return checkIndate;
    }

    public Date getCheckOutdate() {
        return checkOutdate;
    }

    public IRoom getRoom() {
        return room;
    }

    @Override
    public String toString(){
        return "Customer Name: " + customer.firstName + customer.lastName +"\n" +
                "Room Type: " + room.getRoomType() + "\n" +
                "Room Number: " + room.getRoomNumber() + "\n" +
                "Room Price: " +room.getRoomPrice() + "\n" +
                "Check In Date: " + checkIndate + "\n" +
                "Check Out Date: " + checkOutdate;
    }

    @Override
    public int hashCode(){
        return Objects.hash(customer, room, checkIndate, checkOutdate);
    }

    @Override
    public boolean equals(Object o) {
        if(o == this){
            return true;
        }
        else if(!(o instanceof Reservation thisReservation)){
            return false;
        }
        else{
            return(this.customer.equals(thisReservation.customer) &&
                    this.room.equals(thisReservation.room) &&
                    this.checkIndate.equals(thisReservation.checkIndate) &&
                    this.checkOutdate.equals(thisReservation.checkOutdate));
        }
    }
}
