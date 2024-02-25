package com.paololauria.cinema;
import com.paololauria.cinema.model.entities.Reservation;
public class ReservationRequest {
    private long idProjection;
    private int seatNumber;
    private boolean discount;
    public ReservationRequest() {
    }
    public ReservationRequest(Reservation reservation) {
        this.idProjection = reservation.getFilmProjection().getId();
        this.seatNumber =reservation.getReservedSeat();
        this.discount = reservation.isOnDiscount();
    }
    public ReservationRequest(long idProjection, int seatNumber, boolean isOnDiscount) {
        this.idProjection = idProjection;
        this.seatNumber = seatNumber;
        this.discount = isOnDiscount;
    }
    public long getIdProjection() {
        return idProjection;
    }
    public void setIdProjection(long idProjection) {
        this.idProjection = idProjection;
    }
    public int getSeatNumber() {
        return seatNumber;
    }
    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
    public boolean isDiscount() {
        return discount;
    }
    public void setDiscount(boolean discount) {
        this.discount = discount;
    }
}
