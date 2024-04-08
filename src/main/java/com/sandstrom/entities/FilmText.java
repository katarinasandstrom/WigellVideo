package com.sandstrom.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "film_text", schema = "sakila")
public class FilmText {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "film_id")
    private short filmId;
    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "description")
    private String description;

    public FilmText() {
    }

    public FilmText(short filmId, String title, String description) {
        this.filmId = filmId;
        this.title = title;
        this.description = description;
    }


    public short getFilmId() {
        return filmId;
    }

    public void setFilmId(short filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmText that = (FilmText) o;
        return Objects.equals(filmId, that.filmId) && Objects.equals(title, that.title) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId, title, description);
    }
}
