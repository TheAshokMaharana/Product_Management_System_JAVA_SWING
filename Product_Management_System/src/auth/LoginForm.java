// auth/LoginForm.java
package auth;

import db.DBConnection;
import dashboard.Dashboard;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginForm extends JFrame {
    public LoginForm() {
        setTitle("Login");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(230, 240, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel title = new JLabel("Login to Product System");
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

        JButton loginBtn = new JButton("Login");
        JButton registerBtn = new JButton("Register");

        loginBtn.setBackground(new Color(34, 139, 34));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.addActionListener(e -> {
            try (Connection con = DBConnection.getConnection()) {
                String sql = "SELECT * FROM user WHERE username=? AND password=?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, userField.getText());
                pst.setString(2, new String(passField.getPassword()));
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Login successful!");
                    dispose();
                    new Dashboard().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid credentials!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        registerBtn.setBackground(Color.GRAY);
        registerBtn.setForeground(Color.WHITE);
        registerBtn.addActionListener(e -> {
            dispose();
            new RegisterForm().setVisible(true);
        });

        gbc.gridy = 3; gbc.gridx = 0;
        panel.add(loginBtn, gbc);
        gbc.gridx = 1;
        panel.add(registerBtn, gbc);

        add(panel);
    }
}
