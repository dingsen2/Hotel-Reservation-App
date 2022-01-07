package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private CustomerService thisCustomerService = CustomerService.getCustomerService();
    private ReservationService thisReservationService = ReservationService.getInstance();
    private static HotelResource hotelResource;
    private HotelResource(){}
    public static HotelResource getInstance(){
        if(hotelResource == null){
            hotelResource = new HotelResource();
        }
        return hotelResource;
    }

    public Customer getCustomer(String email){
        return thisCustomerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName){
        thisCustomerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber){
        return thisReservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room,
                                 Date checkInDate, Date checkOutDate){
        if(thisCustomerService.getCustomer(customerEmail) == null){
            throw new IllegalArgumentException("This email address does not exist, please register");
        }
        return thisReservationService.reserveARoom(thisCustomerService.getCustomer(customerEmail), room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail){
        return thisReservationService.getCustomerReservation(customerEmail);
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut){
        return thisReservationService.findRooms(checkIn, checkOut);
    }
}
