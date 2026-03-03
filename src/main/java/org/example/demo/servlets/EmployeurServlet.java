package org.example.demo.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo.models.Employer;
import org.example.demo.services.EmployerService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmployeurServlet" , urlPatterns = "/employeur")
public class EmployeurServlet extends HttpServlet {
    private EmployerService employerService;

    @Override
    public void init() throws ServletException{
        employerService = new EmployerService();
    }

    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException {
        List<Employer> employers = employerService.listerEmployeurs();
        request.setAttribute("employerList" , employers);
        request.getRequestDispatcher("/employeurs.jsp").forward(request , response);
    }

    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException{
        String raisonSocial = request.getParameter("raisonSocial");
        String secteurActivite = request.getParameter("secteurActivite");

        employerService.creerEmployeur(raisonSocial , secteurActivite);

        response.sendRedirect(request.getContextPath()+ "/employers");
    }
}
