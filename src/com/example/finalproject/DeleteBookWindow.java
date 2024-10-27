package com.example.finalproject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class DeleteBookWindow extends JDialog {
    private JTextField txtBookID;
    private Connection con;

    public DeleteBookWindow(JFrame parent, Connection con) {
        super(parent, "Delete Book", true);
        this.con = con;
        setBounds(100, 100, 300, 150);
        getContentPane().setLayout(new GridLayout(2, 2));

        getContentPane().add(new JLabel("Book ID"));
        txtBookID = new JTextField();
        getContentPane().add(txtBookID);

        JButton btnDelete = new JButton("Delete Book");
        getContentPane().add(btnDelete);
        btnDelete.addActionListener(e -> deleteBook());

        JButton btnCancel = new JButton("Cancel");
        getContentPane().add(btnCancel);
        btnCancel.addActionListener(e -> dispose());
    }

    private void deleteBook() {
        String bookID = txtBookID.getText();

        try {
            PreparedStatement pst = con.prepareStatement("DELETE FROM Books WHERE BookID = ?");
            pst.setInt(1, Integer.parseInt(bookID));
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Book Deleted!");
            dispose();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting book.");
        }
    }
}
