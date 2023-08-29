import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseViewerApp extends JFrame {
    private JButton viewButton;
    private JTextArea outputTextArea;
    private JLabel jLabel1;
    static String e;

    public DatabaseViewerApp(String i) {

        DatabaseViewerApp.e = i;
        jLabel1 = new JLabel();
        jLabel1.setLayout(null);
        jLabel1.setBounds(0, 10, 1000, 50);
        jLabel1.setFont(new java.awt.Font("Showcard Gothic", 3, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setBackground(new java.awt.Color(204, 025, 255));
        jLabel1.setText(" _________THANK YOU FOR VISITING US_________");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        setTitle("Database Viewer");
        setSize(950, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new java.awt.Color(255, 255, 51));
        mainPanel.setLayout(null);

        viewButton = new JButton("View Appointment");
        viewButton.setBounds(360, 350, 175, 40);
        viewButton.setFont(new Font("arabibblack", 1, 15));

        outputTextArea = new JTextArea();
        outputTextArea.setLayout(null);
        outputTextArea.setBounds(300, 100, 300, 180);
        outputTextArea.setEditable(false);
        mainPanel.add(Box.createRigidArea(new Dimension(5, 1)));
        mainPanel.add(new JScrollPane(outputTextArea));
        mainPanel.add(outputTextArea);

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(jLabel1);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(viewButton);

        viewButton.addActionListener(new ActionListener() {
            // @Override
            public void actionPerformed(ActionEvent e) {
                viewDataFromDatabase();
            }

        });

        add(mainPanel);
    }

    public void viewDataFromDatabase() {
        // Update the following information with your database details
        String jdbcUrl = "jdbc:mysql://localhost:3306/ayush";
        String username = "root";
        String password = "ayush@8953";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM users where email='" + DatabaseViewerApp.e + "' ");

            StringBuilder data = new StringBuilder();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("username");
                String pass = resultSet.getString("password");
                String em = resultSet.getString("email");
                String contact = resultSet.getString("contact");
                String sch = resultSet.getString("schedule");
                String d = resultSet.getString("Doctors");
                // System.out.println("theek chal raha");

                // Add more columns as needed
                data.append("\n").append("\n").append(" \tPatient ID:   ").append(id).append("\n")
                        .append("  \tName:         ").append(name).append("\n")
                        .append("  \tPassword:  ").append(pass).append("\n")
                        .append("   \tEmail ID:     ").append(em).append("\n")
                        .append("   \tContact:      ").append(contact).append("\n")
                        .append("   \tSchedule:   ").append(sch).append("\n")
                        .append("  \tDoctors:      ").append(d).append("\n")
                        .append("\n");
            }

            outputTextArea.setText(data.toString());

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ee) {
            ee.printStackTrace();
            outputTextArea.setText("Error fetching data from the database.");
        }
    }

    public static void main(String[] args) {
        // SwingUtilities.invokeLater(() -> {
        // DatabaseViewerApp app = new DatabaseViewerApp("ayush@gmail.com");
        // app.setVisible(true);

        // });
    }
}
