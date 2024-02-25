package com.paololauria.cinema.api.restcontrollers;
import com.paololauria.cinema.dtos.GenreDto;
import com.paololauria.cinema.model.entities.Genre;
import com.paololauria.cinema.services.abstraction.CinemaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/api/genre")
@CrossOrigin
public class GenreController {
    CinemaService cinemaService;
    @Autowired
    public GenreController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/all")
    @Operation(
            summary = "Get all genres",
            description = "Retrieve details of all movie genres."
    )
    public ResponseEntity<List<GenreDto>> getAllGenres() {
        List<Genre> genres = cinemaService.findAllGenres();
        List<GenreDto> result = genres.stream().map(GenreDto::new).toList();
        return ResponseEntity.ok(result);
    }
}
