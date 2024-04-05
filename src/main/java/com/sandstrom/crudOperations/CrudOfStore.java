package com.sandstrom.crudOperations;

import com.sandstrom.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Timestamp;

public class CrudOfStore {
    private SessionFactory sessionFactory;

    public CrudOfStore() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void registerNewStore(Byte managerStaffId, String address, String district, String city, String postalCode, String phone, String location, String country, Timestamp lastUpdate) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String address2 = null;

            Store store = new Store();
            store.setManagerStaffId(managerStaffId);
            store.setLastUpdate(lastUpdate);

            Country countries = new Country(country, lastUpdate); // get the country that matches

            //Get country Id
            City cities = new City(); // get the city that matches or add new city
            cities.setCity(city); // set fk
            cities.setCountry(countries);
            cities.setLastUpdate(lastUpdate);

            // Skapa en ny Address och koppla den till butiken
            Address storeAddress = new Address();
            storeAddress.setAddress(address);
            storeAddress.setAddress2(address2);
            storeAddress.setDistrict(district);
            storeAddress.setCity(cities);
            storeAddress.setPostalCode(postalCode);
            storeAddress.setPhone(phone);
            storeAddress.setLocation(location);
            storeAddress.setLastUpdate(lastUpdate);

            // Lägg till adressen i butiken
            store.setAddress(storeAddress);

            session.persist(store);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}