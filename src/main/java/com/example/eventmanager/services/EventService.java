package com.example.eventmanager.services;

import com.example.eventmanager.models.Event;
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
}
