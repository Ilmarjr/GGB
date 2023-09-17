package br.edu.unijorge.GGB.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
public class NewsDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;    
    private String subTitle;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    private List<String> tags;
    private String mainPicture;
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsDTO newsDTO = (NewsDTO) o;
        return Objects.equals(id, newsDTO.id) && Objects.equals(title, newsDTO.title) && Objects.equals(date, newsDTO.date) && Objects.equals(tags, newsDTO.tags) && Objects.equals(mainPicture, newsDTO.mainPicture) && Objects.equals(description, newsDTO.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, date, tags, mainPicture, description);
    }
}
