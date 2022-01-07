import api.HotelResource;
import model.Customer;
import model.IRoom;
import service.CustomerService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import java.util.Collection;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MainMenu {

    public void start() {
        boolean keepRunning = true;
        HotelResource hotelResource = HotelResource.getInstance();

        while (keepRunning) {
            System.out.println("1. Find And Reserve A Room");
            System.out.println("2. See My Reservations");
            System.out.println("3. Create An Account");
            System.out.println("4. Admin");
            System.out.println("5. Exit");
            System.out.println("Please Make Your Choice!");
            Scanner scanner = new Scanner(System.in);
            try {
                int selection = Integer.parseInt(scanner.nextLine());
                switch (selection) {
                    case 1 -> {
                        boolean keepFinding = true;
                        boolean keepReserving = true;
                        while (keepFinding) {
                            System.out.println("Please Enter Your Check-In Date (mm/dd/yyyy): ");
                            Scanner userCheckIn = new Scanner(System.in);
                            String checkInDate = userCheckIn.nextLine();
                            System.out.println("Please Enter Your Check-Out Date(mm/dd/yyyy): ");
                            Scanner userCheckOut = new Scanner(System.in);
                            String checkOutDate = userCheckOut.nextLine();
                            Date date1;
                            Date date2;
                            try {
                                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                                date1 = format.parse(checkInDate);
                                date2 = format.parse(checkOutDate);
                                if (date1.after(date2)) {
                                    System.out.println("Check-in date is not allowed to be later than check-out date, please reenter other dates");
                                    continue;
                                }
                                if (hotelResource.findARoom(date1, date2).isEmpty()) {
                                    System.out.println("Rooms are not available in this date range");
                                    continue;
                                }
                                System.out.println(hotelResource.findARoom(date1, date2));
                                System.out.println("Let's reserve a room");
                                while (keepReserving) {
                                    System.out.println("Please Enter Your Email Address: ");

                                    Scanner userEmail = new Scanner(System.in);
                                    String customerEmail = userEmail.nextLine();
                                    String emailRegex = "^(.+)@(.+)\\.(.+)$";
                                    Pattern pattern = Pattern.compile(emailRegex);
                                    if (!pattern.matcher(customerEmail).matches()) {
                                        System.out.println("Please enter a valid email address.");
                                    } else {
                                        System.out.println("Please Enter the RoomId: ");
                                        Scanner userRoomId = new Scanner(System.in);
                                        String thisRoomId = userRoomId.nextLine();
                                        try {
                                            hotelResource.bookARoom(customerEmail, hotelResource.getRoom(thisRoomId), date1, date2);
                                            System.out.println("You have successfully booked a room");
                                            keepReserving = false;
                                            keepFinding = false;
                                        } catch (Exception ex) {
                                            System.out.println("RoomId is wrong, please check again.");
                                            keepReserving = true;
                                        }
                                    }
                                }

                            } catch (ParseException e) {
                                // TODO Auto-generated catch block
                                System.out.println("Wrong date format, please reenter dates.");
                            }
                        }
                    }
                    case 2 -> {
                        boolean validEmail = true;
                        while (validEmail) {
                            System.out.println("Please Enter Your Email Address");
                            Scanner userEmail = new Scanner(System.in);
                            String customerEmail = userEmail.nextLine();
                            validEmail = false;
                            try {
                                System.out.println(hotelResource.getCustomersReservations(customerEmail));
                            } catch (Exception ex) {
                                validEmail = true;
                                ex.printStackTrace();
                                System.out.println("Something Went Wrong, Please See the Exception Message Above for more information");
                            }
                        }
                    }
                    case 3 -> {
                        boolean invalidEmail = true;
                        while (invalidEmail) {
                            System.out.println("Please Enter Your Email Address");
                            Scanner userEmail = new Scanner(System.in);
                            String customerEmail = userEmail.nextLine();
                            String emailRegex = "^(.+)@(.+)\\.(.+)$";
                            Pattern pattern = Pattern.compile(emailRegex);
                            if (!pattern.matcher(customerEmail).matches()) {
                                System.out.println("Please enter a valid email address.");
                                invalidEmail = true;
                            } else {
                                invalidEmail = false;
                                System.out.println("Please Enter Your First Name");
                                Scanner userFirstName = new Scanner(System.in);
                                String firstName = userFirstName.nextLine();
                                System.out.println("Please Enter Your Last Name");
                                Scanner userLastName = new Scanner(System.in);
                                String lastName = userLastName.nextLine();
                                try {
                                    hotelResource.createACustomer(customerEmail, firstName, lastName);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                System.out.println("Account Created Successfully");
                            }
                        }
                    }
                    case 4 -> {
                        AdminMenu adminMenu = new AdminMenu();
                        adminMenu.start();
                    }
                    case 5 -> {
                        System.out.println("You are going to exit");
                        System.exit(0);
                    }
                    default -> System.out.println("Please enter number 1-5");
                }

            }
            catch(Exception ex){
                System.out.println("Please Enter Num 1-5");
                continue;
            }
        }
    }
}
