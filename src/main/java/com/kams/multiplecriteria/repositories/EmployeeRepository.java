package com.kams.multiplecriteria.repositories;

import com.kams.multiplecriteria.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>,JpaSpecificationExecutor<Employee> {
    List<Employee> findByEnterpriseId(Long enterpriseId);
}
