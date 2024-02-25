package com.paololauria.cinema.model.entities;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(generator = "reservation_id_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "reservation_id_generator", sequenceName = "reservation_id_seq", allocationSize = 1)
    @Column(name = "reservation_id")
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "projection_id")
    private FilmProjection filmProjection;
    @Column(name = "reserved_seat")
    private int reservedSeat;
    private LocalDate date;
    @Transient
    private LocalTime time;
    @Column(name = "is_on_discount")
    private boolean isOnDiscount;
    public Reservation() {
    }
    public Reservation(long id, User user, FilmProjection filmProjection, int reservedSeat, LocalDate date) {
        this.id = id;
        this.user = user;
        this.filmProjection = filmProjection;
        this.date = date;
        this.reservedSeat = reservedSeat;
    }
    public Reservation(long id, User user, FilmProjection filmProjection, int reservedSeat, LocalDate date, LocalTime time, boolean isOnDiscount) {
        this.id = id;
        this.user = user;
        this.filmProjection = filmProjection;
        this.reservedSeat = reservedSeat;
        this.date = date;
        this.time = this.getProjectionTime();
        this.isOnDiscount = isOnDiscount;
    }
    public Reservation(User user, FilmProjection fp, int reservedSeat) {
        this.user = user;
        this.filmProjection = fp;
        this.reservedSeat = reservedSeat;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public FilmProjection getFilmProjection() {
        return filmProjection;
    }
    public void setFilmProjection(FilmProjection filmProjection) {
        this.filmProjection = filmProjection;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public int getReservedSeat() {
        return reservedSeat;
    }
    public void setReservedSeat(int reservedSeat) {
        this.reservedSeat = reservedSeat;
    }
    public boolean isOnDiscount() {
        return isOnDiscount;
    }
    public void setOnDiscount(boolean onDiscount) {
        isOnDiscount = onDiscount;
    }
    public LocalTime getProjectionTime(){
        return filmProjection.getProjectionTimes();
    }
}