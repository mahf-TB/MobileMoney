/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.Servlet;

import com.DAO.ClientDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class DeleteClient extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int clientId = Integer.parseInt(req.getParameter("id"));
        ClientDAO clientDAO = new ClientDAO();
        boolean isDeleted = clientDAO.deleteClient(clientId);
        if (isDeleted) {
            req.setAttribute("message", "success to delete client.");
            resp.sendRedirect("listeClient.jsp");
        } else {
            req.setAttribute("error", "Failed to delete client.");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
