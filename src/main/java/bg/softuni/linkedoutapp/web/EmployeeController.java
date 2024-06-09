package bg.softuni.linkedoutapp.web;

import bg.softuni.linkedoutapp.model.DTO.CompanyDTO;
import bg.softuni.linkedoutapp.model.DTO.EmployeeDTO;
import bg.softuni.linkedoutapp.model.entity.Employee;
import bg.softuni.linkedoutapp.repository.EmployeeRepository;
import bg.softuni.linkedoutapp.service.CompanyService;
import bg.softuni.linkedoutapp.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EmployeeController {
    private final EmployeeService employeeService;
    private final CompanyService companyService;

    public EmployeeController(EmployeeService employeeService, CompanyService companyService) {
        this.employeeService = employeeService;
        this.companyService = companyService;
    }


    @GetMapping("/employees/add")
    public String viewAddEmployee(Model model){

        if (!model.containsAttribute("employeeDTO")) {
            model.addAttribute("employeeDTO", new EmployeeDTO());
        }
        List<CompanyDTO> companyDTOList = companyService.allCompanies();
        model.addAttribute("allCompanies", companyDTOList);

        return "employee-add";
    }

    @PostMapping("/employees/add")
    public String addEmployee(@Valid EmployeeDTO employeeDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors() || employeeService.findEmployee(employeeDTO)){
            redirectAttributes.addFlashAttribute("employeeDTO", employeeDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.employeeDTO", bindingResult);

            return "redirect:/employees/add";
        }

        employeeService.addEmployee(employeeDTO);

        long employeeId = employeeService.employeeId(employeeDTO);
        return "redirect:/employee-details/" + employeeId;
    }

    @GetMapping("/employees/all")
    public String viewAllCompanies(Model model){
        List<EmployeeDTO> employeeDTOList = employeeService.allEmployees();
        model.addAttribute("allEmployees", employeeDTOList);

        return "employee-all";
    }

    @GetMapping("/employee-details")
    public String viewEmployeeDetails(){

        return "employee-details";
    }
}

