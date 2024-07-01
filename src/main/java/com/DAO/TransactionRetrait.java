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
    
}
