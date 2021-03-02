package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.VenueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Optional;


@Controller
public class VenueController {
    @Autowired
    private VenueRepository venueRepository;


    private Logger logger = LoggerFactory.getLogger(VenueController.class);

    @GetMapping({"/venuedetails", "/venuedetails/{venueId}"})
    public String venueDetails(Model model, @PathVariable(required = false) Integer venueId) {
        if (venueId == null) return "venuedetails";
        Optional<Venue> optionalVenue = venueRepository.findById(venueId);
        if (optionalVenue.isPresent()) {
            model.addAttribute("venue", optionalVenue.get());
            model.addAttribute("prev", venueId > 1 ? venueId - 1 : venueRepository.count());
            model.addAttribute("next", venueId < venueRepository.count() ? venueId + 1 : 1);
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
    public String filter(Model model, @RequestParam(required = false) Integer minimumCapacity, @RequestParam(required = false) Integer maximumCapacity) {
        Iterable<Venue> venues;
        boolean showFilter = true;
        logger.info("venueListWithFilter");
        logger.info(String.format("venueListWithFilter -- min=%d", minimumCapacity));
        model.addAttribute("showFilter", showFilter);
        long nrVenues;
        model.addAttribute("minimumCapacity", minimumCapacity);
        model.addAttribute("maximumCapacity", maximumCapacity);
        if (minimumCapacity != null && maximumCapacity !=null) {
           venues = venueRepository.findByCapacityBetween(minimumCapacity, maximumCapacity);
            Collection venueCol = (Collection<Venue>) venues;
            nrVenues = venueCol.size();

        }else if(minimumCapacity != null) {
            venues = venueRepository.findByCapacityGreaterThanEqual(minimumCapacity);
            Collection venueCol = (Collection<Venue>) venues;
            nrVenues = venueCol.size();
        }
        else if (maximumCapacity !=null){
            venues = venueRepository.findByCapacityLessThanEqual(maximumCapacity);
            Collection venueCol = (Collection<Venue>) venues;
            nrVenues = venueCol.size();
        }
       else {
            venues = venueRepository.findAll();
            nrVenues = venueRepository.count();
        }
        model.addAttribute("nrVenues", nrVenues);
        model.addAttribute("venues", venues);
        model.addAttribute("showFilter", true);
        return "venuelist";
    }
}
