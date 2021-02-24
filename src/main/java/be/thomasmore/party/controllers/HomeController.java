package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Artist;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.ArtistRepository;
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
    @Autowired
    private ArtistRepository artistRepository;

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

    @GetMapping({"/venuelist", "/venuelist/{what}"})
    public String venueList(Model model, @PathVariable(required = false) String what) {
        Iterable<Venue> venues = venueRepository.findAll();
        model.addAttribute("venues", venues);
        return "venuelist";
    }

    @GetMapping({"/venuelist/outdoor","/venuelist/outdoor/{filter}"})
    public String venueListOutdoor(Model model, @PathVariable(required = false) String filter) {
        boolean boolFilter;
        if (filter!=null && (filter.equals("false") || filter.equals("no") || filter.equals("neen") || filter.equals("0"))) {
            boolFilter = false;
        } else {
            boolFilter = true;
        }
        Iterable<Venue> venues = venueRepository.findByOutdoor(boolFilter);
        model.addAttribute("outdoorFilter", boolFilter);
        model.addAttribute("venues", venues);
        return "venuelist";
    }

    @GetMapping({"/artistdetails", "/artistdetails/{artistId}"})
    public String artistDetails(Model model, @PathVariable(required = false) Integer artistId) {
        if (artistId==null) return "artistdetails";
        Optional<Artist> optionalArtist = artistRepository.findById(artistId);
        if (optionalArtist.isPresent()) {
            model.addAttribute("artist", optionalArtist.get());
            model.addAttribute("prev", artistId>1 ? artistId-1 : artistRepository.count());
            model.addAttribute("next", artistId<artistRepository.count() ? artistId+1 : 1);
        }
        return "artistdetails";
    }

    @GetMapping("/artistlist")
    public String artistList(Model model) {
        Iterable<Artist> artists = artistRepository.findAll();
        model.addAttribute("artists", artists);
        return "artistlist";
    }
}
