package com.example.pooja.myapplication;

public class cardrecyclevalue {
    String id, city, address, building, propertystatus, propertytype, noofbhk, totalbudget;
    double  roomsqft;
    String video;
    public  cardrecyclevalue(){}

    public cardrecyclevalue(String id, String city, String address, String building, String propertystatus, String propertytype, String noofbhk, String totalbudget, double roomsqft, String video) {
        this.id = id;
        this.city = city;
        this.address = address;
        this.building = building;
        this.propertystatus = propertystatus;
        this.propertytype = propertytype;
        this.noofbhk = noofbhk;
        this.totalbudget = totalbudget;
        this.roomsqft = roomsqft;
        this.video = video;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getPropertystatus() {
        return propertystatus;
    }

    public void setPropertystatus(String propertystatus) {
        this.propertystatus = propertystatus;
    }

    public String getPropertytype() {
        return propertytype;
    }

    public void setPropertytype(String propertytype) {
        this.propertytype = propertytype;
    }

    public String getNoofbhk() {
        return noofbhk;
    }

    public void setNoofbhk(String noofbhk) {
        this.noofbhk = noofbhk;
    }

    public String getTotalbudget() {
        return totalbudget;
    }

    public void setTotalbudget(String totalbudget) {
        this.totalbudget = totalbudget;
    }

    public double getRoomsqft() {
        return roomsqft;
    }

    public void setRoomsqft(double roomsqft) {
        this.roomsqft = roomsqft;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}