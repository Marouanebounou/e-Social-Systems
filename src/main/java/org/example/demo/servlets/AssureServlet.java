package org.example.demo.servlets;

import org.example.demo.models.Assure;
import org.example.demo.models.Employer;
import org.example.demo.services.AssureService;
import org.example.demo.services.EmployerService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/assures")
public class AssureServlet extends HttpServlet {

    private AssureService assureService;
    private EmployerService employeurService;

    @Override
    public void init() throws ServletException {
        this.assureService = new AssureService();
        this.employeurService = new EmployerService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Assure> assures = assureService.listerAssures();
        List<Employer> employeurs = employeurService.listerEmployeurs();

        request.setAttribute("assures", assures);
        request.setAttribute("employeurs", employeurs);

        request.getRequestDispatcher("/WEB-INF/views/assures.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        double salaireMensuel = Double.parseDouble(request.getParameter("salaireMensuel"));
        Long employeurId = Long.parseLong(request.getParameter("employeurId"));

        Employer employeur = employeurService.consulterEmployeur(employeurId);

        if (employeur != null) {
            Assure assure = new Assure();
            assure.setName(nom);
            assure.setSalaireMensuel(salaireMensuel);
            assure.setEmployer(employeur);

            assureService.ajouterAssure(assure);
        }

        response.sendRedirect(request.getContextPath() + "/assures");
    }
}