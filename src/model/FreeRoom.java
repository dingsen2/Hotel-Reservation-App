package model;

public class FreeRoom extends Room{
    public FreeRoom(String myRoomNumber, RoomType Roomtype){
        super(myRoomNumber,0.0, Roomtype);
    }

    @Override
    public String toString(){
        return "The Room " + roomNumber + ", a " + enumeration + "is free";
    }
}
