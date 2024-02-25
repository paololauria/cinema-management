package com.paololauria.cinema.model.entities;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "actor")
public class Actor {
    @Id
    @GeneratedValue(generator = "actor_id_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "actor_id_generator", sequenceName = "actor_id_seq", allocationSize = 1)
    @Column(name = "actor_id")
    private long id;
    @Column(name = "actor_name")
    private String actorName;
    @Column(name = "actor_img")
    private String actorImg;
    @OneToMany (mappedBy = "actor",fetch = FetchType.LAZY)
    private List<Performance> performances;

    public Actor() {
    }

    public Actor(long id, String actorName, String actorImg) {
        this.id = id;
        this.actorName = actorName;
        this.actorImg = actorImg;
    }

    public Actor(long id, String actorName) {
        this.id = id;
        this.actorName = actorName;
    }

    public Actor(String actorName, String actorImg) {
        this.actorName = actorName;
        this.actorImg = actorImg;
    }

    public Actor(String actorName) {
        this.actorName = actorName;
    }

    public void addPerformance(Performance performance) {
        performances.add(performance);
        performance.setActor(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }

    public String getActorImg() {
        return actorImg;
    }

    public void setActorImg(String actorImg) {
        this.actorImg = actorImg;
    }
}
