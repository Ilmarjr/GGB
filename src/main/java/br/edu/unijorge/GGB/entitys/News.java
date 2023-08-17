package br.edu.unijorge.GGB.entitys;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class News implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DATE", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;


    @OneToMany(mappedBy = "news",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Tags> tags;

    @Column(name = "MAINPICTURE")
    private String mainPicture;

    @Column(name = "DESCRIPTION")
    private String description;

    @PrePersist
    public void prePersist(){
        setDate(LocalDate.now());
    }


}
