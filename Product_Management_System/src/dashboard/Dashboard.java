package dashboard;

import product.ProductTable;
import auth.LoginForm;

import javax.swing.*;

public class Dashboard extends JFrame {
    public Dashboard() {
        setTitle("Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ProductTable pt = new ProductTable();
        JButton logoutBtn = new JButton("Logout");

        logoutBtn.addActionListener(e -> {
            new LoginForm().setVisible(true);
            this.dispose();
        });

        add(pt);
        add(logoutBtn, "South");

        setLocationRelativeTo(null);
    }
}
