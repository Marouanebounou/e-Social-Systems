package org.example.demo;

import org.example.demo.DAO.EmployerDao;
import org.example.demo.models.Employer;
import org.example.demo.utils.JPAUtil;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting JPA setup...");

        // 1. Create a new Employer
        Employer emp = new Employer();
        emp.setRaison_social("Tech Solutions Maroc");
        emp.setSecteur_activite("IT");

        // 2. Save it using the DAO
        EmployerDao dao = new EmployerDao();
        dao.save(emp);

        System.out.println("Employer saved successfully!");

        // Close the factory when done
        JPAUtil.close();
    }
}