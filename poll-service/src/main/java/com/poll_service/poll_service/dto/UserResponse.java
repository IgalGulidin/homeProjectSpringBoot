package com.poll_service.poll_service.dto;

import java.time.LocalDate;

public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private String address;
    private LocalDate joiningDate;

    public UserResponse() {};

    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public Integer getAge() {
        return this.age;
    }

    public String getAddress() {
        return this.address;
    }

    public LocalDate getJoiningDate() {
        return this.joiningDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }
}
