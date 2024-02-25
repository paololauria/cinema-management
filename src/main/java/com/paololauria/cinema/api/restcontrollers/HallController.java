package com.paololauria.cinema.api.restcontrollers;
import com.paololauria.cinema.dtos.HallDto;
import com.paololauria.cinema.model.entities.Hall;
import com.paololauria.cinema.model.exceptions.EntityNotFoundException;
import com.paololauria.cinema.services.abstraction.CinemaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/hall")
@CrossOrigin
public class HallController {
    CinemaService cinemaService;
    @Autowired
    public HallController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/all")
    @Operation(
            summary = "Get all halls",
            description = "Retrieve details of all cinema halls."
    )
    public ResponseEntity<List<HallDto>> getAllHall() {
        List<Hall> halls = cinemaService.findAllHalls();
        List<HallDto> result = halls.stream().map(HallDto::new).toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get hall by ID",
            description = "Retrieve details of a cinema hall based on its ID."
    )
    public ResponseEntity<HallDto> getHallById(@PathVariable long id){
        Hall hall = cinemaService.findHallById(id);
        if (hall != null){
            HallDto result = new HallDto(hall);
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}