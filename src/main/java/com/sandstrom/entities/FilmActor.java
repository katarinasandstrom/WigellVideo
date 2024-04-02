package com.sandstrom.entities;

import com.sandstrom.compositeKey.FilmActorPK;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@jakarta.persistence.Table(name = "film_actor", schema = "sakila")
@IdClass(FilmActorPK.class)
public class FilmActor {
    @Id
    @Column(name = "actor_id")
    private short actorId;

    public short getActorIdFk() {
        return actorId;
    }

    public void setActorIdFk(short actorId) {
        this.actorId = actorId;
    }


    @Id
    @Column(name = "film_id")
    private short filmId;

    public short getFilmIdFk() {
        return filmId;
    }

    public void setFilmIdFk(short filmId) {
        this.filmId = filmId;
    }

    @Basic
    @Column(name = "last_update")
    private Timestamp lastUpdate;

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "actor_id", referencedColumnName = "actor_id")
    private Actor actor;

    public Actor getActor(){
        return actor;
    }

    public void setActor(Actor actor){
        this.actor = actor;
    }

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "film_id", referencedColumnName = "film_id")
    private Film film;

    public Film getFilm(){
        return film;
    }

    public void setFilm(Film film){
        this.film = film;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmActor filmActor = (FilmActor) o;
        return Objects.equals(actorId, filmActor.actorId) && Objects.equals(filmId, filmActor.filmId) && Objects.equals(lastUpdate, filmActor.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorId, filmId, lastUpdate);
    }

    @Override
    public String toString() {
        return "FilmActor{" +
                "actorId=" + actorId +
                ", filmId=" + filmId +
                ", lastUpdate=" + lastUpdate +
                ", actor=" + actor +
                ", film=" + film +
                '}';
    }
}
