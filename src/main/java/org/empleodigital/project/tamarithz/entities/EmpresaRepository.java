
package org.empleodigital.project.tamarithz.entities;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface EmpresaRepository extends Repository<Empresa, Integer> {

    /**
     * Recupera la {@link Empresa} de la memoria de almacenamiento,devolviendo la que tenga ese <i>CIF</i>.
     * @param identificador el valor de la busqueda
     * @return una Collection de {@link Empresa}s (o vacío si no encuentra ninguna)
     * 
     */
    @Query("SELECT DISTINCT empresa FROM Empresa empresa left join fetch empresa.empleados WHERE empresa.identificador LIKE :identificador%")
    @Transactional(readOnly = true)
    Collection<Empresa> findByIdentificador(@Param("identificador") String identificador);

    /**
     * Recupera una {@link Empresa} del almacenamiento por su id.
     * @param id el id a buscar
     * @return la {@link Empresa} si se encuentra
     */
    @Query("SELECT empresa FROM Empresa empresa left join fetch empresa.empleados WHERE empresa.id =:id")
    @Transactional(readOnly = true)
    Empresa findById(@Param("id") Integer id);

    /**
     * Guarda una {@link Empresa} en la memoria, tanto para actualizacion como para inserción.
     * @param empresa la {@link Empresa} a almacenar
     */
    void save(Empresa empresa);


}
