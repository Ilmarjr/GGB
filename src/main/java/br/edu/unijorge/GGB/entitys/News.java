package br.edu.unijorge.GGB.entitys;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DATE", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "DESCRIPTION")
    private String description;

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDate.now());
    }


}
