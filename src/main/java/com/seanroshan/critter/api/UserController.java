package com.seanroshan.critter.api;

import com.seanroshan.critter.dto.CustomerDTO;
import com.seanroshan.critter.dto.EmployeeDTO;
import com.seanroshan.critter.dto.EmployeeRequestDTO;
import com.seanroshan.critter.entity.Customer;
import com.seanroshan.critter.entity.Employee;
import com.seanroshan.critter.service.CustomerService;
import com.seanroshan.critter.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 * <p>
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final CustomerService customerService;
    private final EmployeeService employeeService;

    public UserController(CustomerService customerService, EmployeeService employeeService) {
        this.customerService = customerService;
        this.employeeService = employeeService;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return CustomerDTO.getInstance(customerService
                .save(Customer.getInstance(customerDTO),
                        customerDTO.getPetIds()));
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerService.getList();
        return customers.stream().map(CustomerDTO::getInstance).collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        return CustomerDTO.getInstance(customerService.getByPetId(petId));
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return EmployeeDTO.getInstance(employeeService.save(Employee.getInstance(employeeDTO)));
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return EmployeeDTO.getInstance(employeeService.getById(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.saveEmployeeAvailability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
        List<Employee> employees = employeeService.getEmployeesBySuitability(employeeRequestDTO.getDate(), employeeRequestDTO.getSkills());
        return employees.stream().map(EmployeeDTO::getInstance).collect(Collectors.toList());
    }

}
