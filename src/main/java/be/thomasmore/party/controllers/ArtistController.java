package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Artist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import be.thomasmore.party.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;
    private Logger logger = LoggerFactory.getLogger(ArtistController.class);

    @GetMapping({"/artistdetails", "/artistdetails/{artistId}"})
    public String artistDetails(Model model, @PathVariable(required = false) Integer artistId) {
        if (artistId == null) return "artistdetails";
        Optional<Artist> optionalArtist = artistRepository.findById(artistId);
        if (optionalArtist.isPresent()) {
            model.addAttribute("artist", optionalArtist.get());
            model.addAttribute("prev", artistId > 1 ? artistId - 1 : artistRepository.count());
            model.addAttribute("next", artistId < artistRepository.count() ? artistId + 1 : 1);
        }
        return "artistdetails";
    }

    @GetMapping("/artistlist/filter")
    public String artistlist(Model model, @RequestParam(required = false) String keyWord) {
        Iterable<Artist> artists = artistRepository.findAll();
        logger.info("artistListWithFilter -- keyword= " + keyWord);
        boolean showFilter = true;
        model.addAttribute("showFilter", showFilter);
        long nrArtists;
            artists = artistRepository.findByKeyword(keyWord);
            Collection artistCol = (Collection<Artist>) artists;
            nrArtists = artistCol.size();

        model.addAttribute("keyWord", keyWord);
        model.addAttribute("nrArtists", nrArtists);
        model.addAttribute("artists", artists);
        model.addAttribute("showFilter", true);
        return "artistlist";
    }

    @GetMapping("/artistlist")
    public String artistList(Model model, @PathVariable(required = false) String what) {
        Iterable<Artist> artists = artistRepository.findAll();
        boolean showFilter = false;
        model.addAttribute("showFilter", showFilter);
        model.addAttribute("artists", artists);
        model.addAttribute("nrArtists", artistRepository.count());
        return "artistlist";
    }


}