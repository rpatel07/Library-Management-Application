package com.example.finalproject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class ReturnBookWindow extends JDialog {
    private JTextField txtBookID;
    private Connection con;
    private PreparedStatement pst;

    public ReturnBookWindow(JFrame parent, Connection con) {
        super(parent, "Return Book", true);
        this.con = con;
        setBounds(100, 100, 300, 200);
        getContentPane().setLayout(new GridLayout(2, 2));

        getContentPane().add(new JLabel("Book ID:"));
        txtBookID = new JTextField();
        getContentPane().add(txtBookID);

        JButton btnReturn = new JButton("Return Book");
        btnReturn.addActionListener(e -> returnBook());
        getContentPane().add(btnReturn);
    }

    private void returnBook() {
        int bookID = Integer.parseInt(txtBookID.getText());

        try {
            pst = con.prepareStatement("UPDATE Books SET Availability = true WHERE BookID = ?");
            pst.setInt(1, bookID);
            pst.executeUpdate();
            pst = con.prepareStatement("DELETE FROM BorrowedBooks WHERE BookID = ?");
            pst.setInt(1, bookID);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Book Returned!");
            dispose();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error returning book.");
        }
    }
}
