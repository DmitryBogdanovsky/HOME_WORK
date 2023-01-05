package controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TilesController {

    @GetMapping({"/"})
    public String homepage(ModelAndView modelAndView) {
        modelAndView.addObject("greeting", "Hello, Person.");
        return "welcome";
    }

}
