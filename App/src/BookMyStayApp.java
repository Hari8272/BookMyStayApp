import java.util.*;

/*
 * Use Case 8: Booking History & Reporting
 * Single File Version
 * @version 8.0
 */

// Reservation class (reuse concept)
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }
}

// Booking History (List maintains order)
class BookingHistory {

    private List<Reservation> confirmedReservations;

    public BookingHistory() {
        confirmedReservations = new ArrayList<>();
    }

    // Add confirmed booking
    public void addReservation(Reservation r) {
        confirmedReservations.add(r);
    }

    // Get all bookings
    public List<Reservation> getConfirmedReservations() {
        return confirmedReservations;
    }
}

// Report Service (separate logic)
class BookingReportService {

    public void generateReport(BookingHistory history) {

        System.out.println("\n=== Booking History Report ===\n");

        for (Reservation r : history.getConfirmedReservations()) {
            System.out.println("Guest: " + r.getGuestName() +
                    ", Room Type: " + r.getRoomType());
        }
    }
}

// Main Class
public class BookMyStayApp {

    public static void main(String[] args) {

        // Booking history
        BookingHistory history = new BookingHistory();

        // Add confirmed bookings (from UC6)
        history.addReservation(new Reservation("Abhi", "Single"));
        history.addReservation(new Reservation("Subha", "Double"));
        history.addReservation(new Reservation("Vanmathi", "Suite"));

        // Generate report
        BookingReportService report = new BookingReportService();
        report.generateReport(history);
    }
}