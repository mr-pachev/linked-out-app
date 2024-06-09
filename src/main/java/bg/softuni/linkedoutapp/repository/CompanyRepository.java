package bg.softuni.linkedoutapp.repository;

import bg.softuni.linkedoutapp.model.entity.Company;
import bg.softuni.linkedoutapp.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByName(String companyName);
    Optional<Company>findById(long id);
}
