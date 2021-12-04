package com.example.demo.domain.DTO;

import java.util.Objects;

public class UserDTO {

    String email;

    String password;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(getEmail(), userDTO.getEmail()) && Objects.equals(getPassword(), userDTO.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPassword());
    }
}
