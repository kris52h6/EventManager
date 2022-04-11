package com.example.eventmanager.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event {
    private int id;
    private String name;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int min;
    private int max;
    private int signedUp;
    private int userId;


    public Event(int id, String name, LocalDate date, LocalTime startTime, LocalTime endTime, int userId, int min, int max, int signedUp) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.userId = userId;
        this.min = min;
        this.max = max;
        this.signedUp = signedUp;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", min=" + min +
                ", max=" + max +
                ", signedUp=" + signedUp +
                ", userId=" + userId +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getUserId() {
        return userId;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getSignedUp() {
        return signedUp;
    }

    public String getDateAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        return formatter.format(date);
    }
}
