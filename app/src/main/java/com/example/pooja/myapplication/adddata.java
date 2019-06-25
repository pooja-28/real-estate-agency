package com.example.pooja.myapplication;

public class adddata {


    String id, city, address, building, propertystatus, propertytype, noofbhk, totalbudget;
  double  roomsqft;


    public adddata() {

    }

    public adddata(String id,String city, String address, String building, String propertystatus, String propertytype, String noofbhk, String totalbudget,double roomsqft) {
        this.id=id;
        this.city = city;
        this.address = address;
        this.building = building;
        this.propertystatus = propertystatus;
        this.propertytype = propertytype;
        this.noofbhk = noofbhk;
        this.totalbudget = totalbudget;
        this.roomsqft = roomsqft;

    }



    public String getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getBuilding() {
        return building;
    }

    public String getPropertystatus() {
        return propertystatus;
    }

    public String getPropertytype() {
        return propertytype;
    }

    public String getNoofbhk() {
        return noofbhk;
    }

    public String getTotalbudget() {
        return totalbudget;
    }


    public double getRoomsqft() {
        return roomsqft;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public void setPropertystatus(String propertystatus) {
        this.propertystatus = propertystatus;
    }

    public void setPropertytype(String propertytype) {
        this.propertytype = propertytype;
    }

    public void setNoofbhk(String noofbhk) {
        this.noofbhk = noofbhk;
    }

    public void setTotalbudget(String totalbudget) {
        this.totalbudget = totalbudget;
    }

    public void setRoomsqft(double roomsqft) {
        this.roomsqft = roomsqft;
    }
}