package com.sandstrom.entities;

import jakarta.persistence.*;
import java.util.List;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "inventory", schema = "sakila")
public class Inventory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "inventory_id")
    private Integer inventoryId;
    @Basic
    @Column(name = "film_id", insertable=false, updatable=false)
    private short filmId;
    @Basic
    @Column(name = "store_id", insertable=false, updatable=false)
    private Byte storeId;
    @Basic
    @Column(name = "last_update")
    private Timestamp lastUpdate;


    @ManyToOne
    @JoinColumn(name = "store_id" , referencedColumnName = "store_id")
    private Store store;

    @OneToMany(mappedBy = "inventory")
    private List<Rental> rentals; //

    public Inventory() {
    }

    // Konstruktor med parametrar
    public Inventory(short filmId, Byte storeId, Timestamp lastUpdate) {
        this.filmId = filmId;
        this.storeId = storeId;
        this.lastUpdate = lastUpdate;
    }


    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public short getFilmId() {
        return filmId;
    }

    public void setFilmId(short filmId) {
        this.filmId = filmId;
    }

    public Byte getStoreId() {
        return storeId;
    }

    public void setStoreId(Byte storeId) {
        this.storeId = storeId;
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
        Inventory that = (Inventory) o;
        return Objects.equals(inventoryId, that.inventoryId) && Objects.equals(filmId, that.filmId) && Objects.equals(storeId, that.storeId) && Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryId, filmId, storeId, lastUpdate);
    }
}
