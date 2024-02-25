package com.paololauria.cinema.dtos;


import com.paololauria.cinema.model.entities.Genre;

public class GenreDto {
    private long id;
    private String genreName;

    public GenreDto() {
    }

    public GenreDto(Genre genre) {
        this.id = genre.getId();
        this.genreName = genre.getGenreName();
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
}
