package com.sandstrom.entities;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@NamedNativeQuery(name = "Country.pk", query = "SELECT c.country_id from country c WHERE c.country =:country", resultClass = Short.class)
@Entity
@Table(name = "country", schema = "sakila")
public class Country {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "country_id")
    private short countryId;

    @Basic
    @Column(name = "country")
    private String country;
    @Basic
    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @OneToMany(mappedBy = "country")
    private List<City> citiesByCountryId = new ArrayList<>();

    public List<City> getCitiesByCountryId() {
        return citiesByCountryId;
    }

    public void setCitiesByCountryId(List<City> citiesByCountryId) {
        this.citiesByCountryId = citiesByCountryId;
    }

    public Country() {
    }

    // Konstruktor med parametrar
    public Country(String country, Timestamp lastUpdate) {
        this.country = country;
        this.lastUpdate = lastUpdate;
    }

    public short getCountryId() {
        return countryId;
    }

    public void setCountryId(short countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country that = (Country) o;
        return Objects.equals(countryId, that.countryId) && Objects.equals(country, that.country) && Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryId, country, lastUpdate);
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryId=" + countryId +
                ", country='" + country + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}

