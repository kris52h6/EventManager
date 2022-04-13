package com.example.eventmanager.controllers;

import com.example.eventmanager.services.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;

@Controller
public class EventController {
    private final EventService eventService = new EventService();

    @GetMapping("/events")
    public String getEvents(Model model) {
        model.addAttribute("events", eventService.getEventList());
        return "events";
    }

    @GetMapping("/createEvent")
    public String createEvent() {
        System.out.println("clicked create");
        return "create-event";
    }

    @PostMapping("/createNewEvent")
    public String createNewEvent(WebRequest dataFromForm, HttpSession session) {
        eventService.createEvent(dataFromForm, session);
        return "redirect:/events";
    }

    @GetMapping("/event")
    public String singleEvent(Model model, @RequestParam int id) {
        System.out.println(id);
        // TODO
        // get single event
        model.addAttribute("event", eventService.getSingleEvent(id));
        return "single-event";
    }
}
