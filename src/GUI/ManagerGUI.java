package GUI;

import javax.swing.*;
import java.util.List;

public class ManagerGUI extends CashierGUI {
    private Manager manager;

    public ManagerGUI(BagShop shop, Manager manager) {
        super(shop, manager);
        this.manager = manager;
        initComponents();
    }

    private void initComponents() {
        setTitle("Manager Interface");
        JPanel panel = (JPanel) getContentPane().getComponent(0);
        JButton viewCashiersButton = new JButton("View Cashiers");
        viewCashiersButton.addActionListener(e -> viewCashiers());
        panel.add(viewCashiersButton);

        JButton createUserButton = new JButton("Create Cashier Account");
        createUserButton.addActionListener(e -> createUser());
        panel.add(createUserButton);
    }

    private void viewCashiers() {
        List<User> cashiers = manager.viewCashiers(shop);
        if (cashiers.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No cashiers found.");
        } else {
            StringBuilder cashierList = new StringBuilder("Cashiers:\n");
            for (User cashier : cashiers) {
                cashierList.append("Username: ").append(cashier.getUsername()).append(", Password: ").append(cashier.getPassword()).append("\n");
            }
            JOptionPane.showMessageDialog(this, cashierList.toString());
        }
    }

    private void createUser() {
        String username = JOptionPane.showInputDialog(this, "Enter Cashier Username:");
        String password = JOptionPane.showInputDialog(this, "Enter Cashier Password:");
        if (username != null && password != null && !username.trim().isEmpty() && !password.trim().isEmpty()) {
            manager.createUser(shop, username, password);
            JOptionPane.showMessageDialog(this, "Cashier account created successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid credentials.");
        }
    }
}

