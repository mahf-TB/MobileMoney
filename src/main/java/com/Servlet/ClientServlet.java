package com.Servlet;

import com.DAO.userDAO;
import com.Models.Client;
import com.Models.Compte;
import static jakarta.faces.component.UIInput.isEmpty;

import java.io.IOException;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ClientServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            userDAO dao = new userDAO();
            String id_client = req.getParameter("id_client");

            HttpSession session = req.getSession();

            if (id_client.equals("client")) {
                Client cli = new Client();
                cli.setNoms(req.getParameter("noms"));
                cli.setAge(req.getParameter("age"));
                cli.setSexe(req.getParameter("sexe"));
                cli.setEmail(req.getParameter("email"));

                int id = dao.clientRegister(cli);

                if (id > 0) {
                    session.setAttribute("id_client", id);
                    resp.sendRedirect("ajouterCompte.jsp");
                }

            } else {
                String numero = req.getParameter("numero");
                String motdepasse = req.getParameter("password");
                String confirmationMotdepasse = req.getParameter("password_confirmation");
                String soldeStr = req.getParameter("solde");
                double solde;

                if (!motdepasse.equals(confirmationMotdepasse)) {

                    session.setAttribute("errorPass", "Les mots de passe ne correspondent pas.");
                    resp.sendRedirect("ajouterCompte.jsp");
                    return;
                }

                try {
                    solde = Double.parseDouble(soldeStr);
                    if (solde < 0) {
                        session.setAttribute("errorSolde", "Le solde doit être pas négative");
                        resp.sendRedirect("ajouterCompte.jsp");
                        return;
                    }
                } catch (NumberFormatException e) {
                    session.setAttribute("errorSolde", "Le solde doit être un nombre valide.");
                    resp.sendRedirect("ajouterCompte.jsp");
                    return;
                }

                Compte compte = new Compte();
                compte.setId_client(Integer.parseInt(id_client));
                compte.setIsActive(true);
                compte.setNumero(numero);
                compte.setMotdepasse(motdepasse);
                compte.setSolde(solde);

                boolean bool = dao.creatCompteClient(compte);
                if (bool) {

                    session.setAttribute("message", "Le compte du client a été créé avec succès.");
                    resp.sendRedirect("listeClient.jsp");
                } else {
                    session.setAttribute("error", "Erreur lors de la création du compte client.");
                    resp.sendRedirect("ajouterCompte.jsp");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
