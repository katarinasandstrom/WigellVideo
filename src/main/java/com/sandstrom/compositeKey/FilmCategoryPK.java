package com.sandstrom.compositeKey;

import java.io.Serializable;
import java.util.Objects;

public class FilmCategoryPK implements Serializable {
    private short filmId;
    private Byte categoryId;


    public FilmCategoryPK(short filmId, Byte categoryId) {
        this.filmId = filmId;
        this.categoryId = categoryId;
    }

    public short getFilmId() {
        return filmId;
    }

    public Byte getCategoryId() {
        return categoryId;
    }

    public void setFilmId(short filmId) {
        this.filmId = filmId;
    }

    public void setCategoryId(Byte categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmCategoryPK that = (FilmCategoryPK) o;
        return filmId == that.filmId && Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId, categoryId);
    }
}
