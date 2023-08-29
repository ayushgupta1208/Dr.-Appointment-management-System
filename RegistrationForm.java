import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RegistrationForm {
    JFrame frame;
    JButton loginButton, signupButton;
    JLabel imageLabel, heading;

    public RegistrationForm() {
        JPanel p1 = new JPanel();
        // p1.setLayout(null);
        p1.setLayout(new BorderLayout());
        p1.setBackground(java.awt.Color.LIGHT_GRAY);

        frame = new JFrame("DOCTOR APPOINTMENT MAGAGEMENT SYSTEM");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 3

        heading = new JLabel(
                "              SAVE YOUR TIME, GET APPOINTENT ONLINE");
        heading.setLayout(null);
        heading.setFont(new Font("Algerian", 1, 35));
        heading.setForeground(java.awt.Color.yellow);
        // heading.setBounds(100, 250, 1000, 500);
        p1.add(heading, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        loginButton = new JButton("Login");
        signupButton = new JButton("Signup");
        buttonPanel.add(loginButton, BorderLayout.NORTH);
        buttonPanel.add(signupButton, BorderLayout.CENTER);

        JPanel imagePanel = new JPanel();
        // imagePanel.setLayout(null);
        ImageIcon doctorSign = new ImageIcon("F:\\doctor5.png");
        // imagePanel.setBounds(10, 200, 100, 50);
        imageLabel = new JLabel(doctorSign);
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        frame.setBackground(java.awt.Color.orange);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(imagePanel, BorderLayout.CENTER);
        frame.add(p1, BorderLayout.NORTH);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog(frame, "Enter username:");
                String pass = JOptionPane.showInputDialog(frame, "Enter password:");

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/ayush";
                    String dbUsername = "root";
                    String dbPassword = "ayush@8953";
                    Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                    String loginQuery = "SELECT * FROM users WHERE username = ? AND password = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(loginQuery);
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, pass);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        JOptionPane.showMessageDialog(frame, "Login successful!");
                        Schedule();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Login failed. Invalid credentials.");
                        // schedule();
                    }

                    resultSet.close();
                    preparedStatement.close();
                    connection.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog(frame, "Enter username:");
                String pass = JOptionPane.showInputDialog(frame, "Enter password:");
                String email = JOptionPane.showInputDialog(frame, "Enter email:");
                // String fullName = JOptionPane.showInputDialog(frame, "Enter full name:");

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/ayush";
                    String dbUsername = "root";
                    String dbPassword = "ayush@8953";
                    Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                    Statement statement = connection.createStatement();
                    // statement.executeUpdate("CREATE DATABASE IF NOT EXISTS ayush");
                    // statement.executeUpdate("USE ayush");
                    // statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (id INT
                    // AUTO_INCREMENT PRIMARY KEY, " +
                    // "username VARCHAR(50), password VARCHAR(50), email VARCHAR(100), full_name
                    // VARCHAR(100))");
                    String insertQuery = "INSERT INTO users (username, password, email) VALUES " +
                            "('" + username + "', '" + pass + "', '" + email + "')";
                    statement.executeUpdate(insertQuery);
                    statement.close();
                    connection.close();
                    {
                        JOptionPane.showMessageDialog(frame, "User registered successfully!");
                        Schedule();
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    // Use Hometest() class
    public static void Schedule() {

        new schedule().setVisible(true);

    }

    public void show() {
        frame.setVisible(true);
        // frame.setSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        frame.setSize(950, 550);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                RegistrationForm registrationForm = new RegistrationForm();
                registrationForm.show();

            }
        });

    }
}
