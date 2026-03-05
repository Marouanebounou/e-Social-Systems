package org.example.demo.servlets;

import org.example.demo.DAO.AssureDAO;
import org.example.demo.DAO.CotisationDAO;
import org.example.demo.models.Assure;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/droits")
public class ConsultationDroitsServlet extends HttpServlet {

    private CotisationDAO cotisationDao;
    private AssureDAO assureDao;

    @Override
    public void init() throws ServletException {
        this.cotisationDao = new CotisationDAO();
        this.assureDao = new AssureDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String assureIdParam = request.getParameter("assureId");

        if (assureIdParam != null && !assureIdParam.isEmpty()) {
            Long assureId = Long.parseLong(assureIdParam);

            Assure assure = assureDao.findById(assureId);

            long nombreDeMois = cotisationDao.countMoisDeclares(assureId);
            double montantTotal = cotisationDao.sumCotisations(assureId);

            boolean estEligible = (nombreDeMois >= 6);

            request.setAttribute("assure", assure);
            request.setAttribute("nombreDeMois", nombreDeMois);
            request.setAttribute("montantTotal", montantTotal);
            request.setAttribute("estEligible", estEligible);
        }

        request.setAttribute("assures", assureDao.findAll());

        request.getRequestDispatcher("/WEB-INF/views/droits.jsp").forward(request, response);
    }
}