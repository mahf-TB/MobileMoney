/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.Servlet;

import com.DAO.TauxDAO;
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
public class DeleteTaux extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         try {

            int idTaux = Integer.parseInt(req.getParameter("id"));            
            String type = req.getParameter("type");

            TauxDAO dao = new TauxDAO();
            boolean isTrue = dao.deleteTaux(type, idTaux);
            HttpSession session = (HttpSession) req.getSession();
            
            if (isTrue) {
                session.setAttribute("data", isTrue);
                resp.sendRedirect("listeTaux.jsp");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

          
}
