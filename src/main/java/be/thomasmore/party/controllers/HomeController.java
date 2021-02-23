package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    private VenueRepository venueRepository;

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
        if (venueId==null) return "venuedetails";
        Optional<Venue> optionalVenue = venueRepository.findById(venueId);
        if (optionalVenue.isPresent()) {
            model.addAttribute("venue", optionalVenue.get());
            model.addAttribute("prev", venueId>1 ? venueId-1 : venueRepository.count());
            model.addAttribute("next", venueId<venueRepository.count() ? venueId+1 : 1);
        }
        return "venuedetails";
    }

    @GetMapping("/venuelist")
    public String venueList(Model model) {
        Iterable<Venue> venues = venueRepository.findAll();
        model.addAttribute("venues", venues);
        return "venuelist";
    }

}
