package com.task.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.task.entity.Employee;
import com.task.exception.EmployeeNotFoundException;
import com.task.repository.EmployeeRepository;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmployeeService.class})
@ExtendWith(SpringExtension.class)
class EmployeeServiceTest {
    @MockBean
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    /**
     * Method under test: {@link EmployeeService#saveEmployee(Employee)}
     */
    @Test
    void testSaveEmployee() {
        Employee supervisor = new Employee();
        supervisor.setId(1L);
        supervisor.setName("Name");
        supervisor.setSupervisor(new Employee());

        Employee supervisor2 = new Employee();
        supervisor2.setId(1L);
        supervisor2.setName("Name");
        supervisor2.setSupervisor(supervisor);

        Employee supervisor3 = new Employee();
        supervisor3.setId(1L);
        supervisor3.setName("Name");
        supervisor3.setSupervisor(supervisor2);

        Employee supervisor4 = new Employee();
        supervisor4.setId(1L);
        supervisor4.setName("Name");
        supervisor4.setSupervisor(supervisor3);

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Name");
        employee.setSupervisor(supervisor4);
        when(employeeRepository.save(Mockito.<Employee>any())).thenReturn(employee);

        Employee supervisor5 = new Employee();
        supervisor5.setId(1L);
        supervisor5.setName("Name");
        supervisor5.setSupervisor(new Employee());

        Employee supervisor6 = new Employee();
        supervisor6.setId(1L);
        supervisor6.setName("Name");
        supervisor6.setSupervisor(supervisor5);

        Employee supervisor7 = new Employee();
        supervisor7.setId(1L);
        supervisor7.setName("Name");
        supervisor7.setSupervisor(supervisor6);

        Employee employee2 = new Employee();
        employee2.setId(1L);
        employee2.setName("Name");
        employee2.setSupervisor(supervisor7);
        assertSame(employee, employeeService.saveEmployee(employee2));
        verify(employeeRepository).save(Mockito.<Employee>any());
    }

