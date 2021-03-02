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
        if (optionalVenue.isPresent()){
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


    @GetMapping({"/venuelist/outdoor/{filter}", "/venuelist/outdoor"})
    public String venueListOutdoorYes(Model model, @PathVariable(required = false) String filter) {
        Iterable<Venue> venues;
        Boolean realFilter = null;
        if (filter==null || filter.equals("yes") || filter.equals("true")) realFilter = true;
        if (filter!=null && (filter.equals("no") || filter.equals("false"))) realFilter = false;
        if (realFilter==null) {
            venues = venueRepository.findAll();
        } else {
            venues = venueRepository.findByOutdoor(realFilter);
        }
        model.addAttribute("venues", venues);
        model.addAttribute("outdoorFilter", realFilter);
        return "venuelist";
    }

    @GetMapping({"/venuelist/indoor/{filter}", "/venuelist/indoor"})
    public String venueListIndoorYes(Model model, @PathVariable(required = false) String filter) {
        Iterable<Venue> venues;
        Boolean realFilter = null;
        if (filter==null || filter.equals("yes") || filter.equals("true")) realFilter = true;
        if (filter!=null && (filter.equals("no") || filter.equals("false"))) realFilter = false;
        if (realFilter==null) {
            venues = venueRepository.findAll();
        } else {
            venues = venueRepository.findByOutdoor(realFilter);
        }
        model.addAttribute("venues", venues);
        model.addAttribute("indoorFilter", realFilter);
        return "venuelist";
    }

    @GetMapping({"/venuedetailsbyid", "/venuedetailsbyid/{id}"})
    public String venueDetailsById(Model model, @PathVariable(required = false) Integer id) {
        model.addAttribute("venue", venueRepository.findById(id).get());
        return "venuedetails";
    }



}
