package com.sandstrom.crudOperations;

import com.sandstrom.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

import java.sql.Timestamp;

public class CrudOfStore {
    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void registerNewStore(Byte managerStaffId, String address, String district, String city, String postalCode, String phone, byte[] location, String country, Timestamp lastUpdate, Short cityId) {
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

            City cities = null;
            if (cityId != null) {
                cities = session.load(City.class, cityId);
            } else {
                //Get city Id
                Query query = session.createNamedQuery("City.pk");
                query.setParameter("city", city);
                List<Short> cityIds = query.getResultList();
                if (!cityIds.isEmpty()) {
                    cities = session.load(City.class, cityIds.get(0));
                }
            }

            if (cities == null) {
                // If city doesn't exist, create a new one
                cities = new City(city, countries.getCountryId(), lastUpdate);
                session.persist(cities);
            }

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

    public void updateStoreInfo(int storeId, String address, String district, String city, String postalCode, String phone, String location, String country, Timestamp lastUpdate) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Hämta butiken med det givna ID:t från databasen
            Store store = session.get(Store.class, storeId);

            if (store != null) {
                // Uppdatera butikens information med de nya värdena
                Address storeAddress = store.getAddress();
                storeAddress.setAddress(address);
                storeAddress.setDistrict(district);
                storeAddress.getCity().setCity(city);
                storeAddress.setPostalCode(postalCode);
                storeAddress.setPhone(phone);
                storeAddress.setLocation(location.getBytes());
                storeAddress.setLastUpdate(lastUpdate);

                // Uppdatera landet om det finns
                Country countries = storeAddress.getCity().getCountry();
                if (countries != null) {
                    countries.setCountry(country);
                    countries.setLastUpdate(lastUpdate);
                } else {
                    // Skapa ett nytt land om det inte finns
                    countries = new Country(country, lastUpdate);
                    session.persist(countries);
                }

                // Spara ändringarna till databasen
                session.update(store);
                transaction.commit();
            } else {
                System.out.println("Butiken med ID " + storeId + " hittades inte.");
            }
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

    public List<Store> getAllStores() {
        Session session = null;
        Transaction transaction = null;
        List<Store> stores = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Hämta alla butiker från databasen
            stores = session.createQuery("FROM Store", Store.class).list();

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

        return stores;
    }

    public void getCustomerRentalInfo(int customerId) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();


            String hql = "SELECT c.firstName, c.lastName, r.rentalDate, r.returnDate, s.storeId, f.title " +
                    "FROM Customer c " +
                    "INNER JOIN c.rentalsByCustomerId r " +
                    "INNER JOIN r.inventory inv " +
                    "INNER JOIN inv.store s " +
                    "INNER JOIN Film f ON inv.filmId = f.filmId " +
                    "WHERE c.customerId = :customerId";

            List<Object[]> results = session.createQuery(hql)
                    .setParameter("customerId", customerId)
                    .list();

            // Visa resultaten
            for (Object[] result : results) {
                String firstName = (String) result[0];
                String lastName = (String) result[1];
                Timestamp rentalDate = (Timestamp) result[2];
                Timestamp returnDate = (Timestamp) result[3];
                Byte storeId = (Byte) result[4];
                String filmTitle = (String) result[5];

                System.out.println("Kund: " + firstName + " " + lastName);
                System.out.println("Uthyrningsdatum: " + rentalDate);
                System.out.println("Returdatum: " + returnDate);
                System.out.println("Butiks-ID: " + storeId);
                System.out.println("Film: " + filmTitle);
            }

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