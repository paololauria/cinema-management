package com.paololauria.cinema.model.exceptions;
public class DuplicateReservationException extends Exception{
    private int seatId;
    private long projectionId;
    public DuplicateReservationException(String message, int seatId, long projectionId, boolean onDiscount) {
        super(message);
        this.seatId = seatId;
        this.projectionId = projectionId;
    }
    public int getSeatId() {
        return seatId;
    }
    public long getProjectionId() {
        return projectionId;
    }
}
