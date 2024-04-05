package com.sandstrom.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;


@Entity
@NamedNativeQuery(name = "Customer.table", query = "SELECT * FROM Customer", resultClass = Customer.class)
//@NamedNativeQuery(name = "Customer.")
@Table(name = "customer", schema = "sakila")
public class Customer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "customer_id")
    private short customerId;

    @Basic
    @Column(name = "store_id", insertable=false, updatable=false)
    private Byte storeId;

    @Basic
    @Column(name = "first_name")
    private String firstName;

    @Basic
    @Column(name = "last_name")
    private String lastName;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "address_id", insertable=false, updatable=false)
    private short addressId;

    @Basic
    @Column(name = "active")
    private byte active;

    @Basic
    @Column(name = "create_date")
    private Timestamp createDate;

    @Basic
    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Payment> payments;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Rental> rentals;

    public Customer() {
    }

    public Customer( Byte storeId, String firstName, String lastName, String email, short addressId, byte active, Timestamp createDate, Timestamp lastUpdate) {
        this.storeId = storeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.addressId = addressId;
        this.active = active;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
    }

    public short getCustomerId() {
        return customerId;
    }

    public void setCustomerId(short customerId) {
        this.customerId = customerId;
    }

    public Byte getStoreId() {
        return storeId;
    }

    public void setStoreId(Byte storeId) {
        this.storeId = storeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public short getAddressId() {
        return addressId;
    }

    public void setAddressId(short addressId) {
        this.addressId = addressId;
    }

    public byte getActive() {
        return active;
    }

    public void setActive(byte active) {
        this.active = active;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer that = (Customer) o;
        return active == that.active && customerId == that.customerId && storeId == that.storeId && addressId == that.addressId && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(createDate, that.createDate) && Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, storeId, firstName, lastName, email, addressId, active, createDate, lastUpdate);
    }
}
