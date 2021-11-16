package com.example.pma.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="user_accounts")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_accounts_seq")
    @Column(name = "user_id")
    private long userId;

    @NotBlank(message = "Must give a username")
    @Size(min=1, max=50)
    @Column(name = "username")
    private String userName;

    @NotBlank
    @Email(message = "Must give a valid email address")
    private String email;

    @NotBlank(message = "Must give a password")
    @Size(min=2, max=14)
    private String password;

    private boolean enabled = true;

    public UserAccount(){

    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
