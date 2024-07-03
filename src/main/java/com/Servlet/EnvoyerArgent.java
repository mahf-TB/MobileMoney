package com.Servlet;

import com.DAO.ClientDAO;
import com.DAO.TauxDAO;
import com.DAO.TransactionDAO;
import com.DAO.TransactionEnvoyer;
import com.Models.ClientCompte;
import com.Models.Recette;
import com.Models.Tauxenv;
import com.Models.Tauxrecp;
import com.mobilemoney.SendMail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author macbookpro
 */
public class EnvoyerArgent extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Créer une session
        try {

            HttpSession session = req.getSession();
            String numEnvoyeur = req.getParameter("numEnvoyeur");
            String numRecepteur = req.getParameter("numRecepteur");
            double montant = Double.parseDouble(req.getParameter("montant"));
            String raison = req.getParameter("raison");
            boolean isFrais = req.getParameter("isFrais") != null;

            double montantRec = 0;
            double montantEnv = 0;
            int idTauxEnv = 0;
            double fraisRet = 0;
            double fraisEnv = 0;
            if (numEnvoyeur.equals(numRecepteur)) {
                session.setAttribute("errorNum", "Invalid transaction sur le même numéro !");
                resp.sendRedirect("envoyerArgent.jsp");
                return;
            }
            TauxDAO daoRet = new TauxDAO();
            if (isFrais) {
                List<Tauxrecp> tauxList = daoRet.getAllTauxRecp();
                for (Tauxrecp cc : tauxList) {
                    if (cc.getMontant_min() <= montant && montant < cc.getMontant_max()) {
                        //montantRec = montant + cc.getFraisRet();
                        fraisRet = cc.getFraisRet();
                    }
                }
            } else {
                montantRec = montant;
            }

            List<Tauxenv> tauxList1 = daoRet.getAllTauxEnv();
            for (Tauxenv cc : tauxList1) {
                if (cc.getMontant_min() <= montant && montant < cc.getMontant_max()) {
                    //montantEnv = montant + cc.getFraisEnv();
                    idTauxEnv = cc.getId();
                    fraisEnv = cc.getFraisEnv();
                }
            }
            montantRec = montant + fraisRet;
            montantEnv = montant + fraisEnv + fraisRet;

            ClientDAO dao = new ClientDAO();
            ClientCompte ccEnvoyeur = dao.ClientsWithAccounts(numEnvoyeur);
            ClientCompte ccRecepteur = dao.ClientsWithAccounts(numRecepteur);

            if (montantEnv > ccEnvoyeur.getComptes().getSolde()) {
                session.setAttribute("errorTrans", "Transaction echoué car votre solde insuffisant !");
                resp.sendRedirect("envoyerArgent.jsp");
                return;
            }
           
            // Initialiser le DAO de transaction
            TransactionEnvoyer transactionDAO = new TransactionEnvoyer();
            boolean isSuccess = transactionDAO.processTransaction(numEnvoyeur, numRecepteur, montant, montantEnv, montantRec, raison, isFrais, idTauxEnv);

            if (isSuccess) {
                Recette recette = new Recette(fraisEnv);
                TransactionDAO daoRec = new TransactionDAO();
                boolean isRec = daoRec.ajouterLeMontantRecette(recette);
                
                double solde = ccEnvoyeur.getComptes().getSolde() - montantEnv;
                double soldeRec = ccRecepteur.getComptes().getSolde() + montantRec;
                
                SendMail emailSender = new SendMail();

                String subjectEnvoyeur = "Confirmation de transaction";
                String messageEnvoyeur = "Votre transaction de " + montantRec + " Ar a été envoyée avec succès. Frais : "+ fraisEnv +"Ar, Frais de retrait: "+fraisRet+" Votre solde actuel est " + solde + "Ar";
                boolean isSend = emailSender.EnvoyerMail(ccEnvoyeur.getClient().getEmail(), subjectEnvoyeur, messageEnvoyeur);

                // Envoyer un email au récepteur
                String subjectRecepteur = "Notification de réception";
                String messageRecepteur = "Vous avez reçu un montant de " + montantRec + "Ar. Votre solde actuel est " + soldeRec + "Ar";
                boolean isRecev = emailSender.EnvoyerMail(ccRecepteur.getClient().getEmail(), subjectRecepteur, messageRecepteur);
                
                if (isRecev && isSend) {
                    session.setAttribute("success", "Transaction effectuée avec succès !");
                    resp.sendRedirect("listeTransaction.jsp");
                }

            } else {
                session.setAttribute("errorTrans", "Transaction echouer avec succès !");
                resp.sendRedirect("envoyerArgent.jsp");

            }

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(EnvoyerArgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
