package com.sandstrom;

import com.sandstrom.Entities.Staff;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Methods {

    public static boolean login(String userName, String password) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            try {
                session.beginTransaction();

                // Kolla så att användarnamnet finns i databasen.
                Staff staff = session.byNaturalId(Staff.class).using("username", userName).load();

                // Kontrollera om användaren är en personal och logga in
                if (staff != null && staff.getPassword().equals(password)) {
                    session.getTransaction().commit();
                    return true; // Inloggning lyckades
                } else {
                    return false; // Inloggning misslyckades eller något gick fel
                }
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
}
