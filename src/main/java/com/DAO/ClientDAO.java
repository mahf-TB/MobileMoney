/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.DB.DBConnect;
import com.Models.Client;
import com.Models.ClientCompte;
import com.Models.Compte;
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
public class ClientDAO {

    private Connection conn;

    public ClientDAO() {
        super();
        this.conn = DBConnect.getConn();
    }

    public List<ClientCompte> getAllClientsWithAccounts() {
        List<ClientCompte> clientList = new ArrayList<>();

        try {
            String sqlQuery = "SELECT CLIENT.id, numero, noms, age, sexe, email, solde, isActive FROM COMPTE JOIN CLIENT ON id_client = CLIENT.id";
            PreparedStatement pstt = conn.prepareStatement(sqlQuery);
            ResultSet res = pstt.executeQuery();

            while (res.next()) {
                int clientId = res.getInt("id");

                Client cli = new Client();
                cli.setId(clientId);
                cli.setNoms(res.getString("noms"));
                cli.setEmail(res.getString("email"));
                cli.setSexe(res.getString("sexe"));
                cli.setAge(res.getString("age"));

                Compte compte = new Compte();
                compte.setId(res.getInt("id"));
                compte.setNumero(res.getString("numero"));
                compte.setSolde(res.getDouble("solde"));
                compte.setIsActive(res.getBoolean("isActive"));

                ClientCompte cliCompte = new ClientCompte(cli, compte);
                clientList.add(cliCompte);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientList;
    }

    public List<ClientCompte> searchClientsWithAccounts(String query) {
        List<ClientCompte> clientList = new ArrayList<>();

        try {
            String sqlQuery = "SELECT CLIENT.id, numero, noms, age, sexe, email, solde, isActive FROM COMPTE JOIN CLIENT ON id_client = CLIENT.id WHERE CLIENT.noms LIKE ? OR CLIENT.email LIKE ? OR COMPTE.numero LIKE ? ";
            PreparedStatement pstt = conn.prepareStatement(sqlQuery);
            pstt.setString(1, "%" + query + "%");
            pstt.setString(2, "%" + query + "%");
             pstt.setString(3, "%" + query + "%");
            ResultSet res = pstt.executeQuery();

            while (res.next()) {
                int clientId = res.getInt("id");

                Client cli = new Client();
                cli.setId(clientId);
                cli.setNoms(res.getString("noms"));
                cli.setEmail(res.getString("email"));
                cli.setSexe(res.getString("sexe"));
                cli.setAge(res.getString("age"));

                Compte compte = new Compte();
                compte.setId(res.getInt("id"));
                compte.setNumero(res.getString("numero"));
                compte.setSolde(res.getDouble("solde"));
                compte.setIsActive(res.getBoolean("isActive"));

                ClientCompte cliCompte = new ClientCompte(cli, compte);
                clientList.add(cliCompte);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientList;
    }

    public ClientCompte ClientsWithAccounts(String numero) throws SQLException {
        String sqlQuery = "SELECT CLIENT.id, numero, noms, age, sexe, email, solde, isActive FROM COMPTE JOIN CLIENT ON id_client = CLIENT.id where numero = ?";
        PreparedStatement pstt = conn.prepareStatement(sqlQuery);
        pstt.setString(1, numero);
        ResultSet rs = pstt.executeQuery();
        while (rs.next()) {
            Client client = new Client();
            client.setId(rs.getInt("id"));
            client.setNoms(rs.getString("noms"));
            client.setAge(rs.getString("age"));
            client.setSexe(rs.getString("sexe"));
            client.setEmail(rs.getString("email"));

            Compte compte = new Compte();
            compte.setNumero(rs.getString("numero"));
            compte.setIsActive(rs.getBoolean("isActive"));
            compte.setSolde(rs.getDouble("solde"));
            compte.setId_client(client.getId());

            ClientCompte cliCompte = new ClientCompte(client, compte);
            return cliCompte;
        }
        return null;
    }

    public boolean updateClientAndAccounts(Client client, Compte comptes) {
        boolean updated = false;
        String updateClientSQL = "UPDATE CLIENT SET noms = ?, age = ?, sexe = ?, email = ? WHERE id = ?";
        String updateCompteSQL = "UPDATE COMPTE SET numero = ?,  solde = ? WHERE id_client = ?";

        try {
            conn.setAutoCommit(false); // Démarrer la transaction

            // Mettre à jour les informations du client
            try (PreparedStatement pstClient = conn.prepareStatement(updateClientSQL)) {
                pstClient.setString(1, client.getNoms());
                pstClient.setString(2, client.getAge());
                pstClient.setString(3, client.getSexe());
                pstClient.setString(4, client.getEmail());
                pstClient.setInt(5, client.getId());
                pstClient.executeUpdate();
            }

            // Mettre à jour les comptes associés
            try (PreparedStatement pstCompte = conn.prepareStatement(updateCompteSQL)) {
                Compte compte = new Compte();
                pstCompte.setString(1, compte.getNumero());
                pstCompte.setDouble(2, compte.getSolde());
                pstCompte.setInt(3, compte.getId_client());
                pstCompte.executeUpdate();

            }

            conn.commit(); // Valider la transaction
            updated = true;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback(); // Annuler la transaction en cas d'erreur
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }
        return updated;
    }

    public boolean deleteClient(int clientId) {
        boolean isDeleted = false;

        String deleteClientSQL = "DELETE FROM CLIENT WHERE id = ?";

        try {
            PreparedStatement deleteClientPst;
            deleteClientPst = conn.prepareStatement(deleteClientSQL);
            deleteClientPst.setInt(1, clientId);
            int clientRowsAffected = deleteClientPst.executeUpdate();
            if (clientRowsAffected > 0) {
                isDeleted = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isDeleted;
    }

}
