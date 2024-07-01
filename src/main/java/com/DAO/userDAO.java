/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import java.sql.*;
import com.DB.DBConnect;
import com.Models.Client;
import com.Models.Compte;

/**
 *
 * @author macbookpro
 */
public class userDAO {

    private Connection conn;

    public userDAO() {
        super();
        this.conn = DBConnect.getConn();

    }

    public int clientRegister(Client cli) {
        try {
            String sqlQuery = "INSERT INTO CLIENT( noms , age , sexe , email ) VALUES ( ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, cli.getNoms());
            ps.setString(2, cli.getAge());
            ps.setString(3, cli.getSexe());
            ps.setString(4, cli.getEmail());

            int res = ps.executeUpdate();

            if (res == 1) {
                String sqlQ = "SELECT * FROM CLIENT WHERE email = ?; ";
                PreparedStatement pstt = conn.prepareStatement(sqlQ);
                pstt.setString(1, cli.getEmail());
                ResultSet resultSet = pstt.executeQuery();
                if (resultSet.next()) {
                    // Process the result if needed (you can retrieve data here)
                    int id_client = resultSet.getInt("id");
                    return id_client;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean creatCompteClient(Compte cmpt) {
        boolean f = false;
        try {
            String sqlQuery = "INSERT INTO COMPTE ( numero, motdepasse, isActive, solde, id_client) VALUES ( ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, cmpt.getNumero());
            ps.setString(2, cmpt.getMotdepasse());
            ps.setBoolean(3, cmpt.isIsActive());        
            ps.setDouble(4, cmpt.getSolde());
            ps.setInt(5, cmpt.getId_client());

            int res = ps.executeUpdate();
            if (res == 1) {
                f = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;

    }

}
