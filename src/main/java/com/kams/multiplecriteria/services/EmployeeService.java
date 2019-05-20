package com.kams.multiplecriteria.services;

import com.kams.multiplecriteria.entities.Employee;
import com.kams.multiplecriteria.repositories.EmployeeRepository;
import com.kams.multiplecriteria.repositories.specifications.employee.EmployeeSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    public List<Employee> searchEmployee(String search){

        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>|>=|<=)(\\w+?)(\"or\"|\"and\"|;)");
        Matcher matcher = pattern.matcher(search);

        EmployeeSpecificationBuilder builder = new EmployeeSpecificationBuilder();
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3),matcher.group(4).replace("\"",""));
        }


        Specification<Employee> spec = builder.build();
        return employeeRepository.findAll(spec);
    }

    public List<Employee> getEmployeesByEnterpriseId(Long id){
        return employeeRepository.findByEnterpriseId(id);
    }
}
