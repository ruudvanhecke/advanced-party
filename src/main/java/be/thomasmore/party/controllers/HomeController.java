package be.thomasmore.party.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final int mySpecialNumber = 729;

    @GetMapping({"/","/home"})
    public String home(Model model) {
        model.addAttribute("mijnSpeciaalNummer", mySpecialNumber);
        return "home";
    }
    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("mijnSpeciaalNummer", mySpecialNumber);
        return "about";
    }
}
