import java.util.HashMap;
import java.util.Map;

/*
 * Use Case 4: Room Search & Availability Check
 * Single File Version
 * @version 4.0
 */

// Room class (Domain Model)
class Room {
    String type;
    int beds;
    int size;
    double price;

    public Room(String type, int beds, int size, double price) {
        this.type = type;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public void displayDetails(int availableRooms) {
        System.out.println(type + " Room:");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
        System.out.println("Available Rooms: " + availableRooms);
        System.out.println();
    }
}

// Inventory (same as Use Case 3)
class RoomInventory {
    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 0); // example: unavailable
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }
}

// Search Service (READ-ONLY)
class RoomSearchService {

    public static void searchRooms(RoomInventory inventory, Map<String, Room> roomData) {

        Map<String, Integer> availability = inventory.getRoomAvailability();

        System.out.println("=== Available Rooms ===\n");

        for (String roomType : availability.keySet()) {

            int count = availability.get(roomType);

            // Show only available rooms
            if (count > 0) {
                Room room = roomData.get(roomType);
                room.displayDetails(count);
            }
        }
    }
}

// Main Class
public class BookMyStayApp {

    public static void main(String[] args) {

        // Inventory
        RoomInventory inventory = new RoomInventory();

        // Room details (Domain objects)
        Map<String, Room> roomData = new HashMap<>();
        roomData.put("Single", new Room("Single", 1, 250, 1500));
        roomData.put("Double", new Room("Double", 2, 400, 2500));
        roomData.put("Suite", new Room("Suite", 3, 750, 5000));

        // Perform search (read-only)
        RoomSearchService.searchRooms(inventory, roomData);
    }
}