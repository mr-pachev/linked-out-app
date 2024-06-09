package bg.softuni.linkedoutapp.web;

import bg.softuni.linkedoutapp.model.DTO.CompanyDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public String viewHome(){

        return "index";
    }

}
