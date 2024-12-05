import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ElectricityBillingSystemEnhanced {

    public static void main(String[] args) {
        new LoginPage(); // Start with the login page
    }
}

class LoginPage {
    JFrame frame;

    public LoginPage() {
        frame = new JFrame("Login - Electricity Billing System");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.DARK_GRAY);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        JTextField usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Empty cell
        panel.add(loginButton);

        frame.add(panel);
        frame.setVisible(true);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals("admin") && password.equals("admin")) {
                JOptionPane.showMessageDialog(frame, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                new ElectricityBillingGUI(); // Open the main GUI
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}

class ElectricityBillingGUI {
    JFrame frame;

    public ElectricityBillingGUI() {
        frame = new JFrame("Electricity Billing System");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.DARK_GRAY);

        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel headerLabel = new JLabel("Electricity Billing System", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.setBackground(new Color(50, 50, 50)); // Dark gray
        headerPanel.add(headerLabel, BorderLayout.CENTER);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.DARK_GRAY);

        JLabel customerIdLabel = new JLabel("Customer ID:");
        customerIdLabel.setForeground(Color.WHITE);
        JTextField customerIdField = new JTextField();

        JLabel customerNameLabel = new JLabel("Customer Name:");
        customerNameLabel.setForeground(Color.WHITE);
        JTextField customerNameField = new JTextField();

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setForeground(Color.WHITE);
        JTextField addressField = new JTextField();

        JLabel contactLabel = new JLabel("Contact Number:");
        contactLabel.setForeground(Color.WHITE);
        JTextField contactField = new JTextField();

        JLabel meterNumberLabel = new JLabel("Meter Number:");
        meterNumberLabel.setForeground(Color.WHITE);
        JTextField meterNumberField = new JTextField();

        JLabel monthLabel = new JLabel("Billing Month:");
        monthLabel.setForeground(Color.WHITE);
        JComboBox<String> monthComboBox = new JComboBox<>(new String[]{
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        });

        JLabel unitsLabel = new JLabel("Units Consumed:");
        unitsLabel.setForeground(Color.WHITE);
        JTextField unitsField = new JTextField();

        JLabel resultLabel = new JLabel("Total Bill: ₹0.00");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultLabel.setForeground(new Color(0, 255, 0)); // Green color

        formPanel.add(customerIdLabel);
        formPanel.add(customerIdField);
        formPanel.add(customerNameLabel);
        formPanel.add(customerNameField);
        formPanel.add(addressLabel);
        formPanel.add(addressField);
        formPanel.add(contactLabel);
        formPanel.add(contactField);
        formPanel.add(meterNumberLabel);
        formPanel.add(meterNumberField);
        formPanel.add(monthLabel);
        formPanel.add(monthComboBox);
        formPanel.add(unitsLabel);
        formPanel.add(unitsField);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.DARK_GRAY);
        JButton calculateButton = new JButton("Calculate Bill");
        JButton saveButton = new JButton("Save");
        JButton resetButton = new JButton("Reset");
        JButton exitButton = new JButton("Exit");

        calculateButton.setForeground(Color.WHITE);
        saveButton.setForeground(Color.WHITE);
        resetButton.setForeground(Color.WHITE);
        exitButton.setForeground(Color.WHITE);

        buttonPanel.add(calculateButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(exitButton);

        // Main Layout
        frame.setLayout(new BorderLayout());
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Action Listeners
        calculateButton.addActionListener(e -> {
            try {
                String customerId = customerIdField.getText();
                String customerName = customerNameField.getText();
                String address = addressField.getText();
                String contact = contactField.getText();
                String meterNumber = meterNumberField.getText();
                String month = (String) monthComboBox.getSelectedItem();
                double units = Double.parseDouble(unitsField.getText());

                if (customerId.isEmpty() || customerName.isEmpty() || address.isEmpty() || contact.isEmpty() || meterNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Calculate bill
                double bill = calculateBill(units);
                resultLabel.setText("Total Bill: ₹" + String.format("%.2f", bill));

                // Show the bill details on a new page
                new BillPage(customerName, address, contact, meterNumber, month, units, bill);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input! Please enter numeric units.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        resetButton.addActionListener(e -> {
            customerIdField.setText("");
            customerNameField.setText("");
            addressField.setText("");
            contactField.setText("");
            meterNumberField.setText("");
            unitsField.setText("");
            resultLabel.setText("Total Bill: ₹0.00");
        });

        exitButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                frame.dispose();
            }
        });

        saveButton.addActionListener(e -> {
            try (FileWriter writer = new FileWriter("billing_data.txt", true)) {
                writer.write("Customer ID: " + customerIdField.getText() +
                        ", Name: " + customerNameField.getText() +
                        ", Address: " + addressField.getText() +
                        ", Contact: " + contactField.getText() +
                        ", Meter No: " + meterNumberField.getText() +
                        ", Month: " + monthComboBox.getSelectedItem() +
                        ", Units: " + unitsField.getText() +
                        ", Total Bill: " + resultLabel.getText() + "\n");
                JOptionPane.showMessageDialog(frame, "Data saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error saving data!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setVisible(true);
    }

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

class BillPage {
    JFrame frame;

    public BillPage(String customerName, String address, String contact, String meterNumber, String month, double units, double billAmount) {
        frame = new JFrame("Detailed Bill");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(Color.DARK_GRAY);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.DARK_GRAY);

        panel.add(createBillDetail("Customer Name: ", customerName));
        panel.add(createBillDetail("Address: ", address));
        panel.add(createBillDetail("Contact: ", contact));
        panel.add(createBillDetail("Meter Number: ", meterNumber));
        panel.add(createBillDetail("Billing Month: ", month));
        panel.add(createBillDetail("Units Consumed: ", String.valueOf(units)));
        panel.add(createBillDetail("Total Bill: ₹", String.format("%.2f", billAmount)));

        JButton closeButton = new JButton("Close");
        closeButton.setForeground(Color.WHITE);
        closeButton.addActionListener(e -> frame.dispose());

        panel.add(Box.createVerticalStrut(20));
        panel.add(closeButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private JPanel createBillDetail(String labelText, String valueText) {
        JPanel detailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        detailPanel.setBackground(Color.DARK_GRAY);
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(Color.WHITE);
        JLabel value = new JLabel(valueText);
        value.setFont(new Font("Arial", Font.PLAIN, 14));
        value.setForeground(Color.LIGHT_GRAY);

        detailPanel.add(label);
        detailPanel.add(value);
        return detailPanel;
    }
}
