package bg.softuni.linkedoutapp.web;

import bg.softuni.linkedoutapp.model.DTO.EmployeeDTO;
import bg.softuni.linkedoutapp.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EmployeeDetailsController {
    private final EmployeeService employeeService;

    public EmployeeDetailsController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee-details/{id}")
    public String employeeDetails(@PathVariable("id") long id, Model model) {
        EmployeeDTO employeeDTO = employeeService.findUserById(id);

        model.addAttribute("employeeDTO",employeeDTO );

        return "employee-details";
    }


}
