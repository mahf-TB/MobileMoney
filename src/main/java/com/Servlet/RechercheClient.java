/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.Servlet;

import com.DAO.ClientDAO;
import com.Models.ClientCompte;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author macbookpro
 */
public class RechercheClient extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String num = req.getParameter("search");

        ClientDAO clientDAO = new ClientDAO();
        List<ClientCompte> clientCompte = clientDAO.searchClientsWithAccounts(num);
        req.setAttribute("clientCompte", clientCompte);
        req.getRequestDispatcher("listeClient.jsp").forward(req, resp);
    }

}
