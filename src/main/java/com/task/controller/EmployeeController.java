package com.task.controller;

import com.task.entity.Employee;
import com.task.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {


    //@Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<String> createEmployeeHierarchy(@RequestBody Map<String, String> employeeData) {
        Map<String, String> map = new HashMap<>();
        String key = null;
        for (Map.Entry<String, String> entry : employeeData.entrySet()) {
            key=entry.getKey();
            String value = entry.getValue();
            if (value != null) {
                map.put(key,value);
            }
        }
        employeeService.createEmployeeHierarchy(map);
        return ResponseEntity.ok("Employee Created Successfully with name : " +key);
    }


    //http://localhost:8080/api/employees/{employeeName}/supervisors
    @GetMapping("/{employeeName}/supervisors")
    public ResponseEntity<Map<String, String>> getSupervisorsByName(@PathVariable String employeeName) {
        Employee supervisor = employeeService.getSupervisorAndSupervisorOfSupervisor(employeeName);
        Employee supervisor1= supervisor.getSupervisor();
        if (supervisor1 == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, String> supervisorsMap = new HashMap<>();
        supervisorsMap.put(employeeName, supervisor1.getName());
//        if (supervisor.getSupervisor() != null) {
//            supervisorsMap.put("supervisorOfSupervisor", supervisor.getSupervisor().getName());
//        }

        return ResponseEntity.ok(supervisorsMap);
    }

    //http://localhost:8080/api/employees/all
    @GetMapping("/all")
    public ResponseEntity<Map<String, String>> getSupervisors() {
        List<Employee> all = employeeService.getAll();
        if (all == null) {
            return ResponseEntity.notFound().build();
        }


        List<String> list=new ArrayList<>();
        for(Employee emp : all){

            list.add(emp.getName());

        }
        Map<String, String> supervisorsMap = new HashMap<>();
        System.out.println(list);
        for(String emp:list) {
            Employee supervisor = employeeService.getSupervisorAndSupervisorOfSupervisor(emp);
            //System.out.println("1");
            Employee supervisor1 = supervisor.getSupervisor();
            //System.out.println("2");
            //supervisor1.getSupervisor();
            //System.out.println("3");

            //System.out.println(employeeName);
//            Employee supervisor2=supervisor1.getSupervisor();
            //System.out.println(supervisor1.getSupervisor());
            if (supervisor1 == null) {
                //System.out.println("4");
                supervisorsMap.put(emp, "owner");
            } else {
                supervisorsMap.put(emp, supervisor1.getName());
            }
        }
        return ResponseEntity.ok(supervisorsMap);
    }
}
