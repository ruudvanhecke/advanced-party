package be.thomasmore.party.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    private final int mySpecialNumber = 729;

    @GetMapping({"/" , "/home"})
    public String home(Model model) {
        model.addAttribute("mijnSpeciaalNummer", mySpecialNumber);
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("mijnSpeciaalNummer", mySpecialNumber);
        return "about";
    }

    @GetMapping({"/venuedetails", "/venuedetails/{venueName}"})
    public String venueDetails(Model model, @PathVariable(required = false) String venueName) {
        model.addAttribute("venuename", venueName==null ? "--no venue specified--" : venueName);
        return "venuedetails";
    }

    @GetMapping("/venuelist")
    public String venuelist(Model model) {
        return "venuelist";
    }

}