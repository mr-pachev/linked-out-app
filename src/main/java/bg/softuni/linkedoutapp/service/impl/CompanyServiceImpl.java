package bg.softuni.linkedoutapp.service.impl;

import bg.softuni.linkedoutapp.model.DTO.CompanyDTO;
import bg.softuni.linkedoutapp.model.entity.Company;
import bg.softuni.linkedoutapp.model.entity.Employee;
import bg.softuni.linkedoutapp.repository.CompanyRepository;
import bg.softuni.linkedoutapp.repository.EmployeeRepository;
import bg.softuni.linkedoutapp.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    public CompanyServiceImpl(CompanyRepository companyRepository, EmployeeRepository employeeRepository, ModelMapper mapper) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Override
    public boolean isExistCompany(CompanyDTO companyDTO) {
        if(companyRepository.findByName(companyDTO.getName()).isPresent()){
            companyDTO.setExist(true);
            return true;
        }
        return false;
    }

    @Override
    public void addCompany(CompanyDTO companyDTO) {
        Company company = mapper.map(companyDTO, Company.class);

        companyRepository.save(company);
    }

    @Override
    public List<CompanyDTO> allCompanies() {
        List<CompanyDTO> companyDTOList = new ArrayList<>();

        for (Company company : companyRepository.findAll()) {
            CompanyDTO companyDTO = mapper.map(company, CompanyDTO.class);
            companyDTO.setExist(true);
            companyDTOList.add(companyDTO);
        }

        return companyDTOList;
    }

    @Override
    public CompanyDTO findCompanyByName(String name) {
        Company company = companyRepository.findByName(name).orElse(null);
        CompanyDTO companyDTO =  mapper.map(company, CompanyDTO.class);
        return companyDTO;
    }

    @Override
    public CompanyDTO findCompanyById(long id) {
        Company company = companyRepository.findById(id).orElse(null);
        CompanyDTO companyDTO = mapper.map(company, CompanyDTO.class);

        return companyDTO;
    }

    @Override
    public void removeEmployees(long id) {
        Company company = companyRepository.findById(id).orElse(null);

        for (Employee employee : company.getEmployees()) {
            company.getEmployees().remove(employee);
        }

    }

    @Override
    public void removeCompany(long id) {
        companyRepository.deleteById(id);
    }
}
