package com.example.demo.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "First name is required")
    private String firstName;

    private String lastName;

    @NotEmpty(message = "email is required")
    @Email
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "Mobile number is required")
    @Pattern(regexp="^(\\d{3}[- ]?){2}\\d{4}$", message="Mobile number is invalid")
    private String mobileNo;

    @Min(value = 5, message = "Age must be between 5-100")
    @Max(value = 100, message = "Age must be between 5-100")
    private int age;

    private String gender;

    private String address;

    @NotEmpty(message = "Password is required")
    @Size(min = 8, message="Password must be atleast 8 characters long.")
    private String password;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId().equals(user.getId()) && getFirstName().equals(user.getFirstName()) && Objects.equals(getLastName(), user.getLastName()) && getEmail().equals(user.getEmail()) && getMobileNo().equals(user.getMobileNo()) && getGender().equals(user.getGender()) && Objects.equals(getAddress(), user.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getEmail(), getMobileNo(), getGender(), getAddress());
    }
}
