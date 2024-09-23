package com.example.proyecto2.domain;

import com.example.proyecto2.exception.BadRequestException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "client")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    @Column(name = "issuer")
    private String issuer;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "cellphone")
    private String cellphone;

    public void validate() {
        if (this.name == null || this.name.isEmpty()) {
            throw new BadRequestException("The name is required");
        }
        if (this.issuer == null || this.issuer.isEmpty()) {
            throw new BadRequestException("The issuer is required");
        }
        if (this.email == null || this.email.isEmpty()) {
            throw new BadRequestException("The email is required");
        }
        if (this.cellphone == null || this.cellphone.isEmpty()) {
            throw new BadRequestException("The cellphone is required");
        }
    }

    // Getters and setters were generated using Lombok with the @Getter and @Setter annotations
}
