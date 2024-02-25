package com.paololauria.cinema.api.restcontrollers;

import com.paololauria.cinema.dtos.FilmDetailsDto;
import com.paololauria.cinema.dtos.OmdbMovieInfo;
import com.paololauria.cinema.model.entities.Film;
import com.paololauria.cinema.services.OmdbAPIService;
import com.paololauria.cinema.services.abstraction.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/omdb")
@CrossOrigin
public class OmdbMovieController {
    private final OmdbAPIService omdbAPIService;
    private final FilmService filmService;

    @Autowired
    public OmdbMovieController(OmdbAPIService omdbAPIService, FilmService filmService) {
        this.omdbAPIService = omdbAPIService;
        this.filmService = filmService;
    }

    @GetMapping("/movie/{title}")
    @Operation(
            summary = "Get movie information from OMDB",
            description = "Retrieve detailed information about a movie from OMDB based on its title."
    )
    public ResponseEntity<String> getMovieInfo(
            @PathVariable
            @Parameter(description = "Title of the movie") String title) {
        try {
            String movieInfo = omdbAPIService.getMovieInfo(title);
            return ResponseEntity.ok(movieInfo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error during the request for movie information. Details: " + e.getMessage());
        }
    }

    @GetMapping("/movie/image/{imdbId}")
    @Operation(
            summary = "Get movie image from OMDB",
            description = "Retrieve the movie image from OMDB based on its IMDB ID."
    )
    public ResponseEntity<String> getMovieImage(
            @PathVariable
            @Parameter(description = "IMDB ID of the movie") String imdbId) {
        try {
            String movieImage = omdbAPIService.getMovieImage(imdbId);
            return ResponseEntity.ok(movieImage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error during the request for movie information. Details: " + e.getMessage());
        }
    }
}
