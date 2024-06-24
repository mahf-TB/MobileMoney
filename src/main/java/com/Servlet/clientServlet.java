/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.Servlet;

import com.DAO.userDAO;
import com.Models.Client;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import static java.lang.System.out;


public class clientServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) {
        try {
            
            String noms = req.getParameter("noms");
            String age = req.getParameter("age");
            String sexe = req.getParameter("sexe");
            String email = req.getParameter("email");
           
            //System.out.println(noms +" " +age +" "+sexe +" "+email +" ");
              
            Client user = new Client();
            user.setNoms(noms);
            user.setAge(age);
            user.setSexe(sexe);
            user.setEmail(email);
            
            userDAO dao = new userDAO();
            boolean f = dao.clientRegister(user);           
            HttpSession sess = req.getSession();
           
            if (f) {               
                sess.setAttribute("success", "Client register successfuly...");
                response.sendRedirect("home.jsp");               
            }else{                 
                sess.setAttribute("failed", "Client register failure...");
                response.sendRedirect("register.jsp");              
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
