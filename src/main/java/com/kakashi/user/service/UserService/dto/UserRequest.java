package com.kakashi.user.service.UserService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserRequest {
    private String userId;

    private String name;
    private String email;

    private String about;
}
