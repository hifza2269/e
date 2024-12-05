import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Bill> bills = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nElectricity Billing System");
            System.out.println("1. Add Customer");
            System.out.println("2. Generate Bill");
            System.out.println("3. View All Bills");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addCustomer(scanner);
                case 2 -> generateBill(scanner);
                case 3 -> viewAllBills();
                case 4 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void addCustomer(Scanner scanner) {
        scanner.nextLine(); // consume newline
        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Contact Number: ");
        String contactNumber = scanner.nextLine();

        customers.add(new Customer(customerId, name, address, contactNumber));
        System.out.println("Customer added successfully!");
    }

    private static void generateBill(Scanner scanner) {
        System.out.print("Enter Customer ID: ");
        String customerId = scanner.next();
        System.out.print("Enter Units Consumed: ");
        double units = scanner.nextDouble();

        double billAmount = BillCalculator.calculateBill(units);
        bills.add(new Bill(customerId, 11, units, billAmount)); // 11 for November

        System.out.println("Bill generated successfully! Amount: ₹" + String.format("%.2f", billAmount));
    }

    private static void viewAllBills() {
        System.out.println("\nAll Bills:");
        for (Bill bill : bills) {
            System.out.println("Customer ID: " + bill.getCustomerId() +
                    ", Units: " + bill.getUnitsConsumed() +
                    ", Bill: ₹" + String.format("%.2f", bill.getBillAmount()));
        }
    }
}
