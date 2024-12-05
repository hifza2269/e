public class Customer {
    private String customerId;
    private String name;
    private String address;
    private String contactNumber;

    public Customer(String customerId, String name, String address, String contactNumber) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getContactNumber() { return contactNumber; }

    public void setAddress(String address) { this.address = address; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
}
