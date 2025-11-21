package hu.nje.gfekak.java.eloadas.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author danielbodi
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/table")
    public String table(Model model) {
        model.addAttribute("items", new String[]{"Alma", "Banán", "Cseresznye"});
        return "table";
    }
}
