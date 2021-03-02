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
public class VenueController {
    @Autowired
    private VenueRepository venueRepository;

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

    @GetMapping({"/venuelist", "/venuelist/{what}"})
    public String venueList(Model model, @PathVariable(required = false) String what) {
        Iterable<Venue> venues = venueRepository.findAll();
        boolean showFilter = false;
        model.addAttribute("showFilter", showFilter);
        model.addAttribute("venues", venues);
        model.addAttribute("nrVenues", venueRepository.count());
        return "venuelist";
    }

    @GetMapping("/venuelist/filter")
    public String venueListFilter(Model model){
        Iterable<Venue> venues = venueRepository.findAll();
        boolean showFilter = true;
        model.addAttribute("showFilter", showFilter);
        model.addAttribute("venues", venues);
        model.addAttribute("nrVenues", venueRepository.count());
        return "venuelist";
    }
}
