package com.revature.POJOS;

import java.util.Objects;

//(8.18.22) This class started with userId, username, email, & password.
public class UserPojo {
    private Integer userId;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private boolean administrator;

    public UserPojo() {
    }

    public UserPojo(Integer userId, String username, String email, String password, String firstName, String lastName, boolean administrator) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.administrator = administrator;
    }

    public UserPojo(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UserPojo(String credentials, String password) {
        this.email = credentials;
        this.username = credentials;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPojo userPojo = (UserPojo) o;
        return Objects.equals(userId, userPojo.userId) && Objects.equals(username, userPojo.username)
                && Objects.equals(email, userPojo.email) && Objects.equals(password, userPojo.password) &&
                Objects.equals(firstName, userPojo.firstName) && Objects.equals(lastName, userPojo.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, email, password, firstName, lastName);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}