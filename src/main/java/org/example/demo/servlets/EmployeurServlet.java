package org.example.demo.servlets;

import org.example.demo.DAO.EmployerDao;
import org.example.demo.models.Employer;
import org.example.demo.services.EmployerService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/employeurs")
public class EmployeurServlet extends HttpServlet {

    private EmployerService employeurService;

    @Override
    public void init() throws ServletException {
        EmployerDao employeurDao = new EmployerDao();

        this.employeurService = new EmployerService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employer> employeurs = employeurService.listerEmployeurs();

        request.setAttribute("employeurs", employeurs);

        request.getRequestDispatcher("/WEB-INF/views/employeurs.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String raisonSociale = request.getParameter("raisonSociale");
        String secteurActivite = request.getParameter("secteurActivite");

        Employer employeur = new Employer();
        employeur.setRaison_social(raisonSociale);
        employeur.setSecteur_activite(secteurActivite);

        employeurService.creerEmployeur(employeur.getRaison_social() , employeur.getSecteur_activite());

        response.sendRedirect(request.getContextPath() + "/employeurs");
    }
}