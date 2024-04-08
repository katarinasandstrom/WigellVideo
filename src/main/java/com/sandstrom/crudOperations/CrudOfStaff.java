package com.sandstrom.crudOperations;

import com.sandstrom.entities.*;
import jakarta.persistence.TypedQuery;
import javafx.scene.control.Label;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

import static org.hibernate.sql.ast.Clause.FROM;

public class CrudOfStaff {
    public void registerNewPersonal(Label labelDuplicatePersonal, String firstName, String lastName, String email, String country, String city,
                                    String address, String district, String postalCode, String phone, String username, String password, int storeId) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            TypedQuery<Staff> query = session.createQuery("SELECT s FROM Staff s WHERE s.email = :email", Staff.class);
            query.setParameter("email", email);

            List<Staff> existingStaff = query.getResultList();

            Staff existingPersonal = null;

            if (!existingStaff.isEmpty()) {
                existingPersonal = existingStaff.getFirst();
            }

            if (existingPersonal != null) {
                labelDuplicatePersonal.setText("En personal med den angivna e-postadressen finns redan i systemet.");
            } else {
                Short countryId = null;

                TypedQuery<Short> countryQuery = session.createNamedQuery("Country.pk", Short.class);
                countryQuery.setParameter("country", country);
                try {
                    countryId = countryQuery.getSingleResult();
                } catch (Exception ex) {
                    System.out.println("Error finding country id: " + ex.getMessage());
                }

                if (countryId != null) {
                    Country countryStaff = session.get(Country.class, countryId);

                    City cityStaff = new City();
                    cityStaff.setCity(city);
                    cityStaff.setCountry(countryStaff);
                    cityStaff.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                    session.persist(cityStaff);

                    Short cityId = null;

                    TypedQuery<Short> cityQuery = session.createNamedQuery("City.pk", Short.class);
                    cityQuery.setParameter("city", city);
                    try {
                        cityId = cityQuery.getSingleResult();
                    } catch (Exception ex) {
                        System.out.println("Error finding city id: " + ex.getMessage());
                    }

                    if (cityId != null) {
                        Address getFirstAddress = session.get(Address.class, "1");
                        byte[] location = getFirstAddress.getLocation();
                        City addressId = session.get(City.class, cityId);

                        Store store = session.get(Store.class, 1);

                        Address staffAddress = new Address();
                        staffAddress.setAddress(address);
                        staffAddress.setDistrict(district);
                        staffAddress.setCity(addressId);
                        staffAddress.setPostalCode(postalCode);
                        staffAddress.setPhone(phone);
                        staffAddress.setLocation(location);
                        staffAddress.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                        session.persist(staffAddress);

                        Staff newStaffMember = new Staff();
                        newStaffMember.setFirstName(firstName);
                        newStaffMember.setLastName(lastName);
                        newStaffMember.setEmail(email);
                        newStaffMember.setUsername(username);
                        newStaffMember.setPassword(password);
                        newStaffMember.setAddress(staffAddress);
                        newStaffMember.setStore(store);
                        newStaffMember.setActive((byte) 1);
                        newStaffMember.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                        session.persist(newStaffMember);

                        transaction.commit();
                    }

                } else {
                    System.out.println("No such country exists, please check your spelling");
                }
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
            sessionFactory.close();
        }
    }

    public void updateStaff(Label labelUpdateResult, int staffId, String firstName, String lastName, String email,
                            String username, String password, String address, String district, String postalCode, String phone, int storeId) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Staff staff = session.get(Staff.class, staffId);

            if (staff != null) {
                staff.setFirstName(firstName);
                staff.setLastName(lastName);
                staff.setEmail(email);
                staff.setUsername(username);
                staff.setPassword(password);

                Address staffAddress = staff.getAddress();
                staffAddress.setAddress(address);
                staffAddress.setDistrict(district);
                staffAddress.setPostalCode(postalCode);
                staffAddress.setPhone(phone);
                staffAddress.setLastUpdate(new Timestamp(System.currentTimeMillis()));

                Store store = session.get(Store.class, storeId);
                staff.setStore(store);

                session.update(staff);

                transaction.commit();
                labelUpdateResult.setText("Personalinformation uppdaterad");
            } else {
                labelUpdateResult.setText("Personal med id " + staffId + " hittades inte");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            labelUpdateResult.setText("Fel vid uppdatering av personalinformation");
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            sessionFactory.close();
        }
    }

    private void deleteStaff(Byte staffId) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
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
            if (session != null) {
                session.close();
            }
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


    public void readFromStaff(List<Staff> staffList) {
        //Lägg till label för när kund finns
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = null;
        Transaction transaction = null;

        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            TypedQuery<Staff> queryStaff = session.createNamedQuery("Staff.table", Staff.class);

            List<Staff> foundStaff = queryStaff.getResultList();
            staffList.addAll(foundStaff);

        }catch(Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
        }finally {
            if (session != null) {
                session.close();
            }
            sessionFactory.close();
        }
    }




    public List<Integer> getStoreIds() {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            return session.createQuery("SELECT s.storeId FROM Store s", Integer.class).list();

        } catch (Exception e) {
            System.err.println("Error occurred while fetching store IDs: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
