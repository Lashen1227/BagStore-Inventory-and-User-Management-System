package GUI;

import javax.swing.*;
import java.awt.*;

public class AddBagDialog extends JDialog {
    private JTextField idField;
    private JTextField nameField;
    private JTextField categoryField;
    private JTextField colourField;
    private JTextField priceField;
    private BagShop shop;
    private User user;

    public AddBagDialog(JFrame parent, BagShop shop, User user) {
        super(parent, "Add New Bag", true);
        this.shop = shop;
        this.user = user;
        initComponents();
    }

    private void initComponents() {
        setSize(400, 300);
        setLayout(new GridLayout(6, 2));

        JLabel idLabel = new JLabel("Bag ID:");
        idField = new JTextField();
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel categoryLabel = new JLabel("Category:");
        categoryField = new JTextField();
        JLabel colourLabel = new JLabel("Colour:");
        colourField = new JTextField();
        JLabel priceLabel = new JLabel("Price:");
        priceField = new JTextField();

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addBag());

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());

        add(idLabel);
        add(idField);
        add(nameLabel);
        add(nameField);
        add(categoryLabel);
        add(categoryField);
        add(colourLabel);
        add(colourField);
        add(priceLabel);
        add(priceField);
        add(addButton);
        add(cancelButton);
    }

    private void addBag() {
        try {
            String id = idField.getText();
            String name = nameField.getText();
            String category = categoryField.getText();
            String colour = colourField.getText();
            double price = Double.parseDouble(priceField.getText());

            if (shop.isBagIdUnique(id)) {
                Bag bag = new Bag(id, name, category, colour, price);
                user.addBag(shop, bag);
                JOptionPane.showMessageDialog(this, "Bag added successfully.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Bag ID already exists. Please enter a unique ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for the price.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

