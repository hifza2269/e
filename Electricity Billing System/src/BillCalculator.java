public class BillCalculator {
    public static double calculateBill(double units) {
        double bill = 0.0;

        if (units <= 100) {
            bill = units * 1.50;
        } else if (units <= 300) {
            bill = (100 * 1.50) + (units - 100) * 2.00;
        } else {
            bill = (100 * 1.50) + (200 * 2.00) + (units - 300) * 3.00;
        }

        double fixedCharge = 50.0;
        return bill + fixedCharge;
    }
}
