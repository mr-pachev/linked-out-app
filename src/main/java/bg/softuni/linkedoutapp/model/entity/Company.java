package bg.softuni.linkedoutapp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.text.DecimalFormat;

@Entity
@Table(name = "companies")
public class Company extends BaseEntity{
    @NotNull
    private DecimalFormat budget;
    @NotNull
    @Column(columnDefinition = "TEXT")
    private String description;
    @NotNull
    @Column(unique = true)
    private String name;
    @NotNull
    private String town;
}
