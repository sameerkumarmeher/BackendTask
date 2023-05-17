package com.task.service;

import com.task.entity.Employee;
import com.task.exception.EmployeeNotFoundException;
import com.task.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    public void createEmployeeHierarchy(Map<String, String> employeeHierarchy) {
        for (Map.Entry<String, String> entry : employeeHierarchy.entrySet()) {
            String employeeName = entry.getKey();
            String supervisorName = entry.getValue();
            Employee employee = employeeRepository.findByName(employeeName);
            Employee supervisor = employeeRepository.findByName(supervisorName);
            if (employee == null) {
                employee = new Employee(employeeName);
            }
            if (supervisor == null) {
                supervisor = new Employee(supervisorName);
            }
//            employee.setSupervisor(supervisor);
//            employeeRepository.save(employee);
            employee.setSupervisor(employeeRepository.findByName(supervisorName));
            employee.setName(employeeName);
            employeeRepository.save(employee);
        }
    }
    public Employee getSupervisorAndSupervisorOfSupervisor(String employeeName) {
        Employee employee = employeeRepository.findByName(employeeName);
        if(employeeName==null)
        {
            throw new EmployeeNotFoundException("Employee not found with name :"+employeeName);
        }
                //.orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));

//        Employee supervisor = employee.getSupervisor();
//        if (supervisor == null) {
//            return null;
//        }

//        return supervisor.getSupervisor();
        return employee;
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }
}
