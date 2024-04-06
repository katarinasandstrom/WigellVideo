package com.sandstrom.crudOperations;

import com.sandstrom.entities.*;
import jakarta.persistence.TypedQuery;
import javafx.scene.control.Label;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;

public class CrudOfCustomer {
    public void registerNewCustomer(Label labelDuplicateCustomer, String firstName, String lastName, String email, String country, String city,
                                    String address, String district, String postalCode, String phone){

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = null;
        Transaction transaction = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Customer exisitngCustomer = session.get(Customer.class, email);

            if(exisitngCustomer != null){
                labelDuplicateCustomer.setText("En kund med den angivna e-postadressen " +
                        "finns redan i systemet.");
            }else{

                String address2 = null;
                short city_id = 0;
                String location = null;
                Timestamp lastUpdate = Timestamp.valueOf("1111-11-11");
                Timestamp createDate = Timestamp.valueOf("1111-11-11");
                byte active = 1;
                Byte store_id = 0;

                Country countries = session.get(Country.class, country); // get the country that matches

                //Get country Id

                City cities = new City(); // get the city that matches or add new city
                cities.setCity(city);
                cities.setCountry(countries);
                cities.setLastUpdate(lastUpdate);


                City addCity = session.get(City.class, city);

                //get city Id
                Address addresses = new Address(); // get the address that matches or add new address
                addresses.setAddress(address);
                addresses.setAddress2(null);
                addresses.setDistrict(district);
                addresses.setCity(addCity);// set fk
                addresses.setPostalCode(postalCode);
                addresses.setPhone(phone);
                //addresses.setLocation(null);
                addresses.setLastUpdate(lastUpdate);

                //Get address id. need corresponding fk
                Address addAddress = session.get(Address.class, address);
                Store stores = session.get(Store.class, 1); // get the store which the customer is connected to.

                Customer customers = new Customer();
                customers.setStore(stores); // Set fk
                customers.setFirstName(firstName);
                customers.setLastName(lastName);
                customers.setEmail(email);
                customers.setAddress(addresses); // Set fk
                customers.setActive(active);
                customers.setCreateDate(createDate);
                customers.setLastUpdate(lastUpdate);

                session.persist(city);
                session.persist(addresses);
                session.persist(customers);

                transaction.commit();

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

    private void readFromCustomers(Label labelDuplicateCustomer, String email) {
        //Lägg till label för när kund finns
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = null;
        Transaction transaction = null;


        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            TypedQuery<Customer> query = session.createNamedQuery("Customer.table", Customer.class);
            for(Customer customer : query.getResultList()){
                    //måste ändras
                labelDuplicateCustomer.setText(String.valueOf(customer));
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
}
