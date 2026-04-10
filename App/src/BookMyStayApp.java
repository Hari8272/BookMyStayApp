import java.util.*;

/*
 * Use Case 9: Error Handling & Validation
 * Single File Version
 * @version 9.0
 */

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Reservation class
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

// Inventory with validation
class RoomInventory {

    private Map<String, Integer> availability = new HashMap<>();

    public RoomInventory() {
        availability.put("Single", 2);
        availability.put("Double", 1);
        availability.put("Suite", 0);
    }

    public int getAvailable(String type) throws InvalidBookingException {
        if (!availability.containsKey(type)) {
            throw new InvalidBookingException("Invalid Room Type: " + type);
        }
        return availability.get(type);
    }

    public void reduceRoom(String type) throws InvalidBookingException {
        int current = getAvailable(type);

        if (current <= 0) {
            throw new InvalidBookingException("No rooms available for: " + type);
        }

        availability.put(type, current - 1);
    }
}

// Validator
class BookingValidator {

    public static void validate(Reservation r, RoomInventory inventory)
            throws InvalidBookingException {

        if (r.getGuestName() == null || r.getGuestName().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty");
        }

        if (r.getRoomType() == null || r.getRoomType().isEmpty()) {
            throw new InvalidBookingException("Room type cannot be empty");
        }

        // Check room type validity
        inventory.getAvailable(r.getRoomType());
    }
}

// Main Class
public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        // Test cases
        List<Reservation> requests = Arrays.asList(
                new Reservation("Abhi", "Single"),
                new Reservation("", "Double"),       // invalid name
                new Reservation("Ram", "Suite"),     // no rooms
                new Reservation("John", "Luxury")    // invalid type
        );

        for (Reservation r : requests) {

            System.out.println("\nProcessing booking for: " + r.getGuestName());

            try {
                // Validate
                BookingValidator.validate(r, inventory);

                // Allocate
                inventory.reduceRoom(r.getRoomType());

                System.out.println("✅ Booking Successful!");

            } catch (InvalidBookingException e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }
}