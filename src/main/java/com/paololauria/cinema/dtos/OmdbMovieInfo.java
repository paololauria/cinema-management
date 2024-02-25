package com.paololauria.cinema.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paololauria.cinema.model.entities.Film;

import java.io.IOException;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OmdbMovieInfo {
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Plot")
    private String plot;
    @JsonProperty("Runtime")
    private String runtime;
    @JsonProperty("Released")
    private String released;
    @JsonProperty("Actors")
    private String actors;
    @JsonProperty("Director")
    private String director;
    @JsonProperty("Poster")
    private String poster;
    @JsonProperty("Awards")
    private String awards;
    @JsonProperty("imdbVotes")
    private String imdbVotes;
    @JsonProperty("imdbRating")
    private String imdbRating;
    @JsonProperty("BoxOffice")
    private String boxOffice;


    public OmdbMovieInfo() {
    }

    public OmdbMovieInfo(String title, String plot,
                         String released, String director,
                         String poster, String awards,
                         String imdbRating, String boxOffice,
                         String runtime, String actors,
                         String imdbVotes) {
        this.title = title;
        this.plot = plot;
        this.released = released;
        this.director = director;
        this.poster = poster;
        this.awards = awards;
        this.imdbRating = imdbRating;
        this.boxOffice = boxOffice;
        this.runtime = runtime;
        this.actors = actors;
        this.imdbVotes = imdbVotes;
    }

    public OmdbMovieInfo(Film film) {
    }

    public static OmdbMovieInfo fromJsonString(String jsonString) throws IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, OmdbMovieInfo.class);
        } catch (IOException e) {
            throw new IOException("Errore durante la deserializzazione JSON", e);
        }
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }



    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }


    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }
}