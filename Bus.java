public class Bus extends Vehicle implements Serviceable {
    private int passengerCapacity;

    // Constructor
    public Bus(String model, int year, double basePrice, int passengerCapacity) {
        super(model, year, basePrice);  // Call parent constructor
        setPassengerCapacity(passengerCapacity);
    }
    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        if (passengerCapacity < 10 || passengerCapacity > 100) {
            throw new IllegalArgumentException("Capacity must be 10-100 passengers");
        }
        this.passengerCapacity = passengerCapacity;
    }

    // Insurance calculation for Bus (different formula)
    @Override
    public double calculateInsuranceFee() {
        int age = getAge(2025);  // Using 2025 as current year
        double insurance = getBasePrice() * 0.08;  // 8% of base price (higher than car)

        // Additional cost based on passenger capacity
        insurance += passengerCapacity * 50;

        // Discount for new buses
        if (age < 3) {
            insurance *= 0.9;  // 10% discount for new buses
        }

        return insurance;
    }
    @Override
    public void performService() {
        System.out.println("Servicing Bus: " + getModel());
        System.out.println("  - Checking engine and transmission");
        System.out.println("  - Inspecting safety equipment");
        System.out.println("  - Testing emergency exits");
        System.out.println("  - Checking seats for " + passengerCapacity + " passengers");
        System.out.println("  - Sanitizing interior");
    }

    @Override
    public int getServiceIntervalKm() {
        return 5000;  // Buses need service more often - every 5,000 km
    }

    // Better toString with bus-specific info
    @Override
    public String toString() {
        return super.toString() + String.format(" | Type: Bus | Capacity: %d | Insurance: $%.2f/year",
                passengerCapacity, calculateInsuranceFee());
    }
}