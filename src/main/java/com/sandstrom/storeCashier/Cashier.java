package com.sandstrom.storeCashier;

import com.sandstrom.entities.Address;
import com.sandstrom.entities.Customer;
import com.sandstrom.entities.Film;
import com.sandstrom.entities.Staff;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.util.List;

public class Cashier {

    private SessionFactory sessionFactory;
    public Cashier(){
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    public void filmRental(short customerNumber, Byte staffNumber, short filmNumber, int daysOfRental){

        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Customer customerFound = session.get(Customer.class, customerNumber);
            Staff staffFound = session.get(Staff.class, staffNumber);
            Film filmFound = session.get(Film.class, filmNumber);

            String customerDetails = "Kunduppgifter:\n" + customerFound.getFirstName() + "\n" + customerFound.getLastName() + "\n" + customerFound.getEmail();
            String filmDetails = "Filmuppgifter:\n" + filmFound.getTitle() + "\n" + filmFound.getFilmCategoriesByFilmId() + "\n";

            System.out.println(customerDetails + "\n" + filmDetails);


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
    public BigDecimal rentalPrice( short filmNumber, int daysOfRental){


        return null;
    }
}
