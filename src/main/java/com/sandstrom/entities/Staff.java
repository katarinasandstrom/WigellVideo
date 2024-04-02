package com.sandstrom.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

@Entity
public class Staff {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Byte staff_id;
    @NaturalId
    private String username;
    private String password;

    public Staff() {

    }

    public Staff(Byte staff_id, String username, String password) {
        this.staff_id = staff_id;
        this.username = username;
        this.password = password;
    }

    public Byte getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(Byte staff_id) {
        this.staff_id = staff_id;
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
}
