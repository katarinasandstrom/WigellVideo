package com.sandstrom.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;


@Entity
@NamedNativeQuery(name = "Customer.table", query = "SELECT * FROM Customer", resultClass = Customer.class)
@NamedNativeQuery(name = "Customer.byEmail", query = "SELECT c.* FROM customer c WHERE c.email = :email ", resultClass = Customer.class)
@NamedNativeQuery(name = "Customer.pk", query = "SELECT c.customer_id from customer c WHERE c.email =: email", resultClass = Customer.class)
@NamedNativeQuery(name = "Customer.address", query = "SELECT COUNT(c.address_id) FROM Customer c WHERE c.address_id = :address_id", resultClass = Long.class)
//@NamedNativeQuery(name = "Customer.")
@Table(name = "customer", schema = "sakila")
public class Customer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "customer_id")
    private short customerId;
    //forpush
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
    @NaturalId
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

    @OneToMany(mappedBy = "customer")
    private List<Payment> paymentsByCustomerId;

    @ManyToOne()
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;

    @ManyToOne()
    @JoinColumn(name = "store_id", referencedColumnName = "store_id")
    private Store store;

    @OneToMany(mappedBy = "customer")
    private List<Rental> rentalsByCustomerId;

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

    public List<Payment> getPaymentsByCustomerId() {
        return paymentsByCustomerId;
    }

    public void setPaymentsByCustomerId(List<Payment> payments) {
        this.paymentsByCustomerId = payments;
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

    public List<Rental> getRentalsByCustomerId() {
        return rentalsByCustomerId;
    }

    public void setRentalsByCustomerId(List<Rental> rentals) {
        this.rentalsByCustomerId = rentals;
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

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", storeId=" + storeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", addressId=" + addressId +
                ", address=" + address +
                ", active=" + active +
                ", createDate=" + createDate +
                ", lastUpdate=" + lastUpdate +
                '}';
    }

        /*", paymentsByCustomerId=" + paymentsByCustomerId +
                ", address=" + address +
                ", store=" + store +
                ", rentalsByCustomerId=" + rentalsByCustomerId +*/
}
