package com.kams.multiplecriteria.restcontrollers;

import com.kams.multiplecriteria.entities.Employee;
import com.kams.multiplecriteria.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<Employee> getAllEmployees(){
        return employeeService.getEmployees();
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> searchEmployees(@RequestParam(name = "criteria") String criteria){
        return employeeService.searchEmployee(criteria);
    }

}
