package com.DAO;

import com.DB.DBConnect;
import com.Models.Tauxenv;
import com.Models.Tauxrecp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mahefa
 */
public class TauxDAO {

    private Connection conn;

    public TauxDAO() {
        super();
        this.conn = DBConnect.getConn();
    }

    public List<Tauxenv> getAllTauxEnv() {
        List<Tauxenv> tauxList = new ArrayList<>();

        try {
            String sqlQuery = "SELECT * FROM TAUXENV";
            PreparedStatement pstt = conn.prepareStatement(sqlQuery);
            ResultSet res = pstt.executeQuery();

            while (res.next()) {
                Tauxenv taux = new Tauxenv();
                taux.setId(res.getInt("id"));
                taux.setMontant_min(res.getInt("montant_min"));
                taux.setMontant_max(res.getInt("montant_max"));
                taux.setFraisEnv(res.getDouble("fraisEnv"));

                tauxList.add(taux);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tauxList;
    }

    public List<Tauxrecp> getAllTauxRecp() {
        List<Tauxrecp> tauxList = new ArrayList<>();

        try {
            String sqlQuery = "SELECT * FROM TAUXRET";
            PreparedStatement pstt = conn.prepareStatement(sqlQuery);
            ResultSet res = pstt.executeQuery();

            while (res.next()) {
                Tauxrecp taux = new Tauxrecp();
                taux.setId(res.getInt("id"));
                taux.setMontant_min(res.getInt("montant_min"));
                taux.setMontant_max(res.getInt("montant_max"));
                taux.setFraisRet(res.getDouble("fraisRet"));

                tauxList.add(taux);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tauxList;
    }

    public boolean addTaux(String type, int min, int max, double frais) {
        boolean f = false;
        String sqlQuery = "";
        try {

            if (type.equals("envoie")) {
                sqlQuery = "INSERT INTO TAUXENV(montant_min, montant_max, fraisEnv) VALUES ( ?, ?, ?)";
            } else if (type.equals("retrait")) {
                sqlQuery = "INSERT INTO TAUXRET(montant_min, montant_max, fraisRet) VALUES ( ?, ?, ?)";
            }

            PreparedStatement ps = conn.prepareStatement(sqlQuery);

            ps.setInt(1, min);
            ps.setInt(2, max);
            ps.setDouble(3, frais);

            int res = ps.executeUpdate();
            if (res == 1) {
                f = true;
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }

    public boolean deleteTaux(String type, int idTaux) {
        boolean isDeleted = false;
        String sqlQuery = "";
        
        if (type.equals("envoie")) {
            sqlQuery = "DELETE FROM TAUXENV WHERE id = ?";
        } else if (type.equals("retrait")) {
            sqlQuery = "DELETE FROM TAUXRET WHERE id = ?";
        }

        try {
            PreparedStatement Pst;
            Pst = conn.prepareStatement(sqlQuery);
            Pst.setInt(1, idTaux);
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
