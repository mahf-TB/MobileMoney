/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.Servlet;

import com.DAO.ClientDAO;
import com.Models.Client;
import com.Models.ClientCompte;
import com.Models.Compte;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author macbookpro
 */
public class UpdateClient extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String num = req.getParameter("numero");

        try {

            ClientDAO clientDAO = new ClientDAO();
            ClientCompte clientCompte = clientDAO.ClientsWithAccounts(num);

            req.setAttribute("clientCompte", clientCompte);
            req.getRequestDispatcher("updateClient.jsp").forward(req, resp);

        } catch (SQLException ex) {
            Logger.getLogger(UpdateClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id_client"));
        String noms = req.getParameter("noms");
        String age = req.getParameter("age");
        String sexe = req.getParameter("sexe");
        String email = req.getParameter("email");
        String numero = req.getParameter("numero");
        double solde = Double.parseDouble(req.getParameter("solde"));

        try {
            Client client = new Client();
            client.setId(id);
            client.setNoms(noms);
            client.setAge(age);
            client.setSexe(sexe);
            client.setEmail(email);

            Compte compte = new Compte();
            compte.setNumero(numero);
            compte.setSolde(solde);
            compte.setId_client(id);

            ClientDAO clientDAO = new ClientDAO();
            boolean isUpdated = clientDAO.updateClientAndAccounts(client, compte);
            if (isUpdated) {
                resp.sendRedirect("listeClient.jsp");
            } else {
                resp.sendRedirect("updateClient.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
