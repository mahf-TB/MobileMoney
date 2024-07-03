/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.Servlet;

import com.DAO.TransactionRetrait;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author macbookpro
 */
public class DeleteTransaction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

                 try {

            int idTrans = Integer.parseInt(req.getParameter("id"));            
            

            TransactionRetrait dao = new TransactionRetrait();
            boolean isTrue = dao.deleteTransaction(idTrans);
            HttpSession session = (HttpSession) req.getSession();
            
            if (isTrue) {
                session.setAttribute("data", "Transaction supprimer avec success...!");
                resp.sendRedirect("listeTransaction.jsp");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
}
