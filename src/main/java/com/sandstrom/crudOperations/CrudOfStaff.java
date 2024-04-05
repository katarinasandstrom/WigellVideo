package com.sandstrom.crudOperations;

import com.sandstrom.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Timestamp;

public class CrudOfStaff {
    public void registerNewStaff(Byte staffId, String firstName, String lastName, byte[] picture, String email, byte active, String username, String password, Timestamp lastUpdate, String selectedStoreName, String selectedAddressLine) {
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
            newStaff.setFirstName(firstName);
            newStaff.setLastName(lastName);
            newStaff.setPicture(picture);
            newStaff.setEmail(email);
            newStaff.setActive(active);
            newStaff.setUsername(username);
            newStaff.setPassword(password);
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
}
