package com.sandstrom.compositeKey;

import java.io.Serializable;
import java.util.Objects;

public class FilmActorPK implements Serializable {


    private int filmId;
    private short actorId;

    public FilmActorPK(int filmId, short actorId){
        this.actorId = actorId;
        this.filmId =  filmId;
    }

    public int getFilmIdfk() {
        return filmId;
    }

    public short getActorIdFk() {
        return actorId;
    }

    public void setFilmIdFk(int filmId) {
        this.filmId = filmId;
    }

    public void setActorIdFk(short actorId) {
        this.actorId = actorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmActorPK that = (FilmActorPK) o;
        return filmId == that.filmId && actorId == that.actorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId, actorId);
    }
}
