import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

class Bus {
    private int busId;
    private String operator;
    private String origin;
    private String destination;
    private Date departureTime;
    private double price;
    private int totalSeats;
    private List<Integer> bookedSeats;

    public Bus(int busId, String operator, String origin, String destination, Date departureTime, double price, int totalSeats) {
        this.busId = busId;
        this.operator = operator;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.price = price;
        this.totalSeats = totalSeats;
        this.bookedSeats = new ArrayList<>();
    }

    // Getters (omitted for brevity, but should be included)
    public int getBusId() { return busId; }
    public String getOperator() { return operator; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public Date getDepartureTime() { return departureTime; }
    public double getPrice() { return price; }
    public int getTotalSeats() { return totalSeats; }
    public int getAvailableSeats() { return totalSeats - bookedSeats.size(); }

    public boolean bookSeat(int seatNumber) {
        if (!bookedSeats.contains(seatNumber) && seatNumber > 0 && seatNumber <= totalSeats) {
            bookedSeats.add(seatNumber);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return String.format("[%d] %s: %s to %s on %s (₹%.2f, Seats: %d/%d)",
                             busId, operator, origin, destination, sdf.format(departureTime), price, getAvailableSeats(), totalSeats);
    }
}

public class BusTicketApp extends JFrame implements ActionListener {
    private List<Bus> availableBuses = new ArrayList<>();
    private JPanel mainPanel;
    private JButton searchButton, bookButton, viewBookingsButton, exitButton;
    private JTextArea displayArea;

    public BusTicketApp() {
        setTitle("Online Bus Ticket Booking");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Sample Buses (in a real app, this would come from a database)
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            availableBuses.add(new Bus(101, "Sai Travels", "Mumbai", "Pune", sdf.parse("2025-06-05 09:00"), 450.00, 40));
            availableBuses.add(new Bus(102, "National Express", "Delhi", "Chandigarh", sdf.parse("2025-06-06 11:30"), 600.00, 35));
        } catch (Exception e) {
            e.printStackTrace();
        }

        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        searchButton = new JButton("Search Buses");
        bookButton = new JButton("Book Ticket");
        viewBookingsButton = new JButton("View Bookings");
        exitButton = new JButton("Exit");

        searchButton.addActionListener(this);
        bookButton.addActionListener(this);
        viewBookingsButton.addActionListener(this);
        exitButton.addActionListener(this);

        mainPanel.add(searchButton);
        mainPanel.add(bookButton);
        mainPanel.add(viewBookingsButton);
        mainPanel.add(exitButton);

        displayArea = new JTextArea(15, 50);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        add(mainPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            displayArea.setText("Functionality to search for buses will be implemented here.\n");
            for (Bus bus : availableBuses) {
                displayArea.append(bus.toString() + "\n");
            }
        } else if (e.getSource() == bookButton) {
            displayArea.setText("Functionality to book a ticket will be implemented here.\n");
            if (!availableBuses.isEmpty()) {
                displayArea.append("Available buses for booking:\n");
                for (Bus bus : availableBuses) {
                    displayArea.append(bus.toString() + "\n");
                }
                displayArea.append("\n(You would typically select a bus and seats here)\n");
            } else {
                displayArea.append("No buses available for booking.\n");
            }
        } else if (e.getSource() == viewBookingsButton) {
            displayArea.setText("Functionality to view your bookings will be implemented here.\n");
            displayArea.append("(You would typically see your booking history here)\n");
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BusTicketApp::new);
    }
}
