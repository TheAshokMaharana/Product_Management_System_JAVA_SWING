
// auth/RegisterForm.java
package auth;

import db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class RegisterForm extends JFrame {
    public RegisterForm() {
        setTitle("Register");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 250, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel title = new JLabel("Create New Account");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(title, gbc);

        gbc.gridwidth = 1;
        JLabel userLabel = new JLabel("Username:");
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(userLabel, gbc);
        JTextField userField = new JTextField(15);
        gbc.gridx = 1;
        panel.add(userField, gbc);

        JLabel passLabel = new JLabel("Password:");
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(passLabel, gbc);
        JPasswordField passField = new JPasswordField(15);
        gbc.gridx = 1;
        panel.add(passField, gbc);

        JButton registerBtn = new JButton("Register");
        registerBtn.setBackground(new Color(70, 130, 180));
        registerBtn.setForeground(Color.WHITE);

        registerBtn.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());

            try (Connection con = DBConnection.getConnection()) {
                PreparedStatement checkUser = con.prepareStatement("SELECT * FROM user WHERE username = ?");
                checkUser.setString(1, username);
                ResultSet rs = checkUser.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Username already exists!");
                    return;
                }

                PreparedStatement pst = con.prepareStatement("INSERT INTO user (username, password) VALUES (?, ?)");
                pst.setString(1, username);
                pst.setString(2, password);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(this, "Registration successful!");
                dispose();
                new LoginForm().setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        gbc.gridy = 3; gbc.gridx = 1;
        panel.add(registerBtn, gbc);

        add(panel);
    }
}
