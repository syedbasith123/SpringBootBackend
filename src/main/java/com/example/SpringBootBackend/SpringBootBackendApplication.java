package com.example.SpringBootBackend;

import com.example.SpringBootBackend.model.Employee;
import com.example.SpringBootBackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBackendApplication.class, args);
	}

	@Autowired
	EmployeeRepository employeeRepository;
	@Override
	public void run(String... args) throws Exception {
		Employee employee=new Employee();
		employee.setEmpId(1114);
		employee.setFirstName("Virat");
		employee.setLastName("virat");
		employee.setCity("abc");
		employeeRepository.save(employee);
	}
}
