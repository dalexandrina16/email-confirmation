package com.utm.tw.entity;

import com.utm.tw.entity.dto.RegisterDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user_tb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    private String role;

    public User(RegisterDTO registerDTO) {
        this.username = registerDTO.getUsername();
        this.email = registerDTO.getEmail();
        this.password = registerDTO.getPassword();
        this.role = "chatter";
    }

}
