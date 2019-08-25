package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService theEmployeeService) {
		
		this.employeeService = theEmployeeService;
	
	}
	// load employee data
	//private List<Employee> theEmployees;
	
//	@PostConstruct
//	private void loadData() {
//		// create employees
//		Employee emp1 = new Employee(1,"Leslie","Andrews","leslie@luv2code.com");
//		Employee emp2 = new Employee(2,"Emma","Baumgarten","emma@luv2code.com");
//		Employee emp3 = new Employee(3,"Avani","Gupta","avani@luv2code.com");
//		
//		// create the list
//		theEmployees = new ArrayList<Employee>();
//		
//		// add to the list
//		theEmployees.add(emp1);
//		theEmployees.add(emp2);
//		theEmployees.add(emp3);
//		
//		
//	}
	
	// add mapping for "/list"
	@GetMapping("/list")
	public String listEmployee(Model theModel) {
		
		// get employees from db
		List<Employee> theEmployees = employeeService.findAll();
		
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);
		
		return "employees/list-employees";
	}
	
	@GetMapping("/showFormForAdd")
	public String addEmployee(Model theModel) {
		
		// create model attribute to bind form data
		Employee theEmployee = new Employee();
		
		theModel.addAttribute("employee", theEmployee);
		
		return "employees/employee-form";
	}
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		employeeService.save(theEmployee);
		return "redirect:/employees/list";
	}
	
}
