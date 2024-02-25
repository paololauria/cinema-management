package com.paololauria.cinema.api.restcontrollers;
import com.paololauria.cinema.dtos.ActorDto;
import com.paololauria.cinema.model.entities.Actor;
import com.paololauria.cinema.services.abstraction.CinemaService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/actor")
@CrossOrigin
@Tag(name = "Actor Controller", description = "Endpoints for managing actors")
public class ActorController {
    private final CinemaService cinemaService;
    private final Logger logger = LoggerFactory.getLogger(ActorController.class);
    @Autowired
    public ActorController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    /**
     * Retrieves all actors.
     *
     * @return ResponseEntity<List<ActorDto>> with the list of actors or no content status if empty.
     */
    @GetMapping("/all")
    @Operation(
            summary = "Get all actors",
            description = "Retrieves a list of all actors.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of actors",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ActorDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No content"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error"
                    )
            }
    )
    public ResponseEntity<List<ActorDto>> getAllActors() {
        try {
            List<Actor> actors = cinemaService.findAllActors();

            if (actors.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            List<ActorDto> result = actors.stream().map(ActorDto::new).toList();
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            logger.error("An error occurred while retrieving the actors", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
