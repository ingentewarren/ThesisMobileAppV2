package com.example.thesisprojectmobileapp;

public class RoomsDatabase {
    private String RoomNumber;
    private Boolean Availability;

    public RoomsDatabase() {
    }

    public RoomsDatabase(String roomNumber, Boolean availability) {
        RoomNumber = roomNumber;
        Availability = availability;
    }
}
