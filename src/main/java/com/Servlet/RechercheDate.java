/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.Servlet;

import com.DAO.TransactionEnvoyer;
import com.Models.EnvoyerTauxTrans;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author macbookpro
 */
public class RechercheDate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date date = Date.valueOf(req.getParameter("date"));
        //Date dateRet = Date.valueOf(req.getParameter("dateRetraite"));
        
        TransactionEnvoyer dao = new TransactionEnvoyer();
        List<EnvoyerTauxTrans> dataEnvoyer = dao.searchTransactionEnvoyer(date);
        req.setAttribute("liste", dataEnvoyer);
        req.getRequestDispatcher("listeTransaction.jsp").forward(req, resp);
    }

}
