package com.example.demo.dto;

import com.example.demo.entities.User;
import org.springframework.beans.BeanUtils;

//O hashcode e equals não precisa aqui
public class UserDTO {
    private Long id;
    private String name;
    private String phone;
    private String password;

    public UserDTO(User user){
        this.name = user.getName();
        this.phone = user.getPhone();
        this.password = user.getPassword();
        this.id = user.getId();
    }

    UserDTO(){}

    public UserDTO(String name, String phone, String password) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.id = null;
    }

    public UserDTO(Long id, String name, String phone, String password){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
