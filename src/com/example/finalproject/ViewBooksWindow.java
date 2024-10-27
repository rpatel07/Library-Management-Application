package com.example.finalproject;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

public class ViewBooksWindow extends JDialog {
    private JTable table;
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    public ViewBooksWindow(JFrame parent, Connection con) {
        super(parent, "View Books", true);
        this.con = con;
        setBounds(100, 100, 600, 400);
        getContentPane().setLayout(new BorderLayout());

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        loadTableData();
    }

    private void loadTableData() {
        try {
            pst = con.prepareStatement("SELECT * FROM Books");
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
