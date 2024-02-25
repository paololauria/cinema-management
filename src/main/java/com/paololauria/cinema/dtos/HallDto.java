package com.paololauria.cinema.dtos;

import com.paololauria.cinema.model.entities.Hall;

public class HallDto {
    private long id;
    private String name;
    private int row;
    private int column;

    public HallDto() {
    }

    public HallDto(Hall hall) {
        this.id = hall.getId();
        this.row = hall.getRowSeat();
        this.column = hall.getColumnSeat();
    }

    public Hall fromDto(){
        return   new Hall(this.id,this.row,this.column);

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }


}
