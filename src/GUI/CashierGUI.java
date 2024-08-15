package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CashierGUI extends JFrame {
    public BagShop shop;
    private User user;

    public CashierGUI(BagShop shop, User user) {
        this.shop = shop;
        this.user = user;
        initComponents();
    }

    private void initComponents() {
        setTitle("Cashier Interface");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1));
        add(panel, BorderLayout.CENTER);

        JButton viewBagsButton = new JButton("View Bags");
        viewBagsButton.addActionListener(e -> viewBags());
        panel.add(viewBagsButton);

        JButton addBagButton = new JButton("Add Bag");
        addBagButton.addActionListener(e -> new AddBagDialog(this, shop, user).setVisible(true));
        panel.add(addBagButton);

        JButton deleteBagButton = new JButton("Delete Bag by ID");
        deleteBagButton.addActionListener(e -> deleteBagById());
        panel.add(deleteBagButton);

        JButton searchBagButton = new JButton("Search Bags by Category");
        searchBagButton.addActionListener(e -> searchBagsByCategory());
        panel.add(searchBagButton);

    }

    private void viewBags() {
        List<Bag> bags = shop.getBags();
        if (bags.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No bags added yet.");
        } else {
            StringBuilder bagList = new StringBuilder("Bags:\n");
            for (Bag bag : bags) {
                bagList.append(bag).append("\n");
            }
            JOptionPane.showMessageDialog(this, bagList.toString());
        }
    }

    private void deleteBagById() {
        String id = JOptionPane.showInputDialog(this, "Enter Bag ID to delete:");
        if (id != null && !id.trim().isEmpty()) {
            user.deleteBagById(shop, id);
        }
    }

    private void searchBagsByCategory() {
        String category = JOptionPane.showInputDialog(this, "Enter Bag Category to search:");
        if (category != null && !category.trim().isEmpty()) {
            user.searchBagsByCategory(shop, category);
            JOptionPane.showMessageDialog(this, "No bags found.");
        }
    }
}

