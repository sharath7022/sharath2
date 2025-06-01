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
import java.util.Date; // Import Date class for dynamic content

/**
 * A Java program that generates a slightly more complex HTML string,
 * including a list and a simple form element, and prints it to the console.
 * This further illustrates dynamic HTML content creation from Java.
 */
public class HtmlGenerator2 {

    public static void main(String[] args) {
        // Use StringBuilder for efficient string concatenation when building HTML.
        StringBuilder htmlContent = new StringBuilder();

        // --- HTML Document Structure ---
        htmlContent.append("<!DOCTYPE html>\n");
        htmlContent.append("<html lang=\"en\">\n");

        // --- Head Section ---
        htmlContent.append("<head>\n");
        htmlContent.append("    <meta charset=\"UTF-8\">\n");
        htmlContent.append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
        htmlContent.append("    <title>Dynamic Java-Generated Page 2</title>\n");
        // Inline CSS for quick styling demonstration
        htmlContent.append("    <style>\n");
        htmlContent.append("        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #e0f2f7; margin: 0; padding: 0; display: flex; justify-content: center; align-items: center; min-height: 100vh; }\n");
        htmlContent.append("        .card { background-color: white; padding: 40px; border-radius: 12px; box-shadow: 0 8px 16px rgba(0,0,0,0.2); max-width: 700px; width: 90%; text-align: center; }\n");
        htmlContent.append("        h1 { color: #00796b; margin-bottom: 20px; font-size: 2.5em; }\n");
        htmlContent.append("        p { color: #424242; line-height: 1.7; margin-bottom: 15px; }\n");
        htmlContent.append("        ul { list-style-type: none; padding: 0; margin: 20px 0; }\n");
        htmlContent.append("        li { background-color: #f0f4f8; margin-bottom: 8px; padding: 12px; border-radius: 6px; color: #37474f; }\n");
        htmlContent.append("        .form-group { margin-bottom: 15px; text-align: left; }\n");
        htmlContent.append("        .form-group label { display: block; margin-bottom: 5px; font-weight: bold; color: #00796b; }\n");
        htmlContent.append("        .form-group input[type=\"text\"] { width: calc(100% - 22px); padding: 10px; border: 1px solid #b2dfdb; border-radius: 5px; font-size: 1em; }\n");
        htmlContent.append("        .submit-button { background-color: #004d40; color: white; padding: 12px 25px; border: none; border-radius: 5px; cursor: pointer; font-size: 1.1em; transition: background-color 0.3s ease; }\n");
        htmlContent.append("        .submit-button:hover { background-color: #00332e; }\n");
        htmlContent.append("    </style>\n");
        htmlContent.append("</head>\n");

        // --- Body Section ---
        htmlContent.append("<body>\n");
        htmlContent.append("    <div class=\"card\">\n");
        htmlContent.append("        <h1>Hello from Java!</h1>\n");
        htmlContent.append("        <p>This is a slightly more elaborate HTML page, generated entirely by a Java program.</p>\n");
        htmlContent.append("        <p>It demonstrates how backend logic can construct complex web elements.</p>\n");

        // Dynamic content: Current date and time
        htmlContent.append("        <p><strong>Generated at:</strong> " + new Date() + "</p>\n");

        // List of features
        htmlContent.append("        <h2>Key Features:</h2>\n");
        htmlContent.append("        <ul>\n");
        htmlContent.append("            <li>Dynamic content generation.</li>\n");
        htmlContent.append("            <li>Structured HTML elements.</li>\n");
        htmlContent.append("            <li>Inline CSS for styling.</li>\n");
        htmlContent.append("            <li>Basic form element integration.</li>\n");
        htmlContent.append("            <li>Simulates server-side rendering.</li>\n");
        htmlContent.append("        </ul>\n");

        // Simple form
        htmlContent.append("        <h2>Leave a Message:</h2>\n");
        htmlContent.append("        <form action=\"/submit-message\" method=\"POST\">\n");
        htmlContent.append("            <div class=\"form-group\">\n");
        htmlContent.append("                <label for=\"name\">Your Name:</label>\n");
        htmlContent.append("                <input type=\"text\" id=\"name\" name=\"userName\" placeholder=\"Enter your name\" required>\n");
        htmlContent.append("            </div>\n");
        htmlContent.append("            <div class=\"form-group\">\n");
        htmlContent.append("                <label for=\"message\">Your Message:</label>\n");
        htmlContent.append("                <input type=\"text\" id=\"message\" name=\"userMessage\" placeholder=\"Type your message here\" required>\n");
        htmlContent.append("            </div>\n");
        htmlContent.append("            <button type=\"submit\" class=\"submit-button\">Submit</button>\n");
        htmlContent.append("        </form>\n");

        htmlContent.append("    </div>\n");
        htmlContent.append("</body>\n");

        // Close the <html> tag
        htmlContent.append("</html>");

        // Print the complete HTML string to the console
        System.out.println(htmlContent.toString());
    }
}
}
