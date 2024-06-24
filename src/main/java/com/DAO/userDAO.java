/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import java.sql.*;
import com.DB.DBConnect;
import com.Models.Client;

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
    
    public boolean clientRegister(Client cli)
    {
        boolean f = false;
           
        try {
            String sqlQuery = "INSERT INTO CLIENT(noms, age, sexe, email) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, cli.getNoms());
            ps.setString(2, cli.getAge());
            ps.setString(3, cli.getSexe());
            ps.setString(4, cli.getEmail());
            
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
