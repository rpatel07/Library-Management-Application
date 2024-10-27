package com.example.finalproject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class MainWindow extends JFrame {
    private Connection con;

    public MainWindow() {
        setTitle("Library System");
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new FlowLayout());

        JButton btnAdd = new JButton("Add Book");
        JButton btnView = new JButton("View Books");
        JButton btnBorrow = new JButton("Borrow Book");
        JButton btnReturn = new JButton("Return Book");
        JButton btnUpdate = new JButton("Update Book");
        JButton btnDelete = new JButton("Delete Book");

        btnAdd.addActionListener(e -> new AddBookWindow(this, con).setVisible(true));
        btnView.addActionListener(e -> new ViewBooksWindow(this, con).setVisible(true));
        btnBorrow.addActionListener(e -> new BorrowBookWindow(this, con).setVisible(true));
        btnReturn.addActionListener(e -> new ReturnBookWindow(this, con).setVisible(true));
        btnUpdate.addActionListener(e -> new UpdateBookWindow(this, con).setVisible(true));
        btnDelete.addActionListener(e -> new DeleteBookWindow(this, con).setVisible(true));

        getContentPane().add(btnAdd);
        getContentPane().add(btnView);
        getContentPane().add(btnBorrow);
        getContentPane().add(btnReturn);
        getContentPane().add(btnUpdate);
        getContentPane().add(btnDelete);

        connectDatabase();
    }

    private void connectDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/assignment", "root", "1234");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MainWindow frame = new MainWindow();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
