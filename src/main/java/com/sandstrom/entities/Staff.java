package com.sandstrom.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@NamedNativeQuery(name = "Staff.byEmail", query = "SELECT c.* FROM staff c WHERE c.email = :email ", resultClass = Staff.class)
@NamedNativeQuery(name = "Staff.table", query = "SELECT * FROM staff", resultClass = Staff.class)
@NamedNativeQuery(name = "Staff.byUsername", query = "SELECT s.* FROM staff s WHERE s.username = :username ", resultClass = Staff.class)
@NamedNativeQuery(name = "Staff.pk", query = "SELECT s.staff_id from staff s WHERE s.username =:username", resultClass = Byte.class)
@Table(name = "staff", schema = "sakila")
public class Staff {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @NaturalId
    @Column(name = "staff_id")
    private Byte staffId;

    @Basic
    @Column(name = "first_name")
    private String firstName;

    @Basic
    @Column(name = "last_name")
    private String lastName;


    @Basic
    @Column(name = "address_id", insertable=false, updatable=false)
    private short addressId;

    @Basic
    @Column(name = "picture")
    private byte[] picture;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "store_id", insertable=false, updatable=false)
    private Byte storeId;

    @Basic
    @Column(name = "active")
    private byte active;

    @Basic
    @NaturalId
    @Column(name = "username")
    private String username;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "store_id", referencedColumnName = "store_id")
    private Store store;

    @OneToMany(mappedBy = "staff")
    private List<Store> managedStores;

    @OneToMany(mappedBy = "staff")
    private List<Payment> payments;

    @OneToMany(mappedBy = "staff")
    private List<Rental> rentals;

    public Staff() {
    }

    public Staff(Byte staffId, String firstName, String lastName, short addressId, byte[] picture, String email, Byte storeId, byte active, String username, String password, Timestamp lastUpdate) {
        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressId = addressId;
        this.picture = picture;
        this.email = email;
        this.storeId = storeId;
        this.active = active;
        this.username = username;
        this.password = password;
        this.lastUpdate = lastUpdate;
    }


    public Byte getStaffId() {
        return staffId;
    }

    public void setStaffId(Byte staffId) {
        this.staffId = staffId;
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

    public short getAddressId() {
        return addressId;
    }

    public void setAddressId(short addressId) {
        this.addressId = addressId;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Byte getStoreId() {
        return storeId;
    }

    public void setStoreId(Byte storeId) {
        this.storeId = storeId;
    }

    public byte getActive() {
        return active;
    }

    public void setActive(byte active) {
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
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

    public List<Store> getManagedStores() {
        return managedStores;
    }

    public void setManagedStores(List<Store> managedStores) {
        this.managedStores = managedStores;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
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
        Staff that = (Staff) o;
        return active == that.active && Objects.equals(staffId, that.staffId) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(addressId, that.addressId) && Arrays.equals(picture, that.picture) && Objects.equals(email, that.email) && Objects.equals(storeId, that.storeId) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(staffId, firstName, lastName, addressId, email, storeId, active, username, password, lastUpdate);
        result = 31 * result + Arrays.hashCode(picture);
        return result;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffId=" + staffId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", addressId=" + addressId +
                ", picture=" + Arrays.toString(picture) +
                ", email='" + email + '\'' +
                ", storeId=" + storeId +
                ", active=" + active +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", address=" + address +
                ", store=" + store +
                ", managedStores=" + managedStores +
                ", payments=" + payments +
                ", rentals=" + rentals +
                '}';
    }
}
