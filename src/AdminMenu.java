import api.AdminResource;
import com.sun.tools.javac.Main;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static model.RoomType.DOUBLE;
import static model.RoomType.SINGLE;

public class AdminMenu {

    public void addRoom(AdminResource adminResource){
        boolean keepAdding = true;

        try{
            while(keepAdding){
                boolean invalidRoomType = true;
                boolean invalidKeepAdding = true;
                System.out.println("Please enter the Room Number: ");
                Scanner roomNum = new Scanner(System.in);
                String roomNumber = roomNum.nextLine();
                System.out.println("Please enter the Room Price: ");
                Scanner roomPrice = new Scanner(System.in);
                double thisRoomPrice = Double.parseDouble(roomPrice.nextLine());

                while(invalidRoomType){
                    System.out.println("Please enter the Room Type(1 for Single/2 for Double): ");
                    Scanner customerChoice = new Scanner(System.in);
                    String choice = customerChoice.nextLine();
                    int numChoice = Integer.parseInt(choice);
                    switch (numChoice){
                        case 1:
                            Room newRoom1 = new Room(roomNumber, thisRoomPrice, SINGLE);
                            List<IRoom> RoomsList1 = new ArrayList<>();
                            RoomsList1.add(newRoom1);
                            adminResource.addRoom(RoomsList1);
                            invalidRoomType = false;
                            break;

                        case 2:
                            Room newRoom2 = new Room(roomNumber, thisRoomPrice, DOUBLE);
                            List<IRoom> RoomsList2 = new ArrayList<>();
                            RoomsList2.add(newRoom2);
                            adminResource.addRoom(RoomsList2);
                            invalidRoomType = false;
                            break;

                        default:
                            System.out.println("Please Enter Valid Input1");
                            break;
                    }

                }
                while(invalidKeepAdding){
                    System.out.println("Do you need to keep adding?(y/n)");
                    Scanner addOrNot = new Scanner(System.in);
                    String addChoice = addOrNot.nextLine();
                    switch (addChoice){
                        case "y":
                            invalidKeepAdding = false;
                            break;
                        case "n":
                            invalidKeepAdding = false;
                            keepAdding = false;
                            break;
                        default:
                            System.out.println("Please Enter Valid Input2");
                            break;
                    }
                }
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void start(){
        boolean keepRunning = true;
        AdminResource adminResource = AdminResource.getAdminResource();
        try(Scanner scanner = new Scanner(System.in)){
            while(keepRunning){
                System.out.println("1. See All Customers");
                System.out.println("2. See All Rooms");
                System.out.println("3. See All Reservations");
                System.out.println("4. Add a Room");
                System.out.println("5. Back to Main Menu");
                int selection = Integer.parseInt(scanner.nextLine());
                switch (selection){
                    case 1:
                        System.out.println(adminResource.getAllCustomers());
                        break;

                    case 2:
                        System.out.println(adminResource.getAllRooms());
                        break;

                    case 3:
                        adminResource.displayAllReservations();
                        break;

                    case 4:
                        addRoom(adminResource);
                        break;

                    case 5:
                        System.out.println("You are going to go back to Main Menu.");
                        MainMenu mainMenu = new MainMenu();
                        mainMenu.start();
                        break;

                    default:
                        System.out.println("Please Enter Number 1-5");
                        break;
                }
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
