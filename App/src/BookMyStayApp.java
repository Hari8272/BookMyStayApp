import java.util.*;

/*
 * Use Case 6: Reservation Confirmation & Room Allocation
 * Single File Version
 * @version 6.0
 */

// Reservation (same as UC5)
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

// Booking Queue (FIFO)
class BookingRequestQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation r) {
        queue.offer(r);
    }

    public Reservation getNextRequest() {
        return queue.poll();
    }

    public boolean hasRequests() {
        return !queue.isEmpty();
    }
}

// Inventory (UC3)
class RoomInventory {
    private Map<String, Integer> availability = new HashMap<>();

    public RoomInventory() {
        availability.put("Single", 2);
        availability.put("Double", 1);
        availability.put("Suite", 1);
    }

    public int getAvailable(String type) {
        return availability.getOrDefault(type, 0);
    }

    public void reduceRoom(String type) {
        availability.put(type, availability.get(type) - 1);
    }
}

// Allocation Service (MAIN LOGIC 🔥)
class RoomAllocationService {

    // Track allocated room IDs
    private Set<String> allocatedRooms = new HashSet<>();

    // Map roomType → allocated IDs
    private Map<String, Set<String>> roomMap = new HashMap<>();

    // Generate unique room ID
    private String generateRoomId(String type, int number) {
        return type.substring(0, 2).toUpperCase() + number;
    }

    public void processRequests(BookingRequestQueue queue, RoomInventory inventory) {

        int counter = 1;

        while (queue.hasRequests()) {
            Reservation r = queue.getNextRequest();
            String type = r.getRoomType();

            System.out.println("\nProcessing: " + r.getGuestName());

            // Check availability
            if (inventory.getAvailable(type) > 0) {

                // Generate unique ID
                String roomId;
                do {
                    roomId = generateRoomId(type, counter++);
                } while (allocatedRooms.contains(roomId));

                // Store in Set (prevent duplicate)
                allocatedRooms.add(roomId);

                // Map type → room IDs
                roomMap.putIfAbsent(type, new HashSet<>());
                roomMap.get(type).add(roomId);

                // Reduce inventory
                inventory.reduceRoom(type);

                System.out.println("✅ Booking Confirmed!");
                System.out.println("Room Type: " + type);
                System.out.println("Assigned Room ID: " + roomId);

            } else {
                System.out.println("❌ Booking Failed (No rooms available)");
            }
        }
    }
}

// Main Class
public class BookMyStayApp {

    public static void main(String[] args) {

        // Queue
        BookingRequestQueue queue = new BookingRequestQueue();

        // Add requests
        queue.addRequest(new Reservation("Abhi", "Single"));
        queue.addRequest(new Reservation("Subha", "Double"));
        queue.addRequest(new Reservation("Ram", "Single"));
        queue.addRequest(new Reservation("John", "Suite"));
        queue.addRequest(new Reservation("Alex", "Suite")); // should fail

        // Inventory
        RoomInventory inventory = new RoomInventory();

        // Process bookings
        RoomAllocationService service = new RoomAllocationService();
        service.processRequests(queue, inventory);
    }
}