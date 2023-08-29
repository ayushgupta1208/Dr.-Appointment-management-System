import javax.swing.*;
// import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class schedule extends JFrame implements ActionListener {
    JTextField tid, tname, tcontact, temail;
    JComboBox cmb, cmb1;
    JButton bSubmit;
    private Component imageLabel;

    // Design Method
    public void design() {
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(java.awt.Color.lightGray);

        JLabel lheading = new JLabel("-----BOOK YOUR APPOINTMENTS----");
        lheading.setFont(new Font("Franklin Gothic Heavy", 1, 25));
        lheading.setBounds(10, 20, 500, 90);
        lheading.setForeground(new java.awt.Color(255, 255, 0));
        p1.add(lheading);

        JLabel lid = new JLabel("ID Number");
        JLabel lname = new JLabel("Your Name");
        JLabel lcontact = new JLabel("Contact No.");
        JLabel lschedule = new JLabel("Schedule");
        JLabel ldoctor = new JLabel("Doctors");
        JLabel lemail = new JLabel("Email ID");

        // --------Set label/button Font-------
        lid.setFont(new Font("Arial Rounded MT Bold", 1, 18));
        lname.setFont(new Font("Arial Rounded MT Bold", 1, 18));
        lcontact.setFont(new Font("Arial Rounded MT Bold", 1, 18));
        lschedule.setFont(new Font("Arial Rounded MT Bold", 1, 18));
        ldoctor.setFont(new Font("Arial Rounded MT Bold", 1, 18));
        lemail.setFont(new Font("Arial Rounded MT Bold", 1, 18));

        bSubmit = new JButton("Submit");
        bSubmit.setFont(new Font("Arial Rounded MT Bold", 1, 22));
        bSubmit.addActionListener(this);

        lid.setBounds(50, 100, 200, 25);
        lname.setBounds(50, 160, 200, 25);
        lcontact.setBounds(50, 220, 200, 25);
        lschedule.setBounds(50, 280, 200, 25);
        ldoctor.setBounds(50, 340, 200, 25);
        lemail.setBounds(50, 400, 200, 25);

        cmb = new JComboBox();
        cmb.setFont(new Font("ArialBlack", 3, 18));
        cmb.setToolTipText("Please Select Your Slot");
        cmb.addItem("--Select Your Schedule-");
        cmb.addItem("10:00 AM (Today)");
        cmb.addItem("10:30 AM (Today)");
        cmb.addItem("11:00 AM (Booked)");
        cmb.addItem("11:30 AM (Tomorrow)");
        cmb.addItem("12:00 PM (Today)");
        cmb.addItem("5:00 PM (Today)");
        cmb.addItem("5:30 PM (Booked)");
        cmb.addItem("6:00 PM (Booked)");
        cmb.addItem("6:30 PM (Tomorrow)");
        cmb.addItem("7:00 PM (Today)");

        cmb1 = new JComboBox();
        cmb1.setFont(new Font("ArialBlack", 3, 18));
        cmb1.setToolTipText("Please Select Your Doctor");
        cmb1.addItem("-Select Your Doctor--");
        cmb1.addItem("Dr. A. K. Gupta");
        cmb1.addItem("Dr. Ashish pandey");
        cmb1.addItem("Dr. Aryan Tiwari");
        cmb1.addItem("Dr. Ayush Tiwari");
        cmb1.addItem("Dr. Aryan Dixit");
        cmb1.addItem("Dr. Sharad");
        cmb1.addItem("Dr. k.k. Tiwari");
        cmb1.addItem("Dr. P.S. Tiwari");
        cmb1.addItem("Dr. H.C. Dixit");
        cmb1.addItem("Dr. Riya ");

        tid = new JTextField();
        tid.setToolTipText("Please Enter Your Valid ID");
        tname = new JTextField();
        tname.setToolTipText("Please Enter Your Name");
        tcontact = new JTextField();
        tcontact.setToolTipText("Please Enter Your Register Mobile No.");
        temail = new JTextField();
        temail.setToolTipText("Please Enter Your Email ID");

        tid.setBounds(170, 100, 240, 25);
        tname.setBounds(170, 160, 240, 25);
        tcontact.setBounds(170, 220, 240, 25);
        cmb.setBounds(170, 280, 240, 25);
        cmb1.setBounds(170, 340, 240, 25);
        temail.setBounds(170, 400, 240, 25);

        bSubmit.setBounds(200, 450, 150, 30);
        bSubmit.setBackground(new java.awt.Color(255, 255, 255));

        p1.add(lid);
        p1.add(lname);
        p1.add(lcontact);
        p1.add(lschedule);
        p1.add(ldoctor);
        p1.add(lemail);
        p1.add(bSubmit);
        p1.add(tid);
        p1.add(tname);
        p1.add(tcontact);
        p1.add(cmb);
        p1.add(cmb1);
        p1.add(temail);

        ImageIcon doctorSign = new ImageIcon("F:\\doctor3.png");
        imageLabel = new JLabel(doctorSign);
        add(imageLabel, BorderLayout.EAST);

        add(p1, BorderLayout.CENTER);
    }

    // 3rd class constructor call
    public void view() {
        String s = temail.getText();
        System.out.println(s);
        new DatabaseViewerApp(s).setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bSubmit) {
            int id = Integer.parseInt(tid.getText());
            String contact = tcontact.getText();
            String schedule = cmb.getSelectedItem().toString();
            String doctor = cmb1.getSelectedItem().toString();
            String email = temail.getText();

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ayush", "root", "ayush@8953");
                String sql = "UPDATE users SET id=?, contact=? ,schedule=?, Doctors=? WHERE email = ?";
                PreparedStatement pstmt = con.prepareStatement(sql);

                pstmt.setInt(1, id); // Set the ID value
                pstmt.setString(2, contact); // Set the contact value
                pstmt.setString(3, schedule); // Set the schedule value
                pstmt.setString(4, doctor);
                pstmt.setString(5, email);// Set the email value
                // pstmt.executeUpdate();

                // pstmt.setString(4, email);
                // System.out.println("hello");

                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    // System.out.println("Record inserted successfully.");
                    JOptionPane.showMessageDialog(this, "Details Saved successfully");
                    new DatabaseViewerApp(email).setVisible(true);
                } else {
                    // System.out.println(" Invalid credentials");
                    JOptionPane.showMessageDialog(this, "Failed: Invalid credentials");
                }
                // new DatabaseViewerApp(email).setVisible(true);
                pstmt.close();
                con.close();

            } catch (Exception ee) {
                System.out.println("Problem: " + ee);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error !!");
        }
    }

    public schedule() {
        // connect();
        setSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(950, 600);
        design();
        setTitle("Appointment Management System"); // Adding the title
        // view();
        // String s = temail.getText();
        // new DatabaseViewerApp(s);
    }

    public static void main(String[] args) {
        // schedule obj = new schedule();
        // obj.setSize(1000, 600);
        // obj.setVisible(true);
        // obj.setDefaultCloseOperation(3);
    }
}
