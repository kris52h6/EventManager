package com.example.eventmanager.services;

import com.example.eventmanager.models.Event;
import com.example.eventmanager.repositories.EventRepo;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;

public class EventService {
    private EventRepo eventRepo;

    public EventService(){
        this.eventRepo = new EventRepo();
    }

    public ArrayList<Event> getEventList() {
        return eventRepo.getEventsFromDB();
    }

    public void createEvent(WebRequest dataFromForm) {
        eventRepo.addEventToDB(dataFromForm);

    }
}
