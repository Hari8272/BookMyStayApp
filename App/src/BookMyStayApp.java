import java.util.*;

/*
 * Use Case 7: Add-On Service Selection
 * Single File Version
 * @version 7.0
 */

// Service class
class AddOnService {
    private String name;
    private double cost;

    public AddOnService(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() { return name; }
    public double getCost() { return cost; }
}

// Manager class
class AddOnServiceManager {

    // Map<ReservationID, List of Services>
    private Map<String, List<AddOnService>> serviceMap = new HashMap<>();

    // Add service to reservation
    public void addService(String reservationId, AddOnService service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    // Calculate total cost
    public double calculateTotalCost(String reservationId) {
        double total = 0;

        List<AddOnService> services = serviceMap.get(reservationId);

        if (services != null) {
            for (AddOnService s : services) {
                total += s.getCost();
            }
        }
        return total;
    }

    // Display services
    public void displayServices(String reservationId) {
        List<AddOnService> services = serviceMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No services selected.");
            return;
        }

        System.out.println("Add-On Services for Reservation " + reservationId + ":");

        for (AddOnService s : services) {
            System.out.println("- " + s.getName() + " : ₹" + s.getCost());
        }

        System.out.println("Total Add-On Cost: ₹" + calculateTotalCost(reservationId));
    }
}

// Main Class
public class BookMyStayApp {

    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        // Assume reservation ID from UC6
        String reservationId = "SI1";

        // Add services
        manager.addService(reservationId, new AddOnService("Breakfast", 300));
        manager.addService(reservationId, new AddOnService("Airport Pickup", 800));
        manager.addService(reservationId, new AddOnService("Extra Bed", 500));

        // Display services
        manager.displayServices(reservationId);
    }
}