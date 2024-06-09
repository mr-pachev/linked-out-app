package bg.softuni.linkedoutapp.service.impl;

import bg.softuni.linkedoutapp.model.DTO.EmployeeDTO;
import bg.softuni.linkedoutapp.model.entity.Employee;
import bg.softuni.linkedoutapp.repository.EmployeeRepository;
import bg.softuni.linkedoutapp.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Override
    public boolean findEmployee(EmployeeDTO employeeDTO) {
        Employee employee = mapper.map(employeeDTO, Employee.class);
        employee.setCompanies(employeeDTO.getCompanies());

        if (employeeRepository.findByFirstNameAndLastNameAndBirthDay(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getBirthDay()).isPresent()) {
            employeeDTO.setExistEmployee(true);
            return true;
        }

        return false;
    }

    @Override
    public void addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = mapper.map(employeeDTO, Employee.class);

        employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeDTO> allEmployees() {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        for (Employee employee : employeeRepository.findAll()) {
            EmployeeDTO employeeDTO = mapper.map(employee, EmployeeDTO.class);
            employeeDTOList.add(employeeDTO);
        }

        return employeeDTOList;
    }

    @Override
    public long employeeId(EmployeeDTO employeeDTO) {
        Employee employee = mapper.map(employeeDTO, Employee.class);

        long employeeId = employeeRepository.findByFirstNameAndLastNameAndBirthDay(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getBirthDay()).orElse(null).getId();

        return employeeId;
    }

    @Override
    public EmployeeDTO findUserById(long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        EmployeeDTO employeeDTO = mapper.map(employee, EmployeeDTO.class);

        return employeeDTO;
    }


}
