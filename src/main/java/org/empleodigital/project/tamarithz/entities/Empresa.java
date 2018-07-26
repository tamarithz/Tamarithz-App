
package org.empleodigital.project.tamarithz.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.empleodigital.project.tamarithz.model.Entidad;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "empresa")
public class Empresa extends Entidad {

	private static final long serialVersionUID = 1L;

	@Column(name = "direccion")
    @NotEmpty
    private String direccion;

    @Column(name = "ciudad")
    @NotEmpty
    private String ciudad;

    @Column(name = "email")
    @NotEmpty
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresa")
    private Set<Empleado> empleados;

    public Empresa() {
		
	}
    
    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return this.ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    protected Set<Empleado> getEmpleadosInternal() {
        if (this.empleados == null) {
            this.empleados = new HashSet<>();
        }
        return this.empleados;
    }

    protected void setEmpleadosInternal(Set<Empleado> empleados) {
        this.empleados = empleados;
    }

    public List<Empleado> getEmpleados() {
        List<Empleado> empleadosOrdenados = new ArrayList<>(getEmpleadosInternal());
        PropertyComparator.sort(empleadosOrdenados,
                new MutableSortDefinition("nombre", true, true));
        return Collections.unmodifiableList(empleadosOrdenados);
    }

    public void addEmpleado(Empleado empleado) {
        if (empleado.isNew()) {
        	getEmpleadosInternal().add(empleado);
        }
        empleado.setEmpresa(this);
    }

    public Empleado getEmpleado(String name) {
        return getEmpleado(name, false);
    }

    public Empleado getEmpleado(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Empleado empleado : getEmpleadosInternal()) {
            if (!ignoreNew || !empleado.isNew()) {
                String compName = empleado.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return empleado;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId()).append("new", this.isNew())
                .append("nombre", this.getNombre())
                .append("identificador", this.getIdentificador()).append("direccion", this.direccion)
                .append("ciudad", this.ciudad).append("email", this.email).toString();
    }
}
