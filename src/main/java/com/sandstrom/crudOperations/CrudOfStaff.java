package com.sandstrom.crudOperations;

import com.sandstrom.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Timestamp;
import java.util.List;

public class CrudOfStaff {
    public void registerNewStaff(Byte staffId, String firstName, String lastName, byte[] picture, String email, byte active, String username,
                                 String password, Timestamp lastUpdate, String selectedStoreName, String selectedAddressLine) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            /*
            jag vet inte om jag tänkt rätt men i och med att store har sin egen entity så tänkte jag att man här skulle visa
            upp en lista med butikernas namn så att man kunde välja därifrån. likadant när det gäller address som hanteras i customer-
            entity. ni får gärna skriva om jag tänkt rätt här. ett annat problem är storeName som inte finns i tabellen så vet ine
            hur jag ska göra.
             */


            Store selectedStore = (Store) session.createQuery("FROM Store WHERE storeName = :name")
                    .setParameter("name", selectedStoreName)
                    .uniqueResult();

            Address selectedAddress = (Address) session.createQuery("FROM Address WHERE address = :line")
                    .setParameter("line", selectedAddressLine)
                    .uniqueResult();


            Staff newStaff = new Staff();
            newStaff.setStaffId(staffId);
            newStaff.setFirstName(firstName); //finns
            newStaff.setLastName(lastName); //finns
            newStaff.setPicture(picture);
            newStaff.setEmail(email); //finns
           // newStaff.setActive(active);
            newStaff.setUsername(username); //finns
            newStaff.setPassword(password); //finns
            newStaff.setLastUpdate(lastUpdate);


            newStaff.setStore(selectedStore);
            newStaff.setAddress(selectedAddress);

            session.save(newStaff);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    private void updatePersonalInfo(Byte staffId, String firstName, String lastName, byte[] picture, String email, byte active, String username, String password, Timestamp lastUpdate, String selectedStoreName, String selectedAddressLine){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Hämta personalen som ska uppdateras från databasen
            Staff staffToUpdate = session.get(Staff.class, staffId);

            // Uppdatera personalens attribut med de nya värdena
            staffToUpdate.setFirstName(firstName);
            staffToUpdate.setLastName(lastName);
            staffToUpdate.setPicture(picture);
            staffToUpdate.setEmail(email);
            // staffToUpdate.setActive(active); // Aktivera om active attributet ska uppdateras
            staffToUpdate.setUsername(username);
            staffToUpdate.setPassword(password);
            staffToUpdate.setLastUpdate(lastUpdate);

            // Hämta den valda butiken
            Store selectedStore = (Store) session.createQuery("FROM Store WHERE storeName = :name")
                    .setParameter("name", selectedStoreName)
                    .uniqueResult();
            staffToUpdate.setStore(selectedStore);

            // Hämta den valda adressen
            Address selectedAddress = (Address) session.createQuery("FROM Address WHERE address = :line")
                    .setParameter("line", selectedAddressLine)
                    .uniqueResult();
            staffToUpdate.setAddress(selectedAddress);

            // Spara uppdateringen till databasen
            session.update(staffToUpdate);
            transaction.commit();


        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    private void deleteStaff(Byte staffId) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Hämta personalen som ska raderas från databasen
            Staff staffToDelete = session.get(Staff.class, staffId);

            /* Avlägsna personalens referens från betalningar och uthyrningar då betalningar och uthyrningar är kopplade
            till butiken.
             */

            List<Payment> payments = staffToDelete.getPayments();
            for (Payment payment : payments) {
                payment.setStaff(null);
                session.update(payment);
            }

            List<Rental> rentals = staffToDelete.getRentals();
            for (Rental rental : rentals) {
                rental.setStaff(null);
                session.update(rental);
            }

            // Hämta adressen som är kopplad till personalen
            Address address = staffToDelete.getAddress();
            if (address != null) {
                List<Staff> staffWithSameAddress = session.createQuery("FROM Staff WHERE address = :address", Staff.class)
                        .setParameter("address", address)
                        .list();

                // Om adressen är kopplad till endast den aktuella personalen, raderar den
                if (staffWithSameAddress.size() == 1) {
                    session.delete(address);
                } else {
                    // Annars raderas bara följande:
                    staffToDelete.setFirstName(null);
                    staffToDelete.setLastName(null);
                    staffToDelete.setPicture(null);
                    staffToDelete.setEmail(null);
                    staffToDelete.setUsername(null);
                    staffToDelete.setPassword(null);
                    staffToDelete.setLastUpdate(null);
                    session.update(staffToDelete);
                }
            }

            // Radera personalen
            session.delete(staffToDelete);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    public List<Staff> getAllStaff() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            // Hämta alla poster från Staff-tabellen
            List<Staff> staffList = session.createQuery("FROM Staff", Staff.class).list();
            return staffList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            sessionFactory.close();
        }
    }


}
