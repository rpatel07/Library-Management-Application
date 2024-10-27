package com.example.finalproject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class UpdateBookWindow extends JDialog {
    private JTextField txtBookID;
    private JTextField txtTitle;
    private JTextField txtISBN;
    private JTextField txtAuthorID;
    private JTextField txtCategoryID;
    private JTextField txtAvailability;
    private Connection con;

    public UpdateBookWindow(JFrame parent, Connection con) {
        super(parent, "Update Book", true);
        this.con = con;
        setBounds(100, 100, 400, 300);
        getContentPane().setLayout(new GridLayout(7, 2));

        getContentPane().add(new JLabel("Book ID"));
        txtBookID = new JTextField();
        getContentPane().add(txtBookID);

        getContentPane().add(new JLabel("Title"));
        txtTitle = new JTextField();
        getContentPane().add(txtTitle);

        getContentPane().add(new JLabel("ISBN"));
        txtISBN = new JTextField();
        getContentPane().add(txtISBN);

        getContentPane().add(new JLabel("Author ID"));
        txtAuthorID = new JTextField();
        getContentPane().add(txtAuthorID);

        getContentPane().add(new JLabel("Category ID"));
        txtCategoryID = new JTextField();
        getContentPane().add(txtCategoryID);

        getContentPane().add(new JLabel("Availability"));
        txtAvailability = new JTextField();
        getContentPane().add(txtAvailability);

        JButton btnUpdate = new JButton("Update Book");
        getContentPane().add(btnUpdate);
        btnUpdate.addActionListener(e -> updateBook());

        JButton btnCancel = new JButton("Cancel");
        getContentPane().add(btnCancel);
        btnCancel.addActionListener(e -> dispose());
    }

    private void updateBook() {
        String bookID = txtBookID.getText();
        String title = txtTitle.getText();
        String isbn = txtISBN.getText();
        String authorID = txtAuthorID.getText();
        String categoryID = txtCategoryID.getText();
        String availability = txtAvailability.getText();

        try {
            PreparedStatement pst = con.prepareStatement(
                "UPDATE Books SET Title = ?, ISBN = ?, AuthorID = ?, CategoryID = ?, Availability = ? WHERE BookID = ?");
            pst.setString(1, title);
            pst.setString(2, isbn);
            pst.setInt(3, Integer.parseInt(authorID));
            pst.setInt(4, Integer.parseInt(categoryID));
            pst.setBoolean(5, Boolean.parseBoolean(availability));
            pst.setInt(6, Integer.parseInt(bookID));
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Book Updated!");
            dispose();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating book.");
        }
    }
}
