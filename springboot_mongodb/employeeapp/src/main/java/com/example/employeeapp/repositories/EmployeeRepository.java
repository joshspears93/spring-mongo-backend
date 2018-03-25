package com.example.employeeapp.repositories;
import com.example.employeeapp.models.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

    @Query("{ 'lastName' : ?0}")
    List<Employee> findByLastName(String lastName);
}
