# 🛍️ Product Management System (Java Swing + MySQL)

A simple and user-friendly **Product Management System** built using **Java Swing** and **MySQL**. All major functionalities — login, registration, product table, and CRUD actions — are handled within a **single dashboard panel** for a smooth desktop experience.

---

## 🔧 Features

- 👤 **User Authentication**
  - Login & Registration
  - Simple password-protected access

- 📋 **Product Dashboard**
  - View all products in a table
  - Add new products
  - Update existing products
  - Delete products with confirmation
  - Real-time table refresh

- 🔐 Logout functionality

- 🖥️ Clean and modern **UI design** using Java Swing

---

## 🗂️ Project Structure

src/
├── db/
│ └── DBConnection.java # Handles DB connection
├── user/
│ ├── LoginForm.java # Login window
│ ├── RegisterForm.java # Registration window
├── product/
│ ├── Dashboard.java # Main application window
│ ├── ProductTable.java # Displays product data in a table
│ ├── ProductForm.java # Add / Update product dialog



Always show details


---

## 💻 How It Works (One-Panel Flow)

1. **User logs in** via `LoginForm.java`.
2. If user has no account, they click **Register** to open `RegisterForm.java`.
3. After login, the **Dashboard** window opens.
4. The `ProductTable` (center panel) loads all products from MySQL.
5. Three action buttons appear below the table:
   - ✅ **Add** — opens a form to add a new product
   - ✏️ **Update** — opens a pre-filled form for the selected product
   - ❌ **Delete** — deletes the selected product after confirmation
6. Changes immediately reflect in the product table.
7. User can click **Logout** to return to login screen.

---

## 🛢️ MySQL Database Setup

sql
CREATE DATABASE product_management;

USE product_management;

CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    price DOUBLE,
    quantity INT
);

Opti


---

## ▶️ Running the App

1. **Clone this repository** or download the ZIP.
2. **Open the project** in **Eclipse IDE**.
3. **Add the MySQL JDBC driver (Connector/J)** to your build path:
   - Right-click the project > Build Path > Configure Build Path > Libraries > Add External JARs
   - Select the MySQL Connector/J `.jar` file
4. **Set up your MySQL database** as described above.
5. **Run `LoginForm.java`** to launch the application.


