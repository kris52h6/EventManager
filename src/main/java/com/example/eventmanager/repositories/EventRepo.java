package com.example.eventmanager.repositories;

import com.example.eventmanager.models.Event;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.SessionCookieConfig;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class EventRepo {
    private final Connection connection;
    ArrayList<Event> eventList = new ArrayList<>();

    public EventRepo() {
        this.connection = DatabaseConnection.getConnection();
    }

    public ArrayList<Event> getEventsFromDB() {
        eventList.removeAll(eventList);
        String query = "SELECT * FROM events ORDER BY event_date";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String dateString = rs.getString(3);
                LocalDate date = stringToLocalDate(dateString);
                String startTimeString = rs.getString(4);
                LocalTime startTime = stringToLocalTime(startTimeString);
                String endTimeString = rs.getString(5);
                LocalTime endTime = stringToLocalTime(endTimeString);
                int userId = Integer.parseInt(rs.getString(6));

                int min = Integer.parseInt(rs.getString(7));
                int max = Integer.parseInt(rs.getString(8));
                int signedUp = Integer.parseInt(rs.getString(9));


                Event event = new Event(id, name, date, startTime, endTime, userId, min, max, signedUp);
                eventList.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventList;
    }

    public Event getSingleEventFromDB(int eventId) {
        String query = "SELECT * FROM events where event_id = "+eventId + ";";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String dateString = rs.getString(3);
                LocalDate date = stringToLocalDate(dateString);
                String startTimeString = rs.getString(4);
                LocalTime startTime = stringToLocalTime(startTimeString);
                String endTimeString = rs.getString(5);
                LocalTime endTime = stringToLocalTime(endTimeString);
                int userId = Integer.parseInt(rs.getString(6));

                int min = Integer.parseInt(rs.getString(7));
                int max = Integer.parseInt(rs.getString(8));
                int signedUp = Integer.parseInt(rs.getString(9));

                return new Event(id, name, date, startTime, endTime, userId, min, max, signedUp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public LocalDate stringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
        return LocalDate.parse(date, formatter);
    }

    public LocalTime stringToLocalTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
        return LocalTime.parse(time, formatter);
    }

    public void addEventToDB(WebRequest dataFromForm, HttpSession session) {
        String name = dataFromForm.getParameter("name");
        String eventDateString = dataFromForm.getParameter("event-date");
        LocalDate eventDate = stringToLocalDate(eventDateString);
        String eventStartTime = dataFromForm.getParameter("event-starttime");
        String eventEndTime = dataFromForm.getParameter("event-endtime");
        String min = dataFromForm.getParameter("event-min");

        int userId = (int) session.getAttribute("userId");
        int signedUp = 0;

        if (min == null) {
            min = "0";
        }
        String max = dataFromForm.getParameter("event-max");
        if (max == null) {
            max = "0";
        }

        String query = "INSERT INTO events (`event_name`, `event_date`, `event_starttime`, `event_endtime`, `event_userid`, `event_minimum`, `event_maximum`, `event_signedup`) " +
                "VALUES (" + "'" + name + "', '" + eventDate + "', '" + eventStartTime + "', '" + eventEndTime + "', '" + userId + "', '" + min + "', '" + max + "', '" + signedUp + "'" +")";

        System.out.println(query);


        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
    }

}
