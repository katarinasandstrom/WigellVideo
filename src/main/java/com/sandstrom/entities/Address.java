package com.sandstrom.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;;

    @Entity
    @Table(name = "address", schema = "sakila")
    public class Address {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "address_id")
        private int addressId;

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
        @Column(name = "city_id")
        private int cityId;

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

        @ManyToOne
        @JoinColumn(name = "store_id", referencedColumnName = "store_id")
        private Store store;

        @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
        private List<Staff> staff;

        public int getAddressId() {
            return addressId;
        }

        public void setAddressId(int addressId) {
            this.addressId = addressId;
        }

        public Store getStore() {
            return store;
        }

        public void setStore(Store store) {
            this.store = store;
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

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
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
                    ", store=" + store +
                    '}';
        }
    }

