package br.edu.unijorge.GGB.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(name = "DATE")
    private String date;
    @Column(name = "IMAGE")
    private String image;
    @Column(name = "DESCRIPTION")
    private String description;

}
