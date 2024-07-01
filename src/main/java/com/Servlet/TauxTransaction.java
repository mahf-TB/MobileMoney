/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.Servlet;

import com.DAO.TauxDAO;
import java.io.IOException;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author macbookpro
 */
public class TauxTransaction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            int min = Integer.parseInt(req.getParameter("min"));
            int max = Integer.parseInt(req.getParameter("max"));
            double frais = Double.parseDouble(req.getParameter("frais"));
            String type = req.getParameter("type");

            TauxDAO dao = new TauxDAO();
            boolean isTrue = dao.addTaux(type, min, max, frais);
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
