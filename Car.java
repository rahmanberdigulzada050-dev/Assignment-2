public class Car extends Vehicle implements Serviceable {
    private int numberOfDoors;
    public Car(String model, int year, double basePrice, int numberOfDoors) {
        super(model, year, basePrice);  // Call parent constructor
        setNumberOfDoors(numberOfDoors);
    }
    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        if (numberOfDoors < 2 || numberOfDoors > 5) {
            throw new IllegalArgumentException("Number of doors must be 2-5");
        }
        this.numberOfDoors = numberOfDoors;
    }

    // Insurance calculation for Car (specific formula)
    @Override
    public double calculateInsuranceFee() {
        int age = getAge(2025);  // Using 2025 as current year
        double insurance = getBasePrice() * 0.05;  // 5% of base price

        // Older cars pay less (10% discount per year after 5 years)
        if (age > 5) {
            int extraYears = age - 5;
            double discount = 0.10 * extraYears;
            if (discount > 0.50) discount = 0.50;  // Max 50% discount
            insurance *= (1 - discount);
        }

        return insurance;
    }
    @Override
    public void performService() {
        System.out.println("Servicing Car: " + getModel());
        System.out.println("  - Changing engine oil");
        System.out.println("  - Checking brakes");
        System.out.println("  - Rotating tires");
        System.out.println("  - Checking all " + numberOfDoors + " doors");
    }

    @Override
    public int getServiceIntervalKm() {
        return 10000;  // Cars need service every 10,000 km
    }

    // Better toString with car-specific info
    @Override
    public String toString() {
        return super.toString() + String.format(" | Type: Car | Doors: %d | Insurance: $%.2f/year",
                numberOfDoors, calculateInsuranceFee());
    }
}