package com.seanroshan.critter.service;

import com.seanroshan.critter.entity.Customer;
import com.seanroshan.critter.entity.Employee;
import com.seanroshan.critter.entity.Pet;
import com.seanroshan.critter.entity.Schedule;
import com.seanroshan.critter.repository.CustomerRepository;
import com.seanroshan.critter.repository.EmployeeRepository;
import com.seanroshan.critter.repository.pet.PetRepository;
import com.seanroshan.critter.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;
    private final PetRepository petRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, EmployeeRepository employeeRepository, CustomerRepository customerRepository, PetRepository petRepository) {
        this.scheduleRepository = scheduleRepository;
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
    }

    public List<Schedule> getList() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getListByPetId(long petId) {
        Pet pet = petRepository.getOne(petId);
        return scheduleRepository.getAllByPetsContains(pet);
    }

    public List<Schedule> getListByEmployeeId(long employeeId) {
        Employee employee = employeeRepository.getOne(employeeId);
        return scheduleRepository.getAllByEmployeesContains(employee);
    }

    public List<Schedule> getListByCustomerId(long customerId) {
        Customer customer = customerRepository.getOne(customerId);
        return scheduleRepository.getAllByPetsIn(customer.getPets());
    }

    public Schedule save(Schedule schedule, List<Long> employeeIds, List<Long> petIds) {
        List<Employee> employees = employeeRepository.findAllById(employeeIds);
        List<Pet> pets = petRepository.findAllById(petIds);
        schedule.setEmployees(employees);
        schedule.setPets(pets);
        return scheduleRepository.save(schedule);
    }

}
