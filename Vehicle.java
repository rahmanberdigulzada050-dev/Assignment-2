import java.time.Year;
public abstract class Vehicle {
    private int id;
    private static int idGen = 1;
    private String model;
    private int year;
    private double basePrice;

    public Vehicle(String model, int year, double basePrice) {
        this.id = idGen++;
        setModel(model);
        setYear(year);
        setBasePrice(basePrice);
    }
    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Model cannot be empty");
        }
        this.model = model.trim();
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        int currentYear = Year.now().getValue();
        if (year < 1900 || year > currentYear) {
            throw new IllegalArgumentException("Year must be between 1900 and " + currentYear);
        }
        this.year = year;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Base price must be positive");
        }
        this.basePrice = basePrice;
    }

    public int getAge(int currentYear) {
        return currentYear - this.year;
    }

    public abstract double calculateInsuranceFee();

    @Override
    public String toString() {
        return String.format("Vehicle ID: %d | Model: %s | Year: %d | Base Price: $%.2f",
                id, model, year, basePrice);
    }
}