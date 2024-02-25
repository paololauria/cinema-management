package com.paololauria.cinema.dtos;

import com.paololauria.cinema.model.entities.Reservation;

import java.util.List;

public class ReservationConfirmDto {
    private Long id;
    private Long userId;
    private long hallId;
    private String filmTitle;
    private String dateProjection;
    private List<Integer> reservedSeats;

    public ReservationConfirmDto() {
    }

    public ReservationConfirmDto(Reservation reservation) {
        this.id = reservation.getId();
        this.userId = reservation.getUser().getId();
        this.hallId = reservation.getFilmProjection().getHallId();
        this.filmTitle = reservation.getFilmProjection().getFilmTitle();
        this.dateProjection = reservation.getFilmProjection().getProjectionDate().toString();
//        this.reservedSeats = reservation.getReservedSeats().stream().toList();
    }

//    public Reservation fromDto() {
////        FilmProjection fp= new FilmProjection();
////        fp.setProjectionDate(LocalDate.parse(dateProjection));
////        User user = new User();
////        user.setId(userId);
////        return new Reservation(user, fp, this.reservedSeats);
//    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public long getHallId() {
        return hallId;
    }

    public void setHallId(long hallId) {
        this.hallId = hallId;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public String getDateProjection() {
        return dateProjection;
    }

    public void setDateProjection(String dateProjection) {
        this.dateProjection = dateProjection;
    }

    public List<Integer> getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(List<Integer> reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
