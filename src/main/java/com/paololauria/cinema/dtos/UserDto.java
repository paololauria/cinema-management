package com.paololauria.cinema.dtos;

import com.paololauria.cinema.model.entities.User;

import java.time.LocalDate;
import java.util.List;

public class UserDto {
    private Long id;
    private String firstname;
    private  String lastname;
    private List<ReservationDto> reservationDtoList;
    private String email;
    private List<FeedbackDto> feedbackDtoList;
    private LocalDate birthdate;

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.reservationDtoList =user.getReservations().stream().map(ReservationDto::new).toList();
        this.email = user.getEmail();
        this.feedbackDtoList =user.getFeedbackList().stream().map(FeedbackDto::new).toList();
        this.birthdate = user.getBirthdate();
    }

    public User fromDto(){
        return new User(this.id, this.firstname, this.lastname, this.email, this.fromDto().getRole());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }



    public List<ReservationDto> getReservationDtoList() {
        return reservationDtoList;
    }

    public void setReservationDtoList(List<ReservationDto> reservationDtoList) {
        this.reservationDtoList = reservationDtoList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<FeedbackDto> getFeedbackDtoList() {
        return feedbackDtoList;
    }

    public void setFeedbackDtoList(List<FeedbackDto> feedbackDtoList) {
        this.feedbackDtoList = feedbackDtoList;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
