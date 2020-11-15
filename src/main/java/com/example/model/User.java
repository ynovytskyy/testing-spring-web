package com.example.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    public enum Authority {
        USER, ADMIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String birthday;

    @Lob
    @Column(columnDefinition="LONGBLOB")
    private String idImg;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Authority authority;

    private Date registrationDate;
}
