package com.paololauria.cinema.model.entities;
import jakarta.persistence.*;
import java.util.List;
@Entity
@Table(name = "hall")
public class Hall {
    @Id
    @GeneratedValue(generator = "hall_id_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "hall_id_generator", sequenceName = "hall_id_seq", allocationSize = 1)
    @Column(name = "hall_id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "row_seat")
    private Integer rowSeat;
    @Column(name = "row_column")
    private Integer columnSeat;
    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL)
    private List<FilmProjection> filmProjections;
    public Hall() {
    }
    public Hall(long id, String name, Integer rowSeat, Integer columnSeat) {
        this.id = id;
        this.name = name;
        this.rowSeat = rowSeat;
        this.columnSeat = columnSeat;
    }
    public Hall(long id, int row, int column) {
        this.id = id;
        this.rowSeat = row;
        this.columnSeat = column;
    }
    public Hall(long id, String name, Integer rowSeat, Integer columnSeat, List<FilmProjection> filmProjections) {
        this.id = id;
        this.name = name;
        this.rowSeat = rowSeat;
        this.columnSeat = columnSeat;
        this.filmProjections = filmProjections;
    }
    public int getTotalSeatNumber(){
        return this.rowSeat * this.columnSeat;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getRowSeat() {
        return rowSeat;
    }
    public void setRowSeat(Integer rowSeat) {
        this.rowSeat = rowSeat;
    }
    public Integer getColumnSeat() {
        return columnSeat;
    }
    public void setColumnSeat(Integer columnSeat) {
        this.columnSeat = columnSeat;
    }
    public List<FilmProjection> getFilmProjections() {
        return filmProjections;
    }
    public void setFilmProjections(List<FilmProjection> filmProjections) {
        this.filmProjections = filmProjections;
    }
}