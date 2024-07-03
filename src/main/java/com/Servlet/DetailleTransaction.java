/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.Servlet;

import com.DAO.ClientDAO;
import com.DAO.TransactionDAO;
import com.Models.ClientCompte;
import com.Models.Transaction;
import com.mobilemoney.PdfGenerator;
import java.io.IOException;
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
public class DetailleTransaction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String num = req.getParameter("numero");
        HttpSession session = req.getSession();
        TransactionDAO daoTrans = new TransactionDAO();
        List<Transaction> debitCredit = daoTrans.getTransactionsForMonthsDebitsCredit(num);
        
        if (debitCredit != null && !debitCredit.isEmpty()) {
            
            ClientDAO dao = new ClientDAO();
            ClientCompte ccEnvoyeur  = null; 
            try {
               ccEnvoyeur = dao.ClientsWithAccounts(num);
            } catch (SQLException ex) {
                Logger.getLogger(DetailleTransaction.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            PdfGenerator pdf = new PdfGenerator();
            pdf.generePDF(debitCredit , ccEnvoyeur);
            req.setAttribute("debitCredit", debitCredit);
            req.setAttribute("numero", num);
            req.getRequestDispatcher("detaillePDF.jsp").forward(req, resp);
        } else {
            session.setAttribute("errorNum", "Invalid transaction sur le même numéro !");
            resp.sendRedirect("listeClient.jsp");          
        }

    }

}
