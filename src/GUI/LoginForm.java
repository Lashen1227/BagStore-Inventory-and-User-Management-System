package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton closeButton;
    private BagShop shop;


    public LoginForm(BagShop shop) {
        this.shop = shop;
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                User user = shop.authenticateUser(username, password);

                if (user != null) {
                    JOptionPane.showMessageDialog(LoginForm.this, "Welcome, " + user.getUsername() + "!");
                    if (user.getRole().equalsIgnoreCase("Cashier")) {
                        new CashierGUI(shop, user).setVisible(true);
                    } else if (user.getRole().equalsIgnoreCase("Manager")) {
                        new ManagerGUI(shop, (Manager) user).setVisible(true);
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginForm.this, "Invalid credentials!", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(100, 20, 165, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 165, 25);
        panel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 255, 25);
        panel.add(loginButton);

        closeButton = new JButton("Close");
        closeButton.setBounds(10, 110, 255, 25);
        panel.add(closeButton);
    }
}
