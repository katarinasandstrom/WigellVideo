package com.sandstrom.crudOperations;

import com.sandstrom.entities.*;
import com.sandstrom.entities.Film;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class CrudOfFilm {
    private final SessionFactory sessionFactory;

    public CrudOfFilm() {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }



    public void createFilm(String title, int releaseYear, String description, int rentalDuration, BigDecimal rentalRate,
                           BigDecimal replacementCost, int length, String rating, String specialFeatures,
                           Timestamp lastUpdate, Language language) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Film film = new Film();
            film.setTitle(title);
            film.setReleaseYear(releaseYear);
            film.setDescription(description);
            film.setRentalDuration(rentalDuration);
            film.setRentalRate(rentalRate);
            film.setReplacementCost(replacementCost);
            film.setLength(length);
            film.setRating(rating);
            film.setSpecialFeatures(specialFeatures);
            film.setLastUpdate(lastUpdate);
            film.setLanguage(language);

            /*
            for (Actor actor : actors) {
              //  film.addActor(actor);
            }

             */

            session.save(film);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void readFromFilms2(String title, List<Film> filmList) {
        //Lägg till label för när kund finns
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = null;
        Transaction transaction = null;


        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            TypedQuery<Film> query = session.createNamedQuery("Film.byTitle", Film.class);
            query.setParameter("title", title);

            List<Film> resultList = query.getResultList();
            if (!resultList.isEmpty()) {

                Film foundFilm = resultList.getFirst();
                filmList.clear();
                filmList.add(foundFilm); // Lägg till den hittade kunden i customerList
            } else {
                // Kund med angiven e-postadress finns inte

            }

            transaction.commit();
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

    public static void readFromFilms(List<Film> filmList) {
        //Lägg till label för när kund finns
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = null;
        Transaction transaction = null;


        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            TypedQuery<Film> query = session.createNamedQuery("Film.table", Film.class);

            List<Film> foundFilm = query.getResultList();
            filmList.addAll(foundFilm);

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


    public static void loadFilmsFromDatabase(List<Film> filmList) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Film> criteriaQuery = session.getCriteriaBuilder().createQuery(Film.class);
            criteriaQuery.from(Film.class);
            Root<Film> root = criteriaQuery.from(Film.class);
            root.fetch("language", JoinType.LEFT);
            criteriaQuery.select(root).distinct(true);
            List<Film> films = session.createQuery(criteriaQuery).getResultList();
            filmList.clear();
            filmList.addAll(films);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }



}
