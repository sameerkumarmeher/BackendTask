package com.task.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.entity.Employee;
import com.task.service.EmployeeService;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.User;
import org.apache.catalina.realm.UserDatabaseRealm;
import org.apache.catalina.users.MemoryUserDatabase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {EmployeeController.class})
@ExtendWith(SpringExtension.class)
class EmployeeControllerTest {
    @Autowired
    private EmployeeController employeeController;

    @MockBean
    private EmployeeService employeeService;

    /**
     * Method under test: {@link EmployeeController#createEmployeeHierarchy(Map)}
     */
    @Test
    void testCreateEmployeeHierarchy() throws Exception {
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/api/v1/employee")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new HashMap<>()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }

    /**
     * Method under test: {@link EmployeeController#createEmployeeHierarchy(Map)}
     */
    @Test
    void testCreateEmployeeHierarchy2() throws Exception {
        User user = mock(User.class);
        when(user.getName()).thenReturn("Name");
        UserDatabaseRealm.UserDatabasePrincipal principal = new UserDatabaseRealm.UserDatabasePrincipal(user,
                new MemoryUserDatabase());

        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/employee");
        getResult.principal(principal);
        MockHttpServletRequestBuilder contentTypeResult = getResult.contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new HashMap<>()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }

    /**
     * Method under test: {@link EmployeeController#getSupervisors()}
     */
    @Test
    void testGetSupervisors() throws Exception {
        when(employeeService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/employee/all");
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{}"));
    }

    /**
     * Method under test: {@link EmployeeController#getSupervisors()}
     */
    @Test
    void testGetSupervisors2() throws Exception {
        Employee supervisor = new Employee();
        supervisor.setId(1L);
        supervisor.setName("?");
        supervisor.setSupervisor(new Employee());

        Employee supervisor2 = new Employee();
        supervisor2.setId(1L);
        supervisor2.setName("?");
        supervisor2.setSupervisor(supervisor);

        Employee supervisor3 = new Employee();
        supervisor3.setId(1L);
        supervisor3.setName("?");
        supervisor3.setSupervisor(supervisor2);

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("?");
        employee.setSupervisor(supervisor3);

        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);

        Employee supervisor4 = new Employee();
        supervisor4.setId(1L);
        supervisor4.setName("Name");
        supervisor4.setSupervisor(new Employee());

        Employee supervisor5 = new Employee();
        supervisor5.setId(1L);
        supervisor5.setName("Name");
        supervisor5.setSupervisor(supervisor4);

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
        when(employeeService.getSupervisorAndSupervisorOfSupervisor(Mockito.<String>any())).thenReturn(employee2);
        when(employeeService.getAll()).thenReturn(employeeList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/employee/all");
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"?\":\"Name\"}"));
    }

    /**
     * Method under test: {@link EmployeeController#getSupervisorsByName(String)}
     */
    @Test
    void testGetSupervisorsByName() throws Exception {
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
        when(employeeService.getSupervisorAndSupervisorOfSupervisor(Mockito.<String>any())).thenReturn(employee);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/employee/{employeeName}/supervisors", "Employee Name");
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"Employee Name\":\"Name\"}"));
    }
}

