/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.Servlet;

import com.DAO.ClientDAO;
import com.DAO.TauxDAO;
import com.DAO.TransactionDAO;
import com.DAO.TransactionRetrait;
import com.Models.ClientCompte;
import com.Models.Recette;
import com.Models.Tauxrecp;
import com.mobilemoney.SendMail;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author macbookpro
 */
public class RetirerTransaction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            HttpSession session = req.getSession();
            String numEnvoyeur = req.getParameter("numEnvoyeur");
            double montant = Double.parseDouble(req.getParameter("montant"));
            String raison = req.getParameter("raison");

            double montantRet = 0;
            int idTauxRet = 0;
            double fraisRet = 0;
            TauxDAO daoRet = new TauxDAO();
            List<Tauxrecp> tauxList = daoRet.getAllTauxRecp();
            for (Tauxrecp cc : tauxList) {
                if (cc.getMontant_min() <= montant && montant < cc.getMontant_max()) {
                    //montantRec = montant + cc.getFraisRet();
                    fraisRet = cc.getFraisRet();
                    idTauxRet = cc.getId();
                }
            }
            montantRet = montant + fraisRet;
            ClientDAO dao = new ClientDAO();
            ClientCompte ccEnvoyeur = dao.ClientsWithAccounts(numEnvoyeur);

            if (montantRet > ccEnvoyeur.getComptes().getSolde()) {
                session.setAttribute("errorTrans", "Transaction echoué car votre solde insuffisant !");
                resp.sendRedirect("envoyerArgent.jsp");
                return;
            }

            // Initialiser le DAO de transaction
            TransactionRetrait transDAO = new TransactionRetrait();
            boolean isSuccess = transDAO.processTransaction(numEnvoyeur, montant, montantRet, raison, idTauxRet);
            if (isSuccess) {
                Recette recette = new Recette(fraisRet);
                TransactionDAO daoRec = new TransactionDAO();
                boolean isRec = daoRec.ajouterLeMontantRecette(recette);
                
                SendMail emailSender = new SendMail();
                double solde = ccEnvoyeur.getComptes().getSolde() - montantRet;
                String subjectEnvoyeur = "Confirmation de Retrait";
                String messageEnvoyeur = "Votre retrait de " + montant + " Ar a été envoyée avec succès. Frais : "+fraisRet+" Ar, Votre solde actuel : " + solde + "Ar";
                boolean isSend = emailSender.EnvoyerMail(ccEnvoyeur.getClient().getEmail(), subjectEnvoyeur, messageEnvoyeur);
                if ( isSend) {
                    session.setAttribute("success", "Retrait effectuée avec succès !");
                    resp.sendRedirect("listeTransaction.jsp");
                }
            } else {
                session.setAttribute("errorTrans", "Transaction echouer avec succès !");
                resp.sendRedirect("envoyerArgent.jsp");

            }
        } catch (Exception e) {
        }
    }

}
