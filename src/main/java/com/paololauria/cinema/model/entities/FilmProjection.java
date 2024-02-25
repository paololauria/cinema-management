package com.paololauria.cinema.model.entities;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Entity
@Table(name = "film_projection")
public class FilmProjection {
    @Id
    @GeneratedValue(generator = "film_projection_id_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "film_projection_id_generator", sequenceName = "film_projection_id_seq", allocationSize = 1)
    @Column(name = "projection_id")
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "film_id")
    private Film film;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hall_id")
    private Hall hall;
    @Column(name = "projection_time")
    private LocalTime projectionTimes;
    @Column(name = "projection_date")
    private LocalDate projectionDate;
    @OneToMany(mappedBy = "filmProjection", cascade = CascadeType.ALL)
    private List<Reservation> reservations;
    @Column(name = "ticket_price")
    private Double ticketPrice ;
    public FilmProjection() {
    }
    public FilmProjection(long id, Film film, Hall hall, LocalTime projectionTimes, LocalDate projectionDate) {
        this.id = id;
        this.film = film;
        this.hall = hall;
        this.projectionTimes = projectionTimes;
        this.projectionDate = projectionDate;
    }
    public FilmProjection(long id, Film film, Hall hall,
                          LocalTime projectionTimes, LocalDate projectionDate,
                          List<Reservation> reservations) {
        this.id = id;
        this.film = film;
        this.hall = hall;
        this.projectionTimes = projectionTimes;
        this.projectionDate = projectionDate;
        this.reservations = reservations;
    }
    public FilmProjection(long id, Film film, Hall hall, LocalTime projectionTimes, LocalDate projectionDate, List<Reservation> reservations,Double ticketPrice) {
        this.id = id;
        this.film = film;
        this.hall = hall;
        this.projectionTimes = projectionTimes;
        this.projectionDate = projectionDate;
        this.reservations = reservations;
        this.ticketPrice = ticketPrice;
    }
    public FilmProjection(long id, Film film, Hall hall,Double ticketPrice,String date, String time) {
        this.id = id;
        this.film = film;
        this.hall = hall;
        this.ticketPrice=ticketPrice;
        this.projectionDate= LocalDate.parse(date);
        this.projectionTimes=LocalTime.parse(time);
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Film getFilm() {
        return film;
    }
    public void setFilm(Film film) {
        this.film = film;
    }
    public Hall getHall() {
        return hall;
    }
    public void setHall(Hall hall) {
        this.hall = hall;
    }
    public LocalTime getProjectionTimes() {
        return projectionTimes;
    }
    public void setProjectionTimes(LocalTime projectionTimes) {
        this.projectionTimes = projectionTimes;
    }
    public LocalDate getProjectionDate() {
        return projectionDate;
    }
    public void setProjectionDate(LocalDate projectionDate) {
        this.projectionDate = projectionDate;
    }
    public List<Reservation> getReservations() {
        return reservations;
    }
    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
    public String getFilmTitle() {
        return film.getTitle();
    }
    public String getPosterImage() {
        return film.getPosterImg();
    }
    public int getFilmDuration() {
        return film.getDuration();
    }
    public long getHallId() {
        return hall.getId();
    }
    public String getHallName() {
        return hall.getName();
    }
    public Double getTicketPrice() {
        return ticketPrice;
    }
    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}