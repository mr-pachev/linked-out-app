package bg.softuni.linkedoutapp.web;

import bg.softuni.linkedoutapp.model.DTO.CompanyDTO;
import bg.softuni.linkedoutapp.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/companies/add")
    public String viewCompanyAdd(Model model) {
        if (!model.containsAttribute("companyDTO")) {
            model.addAttribute("companyDTO", new CompanyDTO());
        }
        List<CompanyDTO> companyDTOList = companyService.allCompanies();
        model.addAttribute("allCompanies", companyDTOList);

        return "/company-add";
    }

    @PostMapping("/companies/add")
    public String addCompany(@Valid CompanyDTO companyDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || companyService.isExistCompany(companyDTO)) {
            redirectAttributes.addFlashAttribute("companyDTO", companyDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.companyDTO", bindingResult);
            return "redirect:/companies/add";
        }

        companyService.addCompany(companyDTO);

        long companyId = companyService.findCompanyByName(companyDTO.getName()).getId();
        return "redirect:/company-details/" + companyId;
    }

    @GetMapping("/companies/all")
    public String viewAllCompanies(Model model) {
        List<CompanyDTO> companyDTOList = companyService.allCompanies();
        model.addAttribute("allCompanies", companyDTOList);

        return "company-all";
    }

    @PostMapping("/delete-company")
    public String deleteCompany(@RequestParam("id") Long id) {
        companyService.removeEmployees(id);
        companyService.removeCompany(id);

        return "redirect:/companies/all";
    }
}
