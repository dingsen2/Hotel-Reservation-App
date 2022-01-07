package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    private static AdminResource adminResource;
    private CustomerService thisCustomerService = CustomerService.getCustomerService();
    private ReservationService thisReservationService = ReservationService.getInstance();

    private AdminResource(){}
    public static AdminResource getAdminResource(){
        if(adminResource == null){
            adminResource = new AdminResource();
        }
        return adminResource;
    }

    public Customer getCustomer(String email){
        return thisCustomerService.getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms){
        for(IRoom room:rooms){
            thisReservationService.addRoom(room);
        }
    }

    public Collection<IRoom> getAllRooms(){
        return thisReservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomers(){
        return thisCustomerService.getAllCustomers();
    }

    public void displayAllReservations(){
        for(Reservation reservation: thisReservationService.getAllReservations()){
            System.out.println(reservation);
        }
    }
}
