package model;

import java.util.Objects;

public class Room implements IRoom{
    String roomNumber;
    Double price;
    RoomType enumeration;

    public Room(String myRoomNumber, Double myPrice, RoomType Roomtype){
        roomNumber = myRoomNumber;
        price = myPrice;
        enumeration = Roomtype;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    @Override
    public boolean isFree() {
        return true;
    }

    @Override
    public String toString(){
        return "RoomNumber: " + roomNumber + "Room Type: " + enumeration + " Price: " + price;
    }

    @Override
    public int hashCode(){
        return Objects.hash(roomNumber, price, enumeration);
    }

    @Override
    public boolean equals(Object o) {
        if(o == this){
            return true;
        }
        else if(!(o instanceof Room thisRoom)){
            return false;
        }
        else{
            return(this.roomNumber.equals(thisRoom.roomNumber) &&
                    this.price.equals(thisRoom.price) &&
                    this.enumeration.equals(thisRoom.enumeration));
        }
    }
}
