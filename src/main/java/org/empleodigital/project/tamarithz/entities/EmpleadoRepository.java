
package org.empleodigital.project.tamarithz.entities;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface EmpleadoRepository extends Repository<Empleado, Integer> {

    @Transactional(readOnly = true)
    Empleado findById(Integer id);

    void save(Empleado empleado);

}

