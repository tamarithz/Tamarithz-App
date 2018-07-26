
package org.empleodigital.project.tamarithz.report;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.empleodigital.project.tamarithz.model.Entidad;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "report")
public class Report extends Entidad {

	private static final long serialVersionUID = 1L;
	
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
    
    
}
