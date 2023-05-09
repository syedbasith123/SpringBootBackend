package com.example.SpringBootBackend.controller;

import com.example.SpringBootBackend.model.Employee;
import com.example.SpringBootBackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "employee")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;
    @GetMapping("getEmployee")
    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

   @PostMapping("/createEmployee")
    public Employee createEmployee(@RequestBody  Employee employee){
        return employeeRepository.save(employee);
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeByID(@PathVariable long id) throws Exception {
        Employee employee= employeeRepository.findById(id)
                .orElseThrow(()->new Exception("Employee Does not exist..."));

        return  ResponseEntity.ok(employee);
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id ,@RequestBody  Employee employeeDetails) throws Exception {
       Employee employeeToBEUpdated= employeeRepository.findById(id)
               .orElseThrow(()->new Exception("Employee Does not exist..."));

       employeeToBEUpdated.setEmpId(employeeDetails.getEmpId());
       employeeToBEUpdated.setFirstName(employeeDetails.getFirstName());
       employeeToBEUpdated.setLastName(employeeDetails.getLastName());
       employeeToBEUpdated.setCity(employeeDetails.getCity());

       employeeRepository.save(employeeToBEUpdated);

        return  ResponseEntity.ok(employeeToBEUpdated);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id) throws Exception {

        Employee employeeToBeDeleted = employeeRepository.findById(id)
                .orElseThrow(()-> new Exception("Employee doedn't exist"));

        employeeRepository.delete(employeeToBeDeleted);

        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
