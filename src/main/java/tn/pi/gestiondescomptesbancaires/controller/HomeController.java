package tn.pi.gestiondescomptesbancaires.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index() { return "index"; }

    @GetMapping("/employe/dashboard")
    public String dashboard() { return "dashboard"; }
}
