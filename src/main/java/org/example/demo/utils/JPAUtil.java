package org.example.demo.utils;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    private static final String PERSISTENCE_NAME = "esocial_db";
    private static EntityManagerFactory factory;

    public static EntityManagerFactory getEntityManagerFactory(){
        if (factory == null){
            try {
                factory = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
            } catch (Exception e) {
                System.err.println("Initial EntityManagerFactory creation failed: " + e);
                throw new RuntimeException(e);
            }
        }
        return factory;
    }

    public static void close(){
        if (factory != null && factory.isOpen()){
            factory.close();
        }
    }

}
