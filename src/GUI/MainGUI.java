package GUI;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {
    private BagShop shop;

    public MainGUI() {
        shop = new BagShop();

        // Create a default manager account
        Manager defaultManager = new Manager("admin", "admin");
        shop.addUser(defaultManager);

        initComponents();
    }


    private void initComponents() {
        setTitle("Bag Shop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(3, 1));

        JButton cashierButton = new JButton("Login as Cashier");
        cashierButton.addActionListener(e -> loginAsCashier());
        add(cashierButton);

        JButton managerButton = new JButton("Login as Manager");
        managerButton.addActionListener(e -> loginAsManager());
        add(managerButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        add(exitButton);
    }

    private void loginAsCashier() {
        new LoginForm(shop).setVisible(true);
    }

    private void loginAsManager() {
        new LoginForm(shop).setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainGUI gui = new MainGUI();
            gui.setVisible(true);
        });
    }
}


/*
References:
- https://www.formdev.com/jformdesigner/
- https://youtu.be/vuQdLKq2LaY?si=zamV_yyWNeriWz5H  (Youtube Tutorial)
- https://docs.oracle.com/javase/tutorial/uiswing/components/index.html
- https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
- https://docs.oracle.com/javase/tutorial/uiswing/layout/grid.html
- https://www.geeksforgeeks.org/java-swing-simple-user-registration-form/
 */