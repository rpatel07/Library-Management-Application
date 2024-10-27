package com.example.finalproject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class AddBookWindow extends JDialog {
    private JTextField txtTitle, txtISBN, txtAuthorID, txtCategoryID, txtAvailability;
    private Connection con;
    private PreparedStatement pst;

    public AddBookWindow(JFrame parent, Connection con) {
        super(parent, "Add Book", true);
        this.con = con;
        setBounds(100, 100, 400, 300);
        getContentPane().setLayout(new GridLayout(6, 2));

        getContentPane().add(new JLabel("Title:"));
        txtTitle = new JTextField();
        getContentPane().add(txtTitle);

        getContentPane().add(new JLabel("ISBN:"));
        txtISBN = new JTextField();
        getContentPane().add(txtISBN);

        getContentPane().add(new JLabel("Author ID:"));
        txtAuthorID = new JTextField();
        getContentPane().add(txtAuthorID);

        getContentPane().add(new JLabel("Category ID:"));
        txtCategoryID = new JTextField();
        getContentPane().add(txtCategoryID);

        getContentPane().add(new JLabel("Availability:"));
        txtAvailability = new JTextField();
        getContentPane().add(txtAvailability);

        JButton btnAdd = new JButton("Add Book");
        btnAdd.addActionListener(e -> addBook());
        getContentPane().add(btnAdd);
    }

    private void addBook() {
        String title = txtTitle.getText();
        String isbn = txtISBN.getText();
        int authorID = Integer.parseInt(txtAuthorID.getText());
        int categoryID = Integer.parseInt(txtCategoryID.getText());
        boolean availability = Boolean.parseBoolean(txtAvailability.getText());

        try {
            pst = con.prepareStatement("INSERT INTO Books (Title, ISBN, AuthorID, CategoryID, Availability) VALUES (?, ?, ?, ?, ?)");
            pst.setString(1, title);
            pst.setString(2, isbn);
            pst.setInt(3, authorID);
            pst.setInt(4, categoryID);
            pst.setBoolean(5, availability);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Book Added!");
            dispose();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding book.");
        }
    }
}
