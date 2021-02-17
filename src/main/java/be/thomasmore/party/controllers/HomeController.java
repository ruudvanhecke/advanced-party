package be.thomasmore.party.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    private final int mySpecialNumber = 729;
    private final String[] venueNames = { "De Club", "De Loods", "Zapoi", "Nekkerhal"};

    @GetMapping({"/" , "/home"})
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @GetMapping({"/venuedetails", "/venuedetails/{venueName}"})
    public String venueDetails(Model model, @PathVariable(required = false) String venueName) {
        model.addAttribute("venuename", venueName);
        return "venuedetails";
    }


    @GetMapping({"/venuedetailsbyindex", "/venuedetailsbyindex/{venueId}"})
    public String venueDetailsByIndex(Model model, @PathVariable(required = false) Integer venueId) {
        if (venueId!=null && venueId>=0 && venueId<venueNames.length)
        {
            model.addAttribute("venuename", venueNames[venueId]);
            model.addAttribute("prev", venueId>0 ? venueId - 1 : venueNames.length - 1);
            model.addAttribute("next" , venueId< venueNames.length - 1 ? venueId + 1 : 0);
        }
        //model.addAttribute("venuename", venueName);
        return "venuedetails";
    }

    @GetMapping("/venuelist")
    public String venueList(Model model) {
        model.addAttribute("venues", venueNames);
        return "venuelist";
    }

}