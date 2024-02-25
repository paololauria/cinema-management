package com.paololauria.cinema.model.entities;
import jakarta.persistence.*;
import java.util.List;
@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(generator = "genre_id_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "genre_id_generator", sequenceName = "genre_id_seq", allocationSize = 1)
    @Column(name = "genre_id")
    private long id;
    @Column(name = "genre_name")
    private String genreName;
    @ManyToMany(mappedBy = "genres")
    private List<Film> films;
    public Genre() {
    }
    public Genre(long id, String genreName, List<Film> films) {
        this.id = id;
        this.genreName = genreName;
        this.films = films;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getGenreName() {
        return genreName;
    }
    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
    public List<Film> getFilms() {
        return films;
    }
    public void setFilms(List<Film> films) {
        this.films = films;
    }
}