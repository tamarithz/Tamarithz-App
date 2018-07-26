
package org.empleodigital.project.tamarithz.visit;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;

public interface VisitRepository extends Repository<Visit, Integer> {

    void save(Visit visit) throws DataAccessException;

    List<Visit> findByEmpleadoId(Integer empleadoId);

}
