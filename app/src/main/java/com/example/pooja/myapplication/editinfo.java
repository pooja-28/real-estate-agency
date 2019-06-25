package com.example.pooja.myapplication;

public class editinfo {
    String noofbhk,totalbudget,building;
    double roomsqft;
    public editinfo() {
    }

    public editinfo(String noofbhk, String totalbudget, String building, double roomsqft) {
        this.noofbhk = noofbhk;
        this.totalbudget = totalbudget;
        this.building = building;
        this.roomsqft = roomsqft;
    }

    public String getNoofbhk() {
        return noofbhk;
    }

    public String getTotalbudget() {
        return totalbudget;
    }

    public String getBuilding() {
        return building;
    }

    public double getRoomsqft() {
        return roomsqft;
    }
}
