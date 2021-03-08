package com.ironhack.finalproject.amazaingmanagement.controller.impl;

import com.ironhack.finalproject.amazaingmanagement.controller.dto.EmployeeDTO;
import com.ironhack.finalproject.amazaingmanagement.controller.interfaces.IEmployeeController;
import com.ironhack.finalproject.amazaingmanagement.model.Employee;
import com.ironhack.finalproject.amazaingmanagement.service.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController implements IEmployeeController {

	@Autowired
	private IEmployeeService employeeService;

	@GetMapping("/employees")
	@ResponseStatus(HttpStatus.OK)
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@GetMapping("/employee/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Employee getEmployeeById(@PathVariable Long id) {
		return employeeService.getEmployeeById(id);
	}

	@PostMapping("/employee")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee storeEmployee(@RequestBody EmployeeDTO employeeDTO) {
		return employeeService.storeEmployee(employeeDTO);
	}

	@PutMapping("/employee/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
		employeeService.updateEmployee(id, employeeDTO);
	}

	@DeleteMapping("/employee/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
	}
}
