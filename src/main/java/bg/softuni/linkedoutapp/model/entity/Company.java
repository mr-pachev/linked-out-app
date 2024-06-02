package bg.softuni.linkedoutapp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Set;

@Entity
@Table(name = "companies")
public class Company extends BaseEntity{
    @NotNull
    @Column(precision = 19, scale = 2)
    private BigDecimal budget;
    @NotNull
    @Column(columnDefinition = "TEXT")
    private String description;
    @NotNull
    @Column(unique = true)
    private String name;
    @NotNull
    private String town;
    @OneToMany(targetEntity = Employee.class, mappedBy = "company")
    Set<Employee> employees;

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
