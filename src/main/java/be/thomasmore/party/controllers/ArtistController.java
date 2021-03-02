package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Artist;
import be.thomasmore.party.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

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
