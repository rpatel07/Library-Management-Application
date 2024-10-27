package com.example.finalproject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class BorrowBookWindow extends JDialog {
    private JTextField txtBookID, txtStudentID;
    private Connection con;
    private PreparedStatement pst;

    public BorrowBookWindow(JFrame parent, Connection con) {
        super(parent, "Borrow Book", true);
        this.con = con;
        setBounds(100, 100, 300, 200);
        getContentPane().setLayout(new GridLayout(3, 2));

        getContentPane().add(new JLabel("Book ID:"));
        txtBookID = new JTextField();
        getContentPane().add(txtBookID);

        getContentPane().add(new JLabel("Student ID:"));
        txtStudentID = new JTextField();
        getContentPane().add(txtStudentID);

        JButton btnBorrow = new JButton("Borrow Book");
        btnBorrow.addActionListener(e -> borrowBook());
        getContentPane().add(btnBorrow);
    }

    private void borrowBook() {
        int bookID = Integer.parseInt(txtBookID.getText());
        int studentID = Integer.parseInt(txtStudentID.getText());

        try {
            pst = con.prepareStatement("UPDATE Books SET Availability = false WHERE BookID = ?");
            pst.setInt(1, bookID);
            pst.executeUpdate();
            pst = con.prepareStatement("INSERT INTO BorrowedBooks (BookID, StudentID) VALUES (?, ?)");
            pst.setInt(1, bookID);
            pst.setInt(2, studentID);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Book Borrowed!");
            dispose();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error borrowing book.");
        }
    }
}
