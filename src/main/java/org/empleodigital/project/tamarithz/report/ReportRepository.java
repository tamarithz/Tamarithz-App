package org.empleodigital.project.tamarithz.report;

import java.util.Collection;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface ReportRepository extends Repository<Report, Integer> {

    @Transactional(readOnly = true)
    @Cacheable("reports")
    Collection<Report> findAll() throws DataAccessException;
}
