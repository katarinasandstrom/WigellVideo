package com.sandstrom.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "store", schema = "sakila")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Byte storeId;
    //forpush
    @Basic
    @Column(name = "manager_staff_id", insertable=false, updatable=false)
    private Byte managerStaffId;


    @Basic
    @Column(name = "address_id", insertable=false, updatable=false)
    private short addressId;

    @Basic
    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @ManyToOne(cascade = CascadeType.REMOVE)// Many-To-One store to staff
    @JoinColumn(name = "manager_staff_id", referencedColumnName = "staff_id")
    private Staff staff;

    @ManyToOne(cascade = CascadeType.ALL)// Many-To-One store to address
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;

    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;

    }


    @OneToMany(mappedBy = "store")// One-To-Many store to staff
    private Collection<Inventory> staffByStoreId = new ArrayList<>();

    public Collection<Inventory> getStaffByStoreId() {
        return staffByStoreId;
    }

    public void setStaffByStoreId(Collection<Inventory> staffByStoreId) {
        this.staffByStoreId = staffByStoreId;
    }


    @OneToMany(mappedBy = "store")// One-To-Many store to inventory
    private Collection<Inventory> inventoriesByStoreId = new ArrayList<>();

    public Collection<Inventory> getInventoriesByStoreId() {
        return inventoriesByStoreId;
    }

    public void setInventoriesByStoreId(Collection<Inventory> inventoriesByStoreId) {
        this.inventoriesByStoreId = inventoriesByStoreId;
    }

    public Store() {
    }

    // Konstruktor med parametrar
    public Store(Byte managerStaffId, short addressId, Timestamp lastUpdate) {
        this.managerStaffId = managerStaffId;
        this.addressId = addressId;
        this.lastUpdate = lastUpdate;
    }

    public Byte getStoreId() {
        return storeId;
    }

    public void setStoreId(Byte storeId) {
        this.storeId = storeId;
    }

    public Byte getManagerStaffId() {
        return managerStaffId;
    }

    public void setManagerStaffId(Byte managerStaffId) {
        this.managerStaffId = managerStaffId;
    }

    public short getAddressId() {
        return addressId;
    }

    public void setAddressId(short addressId) {
        this.addressId = addressId;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return storeId == store.storeId &&
                managerStaffId == store.managerStaffId &&
                addressId == store.addressId &&
                Objects.equals(lastUpdate, store.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, managerStaffId, addressId, lastUpdate);
    }

    @Override
    public String toString() {
        return "Store{" +
                "storeId=" + storeId +
                ", managerStaffId=" + managerStaffId +
                ", addressId=" + addressId +
                ", lastUpdate=" + lastUpdate +
                ", staff=" + staff +
                ", address=" + address +
                ", staffByStoreId=" + staffByStoreId +
                ", inventoriesByStoreId=" + inventoriesByStoreId +
                '}';
    }
}
