package com.example.eventmanager.services;

import com.example.eventmanager.models.Event;
import com.example.eventmanager.models.User;
import com.example.eventmanager.repositories.EventRepo;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class EventService {
    private EventRepo eventRepo;

    public EventService(){
        this.eventRepo = new EventRepo();
    }

    public ArrayList<Event> getEventList() {
        return eventRepo.getEventsFromDB();
    }

    public void createEvent(WebRequest dataFromForm, HttpSession session) {
        eventRepo.addEventToDB(dataFromForm, session);

    }

    public Event getSingleEvent(int id) {
        return eventRepo.getSingleEventFromDB(id);
    }

    public ArrayList<User> getAttendeesFromEvent(int eventId) {
        ArrayList<Integer> attendeeList = eventRepo.getAttendeesFromEvent(eventId);
        ArrayList<User> userList = new ArrayList<>();

        for (int attendee : attendeeList) {
            User u = getUserFromDb(attendee);
            userList.add(u);
        }

        return userList;
    }

    private User getUserFromDb(int attendee) {
        return eventRepo.getUserFromDb(attendee);
    }

    public ArrayList<User> showUsers(int eventId) {
        return eventRepo.getUsersNotAttending(eventId);
    }

    public void addAttendant(int id, int eventId) {
        eventRepo.addAttendant(id, eventId);
    }

    public void deleteAttendant(int id, int eventId) {
        eventRepo.deleteAttendant(id, eventId);
    }
}
