/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.DB.DBConnect;
import com.Models.Envoyer;
import com.Models.EnvoyerTauxTrans;
import com.Models.Retrait;
import com.Models.RetraitTauxTrans;
import com.Models.Tauxenv;
import com.Models.Tauxrecp;
import com.Models.Transaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author macbookpro
 */
public class TransactionRetrait {

    private Connection conn;

    public TransactionRetrait() {
        super();
        this.conn = DBConnect.getConn();
    }

    public List<RetraitTauxTrans> getAllTransactionRetrait() {
        List<RetraitTauxTrans> listeRetrait = new ArrayList<>();
        try {
            String sqlQuery = "SELECT * FROM RETRAIT En JOIN TRANSACTION Tr ON Tr.id = En.id LEFT JOIN TAUXRET TEn ON TEn.id = En.idtauxrec";
            PreparedStatement pstt = conn.prepareStatement(sqlQuery);
            ResultSet res = pstt.executeQuery();

            while (res.next()) {
                int Id = res.getInt("id");

                Transaction trans = new Transaction();
                trans.setId(Id);
                trans.setNumEnvoyeur(res.getString("numEnvoyeur"));
                trans.setNumRecepteur(res.getString("numRecepteur"));
                trans.setMontant(res.getDouble("montant"));
                trans.setRaison(res.getString("raison"));

                Retrait ret = new Retrait();
                ret.setDateRet(res.getDate("dateRet"));

                Tauxrecp taux = new Tauxrecp();

                taux.setId(res.getInt("idtauxrec"));
                taux.setFraisRet(res.getDouble("fraisRet"));

                RetraitTauxTrans transEnv = new RetraitTauxTrans(ret, trans, taux);
                listeRetrait.add(transEnv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeRetrait;
    }
    
    
    
    public boolean processTransaction(String numEnvoyeur,  double montant, double montantRetirer, String raison, int fraisTaux) {
        boolean f = false;
        try {
            // Début de la transaction
            conn.setAutoCommit(false);

            // Enregistrer la transaction
            String sqlInsertTransaction = "INSERT INTO Transaction (numEnvoyeur,  montant, raison, date) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
            PreparedStatement pstTransaction = conn.prepareStatement(sqlInsertTransaction, Statement.RETURN_GENERATED_KEYS);
            pstTransaction.setString(1, numEnvoyeur);
            pstTransaction.setDouble(2, montant);
            pstTransaction.setString(3, raison);
            pstTransaction.executeUpdate();

            ResultSet Keys = pstTransaction.getGeneratedKeys();
            try {
                int transactionId = 0;
                while (Keys.next()) {
                    transactionId = Keys.getInt(1);
                }
                String sqlEnvoyer = "INSERT INTO RETRAIT( id , dateRet, idtauxrec) VALUES (?, CURRENT_TIMESTAMP , ? ) ";
                PreparedStatement pstEnvoie = conn.prepareStatement(sqlEnvoyer);
                pstEnvoie.setInt(1, transactionId);
                pstEnvoie.setInt(2, fraisTaux);
                int isEnvoyer = pstEnvoie.executeUpdate();
                if (isEnvoyer > 0) {
                    // Déduire le montant de l'envoyeur
                    boolean isDeduire = deduireLeMontant(numEnvoyeur, montantRetirer);
                    
                    if (isDeduire) {
                        f = true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (f) {
                conn.commit();
            } else {
                conn.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            f = false;
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return f;
    }

    
    public boolean deduireLeMontant(String numEnvoyeur, double montantEnvoyer) {
        boolean f = false;
        try {
            String sqlUpdateEnvoyeur = "UPDATE COMPTE SET solde = solde - ? WHERE numero = ?";
            PreparedStatement pstEnvoyeur = conn.prepareStatement(sqlUpdateEnvoyeur);
            pstEnvoyeur.setDouble(1, montantEnvoyer);
            pstEnvoyeur.setString(2, numEnvoyeur);
            int res = pstEnvoyeur.executeUpdate();
            if (res > 0) {
                f = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;

    }

    
    public boolean deleteTransaction(int id) {
        boolean isDeleted = false;

        String sqlQuery = "DELETE FROM TRANSACTION WHERE id = ?";

        try {
            PreparedStatement Pst;
            Pst = conn.prepareStatement(sqlQuery);
            Pst.setInt(1, id);
            int Affected = Pst.executeUpdate();
            if (Affected > 0) {
                isDeleted = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isDeleted;
    }
}
