package org.tdb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tdb_user")
@JsonIgnoreProperties(value = { "password" })
public class User extends BaseEntity {

    private String email;

    private String password;

    public User() {
    }

    public User(String email, String password) {
        super(email);
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password) {
        super(name);
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
