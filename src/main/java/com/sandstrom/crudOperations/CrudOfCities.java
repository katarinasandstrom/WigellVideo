package com.sandstrom.crudOperations;

import com.sandstrom.entities.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


import java.util.List;
import java.util.Scanner;

public class CrudOfCities implements CrudOfCityInterface{
    private Scanner scanner = new Scanner(System.in);
    private City city = new City();
    @Override
    public void getAllCities() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<City> cities = session.createQuery("FROM City ", City.class).list();
        for (City city : cities){
            System.out.println("City-id är: " + city.getCityId() + " stan: " + city.getCity() + " landet: " + city.getCountryId() +
                    "senaste uppdatering: " + city.getLastUpdate());
        }

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void getCityById() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        city = session.get(City.class, getAddressIdFromUser());
        System.out.println("City-id är: " + city.getCityId() + " stan: " + city.getCity() + " landet: " + city.getCountryId() +
                "senaste uppdatering: " + city.getLastUpdate());

        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void saveCity() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        city.setCityId(getGeneratedId());
        System.out.println("Stadens namn: ");
        city.setCity(scanner.nextLine());


        session.persist(city);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void deleteCity() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();


        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void updateCity() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();


        session.getTransaction().commit();
        session.close();

    }

    private int getAddressIdFromUser(){
        System.out.println("Skriv city-Id: du vill hämta? ");
        int getAddressIdFromUser = scanner.nextInt();
        return getAddressIdFromUser;
    }

    private int idGenerating(){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Integer> query = session.createQuery("SELECT max(c.cityId) FROM City c", Integer.class);
        Integer latestId = query.uniqueResult();
        session.getTransaction().commit();
        return latestId != null ? latestId : 0;
    }

    private short getGeneratedId(){
        return (short) (idGenerating() + 1);
    }
}

