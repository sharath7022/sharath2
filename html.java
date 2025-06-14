import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

class Bus {
    private int busId;
    private String operator;
    private String origin;
    private String destination;
    private Date departureTime;
    private double price;
    private int totalSeats;
    private List<Integer> bookedSeats;

    public Bus(int busId, String operator, String origin, String destination, String departureTime, double price, int totalSeats) throws ParseException {
        this.busId = busId;
        this.operator = operator;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(departureTime);
        this.price = price;
        this.totalSeats = totalSeats;
        this.bookedSeats = new ArrayList<>();
    }

    public int getBusId() {
        return busId;
    }

    public String getOperator() {
        return operator;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public double getPrice() {
        return price;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return totalSeats - bookedSeats.size();
    }

    public boolean bookSeat(int seatNumber) {
        if (!bookedSeats.contains(seatNumber) && seatNumber > 0 && seatNumber <= totalSeats) {
            bookedSeats.add(seatNumber);
            return true;
        }
        return false;
    }

    public List<Integer> getBookedSeats() {
        return bookedSeats;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return String.format("[%d] %s: %s to %s on %s (₹%.2f, Seats: %d/%d)",
                             busId, operator, origin, destination, sdf.format(departureTime), price, getAvailableSeats(), totalSeats);
    }
}

class Booking {
    private int bookingId;
    private int busId;
    private String passengerName;
    private List<Integer> seatNumbers;
    private Date bookingDate;

    public Booking(int bookingId, int busId, String passengerName, List<Integer> seatNumbers) {
        this.bookingId = bookingId;
        this.busId = busId;
        this.passengerName = passengerName;
        this.seatNumbers = seatNumbers;
        this.bookingDate = new Date();
    }

    @Override
    public String toString() {
        return String.format("Booking ID: %d, Bus ID: %d, Passenger: %s, Seats: %s, Booked on: %s",
                             bookingId, busId, passengerName, seatNumbers, new SimpleDateFormat("yyyy-MM-dd HH:mm").format(bookingDate));
    }
}

public class BusTicketWebsite {
    private static List<Bus> buses = new ArrayList<>();
    private static List<Booking> bookings = new ArrayList<>();
    private static int nextBookingId = 1;

    public static void main(String[] args) throws ParseException {
        // Sample buses
        buses.add(new Bus(101, "Sai Travels", "Mumbai", "Pune", "2025-06-05 09:00", 450.00, 40));
        buses.add(new Bus(102, "National Express", "Delhi", "Chandigarh", "2025-06-06 11:30", 600.00, 35));
        buses.add(new Bus(103, "Kaveri Tours", "Bangalore", "Chennai", "2025-06-07 14:00", 520.00, 45));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to Online Bus Ticket Booking!");
            System.out.println("1. View Available Buses");
            System.out.println("2. Book a Ticket");
            System.out.println("3. View My Bookings");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAvailableBuses(scanner);
                    break;
                case 2:
                    bookTicket(scanner);
                    break;
                case 3:
                    viewMyBookings();
                    break;
                case 4:
                    System.out.println("Thank you for using our service!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewAvailableBuses(Scanner scanner) {
        System.out.print("Enter origin city: ");
        String origin = scanner.nextLine();
        System.out.print("Enter destination city: ");
        String destination = scanner.nextLine();
        System.out.print("Enter travel date (yyyy-MM-dd): ");
        String dateStr = scanner.nextLine();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date travelDate = sdf.parse(dateStr);
            System.out.println("\nAvailable Buses:");
            for (Bus bus : buses) {
                SimpleDateFormat busDateSdf = new SimpleDateFormat("yyyy-MM-dd");
                if (bus.getOrigin().equalsIgnoreCase(origin) &&
                    bus.getDestination().equalsIgnoreCase(destination) &&
                    busDateSdf.format(bus.getDepartureTime()).equals(sdf.format(travelDate))) {
                    System.out.println(bus);
                }
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    private static void bookTicket(Scanner scanner) {
        System.out.print("Enter Bus ID to book: ");
        int busIdToBook = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Bus selectedBus = null;
        for (Bus bus : buses) {
            if (bus.getBusId() == busIdToBook) {
                selectedBus = bus;
                break;
            }
        }

        if (selectedBus == null) {
            System.out.println("Invalid Bus ID.");
            return;
        }

        System.out.println("Selected Bus: " + selectedBus);
        System.out.print("Enter passenger name: ");
        String passengerName = scanner.nextLine();
        System.out.print("Enter number of seats to book: ");
        int numSeats = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (numSeats > selectedBus.getAvailableSeats()) {
            System.out.println("Not enough seats available.");
            return;
        }

        List<Integer> selectedSeats = new ArrayList<>();
        for (int i = 0; i < numSeats; i++) {
            System.out.print("Enter seat number " + (i + 1) + ": ");
            int seatNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (selectedBus.bookSeat(seatNumber)) {
                selectedSeats.add(seatNumber);
            } else {
                System.out.println("Seat " + seatNumber + " is already booked or invalid.");
                // In a real scenario, you might want to handle this more gracefully
                selectedBus.getBookedSeats().removeAll(selectedSeats); // Revert bookings
                return;
            }
        }

        Booking booking = new Booking(nextBookingId++, busIdToBook, passengerName, selectedSeats);
        bookings.add(booking);
        System.out.println("Booking successful!");
        System.out.println(booking);
    }

    private static void viewMyBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }
        System.out.println("\nMy Bookings:");
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }
}
