import java.util.HashMap;
import java.util.Map;

/*
 * Use Case 3: Centralized Room Inventory Management
 * Single File Version
 * @version 3.0
 */

class RoomInventory {

    // HashMap to store room type and availability
    private Map<String, Integer> roomAvailability;

    // Constructor
    public RoomInventory() {
        roomAvailability = new HashMap<>();
        initializeInventory();
    }

    // Initialize default room availability
    private void initializeInventory() {
        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);
    }

    // Get current availability
    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    // Update availability
    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        // Create inventory
        RoomInventory inventory = new RoomInventory();

        // Display initial inventory
        System.out.println("=== Current Room Availability ===");
        displayInventory(inventory.getRoomAvailability());

        // Update example
        inventory.updateAvailability("Single", 4);

        System.out.println("\n=== After Updating Single Rooms ===");
        displayInventory(inventory.getRoomAvailability());
    }

    // Method to display inventory
    public static void displayInventory(Map<String, Integer> inventory) {
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " Room Available: " + entry.getValue());
        }
    }
}