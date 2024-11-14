import java.util.ArrayList;
import java.util.Scanner;

// Room class representing each hotel room
class Room {
    private int roomNumber;
    private String category;
    private boolean isAvailable;
    private double price;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.isAvailable = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void reserve() {
        isAvailable = false;
    }

    public void cancelReservation() {
        isAvailable = true;
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber + ", Category: " + category + ", Price: " + price + ", Available: " + isAvailable;
    }
}

// Booking class for storing reservation details
class Booking {
    private Room room;
    private String guestName;
    private double amountPaid;

    public Booking(Room room, String guestName, double amountPaid) {
        this.room = room;
        this.guestName = guestName;
        this.amountPaid = amountPaid;
        room.reserve(); // Reserve the room
    }

    public Room getRoom() {
        return room;
    }

    public String getGuestName() {
        return guestName;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    @Override
    public String toString() {
        return "Guest: " + guestName + ", Room: " + room.getRoomNumber() + ", Category: " + room.getCategory() + ", Amount Paid: " + amountPaid;
    }
}

// Hotel class managing rooms and bookings
class Hotel {
    private ArrayList<Room> rooms;
    private ArrayList<Booking> bookings;

    public Hotel() {
        rooms = new ArrayList<>();
        bookings = new ArrayList<>();
        initializeRooms(); // Initialize with some sample rooms
    }

    private void initializeRooms() {
        rooms.add(new Room(101, "Single", 100));
        rooms.add(new Room(102, "Single", 100));
        rooms.add(new Room(201, "Double", 200));
        rooms.add(new Room(202, "Double", 200));
        rooms.add(new Room(301, "Suite", 500));
    }

    public void showAvailableRooms(String category) {
        System.out.println("Available Rooms in Category: " + category);
        for (Room room : rooms) {
            if (room.isAvailable() && room.getCategory().equalsIgnoreCase(category)) {
                System.out.println(room);
            }
        }
    }

    public Room findAvailableRoom(String category) {
        for (Room room : rooms) {
            if (room.isAvailable() && room.getCategory().equalsIgnoreCase(category)) {
                return room;
            }
        }
        return null;
    }

    public void makeReservation(String guestName, String category) {
        Room availableRoom = findAvailableRoom(category);
        if (availableRoom != null) {
            System.out.println("Processing payment of " + availableRoom.getPrice() + "...");
            Booking booking = new Booking(availableRoom, guestName, availableRoom.getPrice());
            bookings.add(booking);
            System.out.println("Booking successful! Details:");
            System.out.println(booking);
        } else {
            System.out.println("No available rooms in this category.");
        }
    }

    public void viewAllBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            System.out.println("All Bookings:");
            for (Booking booking : bookings) {
                System.out.println(booking);
            }
        }
    }
}

// Main class to run the hotel reservation system
public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();
        System.out.println("Welcome to the Hotel Reservation System!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Search for Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Booking Details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter room category (Single, Double, Suite): ");
                    String category = scanner.nextLine();
                    hotel.showAvailableRooms(category);
                    break;
                case 2:
                    System.out.print("Enter guest name: ");
                    String guestName = scanner.nextLine();
                    System.out.print("Enter room category (Single, Double, Suite): ");
                    category = scanner.nextLine();
                    hotel.makeReservation(guestName, category);
                    break;
                case 3:
                    hotel.viewAllBookings();
                    break;
                case 4:
                    System.out.println("Thank you for using the Hotel Reservation System!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
