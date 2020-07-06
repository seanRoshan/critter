package com.seanroshan.critter.service;

import com.seanroshan.critter.constants.EmployeeSkill;
import com.seanroshan.critter.entity.Employee;
import com.seanroshan.critter.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getById(long employeeId) {
        return employeeRepository.getOne(employeeId);
    }

    public List<Employee> getList() {
        return employeeRepository.findAll();
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployeesBySuitability(LocalDate date, Set<EmployeeSkill> skills) {
        return employeeRepository
                .getAllByDaysAvailableContains(date.getDayOfWeek()).stream()
                .filter(employee -> employee.getSkills().containsAll(skills))
                .collect(Collectors.toList());
    }

    public void saveEmployeeAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = employeeRepository.getOne(employeeId);
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }

}
