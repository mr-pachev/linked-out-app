package bg.softuni.linkedoutapp.service;

import bg.softuni.linkedoutapp.model.DTO.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    public boolean findEmployee(EmployeeDTO employeeDTO);

    public void addEmployee(EmployeeDTO employeeDTO);

    public List<EmployeeDTO> allEmployees();

    public long employeeId(EmployeeDTO employeeDTO);
    public EmployeeDTO findUserById(long id);

}
