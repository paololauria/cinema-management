package com.paololauria.cinema.api.restcontrollers;
import com.paololauria.cinema.dtos.FeedbackDto;
import com.paololauria.cinema.dtos.OmdbMovieInfo;
import com.paololauria.cinema.dtos.ReservationDto;
import com.paololauria.cinema.dtos.UserDto;
import com.paololauria.cinema.model.entities.*;
import com.paololauria.cinema.services.OmdbAPIService;
import com.paololauria.cinema.services.abstraction.CinemaService;
import com.paololauria.cinema.services.abstraction.FilmService;
import com.paololauria.cinema.services.abstraction.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {
    UserService userService;
    CinemaService cinemaService;
    FilmService filmService;
    OmdbAPIService omdbAPIService;

    @Autowired
    public UserController(UserService userService,
                          CinemaService cinemaService,
                          FilmService filmService,
                          OmdbAPIService omdbAPIService) {
        this.filmService = filmService;
        this.userService = userService;
        this.cinemaService = cinemaService;
        this.omdbAPIService = omdbAPIService;
    }

    /**
     * Retrieves user details based on the user ID.
     *
     * @param id   The user ID.
     * @param user The authenticated user.
     * @return ResponseEntity with the user details or not found if the user is not present.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserDetailsById(@PathVariable long id, @AuthenticationPrincipal User user) {
        Optional<User> os = userService.findById(id);
        if (os.isPresent()) {
            UserDto userDto = new UserDto(os.get());
            return ResponseEntity.ok(userDto);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Retrieves all users (only for admin or manager).
     *
     * @return ResponseEntity with a list of user details or forbidden if not authorized.
     */
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('admin:update') or hasAuthority('manager:update')")
    public ResponseEntity<List<UserDto>> getAllUser() {
        List<User> users = userService.findAllUser();
        List<UserDto> result = users.stream().map(UserDto::new).toList();

        return ResponseEntity.ok(result);

    }

    /**
     * Retrieves reservations based on the user ID.
     *
     * @param userId The user ID.
     * @return ResponseEntity with a list of reservation details or not found if the user is not present.
     */
    @GetMapping("/{userId}/reservation")
    public ResponseEntity<List<ReservationDto>> getReservationByUserId(@PathVariable Long userId) {
        Optional<User> userOpt = userService.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = userOpt.get();
        List<Reservation> reservations = cinemaService.findReservationByUser(userId);
        if (reservations != null) {
            List<ReservationDto> reservationDtos = new ArrayList<>();

            for (Reservation reservation : reservations) {
                FilmProjection filmProjection = reservation.getFilmProjection();
                String filmTitle = filmProjection.getFilmTitle();

                OmdbMovieInfo movieInfo = omdbAPIService.getMovieByTitle(filmTitle);

                ReservationDto reservationDto = new ReservationDto(reservation, movieInfo);
                reservationDtos.add(reservationDto);
            }

            return ResponseEntity.ok(reservationDtos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    /**
     * Retrieves feedbacks based on the user ID.
     *
     * @param userId The user ID.
     * @return ResponseEntity with a list of feedback details or not found if the user is not present.
     */
    @GetMapping("/{userId}/feedback")
    public ResponseEntity<List<FeedbackDto>> getFeedbacksByUserId(@PathVariable Long userId) {
        Optional<User> userOpt = userService.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = userOpt.get();
        List<Feedback> feedbacks = cinemaService.findFeedbackByUser(userId);
        if (feedbacks != null) {
            List<FeedbackDto> feedbackDtoList = new ArrayList<>();

            for (Feedback feedback : feedbacks) {
                Film film = feedback.getFilm();
                String filmTitle = film.getTitle();

                OmdbMovieInfo movieInfo = omdbAPIService.getMovieByTitle(filmTitle);

                FeedbackDto feedbackDto = new FeedbackDto(feedback, movieInfo);
                feedbackDtoList.add(feedbackDto);
            }

            return ResponseEntity.ok(feedbackDtoList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    /**
     * Creates a new feedback for a film given the film ID.
     *
     * @param feedback The FeedbackDto containing feedback details.
     * @param user     The authenticated user creating the feedback.
     * @param filmId   The ID of the film for which feedback is being created.
     * @return ResponseEntity with the created FeedbackDto or bad request if the user or film is not found.
     * @throws URISyntaxException If there is an issue with creating the URI for the location header.
     */
    @PostMapping("/{filmId}/feedback")
    public ResponseEntity<FeedbackDto> createFeedback(@RequestBody FeedbackDto feedback, @AuthenticationPrincipal User user, @PathVariable long filmId) throws URISyntaxException {
        Film film = filmService.findFilmById(filmId);
        Optional<User> u = userService.findById(user.getId());
        if(u.isPresent()){
            Feedback fd = feedback.fromDto(film,u.get());
            cinemaService.createFeedback(fd,user);
            URI location = new URI("/api/user/feedback/" + fd.getId());
            FeedbackDto created = new FeedbackDto(fd);
            return ResponseEntity.created(location).body(created);}
        else {
            return ResponseEntity.badRequest().build();}
    }
}
