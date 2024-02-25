package com.paololauria.cinema.api.restcontrollers;
import com.paololauria.cinema.model.responses.ErrorResponse;
import com.paololauria.cinema.dtos.FilmDetailsDto;
import com.paololauria.cinema.dtos.FilmProjectionDto;
import com.paololauria.cinema.dtos.UserDto;
import com.paololauria.cinema.model.entities.Film;
import com.paololauria.cinema.model.entities.FilmProjection;
import com.paololauria.cinema.model.entities.User;
import com.paololauria.cinema.model.exceptions.FilmNotFoundException;
import com.paololauria.cinema.model.exceptions.ProjectionNotFoundException;
import com.paololauria.cinema.model.exceptions.UnauthorizedOperationException;
import com.paololauria.cinema.services.abstraction.FilmProjectionService;
import com.paololauria.cinema.services.abstraction.FilmService;
import com.paololauria.cinema.services.abstraction.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
/**
 * Controller handling administrative operations related to films, projections, and users.
 * All endpoints are under the "/api/admin/" path.
 */
@RestController
@RequestMapping("/api/admin/")
@OpenAPIDefinition(
        info = @Info(
                title = "Admin Operations",
                description = "Endpoints for managing films, projections, and users by admin"
        )
)public class AdminController {
    FilmService filmService;
    FilmProjectionService filmProjectionService;
    UserService userService;

    private static final String FILM_API_PATH = "/film/";
    private static final String PROJECTION_API_PATH = "/projection/";
    private static final String USER_API_PATH = "/user/";
    @Autowired
    public AdminController(FilmService filmService,
                           FilmProjectionService filmProjectionService,
                           UserService userService) {
        this.filmService = filmService;
        this.filmProjectionService = filmProjectionService;
        this.userService = userService;
    }

    /**
     * Creates a new film.
     *
     * @param filmDto The DTO containing film details.
     * @param user    The authenticated user.
     * @return ResponseEntity<FilmDetailsDto> with the created film details.
     * @throws URISyntaxException if the URI is invalid.
     */
    @PostMapping(FILM_API_PATH)
    @PreAuthorize("hasAuthority('admin:create') or hasAuthority('manager:create')")
    public ResponseEntity<FilmDetailsDto> createFilm(@RequestBody @Valid FilmDetailsDto filmDto,
                                                     @AuthenticationPrincipal User user) throws URISyntaxException {
        Film film = filmDto.fromDto();
        filmService.createFilm(film);
        URI location = new URI("/api/admin/film/" + film.getId());
        FilmDetailsDto created = new FilmDetailsDto(film);
        return ResponseEntity.created(location).body(created);
    }

    /**
     * Deletes a film by its ID.
     *
     * @param id   The ID of the film to be deleted.
     * @param user The authenticated user.
     * @return ResponseEntity<?> with no content status or appropriate error status.
     */
    @DeleteMapping(FILM_API_PATH + "{id}")
    @PreAuthorize("hasAuthority('admin:delete') or hasAuthority('manager:delete')")
    public ResponseEntity<?> deleteFilm(@PathVariable long id, @AuthenticationPrincipal User user) {
        try {
            filmService.deleteFilmById(id, user);
            return ResponseEntity.ok().build();
        } catch (FilmNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Film not found", e.getMessage()));
        } catch (UnauthorizedOperationException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("Unauthorized", e.getMessage()));
        }
    }
    /**
     * Updates an existing film.
     *
     * @param id           The ID of the film to be updated.
     * @param updatedFilm  The updated film details.
     * @param user         The authenticated user.
     * @return ResponseEntity<?> with no content status or appropriate error status.
     */
    @PutMapping("film/{id}")
    @PreAuthorize("hasAuthority('admin:update') or hasAuthority('manager:update')")
    public ResponseEntity<?> updateFilm(@PathVariable long id, @RequestBody Film updatedFilm, @AuthenticationPrincipal User user) {
        try {
            filmService.updateFilm(updatedFilm, user);
            return ResponseEntity.noContent().build();
        } catch (FilmNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (UnauthorizedOperationException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    /**
     * Creates a new projection.
     *
     * @param projectionDto The DTO containing projection details.
     * @param user          The authenticated user.
     * @return ResponseEntity<FilmProjectionDto> with the created projection details.
     * @throws URISyntaxException if the URI is invalid.
     */
    @PostMapping(PROJECTION_API_PATH)
    @PreAuthorize("hasAuthority('admin:create') or hasAuthority('manager:create')")
    public ResponseEntity<FilmProjectionDto> createProjection(@RequestBody FilmProjectionDto projectionDto,
                                                              @AuthenticationPrincipal User user
                                                              ) throws URISyntaxException {
        Optional<Film> film = Optional.ofNullable(filmService.findFilmById(projectionDto.getFilmId()));
        FilmProjection projection = film.map(projectionDto::fromDto)
                .orElseThrow(() -> new ProjectionNotFoundException("Film not found"));

        filmProjectionService.createProjection(projection, user);
        FilmProjectionDto result = new FilmProjectionDto(projection);
        URI location = URI.create("/api/projection/" + result.getId());
        return ResponseEntity.created(location).body(result);
    }
    /**
     * Deletes a projection by its ID.
     *
     * @param id   The ID of the projection to be deleted.
     * @param user The authenticated user.
     * @return ResponseEntity<Void> with a no content status if successful, or not found if the projection or user is unauthorized.
     */
    @DeleteMapping("/{id}/projection")
    @PreAuthorize("hasAuthority('admin:delete') or hasAuthority('manager:delete')")
    public ResponseEntity<Void> deleteProjection(@PathVariable long id, @AuthenticationPrincipal User user) {
        try {
            filmProjectionService.deleteProjectionById(id, user);
            return ResponseEntity.noContent().build();
        } catch (ProjectionNotFoundException | UnauthorizedOperationException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Updates a projection.
     *
     * @param id                The ID of the projection to be updated.
     * @param updatedProjection The updated projection.
     * @param user              The authenticated user.
     * @return ResponseEntity<Void> with a no content status if successful, or not found if the projection is not present or user is unauthorized.
     */
    @PutMapping("projection/{id}")
    @PreAuthorize("hasAuthority('admin:update') or hasAuthority('manager:update')")
    public ResponseEntity<Void> updateFilmProjection(@PathVariable long id,
                                                     @RequestBody FilmProjection updatedProjection,
                                                     @AuthenticationPrincipal User user) {
        try {
            Optional<FilmProjection> existingProjection = filmProjectionService.findFilmProjectionById(id);
            if (existingProjection.isPresent()) {
                updatedProjection.setId(id);
                filmProjectionService.updateProjection(updatedProjection, user);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (UnauthorizedOperationException e) {
            return ResponseEntity.status(403).build();
        }
    }

    /**
     * Creates a new user.
     *
     * @param userDto The data to create the new user.
     * @param user    The authenticated user.
     * @return ResponseEntity<UserDto> with the created user details, or forbidden if the operation is unauthorized.
     * @throws URISyntaxException if the URI syntax is invalid.
     */
    @PostMapping(USER_API_PATH)
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto,
                                              @AuthenticationPrincipal User user) throws URISyntaxException {
        try {
            User newUser = userDto.fromDto();
            userService.createUser(newUser, user);
            UserDto result = new UserDto(newUser);
            URI location = new URI("/api/user/" + result.getId());
            return ResponseEntity.created(location).body(result);
        } catch (UnauthorizedOperationException e) {
            return ResponseEntity.status(403).build();
        }
    }

    /**
     * Deletes a user by ID.
     *
     * @param id   The ID of the user to be deleted.
     * @param user The authenticated user.
     * @return ResponseEntity<Void> with a no content status if successful, or not found if the user or operation is unauthorized.
     */
    @DeleteMapping("user/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<Void> deleteUser(@PathVariable long id, @AuthenticationPrincipal User user) {
        try {
            userService.deleteUserById(id, user);
            return ResponseEntity.noContent().build();
        } catch (ProjectionNotFoundException | UnauthorizedOperationException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Updates an existing user.
     *
     * @param id          The ID of the user to be updated.
     * @param updatedUser The updated user details.
     * @param user        The authenticated user.
     * @return ResponseEntity<Void> with no content status or appropriate error status.
     */
    @PutMapping("user/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<Void> updateUser(@PathVariable long id,
                                           @RequestBody UserDto updatedUser,
                                           @AuthenticationPrincipal User user) {
        try {
            Optional<User> existingProjection = userService.findById(id);
            if (existingProjection.isPresent()) {
                updatedUser.setId(id);
                userService.updateUser(updatedUser.fromDto(), user);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (UnauthorizedOperationException e) {
            return ResponseEntity.status(403).build();
        }
    }
}
