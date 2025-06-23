package product;

import db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ProductForm extends JFrame {
    JTextField nameField, priceField, qtyField;
    Integer productId;
    ProductTable productTable;

    public ProductForm(Integer productId, ProductTable tableRef) {
        this.productId = productId;
        this.productTable = tableRef;

        setTitle(productId == null ? "Add Product" : "Update Product");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(250, 250, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel(productId == null ? "Add New Product" : "Update Product");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        nameField = new JTextField(20);
        priceField = new JTextField(20);
        qtyField = new JTextField(20);

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Price:"), gbc);
        gbc.gridx = 1;
        panel.add(priceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Quantity:"), gbc);
        gbc.gridx = 1;
        panel.add(qtyField, gbc);

        JButton saveBtn = new JButton("Save");
        saveBtn.setBackground(new Color(34, 139, 34));
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(saveBtn, gbc);

        saveBtn.addActionListener(e -> saveProduct());

        if (productId != null) loadProductData();

        add(panel);
    }

    void loadProductData() {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM products WHERE id=?");
            pst.setInt(1, productId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                nameField.setText(rs.getString("name"));
                priceField.setText(String.valueOf(rs.getDouble("price")));
                qtyField.setText(String.valueOf(rs.getInt("quantity")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void saveProduct() {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement pst;
            if (productId == null) {
                pst = con.prepareStatement("INSERT INTO products(name, price, quantity) VALUES (?, ?, ?)");
            } else {
                pst = con.prepareStatement("UPDATE products SET name=?, price=?, quantity=? WHERE id=?");
            }
            pst.setString(1, nameField.getText());
            pst.setDouble(2, Double.parseDouble(priceField.getText()));
            pst.setInt(3, Integer.parseInt(qtyField.getText()));
            if (productId != null) pst.setInt(4, productId);
            pst.executeUpdate();

            productTable.loadProducts();
            dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
