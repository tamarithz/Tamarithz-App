package org.empleodigital.project.tamarithz.visit;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.empleodigital.project.tamarithz.model.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "visit_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotEmpty
    @Column(name = "description")
    private String description;

    @Column(name = "empleadoId")
    private Integer empleadoId;

    public Visit() {
        this.date = LocalDate.now();
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getEmpleadoId() {
        return this.empleadoId;
    }

    public void setEmpleadoId(Integer empleadoId) {
        this.empleadoId = empleadoId;
    }

}
