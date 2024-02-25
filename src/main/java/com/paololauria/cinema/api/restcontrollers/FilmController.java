package com.paololauria.cinema.api.restcontrollers;
import com.paololauria.cinema.dtos.FeedbackDto;
import com.paololauria.cinema.dtos.FilmDetailsDto;
import com.paololauria.cinema.dtos.OmdbMovieInfo;
import com.paololauria.cinema.model.entities.Feedback;
import com.paololauria.cinema.model.entities.Film;
import com.paololauria.cinema.services.OmdbAPIService;
import com.paololauria.cinema.services.abstraction.CinemaService;
import com.paololauria.cinema.services.abstraction.FilmService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/film")
@CrossOrigin
public class FilmController {
    FilmService filmService;
    CinemaService cinemaService;
    OmdbAPIService omdbAPIService;
    @Autowired
    public FilmController(FilmService filmService,
                          CinemaService cinemaService,
                          OmdbAPIService omdbAPIService) {
        this.filmService = filmService;
        this.cinemaService= cinemaService;
        this.omdbAPIService = omdbAPIService;
    }

    @GetMapping("/top10")
    @Operation(
            summary = "Get top 10 films",
            description = "Retrieve details of the top 10 films."
    )
    public ResponseEntity<List<FilmDetailsDto>> getTop10Films() {
        List<Film> top10Films = filmService.findTop10Films();
        List<FilmDetailsDto> result = new ArrayList<>();

        for (Film film : top10Films) {
            try {
                String omdbMovieInfoString = omdbAPIService.getMovieInfo(film.getTitle());
                OmdbMovieInfo omdbMovieInfo = OmdbMovieInfo.fromJsonString(omdbMovieInfoString);
                FilmDetailsDto filmDetailsDto = new FilmDetailsDto(film);
                filmDetailsDto.setOmdbMovieInfo(omdbMovieInfo);
                result.add(filmDetailsDto);
            } catch (IOException e){

            }
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/all")
    @Operation(
            summary = "Get all films",
            description = "Retrieve details of all films."
    )
    public ResponseEntity<List<FilmDetailsDto>> getAllFilms() {
        List<Film> films = filmService.findAllFilms();
        List<FilmDetailsDto> result = new ArrayList<>();

        for (Film film : films) {
            try {
                String omdbMovieInfoString = omdbAPIService.getMovieInfo(film.getTitle());
                OmdbMovieInfo omdbMovieInfo = OmdbMovieInfo.fromJsonString(omdbMovieInfoString);

                FilmDetailsDto filmDetailsDto = new FilmDetailsDto(film);
                filmDetailsDto.setOmdbMovieInfo(omdbMovieInfo);

                result.add(filmDetailsDto);

            } catch (IOException e) {

            }
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get film by ID",
            description = "Retrieve details of a film based on its ID."
    )
    public ResponseEntity<FilmDetailsDto> getFilmById(@PathVariable long id) {
        Film film = filmService.findFilmById(id);
        if (film != null) {
            try {
                String omdbMovieInfoString = omdbAPIService.getMovieInfo(film.getTitle());
                OmdbMovieInfo omdbMovieInfo = OmdbMovieInfo.fromJsonString(omdbMovieInfoString);

                FilmDetailsDto result = new FilmDetailsDto(film);
                result.setOmdbMovieInfo(omdbMovieInfo);

                return ResponseEntity.ok(result);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/filter")
    @Operation(
            summary = "Get films by genre",
            description = "Retrieve details of films based on a specified genre."
    )
    public ResponseEntity<List<FilmDetailsDto>> getFilmsByGenres(@RequestParam String genre) {
        List<Film> films = filmService.findFilmsByGenre(genre);
        List<FilmDetailsDto> result = new ArrayList<>();

        for (Film film : films) {
            try {
                String omdbMovieInfoString = omdbAPIService.getMovieInfo(film.getTitle());
                OmdbMovieInfo omdbMovieInfo = OmdbMovieInfo.fromJsonString(omdbMovieInfoString);
                FilmDetailsDto filmDto = new FilmDetailsDto(film);
                filmDto.setOmdbMovieInfo(omdbMovieInfo);
                result.add(filmDto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{projectionId}/projection")
    @Operation(
            summary = "Get film details by projection ID",
            description = "Retrieve details of a film based on the ID of its projection."
    )
    public ResponseEntity<FilmDetailsDto> getFilmDetailsByProjectionId(@PathVariable long projectionId) {
        try {
            Film film = filmService.findFilmByProjectionId(projectionId);
            String omdbMovieInfoString = omdbAPIService.getMovieInfo(film.getTitle());
            OmdbMovieInfo omdbMovieInfo = OmdbMovieInfo.fromJsonString(omdbMovieInfoString);
            FilmDetailsDto filmDetailsDto = new FilmDetailsDto(film);
            filmDetailsDto.setOmdbMovieInfo(omdbMovieInfo);
            return ResponseEntity.ok(filmDetailsDto);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/search")
    @Operation(
            summary = "Search movies by title",
            description = "Retrieve details of films based on a specified title."
    )
    public ResponseEntity<List<FilmDetailsDto>> searchMoviesByTitle(@RequestParam String title) {
        try {
            List<Film> movieInfoList = filmService.searchByTitle(title);
            List<FilmDetailsDto> filmDetailsDtos = new ArrayList<>();

            for (Film film : movieInfoList) {
                String omdbMovieInfoString = omdbAPIService.getMovieInfo(film.getTitle());
                OmdbMovieInfo omdbMovieInfo = OmdbMovieInfo.fromJsonString(omdbMovieInfoString);

                FilmDetailsDto filmDetailsDto = new FilmDetailsDto(film);
                filmDetailsDto.setOmdbMovieInfo(omdbMovieInfo);

                filmDetailsDtos.add(filmDetailsDto);
            }

            return ResponseEntity.ok(filmDetailsDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }

    @GetMapping("/{filmId}/feedback")
    @Operation(
            summary = "Get feedback by film ID",
            description = "Retrieve feedback for a film based on its ID."
    )
    public ResponseEntity<List<FeedbackDto>> getFeedbackByFilmId(@PathVariable long filmId) {
        List<Feedback> feedbackList = cinemaService.findAllFeedbackByFilmId(filmId);
        if (feedbackList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<FeedbackDto> feedbackDtoList = feedbackList.stream().map(FeedbackDto::new).toList();
        return ResponseEntity.ok(feedbackDtoList);
    }

    @GetMapping("/search-suggestions")
    @Operation(
            summary = "Get search suggestions",
            description = "Retrieve film search suggestions based on a search term."
    )
    public ResponseEntity<List<FilmDetailsDto>> getSearchSuggestions(@RequestParam String term) {
        try {
            List<Film> films = filmService.getSearchSuggestion(term);
            List<FilmDetailsDto> suggestions = films.stream()
                    .map(FilmDetailsDto::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(suggestions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }

}
