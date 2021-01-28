package com.utm.tw.entity.dto;

import com.utm.tw.entity.User;
import lombok.Data;

@Data
public class UserFromDBDTO {
    private String username;
    private String email;

    public UserFromDBDTO(User chatter) {
        this.username = chatter.getUsername();
        this.email = chatter.getEmail();
    }
}
