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
    private int inventoryId;
    @Basic
    @Column(name = "film_id")
    private int filmId;
    @Basic
    @Column(name = "store_id")
    private int storeId;
    @Basic
    @Column(name = "last_update")
    private Timestamp lastUpdate;


    @ManyToOne
    @JoinColumn(name = "store_id" , referencedColumnName = "store_id")
    private Store store;

    @OneToMany(mappedBy = "inventory")
    private List<Rental> rentals; //



    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
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
