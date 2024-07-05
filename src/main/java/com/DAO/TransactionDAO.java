/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.DB.DBConnect;
import com.Models.EnvoyerRetrait;
import com.Models.Recette;
import com.Models.Transaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author macbookpro
 */
public class TransactionDAO {

    private Connection conn;

    public TransactionDAO() {
        super();
        this.conn = DBConnect.getConn();
    }

    public List<Transaction> getTransactionsForMonthsDebitsCredit(String numero) {
        List<Transaction> debits = new ArrayList<>();
        String sql = "SELECT * FROM `TRANSACTION` WHERE ((numRecepteur IS NULL AND numEnvoyeur= ? )OR numEnvoyeur = ? OR numRecepteur = ?) AND date >= DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH) ORDER BY date;";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, numero);
            pst.setString(2, numero);
            pst.setString(3, numero);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(rs.getInt("id"));
                transaction.setNumEnvoyeur(rs.getString("numEnvoyeur"));
                transaction.setNumRecepteur(rs.getString("numRecepteur"));
                transaction.setMontant(rs.getDouble("montant"));
                transaction.setRaison(rs.getString("raison"));
                transaction.setDate(rs.getDate("date"));
                debits.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return debits;
    }

    public boolean ajouterLeMontantRecette(Recette recette) {
        boolean f = false;
        try {
            String sqlUpdateRecepteur = "INSERT INTO `RECETTE`(`montant`, `date`) VALUES (? , CURRENT_TIMESTAMP)";
            PreparedStatement pstRecepteur = conn.prepareStatement(sqlUpdateRecepteur);
            pstRecepteur.setDouble(1, recette.getMontant());
            int res = pstRecepteur.executeUpdate();
            if (res > 0) {
                f = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;

    }

    public double allRecette() {
        double f = 0;
        try {
            String sqlUpdateRecepteur = "SELECT sum(montant) as somme FROM `RECETTE`; ";
            PreparedStatement pstRecepteur = conn.prepareStatement(sqlUpdateRecepteur);
            ResultSet rs = pstRecepteur.executeQuery();
            while (rs.next()) {
                f = rs.getDouble("somme");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }

    public int allClient() {
        int f = 0;
        try {
            String sqlUpdateRecepteur = "SELECT COUNT(*) as nb FROM `COMPTE` ";
            PreparedStatement pstRecepteur = conn.prepareStatement(sqlUpdateRecepteur);
            ResultSet rs = pstRecepteur.executeQuery();
            while (rs.next()) {
                f = rs.getInt("nb");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }

    public int allTrans() {
        int f = 0;
        try {
            String sqlUpdateRecepteur = "SELECT COUNT(*) as nb FROM `TRANSACTION` ";
            PreparedStatement pstRecepteur = conn.prepareStatement(sqlUpdateRecepteur);
            ResultSet rs = pstRecepteur.executeQuery();
            while (rs.next()) {
                f = rs.getInt("nb");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }

}
