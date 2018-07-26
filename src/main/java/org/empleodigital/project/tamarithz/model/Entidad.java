
package org.empleodigital.project.tamarithz.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

@MappedSuperclass
public class Entidad extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "nombre")
    @NotEmpty
    private String nombre;

    @Column(name = "identificador")
    @NotEmpty
    private String identificador;

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificador() {
        return this.identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

}
