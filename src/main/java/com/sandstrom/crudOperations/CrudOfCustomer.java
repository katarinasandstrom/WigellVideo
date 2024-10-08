package com.sandstrom.crudOperations;

import com.sandstrom.entities.*;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import javafx.scene.control.Label;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CrudOfCustomer {
    private SessionFactory sessionFactory;
    public CrudOfCustomer(){
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }
    public void registerNewCustomer(Label labelDuplicateCustomer, String firstName, String lastName, String email, String country, String city,
                                    String address, String district, String postalCode, String phone){

        Session session = null;
        Transaction transaction = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            TypedQuery<Customer> query = session.createNamedQuery("Customer.byEmail", Customer.class);
            query.setParameter("email", email);

            List<Customer> existingCustomers = query.getResultList();

            Customer existingCustomer = null;

            if(!existingCustomers.isEmpty()){
                existingCustomer = existingCustomers.getFirst();
            }

            if(existingCustomer != null){
                labelDuplicateCustomer.setText("En kund med den angivna e-postadressen " +
                        "finns redan i systemet.");
            }else{
                Short countryId = null;

                TypedQuery<Short> countryQuery = session.createNamedQuery("Country.pk", Short.class);
                countryQuery.setParameter("country", country);
                try {
                    countryId = countryQuery.getSingleResult();
                } catch (Exception ex) {
                    System.out.println("Error finding country id: " + ex.getMessage());
                }

                if (countryId != null) {
                    Country countryObj = session.get(Country.class, countryId);

                    City cityObj = new City();
                    cityObj.setCity(city);
                    cityObj.setCountry(countryObj);
                    cityObj.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                    session.persist(cityObj);

                    Address getFirstAddress = session.get(Address.class, "1");
                    byte[] location = getFirstAddress.getLocation();

                    Address addressObj = new Address();
                    addressObj.setAddress(address);
                    addressObj.setDistrict(district);
                    addressObj.setCity(cityObj);
                    addressObj.setPostalCode(postalCode);
                    addressObj.setPhone(phone);
                    addressObj.setLocation(location);
                    addressObj.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                    session.persist(addressObj);

                    Store store = session.get(Store.class, 1);

                    Customer customer = new Customer();
                    customer.setStore(store);
                    customer.setFirstName(firstName);
                    customer.setLastName(lastName);
                    customer.setEmail(email);
                    customer.setAddress(addressObj);
                    customer.setActive((byte) 1);
                    customer.setCreateDate(new Timestamp(System.currentTimeMillis()));
                    customer.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                    session.persist(customer);

                    transaction.commit();
                }
            }

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



    public void updateCustomer(Label labelDuplicateCustomer, String firstName, String lastName, String email, String country, String city,
                               String address, String district, String postalCode, String phone){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            TypedQuery<Customer> query = session.createNamedQuery("Customer.byEmail", Customer.class);
            query.setParameter("email", email);

            List<Customer> existingCustomers = query.getResultList();

            Customer existingCustomer = null;

            if (!existingCustomers.isEmpty()) {
                existingCustomer = existingCustomers.getFirst();
            }

            if (existingCustomer == null) {
                labelDuplicateCustomer.setText("En kund med den angivna e-postadressen " +
                        "finns inte i systemet.");
            } else {
                Short countryId = null;

                TypedQuery<Short> countryQuery = session.createNamedQuery("Country.pk", Short.class);
                countryQuery.setParameter("country", country);

                Short cityId = null;

                TypedQuery<Short> cityQuery = session.createNamedQuery("City.pk", Short.class);
                cityQuery.setParameter("city", city);

                Short customerId = null;

                TypedQuery<Short> customerQuery = session.createNamedQuery("Customer.pk", Short.class);
                customerQuery.setParameter("email", email);

                Short addressId = null;

                TypedQuery<Short> addressQuery = session.createNamedQuery("Customer.addressFk", Short.class);
                addressQuery.setParameter("email", email);


                try {
                    countryId = countryQuery.getSingleResult();
                    cityId = countryQuery.getSingleResult();
                    customerId =  customerQuery.getSingleResult();
                    addressId = addressQuery.getSingleResult();
                } catch (Exception ex) {
                    //Something
                }

                if (countryId != null) {
                    Country countryObj = session.get(Country.class, countryId);

                    City cityObj = session.get(City.class, cityId);
                    cityObj.setCity(city);
                    cityObj.setCountry(countryObj);
                    cityObj.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                    session.persist(cityObj);

                    try {
                        cityId = countryQuery.getSingleResult();
                    } catch (Exception ex) {
                        //
                    }

                    if(cityId != null){

                        Address getFirstAddress = session.get(Address.class, "1");
                        byte[] location = getFirstAddress.getLocation();


                        Address addressObj = session.get(Address.class, addressId);
                        addressObj.setAddress(address);
                        addressObj.setDistrict(district);
                        addressObj.setCity(cityObj);
                        addressObj.setPostalCode(postalCode);
                        addressObj.setPhone(phone);
                        addressObj.setLocation(location);
                        addressObj.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                        session.persist(addressObj);

                        Store store = session.get(Store.class, 1);
                        Customer customerObj = session.get(Customer.class, customerId);
                        customerObj.setStore(store);
                        customerObj.setFirstName(firstName);
                        customerObj.setLastName(lastName);
                        customerObj.setEmail(email);
                        customerObj.setAddress(addressObj);
                        customerObj.setActive((byte) 1);
                        customerObj.setCreateDate(new Timestamp(System.currentTimeMillis()));
                        customerObj.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                        session.persist(customerObj);


                        transaction.commit();
                    }

                }else{
                    System.out.println("No such country exists, please check your spelling");
                }

            }
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


    public static void readFromCustomers(List<Customer> customerList) {
        //Lägg till label för när kund finns
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = null;
        Transaction transaction = null;


        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            TypedQuery<Customer> query = session.createNamedQuery("Customer.table", Customer.class);

            List<Customer> foundCustomer = query.getResultList();
            customerList.addAll(foundCustomer);

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
    private void readFromCustomer(Label labelDuplicateCustomer, String email) {
        //Lägg till label för när kund finns
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = null;
        Transaction transaction = null;



        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Customer exisitngCustomer = session.get(Customer.class, email);
            TypedQuery<Customer> query = null;
            if (exisitngCustomer != null) {
                query = session.createNamedQuery("Customer.byEmail", Customer.class);
                query.setParameter("email", email);

                for (Customer row : query.getResultList()) {
                    //måste ändras
                    labelDuplicateCustomer.setText(String.valueOf(row));
                }
            } else {
                labelDuplicateCustomer.setText("En användare med den angivna e-postadressen " +
                        "finns redan i systemet.");
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

    private void removeCustomer(String email){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Customer customerExists = session.get(Customer.class, email);

            TypedQuery<Customer> query = null;
            TypedQuery<Long> queryCount = null;

            if(customerExists != null){
                Address address = customerExists.getAddress();
                short addressId = address.getAddressId();

                query = session.createNamedQuery("Customer.pk", Customer.class);
                query.setParameter("email", email);
                Customer customerId = (Customer) query.getResultList();

                queryCount = session.createNamedQuery("Customer.address", Long.class);
                queryCount.setParameter("address_id", addressId);

                long customerCount = queryCount.getSingleResult();

                if(customerCount == 1){
                    session.remove(addressId);
                }else{

                }

                if(!customerId.getRentalsByCustomerId().isEmpty()){
                        for(Rental rental : customerId.getRentalsByCustomerId()){

                            if(!customerId.getPaymentsByCustomerId().isEmpty()){
                                for(Payment payment : customerId.getPaymentsByCustomerId()){
                                    session.remove(payment);
                                }
                            }

                            session.remove(rental);
                        }
                        System.out.println("Dependent rows have been deleted");
                }

                session.remove(customerId);
            }

        }catch (Exception e) {
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

    public static void loadCustomersFromDatabase(List<Customer> customerList) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Customer> criteriaQuery = session.getCriteriaBuilder().createQuery(Customer.class);
            criteriaQuery.from(Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            root.fetch("address", JoinType.LEFT); // Hämta även adressuppgifter för varje kund
            criteriaQuery.select(root).distinct(true);
            List<Customer> customers = session.createQuery(criteriaQuery).getResultList();
            customerList.clear(); // Rensa befintliga kunder i listan
            customerList.addAll(customers); // Lägg till kunder från databasen i listan
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
    public void readFromCustomer2(String email, List<Customer> customerList) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            TypedQuery<Customer> query = session.createNamedQuery("Customer.byEmail", Customer.class);
            query.setParameter("email", email);

            List<Customer> resultList = query.getResultList();
            if (!resultList.isEmpty()) {

                Customer foundCustomer = resultList.getFirst();
                customerList.clear();
                customerList.add(foundCustomer); // Lägg till den hittade kunden i customerList
            } else {
                // Kund med angiven e-postadress finns inte

            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            // Visa felmeddelande


        }
    }
}
