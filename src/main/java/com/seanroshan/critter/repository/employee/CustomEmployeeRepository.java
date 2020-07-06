package com.seanroshan.critter.repository.employee;

import com.seanroshan.critter.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomEmployeeRepository {
    @Query(value = "select e.* from employee_days_available eda join employee e on eda.employee_id = e.id where eda.days_available = :dayOfWeek",
            nativeQuery = true)
    List<Employee> findEmployeesForService(int dayOfWeek);
}
