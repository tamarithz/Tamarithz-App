package org.empleodigital.project.tamarithz.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.empleodigital.project.tamarithz.model.NamedEntity;
import org.empleodigital.project.tamarithz.visit.Visit;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

@Entity
@Table(name = "empleado")
public class Empleado extends NamedEntity {

	private static final long serialVersionUID = 1L;

	
	private String apellidos;
	
	private String email;
	
    @ManyToOne
    @JoinColumn(name = "identificador")
    private Empresa empresa;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadoId", fetch = FetchType.EAGER)
    private Set<Visit> visits = new LinkedHashSet<>();

    public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Empresa getEmpresa() {
        return this.empresa;
    }

    protected void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    protected Set<Visit> getVisitsInternal() {
        if (this.visits == null) {
            this.visits = new HashSet<>();
        }
        return this.visits;
    }

    protected void setVisitsInternal(Set<Visit> visits) {
        this.visits = visits;
    }

    public List<Visit> getVisits() {
        List<Visit> sortedVisits = new ArrayList<>(getVisitsInternal());
        PropertyComparator.sort(sortedVisits,
                new MutableSortDefinition("date", false, false));
        return Collections.unmodifiableList(sortedVisits);
    }

    public void addVisit(Visit visit) {
        getVisitsInternal().add(visit);
        visit.setEmpleadoId(this.getId());
    }

}
