package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import java.time.LocalDate;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Pattern;

public class ReservationService {
    private HashMap<String, IRoom> rooms = new HashMap<>();
    private List<Reservation> reservations = new ArrayList<>();
    private static ReservationService reservationService;
    private ReservationService(){}
    public static ReservationService getInstance(){
        if(reservationService == null){
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    public void addRoom(IRoom room){
        rooms.put(room.getRoomNumber(), room);
    }

    public IRoom getARoom(String roomId){
        if(rooms.get(roomId) == null){
            throw new IllegalArgumentException("This roomId does not exist");
        }
        else{
            return rooms.get(roomId);
        }
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        if(checkInDate.after(checkOutDate)){
            throw new IllegalArgumentException("Check-in date is not allowed to be later than Check-out day");
        }
        else if(findRooms(checkInDate, checkOutDate).isEmpty()){
            throw new IllegalArgumentException("these dates are not available");
        }
        else{
            Reservation newReservation = new Reservation(customer, room, checkInDate, checkOutDate);
            extendReservation(newReservation);
            return newReservation;
        }
    }

    void extendReservation(Reservation reservation)
    {
        reservations.add(reservation);
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        if(checkInDate.after(checkOutDate)){
            throw new IllegalArgumentException("Check-in date is not allowed to be later than Check-out day");
        }
        else{
            List<IRoom> allReservedRooms = new ArrayList<>();
            List<IRoom> returnRooms = new ArrayList<>(rooms.values());
            for(Reservation existingReservation:reservations){
                allReservedRooms.add(existingReservation.getRoom());
            }
            returnRooms.removeAll(allReservedRooms);

            for(Reservation reservation:reservations){
                if(checkOutDate.before(reservation.getCheckIndate()) || checkInDate.after(reservation.getCheckOutdate())){
                    returnRooms.add(reservation.getRoom());
                }
            }

            //Recommend Rooms if the returnRooms is empty
            if(returnRooms.isEmpty()){
                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(checkInDate);
                cal1.add(Calendar.DATE, 7);
                Date newCheckInDate = cal1.getTime();

                Calendar cal2 = Calendar.getInstance();
                cal2.setTime(checkOutDate);
                cal2.add(Calendar.DATE, 7);
                Date newCheckOutDate = cal2.getTime();

                for(Reservation reservation:reservations){
                    if(newCheckOutDate.before(reservation.getCheckIndate()) || newCheckInDate.after(reservation.getCheckOutdate())){
                        returnRooms.add(reservation.getRoom());
                    }
                }
            }
            return returnRooms;
        }
    }

    public Collection<Reservation> getCustomerReservation(String customerEmail){
        String emailRegex = "^(.+)@(.+).(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        if(!pattern.matcher(customerEmail).matches()){
            throw new IllegalArgumentException("Please enter a valid email address.");
        }
        else{
            List<Reservation> toReturn = new ArrayList<>();
            for(Reservation reservation:reservations){
                System.out.println(customerEmail);
                System.out.println(reservation.getCustomer().getEmail());
                if(reservation.getCustomer().getEmail().equals(customerEmail)){
                    toReturn.add(reservation);
                }
            }
            return toReturn;
        }
    }

    public Collection<IRoom> getAllRooms(){
        return rooms.values();
    }

    public List<Reservation> getAllReservations(){
        return reservations;
    }
}
