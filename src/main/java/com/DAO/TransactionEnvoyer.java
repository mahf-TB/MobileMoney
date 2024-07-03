package com.DAO;

import com.DB.DBConnect;
import com.Models.Envoyer;
import com.Models.EnvoyerTauxTrans;
import com.Models.Tauxenv;
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
public class TransactionEnvoyer {

    private Connection conn;

    public TransactionEnvoyer() {
        super();
        this.conn = DBConnect.getConn();
    }

    public List<EnvoyerTauxTrans> getAllTransactionEnvoyer() {
        List<EnvoyerTauxTrans> listeEnvoyer = new ArrayList<>();
        try {
            String sqlQuery = "SELECT * FROM ENVOYER En JOIN TRANSACTION Tr ON Tr.id = En.id LEFT JOIN TAUXENV TEn ON TEn.id = En.idtauxenv";
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

                Envoyer env = new Envoyer();
                env.setDateEnv(res.getDate("dateEnv"));
                env.setIs_frais_retrait(res.getBoolean("is_frais_retrait"));

                Tauxenv taux = new Tauxenv();

                taux.setId(res.getInt("idtauxenv"));
                taux.setFraisEnv(res.getDouble("fraisEnv"));

                EnvoyerTauxTrans transEnv = new EnvoyerTauxTrans(env, trans, taux);
                listeEnvoyer.add(transEnv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeEnvoyer;
    }

    public boolean processTransaction(String numEnvoyeur, String numRecepteur, double montant, double montantEnvoyer, double montantRecu, String raison, boolean isFrais, int fraisTaux) {
        boolean f = false;
        try {
            // Début de la transaction
            conn.setAutoCommit(false);

            // Enregistrer la transaction
            String sqlInsertTransaction = "INSERT INTO Transaction (numEnvoyeur, numRecepteur, montant, raison, date) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";
            PreparedStatement pstTransaction = conn.prepareStatement(sqlInsertTransaction, Statement.RETURN_GENERATED_KEYS);
            pstTransaction.setString(1, numEnvoyeur);
            pstTransaction.setString(2, numRecepteur);
            pstTransaction.setDouble(3, montant);
            pstTransaction.setString(4, raison);
            pstTransaction.executeUpdate();

            ResultSet Keys = pstTransaction.getGeneratedKeys();
            try {
                int transactionId = 0;
                while (Keys.next()) {
                    transactionId = Keys.getInt(1);
                }
                String sqlEnvoyer = "INSERT INTO ENVOYER( id , is_frais_retrait, dateEnv, idtauxenv) VALUES (?, ? , CURRENT_TIMESTAMP , ? ) ";
                PreparedStatement pstEnvoie = conn.prepareStatement(sqlEnvoyer);
                pstEnvoie.setInt(1, transactionId);
                pstEnvoie.setBoolean(2, isFrais);
                pstEnvoie.setInt(3, fraisTaux);
                int isEnvoyer = pstEnvoie.executeUpdate();
                if (isEnvoyer > 0) {
                    // Déduire le montant de l'envoyeur
                    boolean isDeduire = deduireLeMontant(numEnvoyeur, montantEnvoyer);
                    // Ajouter le montant au receveur
                    boolean isAdd = ajouterLeMontant(numRecepteur, montantRecu);
                    if (isAdd && isDeduire) {
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

    public boolean ajouterLeMontant(String numRecu, double montantRecu) {
        boolean f = false;
        try {
            String sqlUpdateRecepteur = "UPDATE COMPTE SET solde = solde + ? WHERE numero = ?";
            PreparedStatement pstRecepteur = conn.prepareStatement(sqlUpdateRecepteur);
            pstRecepteur.setDouble(1, montantRecu);
            pstRecepteur.setString(2, numRecu);
            int res = pstRecepteur.executeUpdate();
            if (res > 0) {
                f = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }
}
