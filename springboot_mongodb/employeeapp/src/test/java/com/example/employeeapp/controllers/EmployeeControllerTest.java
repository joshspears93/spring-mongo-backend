package com.example.employeeapp.controllers;

import com.example.employeeapp.models.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeControllerTest {

    private static EmployeeController employeeControllerMock = null;
    private static Employee employee1;
    private static Employee employee2;
    private static Employee employee3;
    private static Employee employee2Updated;

    @Before
    public void setUp() throws Exception {
        //Create mock of object EmployeeController
        employeeControllerMock = mock(EmployeeController.class);
        //LocalDate birthDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        //Create test instances of Employee
        employee1 = new Employee("1234", "John", "Smith", "B", "smith_john@google.com", "1234567891",
                "Direct", LocalDate.of(2018, 3, 23), "6895 Working Lane", "Apt 213", "Virginia Beach",
                "VA", 22304, true);
        employee2 = new Employee("4321", "Jane", "Doe", "F", "doe_jane@google.com", "7891234567",
                "Indirect", LocalDate.of(2016, 11, 13), "123 Loser Lane", "", "Richmond",
                "VA", 22205, true);
        employee3 = new Employee("6789", "Terry", "Smith", "P", "jones_terry@google.com", "4567891234",
                "Director", LocalDate.of(2006, 9, 18), "456 Loser Court", "", "Harrisonburg",
                "VA", 22316, true);
        employee2Updated = new Employee("4321", "Jane", "Pardon", "D", "doe_jane@google.com", "7891234567",
                "Indirect", LocalDate.of(2016, 11, 13), "123 Loser Lane", "", "Richmond",
                "VA", 22205, true);

        //Stubbing the methods from EmployeeController
        when(employeeControllerMock.getAllEmployees()).thenReturn(Arrays.asList(employee1,employee2,employee3));
        when(employeeControllerMock.createEmployee(employee1)).thenReturn(employee1);
        when(employeeControllerMock.getEmployeeByID("1234")).thenReturn(ResponseEntity.ok().body(employee1));
        when(employeeControllerMock.updateEmployee("4321", employee2Updated)).thenReturn(ResponseEntity.ok().body(employee2Updated));
        when(employeeControllerMock.deleteEmployee("6789")).thenReturn(ResponseEntity.ok().body("employee record deleted"));
        when(employeeControllerMock.deleteEmployee("8899")).thenReturn(ResponseEntity.badRequest().body("no employee record found"));
        when(employeeControllerMock.searchEmployeeByLastName("Smith")).thenReturn(Arrays.asList(employee1, employee3));
        when(employeeControllerMock.searchEmployeeByLastName("Jones")).thenReturn(Arrays.asList());

    }

    @After
    public void tearDown() throws Exception {
        employeeControllerMock = null;
        employee1 = null;
        employee2 = null;
        employee3 = null;
        employee2Updated = null;

    }

    @Test
    public void getAllEmployeesTest() {
        List<Employee> employeeList = employeeControllerMock.getAllEmployees();
        assertEquals(3, employeeList.size());
    }

    @Test
    public void createEmployeeTest() {
        Employee returnedEmployee = employeeControllerMock.createEmployee(employee1);
        assertNotNull(returnedEmployee);
        assertEquals(returnedEmployee.getFirstName(), "John");
        assertEquals(returnedEmployee.getLastName(), "Smith");
        assertEquals(returnedEmployee.getMiddleInitial(), "B");
        assertEquals(returnedEmployee.getEmailAddress(), "smith_john@google.com");
        assertEquals(returnedEmployee.getPhoneNumber(), "1234567891");
        assertEquals(returnedEmployee.getPositionCategory(), "Direct");
        assertEquals(returnedEmployee.getDateHired(), LocalDate.of(2018, 3, 23));
        assertEquals(returnedEmployee.getAddressOne(), "6895 Working Lane");
        assertEquals(returnedEmployee.getAddressTwo(), "Apt 213");
        assertEquals(returnedEmployee.getCity(), "Virginia Beach");
        assertEquals(returnedEmployee.getState(), "VA");
        assertEquals(returnedEmployee.getZipCode(), new Integer(22304));
        assertEquals(returnedEmployee.getActive(), true);
    }

    @Test
    public void getEmployeeByIDTest() {
        ResponseEntity<Employee> returnedEmployee = employeeControllerMock.getEmployeeByID("1234");
        assertNotNull(returnedEmployee);
        assertEquals(returnedEmployee, ResponseEntity.ok().body(employee1));
    }

    @Test
    public void updateEmployeeTest() {
        ResponseEntity<Employee> returnedEmployee = employeeControllerMock.updateEmployee("4321", employee2Updated);
        assertNotNull(returnedEmployee);
        assertEquals(returnedEmployee, ResponseEntity.ok().body(employee2Updated));
    }

    @Test
    public void deleteEmployeeTest() {
        ResponseEntity<String> returnedGoodResponse = employeeControllerMock.deleteEmployee("6789");
        assertNotNull(returnedGoodResponse);
        assertEquals(returnedGoodResponse, ResponseEntity.ok().body("employee record deleted"));

        ResponseEntity<String> returnedBadResponse = employeeControllerMock.deleteEmployee("8899");
        assertNotNull(returnedBadResponse);
        assertEquals(returnedBadResponse, ResponseEntity.badRequest().body("no employee record found"));
    }

    @Test
    public void searchEmployeeByLastNameTest() {
        List<Employee> returnedGoodList = employeeControllerMock.searchEmployeeByLastName("Smith");
        assertNotNull(returnedGoodList);
        assertEquals(returnedGoodList.size(), 2);
        List<Employee> returnedBadList = employeeControllerMock.searchEmployeeByLastName("Jones");
        assertNotNull(returnedBadList);
        assertEquals(returnedBadList.size(), 0);
    }
}