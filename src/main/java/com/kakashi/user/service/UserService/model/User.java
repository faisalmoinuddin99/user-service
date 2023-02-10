package com.kakashi.user.service.UserService.model;

import com.kakashi.user.service.UserService.dto.Hotel;
import com.kakashi.user.service.UserService.dto.Rating;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "tbl_user")
public class User {

    @Id
    @Column(name = "ID")
    private String userId;

    @Column(name = "NAME", length = 20)
    private String name;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ABOUT")
    private String about;

    @Transient // transient ka matlab, hibernate rating ko db mai store nahi krega
    private List<Rating> ratings = new ArrayList<>();


}
