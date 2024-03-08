package com.paololauria.cinema.api.restcontrollers;
import com.paololauria.cinema.dtos.*;
import com.paololauria.cinema.model.entities.*;
import com.paololauria.cinema.model.exceptions.EntityNotFoundException;
import com.paololauria.cinema.services.OmdbAPIService;
import com.paololauria.cinema.services.abstraction.CinemaService;
import com.paololauria.cinema.services.abstraction.FilmProjectionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projection")
@CrossOrigin
public class FilmProjectionController {
    FilmProjectionService filmProjectionService;
    CinemaService cinemaService;
    OmdbAPIService omdbAPIService;

    @Autowired
    public FilmProjectionController(FilmProjectionService filmProjectionService,
                                    CinemaService cinemaService,
                                    OmdbAPIService omdbAPIService) {
        this.filmProjectionService = filmProjectionService;
        this.cinemaService = cinemaService;
        this.omdbAPIService = omdbAPIService;
    }

    @GetMapping("/all")
    @Operation(
            summary = "Get all projections",
            description = "Retrieve details of all film projections."
    )
    public ResponseEntity<List<FilmProjectionDto>> getAllProjection() {
        List<FilmProjection> films = filmProjectionService.findAllProjections();
        List<FilmProjectionDto> result = films.stream().map(filmProjection -> {
            FilmProjectionDto dto = new FilmProjectionDto(filmProjection);
            OmdbMovieInfo omdbInfo = omdbAPIService.getMovieByTitle(filmProjection.getFilmTitle());
            dto.setOmdbMovieInfo(omdbInfo);
            return dto;
        }).toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/today")
    @Operation(
            summary = "Get projections for today",
            description = "Retrieve details of film projections scheduled for today."
    )
    public ResponseEntity<List<FilmProjectionDto>> getFilmProjectionForToday() {
        List<FilmProjection> filmProjectionList = filmProjectionService.getFilmProjectionForToday();
        List<FilmProjectionDto> result = filmProjectionList.stream()
                .map(fp -> {
                    FilmProjectionDto filmProjectionDto = new FilmProjectionDto(fp);

                    Film film = fp.getFilm();
                    try {
                        String omdbMovieInfoString = omdbAPIService.getMovieInfo(film.getTitle());
                        OmdbMovieInfo omdbMovieInfo = OmdbMovieInfo.fromJsonString(omdbMovieInfoString);
                        filmProjectionDto.setOmdbMovieInfo(omdbMovieInfo);
                    } catch (IOException ignored) {
                    }

                    return filmProjectionDto;
                })
                .toList();

        return ResponseEntity.ok(result);
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Get projection by ID",
            description = "Retrieve details of a film projection based on its ID."
    )
    public ResponseEntity<FilmProjectionDto> getFilmProjectionById(@PathVariable long id) {
        Optional<FilmProjection> filmProjection = filmProjectionService.findFilmProjectionById(id);

        if (filmProjection.isPresent()) {
            FilmProjectionDto result = new FilmProjectionDto(filmProjection.get());

            Film film = filmProjection.get().getFilm();
            try {
                String omdbMovieInfoString = omdbAPIService.getMovieInfo(film.getTitle());
                OmdbMovieInfo omdbMovieInfo = OmdbMovieInfo.fromJsonString(omdbMovieInfoString);
                result.setOmdbMovieInfo(omdbMovieInfo);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/next-week")
    @Operation(
            summary = "Get projections for the next week",
            description = "Retrieve details of film projections scheduled for the next seven days."
    )
    public ResponseEntity<List<FilmProjectionDto>> getFilmProjectionNextWeek() {
        List<FilmProjection> filmProjectionList = filmProjectionService.getFilmProjectionNextWeek();
        List<FilmProjectionDto> result = filmProjectionList.stream()
                .map(fp -> {
                    FilmProjectionDto filmProjectionDto = new FilmProjectionDto(fp);

                    Film film = fp.getFilm();
                    try {
                        String omdbMovieInfoString = omdbAPIService.getMovieInfo(film.getTitle());
                        OmdbMovieInfo omdbMovieInfo = OmdbMovieInfo.fromJsonString(omdbMovieInfoString);
                        filmProjectionDto.setOmdbMovieInfo(omdbMovieInfo);
                    } catch (IOException ignored) {
                    }

                    return filmProjectionDto;
                })
                .toList();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/past")
    @Operation(
            summary = "Get past projections",
            description = "Retrieve details of film projections that have already taken place."
    )
    public ResponseEntity<List<FilmProjectionDto>> getPastProjections() {
        List<FilmProjection> pastProjections = filmProjectionService.getPastProjections();
        List<FilmProjectionDto> result = pastProjections.stream().map(FilmProjectionDto::new).toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/reservation")
    @Operation(
            summary = "Get reservation by projection ID",
            description = "Retrieve details of reservations for a film projection based on its ID."
    )
    public ResponseEntity<ProjectionReservationDto> getReservationByProjection(@PathVariable long id) {
        Optional<FilmProjection> projectionOpt = filmProjectionService.findFilmProjectionById(id);
        if (projectionOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        FilmProjection projection = projectionOpt.get();
        List<Reservation> reservations = cinemaService.findReservationByProjection(id);
        if (reservations != null) {
            ProjectionReservationDto projectionReservationDtos = new ProjectionReservationDto(reservations, projection);
            return ResponseEntity.ok(projectionReservationDtos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/hall")
    @Operation(
            summary = "Find hall by projection ID",
            description = "Retrieve details of the hall for a film projection based on its ID."
    )
    public ResponseEntity<HallDto> findHallByProjectionId(@PathVariable("id") long projectionId) {
        try {
            Hall hall = cinemaService.findHallByProjectionId(projectionId);
            HallDto dto = new HallDto(hall);
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/find-by-film/{filmId}")
    @Operation(
            summary = "Find projections by film ID",
            description = "Retrieve details of future film projections based on the ID of the film."
    )
    public ResponseEntity<List<FilmProjectionDto>> findProjectionsByFilmId(@PathVariable long filmId) {
        List<FilmProjection> filmProjections = filmProjectionService.findAllProjectionByFilmId(filmId);

        List<FilmProjection> upcomingProjections = filmProjections.stream()
                .filter(p -> p.getProjectionDate().isAfter(LocalDate.now().minusDays(1))).toList();
        List<FilmProjectionDto> filmProjectionDtos = upcomingProjections.stream()
                .map(FilmProjectionDto::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(filmProjectionDtos, HttpStatus.OK);
    }

    @PostMapping("/generate-daily")
    @Operation(
            summary = "Generate daily projections for the next seven days",
            description = "Automatically generate and save film projections for the next seven days, one projection per film per day."
    )
    public ResponseEntity<String> generateDailyProjections() {
        filmProjectionService.generateDailyProjections();
        return ResponseEntity.ok("Projections generated successfully");
    }

}