    /**
     * Method under test: {@link EmployeeService#saveEmployee(Employee)}
     */
    @Test
    void testSaveEmployee2() {
        when(employeeRepository.save(Mockito.<Employee>any()))
                .thenThrow(new EmployeeNotFoundException("An error occurred"));

        Employee supervisor = new Employee();
        supervisor.setId(1L);
        supervisor.setName("Name");
        supervisor.setSupervisor(new Employee());

        Employee supervisor2 = new Employee();
        supervisor2.setId(1L);
        supervisor2.setName("Name");
        supervisor2.setSupervisor(supervisor);

        Employee supervisor3 = new Employee();
        supervisor3.setId(1L);
        supervisor3.setName("Name");
        supervisor3.setSupervisor(supervisor2);

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Name");
        employee.setSupervisor(supervisor3);
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.saveEmployee(employee));
        verify(employeeRepository).save(Mockito.<Employee>any());
    }

    /**
     * Method under test: {@link EmployeeService#createEmployeeHierarchy(Map)}
     */
    @Test
    void testCreateEmployeeHierarchy() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        employeeService.createEmployeeHierarchy(new HashMap<>());
    }

    /**
     * Method under test: {@link EmployeeService#createEmployeeHierarchy(Map)}
     */
    @Test
    void testCreateEmployeeHierarchy2() {
        Employee supervisor = new Employee();
        supervisor.setId(1L);
        supervisor.setName("Name");
        supervisor.setSupervisor(new Employee());

        Employee supervisor2 = new Employee();
        supervisor2.setId(1L);
        supervisor2.setName("Name");
        supervisor2.setSupervisor(supervisor);

        Employee supervisor3 = new Employee();
        supervisor3.setId(1L);
        supervisor3.setName("Name");
        supervisor3.setSupervisor(supervisor2);

        Employee supervisor4 = new Employee();
        supervisor4.setId(1L);
        supervisor4.setName("Name");
        supervisor4.setSupervisor(supervisor3);

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Name");
        employee.setSupervisor(supervisor4);

        Employee supervisor5 = new Employee();
        supervisor5.setId(1L);
        supervisor5.setName("Name");
        supervisor5.setSupervisor(new Employee());

        Employee supervisor6 = new Employee();
        supervisor6.setId(1L);
        supervisor6.setName("Name");
        supervisor6.setSupervisor(supervisor5);

        Employee supervisor7 = new Employee();
        supervisor7.setId(1L);
        supervisor7.setName("Name");
        supervisor7.setSupervisor(supervisor6);

        Employee supervisor8 = new Employee();
        supervisor8.setId(1L);
        supervisor8.setName("Name");
        supervisor8.setSupervisor(supervisor7);

        Employee employee2 = new Employee();
        employee2.setId(1L);
        employee2.setName("Name");
        employee2.setSupervisor(supervisor8);
        when(employeeRepository.findByName(Mockito.<String>any())).thenReturn(employee);
        when(employeeRepository.save(Mockito.<Employee>any())).thenReturn(employee2);

        HashMap<String, String> employeeHierarchy = new HashMap<>();
        employeeHierarchy.put("foo", "foo");
        employeeService.createEmployeeHierarchy(employeeHierarchy);
        verify(employeeRepository, atLeast(1)).findByName(Mockito.<String>any());
        verify(employeeRepository).save(Mockito.<Employee>any());
    }

    /**
     * Method under test: {@link EmployeeService#getSupervisorAndSupervisorOfSupervisor(String)}
     */
    @Test
    void testGetSupervisorAndSupervisorOfSupervisor() {
        Employee supervisor = new Employee();
        supervisor.setId(1L);
        supervisor.setName("Name");
        supervisor.setSupervisor(new Employee());

        Employee supervisor2 = new Employee();
        supervisor2.setId(1L);
        supervisor2.setName("Name");
        supervisor2.setSupervisor(supervisor);

        Employee supervisor3 = new Employee();
        supervisor3.setId(1L);
        supervisor3.setName("Name");
        supervisor3.setSupervisor(supervisor2);

        Employee supervisor4 = new Employee();
        supervisor4.setId(1L);
        supervisor4.setName("Name");
        supervisor4.setSupervisor(supervisor3);

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Name");
        employee.setSupervisor(supervisor4);
        when(employeeRepository.findByName(Mockito.<String>any())).thenReturn(employee);
        assertSame(employee, employeeService.getSupervisorAndSupervisorOfSupervisor("Employee Name"));
        verify(employeeRepository).findByName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link EmployeeService#getSupervisorAndSupervisorOfSupervisor(String)}
     */
    @Test
    void testGetSupervisorAndSupervisorOfSupervisor2() {
        Employee supervisor = new Employee();
        supervisor.setId(1L);
        supervisor.setName("Name");
        supervisor.setSupervisor(new Employee());

        Employee supervisor2 = new Employee();
        supervisor2.setId(1L);
        supervisor2.setName("Name");
        supervisor2.setSupervisor(supervisor);

        Employee supervisor3 = new Employee();
        supervisor3.setId(1L);
        supervisor3.setName("Name");
        supervisor3.setSupervisor(supervisor2);

        Employee supervisor4 = new Employee();
        supervisor4.setId(1L);
        supervisor4.setName("Name");
        supervisor4.setSupervisor(supervisor3);

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Name");
        employee.setSupervisor(supervisor4);
        when(employeeRepository.findByName(Mockito.<String>any())).thenReturn(employee);
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getSupervisorAndSupervisorOfSupervisor(null));
        verify(employeeRepository).findByName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link EmployeeService#getSupervisorAndSupervisorOfSupervisor(String)}
     */
    @Test
    void testGetSupervisorAndSupervisorOfSupervisor3() {
        when(employeeRepository.findByName(Mockito.<String>any()))
                .thenThrow(new EmployeeNotFoundException("An error occurred"));
        assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.getSupervisorAndSupervisorOfSupervisor("Employee Name"));
        verify(employeeRepository).findByName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link EmployeeService#getAll()}
     */
    @Test
    void testGetAll() {
        ArrayList<Employee> employeeList = new ArrayList<>();
        when(employeeRepository.findAll()).thenReturn(employeeList);
        List<Employee> actualAll = employeeService.getAll();
        assertSame(employeeList, actualAll);
        assertTrue(actualAll.isEmpty());
        verify(employeeRepository).findAll();
    }

    /**
     * Method under test: {@link EmployeeService#getAll()}
     */
    @Test
    void testGetAll2() {
        when(employeeRepository.findAll()).thenThrow(new EmployeeNotFoundException("An error occurred"));
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getAll());
        verify(employeeRepository).findAll();
    }
}

