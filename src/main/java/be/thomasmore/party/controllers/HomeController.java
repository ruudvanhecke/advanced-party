package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller
public class HomeController {
    private final Venue[] venues = {
            new Venue("De Club", "http://declubinfo"),
            new Venue("De Loods", "http://deloodsinfo"),
            new Venue("Zapoi", "http://zapoiinfo"),
            new Venue("Nekkerhal", "http://nekkerhalinfo")
    };

    @GetMapping({"/","/home"})
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model){
        return "about";
    }

    @GetMapping({"/venuedetails", "/venuedetails/{venueId}"})
    public String venueDetails(Model model, @PathVariable(required = false) Integer venueId) {
        if (venueId!=null && venueId>=0 && venueId<venues.length) {
            model.addAttribute("venuename", venues[venueId].getVenueName());
            model.addAttribute("prev", venueId>0 ? venueId-1 : venues.length-1);
            model.addAttribute("next", venueId<venues.length-1 ? venueId+1 : 0);
        }
        return "venuedetails";
    }

    @GetMapping("/venuelist")
    public String venueList(Model model) {
        model.addAttribute("venues", venues);
        return "venuelist";
    }

}
