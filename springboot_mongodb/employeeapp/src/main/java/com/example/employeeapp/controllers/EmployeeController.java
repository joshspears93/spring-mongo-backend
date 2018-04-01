package com.example.employeeapp.controllers;

import com.example.employeeapp.models.Employee;
import com.example.employeeapp.repositories.EmployeeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;


@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
        return employeeRepository.findAll(sortByCreatedAtDesc);
    }

    //Method for adding a new employee to the DB
    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    //Method for getting employee by ID
    @GetMapping(value="/employees/{id}")
    public ResponseEntity<Employee> getEmployeeByID(@PathVariable("id") String id){
        return employeeRepository.findById(id).map(employee -> ResponseEntity.ok().body(employee)).orElse(ResponseEntity.badRequest().build());
    }

    //Method for updating an employee by ID
    @PutMapping(value="/employees/{id}")
    public  ResponseEntity<Employee> updateEmployee(@PathVariable("id") String id, @Valid @RequestBody Employee employee){
        return employeeRepository.findById(id).map(employeeData -> {
            employeeData.setFirstName(employee.getFirstName());
            employeeData.setLastName(employee.getLastName());
            employeeData.setMiddleInitial(employee.getMiddleInitial());
            employeeData.setEmailAddress(employee.getEmailAddress());
            employeeData.setPhoneNumber(employee.getPhoneNumber());
            employeeData.setPositionCategory(employee.getPositionCategory());
            employeeData.setDateHired(employee.getDateHired());
            employeeData.setAddressOne(employee.getAddressOne());
            employeeData.setAddressTwo(employee.getAddressTwo());
            employeeData.setCity(employee.getCity());
            employeeData.setState(employee.getState());
            employeeData.setZipCode(employee.getZipCode());
            employeeData.setActive(employee.getActive());

            Employee updatedEmployee = employeeRepository.save(employeeData);

            return ResponseEntity.ok().body(updatedEmployee);

        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value="/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") String id){
        if(employeeRepository.findById(id).isPresent()){
            employeeRepository.deleteById(id);
            // return ResponseEntity.ok().body("employee record deleted");
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value="/employees/search/{lastName}")
    public List<Employee> searchEmployeeByLastName(@PathVariable("lastName") String lastName){
        List<Employee> matchedEmployees = employeeRepository.findByLastName(lastName);
        return matchedEmployees;
    }

}
