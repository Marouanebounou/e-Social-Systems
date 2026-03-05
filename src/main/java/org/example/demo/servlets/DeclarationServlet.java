package org.example.demo.servlets;

import org.example.demo.models.Assure;
import org.example.demo.models.Cotisation;
import org.example.demo.models.Declaration;
import org.example.demo.models.Employer;
import org.example.demo.services.AssureService;
import org.example.demo.services.CotisationService;
import org.example.demo.services.DeclarationService;
import org.example.demo.services.EmployerService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/declarations")
public class DeclarationServlet extends HttpServlet {

    private DeclarationService declarationService;
    private EmployerService employeurService;
    private AssureService assureService;
    private CotisationService cotisationService;

    @Override
    public void init() throws ServletException {
        this.declarationService = new DeclarationService();
        this.employeurService = new EmployerService();
        this.assureService = new AssureService();
        this.cotisationService = new CotisationService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("declarations", declarationService.listerDeclarations());
        request.setAttribute("employeurs", employeurService.listerEmployeurs());
        request.getRequestDispatcher("/WEB-INF/views/declarations.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long employeurId = Long.parseLong(request.getParameter("employeurId"));
            int mois = Integer.parseInt(request.getParameter("mois"));
            int annee = Integer.parseInt(request.getParameter("annee"));

            Employer employeur = employeurService.consulterEmployeur(employeurId);

            Declaration declaration = new Declaration();
            declaration.setEmployer(employeur);
            declaration.setMois(mois);
            declaration.setAnnee(annee);
            declaration.setDateDeclaration(LocalDate.now());

            declarationService.creerDeclaration(declaration);

            List<Assure> employes = assureService.consulterAssuresParEmployeur(employeurId);
            List<Cotisation> cotisations = new ArrayList<>();

            for (Assure employe : employes) {
                Cotisation cot = cotisationService.calculerCotisation(employe, declaration);
                cotisations.add(cot);
            }

            cotisationService.enregistrerCotisations(cotisations);

            response.sendRedirect(request.getContextPath() + "/declarations?success=true");

        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            doGet(request, response);
        }
    }
}