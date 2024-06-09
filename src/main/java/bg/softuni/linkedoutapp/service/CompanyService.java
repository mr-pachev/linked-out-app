package bg.softuni.linkedoutapp.service;

import bg.softuni.linkedoutapp.model.DTO.CompanyDTO;

import java.util.List;
import java.util.Set;

public interface CompanyService {
    public boolean isExistCompany(CompanyDTO companyDTO);

    public void addCompany(CompanyDTO companyDTO);

    public List<CompanyDTO> allCompanies();
    public CompanyDTO findCompanyByName(String name);

    public CompanyDTO findCompanyById(long id);

    public void removeEmployees(long id);
    public void removeCompany(long id);
}
