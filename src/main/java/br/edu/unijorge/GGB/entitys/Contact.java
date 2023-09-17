package br.edu.unijorge.GGB.entitys;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Contact implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "EMAIL", updatable = false)
    private String email;

    @Column(name = "VALID")
    private boolean valid;

    @PrePersist
    public void prePersist(){
        setEmail(getEmail().toLowerCase());
    }
}