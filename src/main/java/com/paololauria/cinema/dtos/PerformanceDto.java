package com.paololauria.cinema.dtos;


import com.paololauria.cinema.model.entities.Performance;

public class PerformanceDto {
    private String role;

    public PerformanceDto(Performance performance) {
        this.role = performance.getRoleName();
    }


    public PerformanceDto() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
