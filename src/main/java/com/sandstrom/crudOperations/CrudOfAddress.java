package com.sandstrom.crudOperations;
import com.sandstrom.entities.Address;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


import java.util.List;
import java.util.Scanner;
public class CrudOfAddress implements CrudOfAddressInterface{
    private Address addressEntity = new Address();
    private Scanner scanner = new Scanner(System.in);
    @Override
    public void getAllAddresses() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Address> addresses = session.createQuery("FROM Address", Address.class).list();
        for(Address address : addresses){
            System.out.println("address Id är: " + address.getAddressId() + " address 1: "+ address.getAddress() + " address 2: " + address.getAddress2() +
                    " distrikt: " + address.getDistrict() + "stans Id: " + address.getCityId() + " post adress: " + address.getPostalCode() +
                    " telefonnummer: " + address.getPhone() + " location: " + address.getLocation() + " senaste uppdatering: " + address.getLastUpdate());
        }

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void getAddressById() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        addressEntity = session.get(Address.class, getAddressIdFromUser());

        System.out.println("address Id är: " + addressEntity.getAddressId() + " address 1: "+ addressEntity.getAddress() + " address 2: " +
                addressEntity.getAddress2() + " distrikt: " + addressEntity.getDistrict() + "stans Id: " + addressEntity.getCityId() +
                " post adress: " + addressEntity.getPostalCode() + " telefonnummer: " + addressEntity.getPhone() +
                " location: " + addressEntity.getLocation() + " senaste uppdatering: " + addressEntity.getLastUpdate());

        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void saveAddress() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        addressEntity.setAddressId(getGeneratedId());
        System.out.println("Skriv in address 1");
        addressEntity.setAddress(scanner.nextLine());
        System.out.println("Skriv in address 2"); // vi behöver kanske inte det?
        addressEntity.setAddress2(scanner.nextLine());
        System.out.println("Skriv in disrikten");
        addressEntity.setDistrict(scanner.nextLine());
        System.out.println("Stans id genereras automatiskt");
        // addressEntity.setCityId(scanner.nextLine());
        System.out.println("Skriv in post-address");
        addressEntity.setPostalCode(scanner.nextLine());
        System.out.println("Skriv in telefonnumret");
        addressEntity.setPhone(scanner.nextLine());

        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void deleteAddress() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        System.out.println("Skriv adress-id du vill radera?");
        int address_id = scanner.nextInt();
        Address address = session.get(Address.class, address_id);
        if(address != null){
            session.delete(address);
            System.out.println("Addressen har raderats");
        }else{
            System.out.println("Address med address-id" + address_id + " hittades inte i databasen.");
        }

        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void updateAddress() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.getTransaction().commit();
        session.close();

    }

    private int getAddressIdFromUser(){
        System.out.println("Skriv address-Id: du vill hämta? ");
        int getAddressIdFromUser = scanner.nextInt();
        return getAddressIdFromUser;
    }

    private int idGenerating(){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Integer> query = session.createQuery("SELECT max(a.addressId) FROM Address a", Integer.class);
        Integer latestId = query.uniqueResult();
        session.getTransaction().commit();
        return latestId != null ? latestId : 0;
    }

    private int getGeneratedId(){
        return idGenerating() + 1;
    }


}
