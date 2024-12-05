public class Bill {
    private String customerId;
    private int month;
    private double unitsConsumed;
    private double billAmount;

    public Bill(String customerId, int month, double unitsConsumed, double billAmount) {
        this.customerId = customerId;
        this.month = month;
        this.unitsConsumed = unitsConsumed;
        this.billAmount = billAmount;
    }

    public String getCustomerId() { return customerId; }
    public int getMonth() { return month; }
    public double getUnitsConsumed() { return unitsConsumed; }
    public double getBillAmount() { return billAmount; }
}
