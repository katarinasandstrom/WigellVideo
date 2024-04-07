package com.sandstrom.crudOperations;

import com.sandstrom.entities.Actor;
import com.sandstrom.entities.Film;
import com.sandstrom.entities.Language;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class CrudOfFilm {
    private final SessionFactory sessionFactory;

    public CrudOfFilm(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createFilm(String title, int releaseYear, int rentalDuration, BigDecimal rentalRate, BigDecimal replacementCost, int length, String rating, String specialFeatures, Timestamp lastUpdate, Language language, List<Actor> actors) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Film film = new Film();
            film.setTitle(title);
            film.setReleaseYear(releaseYear);
            film.setRentalDuration(rentalDuration);
            film.setRentalRate(rentalRate);
            film.setReplacementCost(replacementCost);
            film.setLength(length);
            film.setRating(rating);
            film.setSpecialFeatures(specialFeatures);
            film.setLastUpdate(lastUpdate);
            film.setLanguage(language);

            for (Actor actor : actors) {
              //  film.addActor(actor);
            }

            session.save(film);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
