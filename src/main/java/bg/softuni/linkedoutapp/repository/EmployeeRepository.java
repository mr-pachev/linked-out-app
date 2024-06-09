package bg.softuni.linkedoutapp.repository;

import bg.softuni.linkedoutapp.model.entity.Company;
import bg.softuni.linkedoutapp.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByFirstNameAndLastNameAndBirthDay(String firstName, String lastName, LocalDate birthDay);
    Optional<Employee> findById(long id);
}
