
import java.util.Scanner;

class Room {

    int roomNumber;
    String status; // Available, Booked, Not Available

    Room(int roomNumber, String status) {
        this.roomNumber = roomNumber;
        this.status = status;
    }

    void displayRoom() {
        System.out.println("Room " + roomNumber + " - " + status);
    }
}

public class HotelBooking {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create some rooms
        Room[] rooms = {
            new Room(101, "Available"),
            new Room(102, "Available"),
            new Room(103, "Not Available"),
            new Room(104, "Available"),
            new Room(105, "Booked")
        };

        int choice;
        do {
            System.out.println("\n--- Hotel Room Booking System ---");
            System.out.println("1. View all rooms");
            System.out.println("2. Book a room");
            System.out.println("3. Cancel booking");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nRoom List:");
                    for (Room r : rooms) {
                        r.displayRoom();
                    }
                    break;

                case 2:
                    System.out.print("Enter room number to book: ");
                    int bookRoom = sc.nextInt();
                    boolean booked = false;
                    for (Room r : rooms) {
                        if (r.roomNumber == bookRoom) {
                            if (r.status.equals("Available")) {
                                r.status = "Booked";
                                System.out.println("Room " + bookRoom + " has been booked successfully.");
                            } else {
                                System.out.println("Room " + bookRoom + " is not available for booking.");
                            }
                            booked = true;
                            break;
                        }
                    }
                    if (!booked) {
                        System.out.println("Invalid room number.");
                    }
                    break;

                case 3:
                    System.out.print("Enter room number to cancel booking: ");
                    int cancelRoom = sc.nextInt();
                    boolean cancelled = false;
                    for (Room r : rooms) {
                        if (r.roomNumber == cancelRoom) {
                            if (r.status.equals("Booked")) {
                                r.status = "Available";
                                System.out.println("Booking for Room " + cancelRoom + " has been cancelled.");
                            } else {
                                System.out.println("Room " + cancelRoom + " is not currently booked.");
                            }
                            cancelled = true;
                            break;
                        }
                    }
                    if (!cancelled) {
                        System.out.println("Invalid room number.");
                    }
                    break;

                case 4:
                    System.out.println("Exiting the system...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 4);

        sc.close();
    }
}
