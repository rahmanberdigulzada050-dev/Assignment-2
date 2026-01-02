import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FleetApp {
    private List<Vehicle> vehicles;
    private Scanner scanner;

    public FleetApp() {
        vehicles = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("=== FLEET MANAGEMENT SYSTEM ===");

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        printAllVehicles();
                        break;
                    case 2:
                        addNewCar();
                        break;
                    case 3:
                        addNewBus();
                        break;
                    case 4:
                        showTotalInsurance();
                        break;
                    case 5:
                        showVehiclesOlderThan();
                        break;
                    case 6:
                        performServiceForAll();
                        break;
                    case 7:
                        System.out.println("Thank you for using Fleet Management System!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice! Please enter 1-7.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println();
        }

        scanner.close();
    }

    private void printMenu() {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1. Show all vehicles");
        System.out.println("2. Add new car");
        System.out.println("3. Add new bus");
        System.out.println("4. Show total insurance fees");
        System.out.println("5. Show vehicles older than N years");
        System.out.println("6. Perform service for all vehicles");
        System.out.println("7. Exit");
    }

    private void printAllVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the fleet.");
        } else {
            System.out.println("\n=== ALL VEHICLES IN FLEET ===");
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle);
            }
            System.out.println("Total vehicles: " + vehicles.size());
        }
    }

    private void addNewCar() {
        System.out.println("\n=== ADD NEW CAR ===");

        try {
            System.out.print("Enter car model: ");
            String model = scanner.nextLine();

            System.out.print("Enter manufacturing year: ");
            int year = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter base price: $");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter number of doors (2-5): ");
            int doors = Integer.parseInt(scanner.nextLine());

            Car newCar = new Car(model, year, price, doors);
            vehicles.add(newCar);

            System.out.println("Car added successfully! ID: " + newCar.getId());

        } catch (Exception e) {
            System.out.println("Failed to add car: " + e.getMessage());
        }
    }

    private void addNewBus() {
        System.out.println("\n=== ADD NEW BUS ===");

        try {
            System.out.print("Enter bus model: ");
            String model = scanner.nextLine();

            System.out.print("Enter manufacturing year: ");
            int year = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter base price: $");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter passenger capacity (10-100): ");
            int capacity = Integer.parseInt(scanner.nextLine());

            Bus newBus = new Bus(model, year, price, capacity);
            vehicles.add(newBus);

            System.out.println("Bus added successfully! ID: " + newBus.getId());

        } catch (Exception e) {
            System.out.println("Failed to add bus: " + e.getMessage());
        }
    }

    private void showTotalInsurance() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the fleet.");
            return;
        }

        double totalInsurance = 0;
        for (Vehicle vehicle : vehicles) {
            totalInsurance += vehicle.calculateInsuranceFee();
        }

        System.out.printf("\n=== TOTAL YEARLY INSURANCE FEES ===\n");
        System.out.printf("Total for all vehicles: $%.2f per year\n", totalInsurance);
        System.out.printf("Average per vehicle: $%.2f per year\n",
                totalInsurance / vehicles.size());
    }

    private void showVehiclesOlderThan() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the fleet.");
            return;
        }

        try {
            System.out.print("Enter current year: ");
            int currentYear = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter minimum age (N years): ");
            int minAge = Integer.parseInt(scanner.nextLine());

            System.out.println("\n=== VEHICLES OLDER THAN " + minAge + " YEARS ===");
            boolean found = false;

            for (Vehicle vehicle : vehicles) {
                int age = vehicle.getAge(currentYear);
                if (age > minAge) {
                    System.out.println(vehicle + " | Age: " + age + " years");
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No vehicles found older than " + minAge + " years.");
            }

        } catch (Exception e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }

    private void performServiceForAll() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the fleet.");
            return;
        }

        System.out.println("\n=== PERFORMING SERVICE FOR ALL VEHICLES ===");

        // Polymorphism in action!
        // We treat all vehicles as Serviceable objects
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Serviceable) {
                Serviceable serviceableVehicle = (Serviceable) vehicle;
                serviceableVehicle.performService();
                System.out.println("  Next service in: " +
                        serviceableVehicle.getServiceIntervalKm() + " km");
                System.out.println();
            }
        }

        System.out.println("Service completed for " + vehicles.size() + " vehicles.");
    }

    // Main method to run the application
    public static void main(String[] args) {
        // Example of polymorphism as required in Task 2
        Serviceable myCar = new Car("Toyota Camry", 2020, 25000, 4);
        myCar.performService();  // This will call Car's performService()

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Run the main application
        FleetApp app = new FleetApp();
        app.run();
    }
}