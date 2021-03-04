package com.batch.springboot.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.batch.springboot.common.NotFoundExecption;
import com.batch.springboot.model.Employee;
import com.batch.springboot.service.EmployeeService;

@RestController
@RequestMapping("/em")
public class EpmloyeeController {
	
	@Autowired
	EmployeeService employeeService;
@GetMapping("/employee")	
public ResponseEntity<List<Employee>> getAllEmployees() {
		
	
	List<Employee> allEmployees = employeeService.getAllEmployees();
	
	URI a =ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri();
	
		return  ResponseEntity.created(a).body(allEmployees);
				
		
	}


@RequestMapping(method=RequestMethod.GET,value= "/employee/{id}")
public ResponseEntity<Employee> getByIdEmployee(@PathVariable int id) {
	//int Status=202;
	Employee e=employeeService.getByIdEmployee(id);    
	
	if(e==null) {
		
		
			throw new NotFoundExecption("Employee with id "+id+ "  not found");
		
	}
	
	HttpHeaders headers= new HttpHeaders();
	headers.add("Content-Type", "Application/Json");

	
	return ResponseEntity.status(HttpStatus.ACCEPTED).headers(headers).body(e);
	
}

@PostMapping("/employee")
public Employee addEmployee(@RequestBody Employee e) {
	
	return employeeService.addEmployee(e);
}

@PutMapping("/employee/{id}")
public Employee updateEmployee(@RequestBody Employee e,@PathVariable int id) {
	
	return employeeService.updateEmployee(e,id);
}

@RequestMapping(method=RequestMethod.DELETE, value="/employee/{id}")
public String deleteEmployee(@PathVariable int id) {

	
	return employeeService.deleteEmployee(id);
}
}
