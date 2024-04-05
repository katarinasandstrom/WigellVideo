package com.sandstrom.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Objects;;

@Entity
@Table(name = "address", schema = "sakila")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private short addressId;

    @Basic
    @Column(name = "address")
    private String address;

    @Basic
    @Column(name = "address2")
    private String address2;

    @Basic
    @Column(name = "district")
    private String district;

    @Basic
    @Column(name = "city_id", insertable=false, updatable=false)
    private short cityId;

    @Basic
    @Column(name = "postal_code")
    private String postalCode;

    @Basic
    @Column(name = "phone")
    private String phone;

    @Basic
    @Column(name = "location")
    private String location;

    @Basic
    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "city_id", referencedColumnName = "city_id")
    private City city;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }


    @OneToMany(mappedBy = "address")
    private Collection<Staff> staffByAddresId;

    public Collection<Staff> getStaffByAddresId() {
        return staffByAddresId;
    }

    public void setStaffByAddresId(Collection<Staff> staffByAddresId) {
        this.staffByAddresId = staffByAddresId;
    }

    @OneToMany(mappedBy = "address")
    private Collection<Customer> customersByAddresId;

    public Collection<Customer> getCustomersByAddresId() {
        return customersByAddresId;
    }

    public void setStoresByAddressId(Collection<Store> storesByAddressId) {
        this.storesByAddressId = storesByAddressId;
    }

    public void setCustomersByAddresId(Collection<Customer> customersByAddresId) {
        this.customersByAddresId = customersByAddresId;
    }

    public Collection<Store> getStoresByAddressId() {
        return storesByAddressId;
    }

    @OneToMany(mappedBy = "address")
    private Collection<Store> storesByAddressId;

    public Address(String address, String address2, String district, short cityId, String postalCode, String phone, String location, Timestamp lastUpdate) {
        this.address = address;
        this.address2 = address2;
        this.district = district;
        this.cityId = cityId;
        this.postalCode = postalCode;
        this.phone = phone;
        this.location = location;
        this.lastUpdate = lastUpdate;
    }

    public Address(){

    }

    public short getAddressId() {
        return addressId;
    }

    public void setAddressId(short addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public short getCityId() {
        return cityId;
    }

    public void setCityId(short cityId) {
        this.cityId = cityId;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
        Address that = (Address) o;
        return addressId == that.addressId &&
                cityId == that.cityId &&
                Objects.equals(address, that.address) &&
                Objects.equals(address2, that.address2) &&
                Objects.equals(district, that.district) &&
                Objects.equals(postalCode, that.postalCode) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(location, that.location) &&
                Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, address, address2, district, cityId, postalCode, phone, location, lastUpdate);
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", address='" + address + '\'' +
                ", address2='" + address2 + '\'' +
                ", district='" + district + '\'' +
                ", cityId=" + cityId +
                ", postalCode='" + postalCode + '\'' +
                ", phone='" + phone + '\'' +
                ", location='" + location + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", city=" + city +
                '}';
    }
}