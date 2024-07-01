package com.Servlet;

import com.DAO.TauxDAO;
import com.DAO.TransactionEnvoyer;
import com.Models.Tauxenv;
import com.Models.Tauxrecp;
import java.io.IOException;
import java.io.PrintWriter;
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
public class EnvoyerArgent extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Créer une session
        HttpSession session = req.getSession();
        String numEnvoyeur = req.getParameter("numEnvoyeur");
        String numRecepteur = req.getParameter("numRecepteur");
        double montant = Double.parseDouble(req.getParameter("montant"));
        String raison = req.getParameter("raison");
        boolean isFrais = req.getParameter("isFrais") != null;

        double montantRec = 0;
        double montantEnv = 0;

        if (numEnvoyeur.equals(numRecepteur)) {
            session.setAttribute("error", "Invalid transaction sur le même numéro !");
            resp.sendRedirect("listeTransaction.jsp");
        }
        TauxDAO daoRet = new TauxDAO();
        List<Tauxenv> tauxList1 = daoRet.getAllTauxEnv();
        for (Tauxenv cc : tauxList1) {
            if (cc.getMontant_min() <= montant && montant < cc.getMontant_max()) {
                montantEnv = montant + cc.getFraisEnv();
                break;
            }
        }
        if (isFrais) {
            List<Tauxrecp> tauxList = daoRet.getAllTauxRecp();
            for (Tauxrecp cc : tauxList) {
                if (cc.getMontant_min() <= montant && montant < cc.getMontant_max()) {
                    montantRec = montant + cc.getFraisRet();
                    break;
                }
            }
        } else {
            montantRec = montant;
        }
        // Initialiser le DAO de transaction
        TransactionEnvoyer transactionDAO = new TransactionEnvoyer();
        boolean isSuccess = transactionDAO.processTransaction(numEnvoyeur, numRecepteur, montant, montantEnv, montantRec, raison, isFrais, 1);

        if (isSuccess) {
            session.setAttribute("success", "Transaction effectuée avec succès !");
            resp.sendRedirect("listeTransaction.jsp");
        }
    }

}
