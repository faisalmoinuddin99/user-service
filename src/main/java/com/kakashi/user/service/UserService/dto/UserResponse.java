package com.kakashi.user.service.UserService.dto;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserResponse {
    private String userId;

    private String name;
    private String email;

    private String about;
}
