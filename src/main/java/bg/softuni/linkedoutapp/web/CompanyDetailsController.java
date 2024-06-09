package bg.softuni.linkedoutapp.web;

import bg.softuni.linkedoutapp.model.DTO.CompanyDTO;
import bg.softuni.linkedoutapp.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CompanyDetailsController {
    private final CompanyService companyService;

    public CompanyDetailsController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/company-details/{id}")
    public String viewCompanyDetails(@PathVariable("id") long id, Model model){
        CompanyDTO companyDTO = companyService.findCompanyById(id);

        model.addAttribute("companyDetails", companyDTO);

        return "company-details";
    }
}
