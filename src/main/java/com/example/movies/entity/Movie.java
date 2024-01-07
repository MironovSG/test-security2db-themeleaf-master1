package com.example.movies.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "movies")
public class Movie {
    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Title", nullable = false)
    @NotEmpty(message = "Это поле не может быть пустым")
    @Size(min=2, max=30)
    private String title;

    @NotEmpty(message = "Это поле не может быть пустым")
    @Column(name = "Genre", nullable = false)
    private String genre;

    @NotEmpty(message = "Это поле не может быть пустым")
    @Column(name = "Director", nullable = false)
    private String director;

    @NotEmpty(message = "Это поле не может быть пустым")
    @Column(name = "Duration", nullable = false)
    private String duration;

    @NotEmpty(message = "Это поле не может быть пустым")
    @Column(name = "MainActors", nullable = false)
    private String mainActors;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ReleaseDate")
    private Date releaseDate;

    @NotNull(message = "Номер в коллекции не может быть равным 0")
    @Min(1)
    @Column(name = "NumberCollections", nullable = false)
    private int numberCollections;
}

