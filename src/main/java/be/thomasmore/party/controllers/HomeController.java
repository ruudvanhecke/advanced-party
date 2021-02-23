package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    private final int mySpecialNumber = 729;
        private final Venue[] venues = {
                new Venue("De Club", "http://declubinfo", 100, true,"Mechelen"),
                new Venue("De Loods", "http://deloodsinfo", 120, true, "Retie"),
                new Venue("Zapoi", "http://zapoiinfo", 300, false, "Retie"),
                new Venue("Nekkerhal", "http://nekkerhalinfo", 329, false, "Antwerpen")};


    @GetMapping({"/" , "/home"})
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @GetMapping({"/venuedetails", "/venuedetails/{venueId}"})
    public String venueDetails(Model model, @PathVariable(required = false) Integer venueId) {
        Venue venue = (venueId>=0 && venueId < venues.length) ? venues[venueId] : null;
        model.addAttribute("venue", venue);
        model.addAttribute("prev", (venueId>=0 && venueId < venues.length) ? (venueId>0 ? venueId-1 : venues.length-1) : null);
        model.addAttribute("next", (venueId>=0 && venueId < venues.length) ? (venueId<venues.length-1 ? venueId+1 : 0) : null);
        return "venuedetails";
    }


    @GetMapping({"/venuedetailsbyindex", "/venuedetailsbyindex/{venueId}"})
    public String venueDetailsByIndex(Model model, @PathVariable(required = false) Integer venueId) {
        if (venueId!=null && venueId>=0 && venueId<venues.length)
        {
            model.addAttribute("venue", venues[venueId]);
            model.addAttribute("prev", venueId>0 ? venueId - 1 : venues.length - 1);
            model.addAttribute("next" , venueId< venues.length - 1 ? venueId + 1 : 0);
        }
      //  model.addAttribute("venuename", venues);
        return "venuedetails";
    }

    @GetMapping("/venuelist")
    public String venueList(Model model) {
        model.addAttribute("venues", venues);
        return "venuelist";
    }



}