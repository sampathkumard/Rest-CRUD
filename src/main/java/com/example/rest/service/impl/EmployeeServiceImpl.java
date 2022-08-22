package com.example.rest.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.rest.entity.Employee;
import com.example.rest.exception.ResourceNotFoundException;
import com.example.rest.repository.EmployeeRepository;
import com.example.rest.service.EmployeeService;
@Service
public class EmployeeServiceImpl implements EmployeeService{
	private EmployeeRepository employeeRepository; 
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
		// TODO Auto-generated method stub
		return employeeRepository.findById(id).orElseThrow(()-> 
		                  new ResourceNotFoundException("Employee", "Id", id));
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		///checking if the employee exist or not/////
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(()->
		                 new ResourceNotFoundException("Employee", "Id", id));
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.geLastName());
		existingEmployee.setEmail(employee.getEmail());
		employeeRepository.save(existingEmployee);
		return existingEmployee;
		
		
		
	}

	@Override
	public void deleteEmployee(long id) {
		////if the id is not in db
		employeeRepository.findById(id).orElseThrow(()-> 
		new ResourceNotFoundException("Employee", "id", id));
		employeeRepository.deleteById(id);
	}
}
