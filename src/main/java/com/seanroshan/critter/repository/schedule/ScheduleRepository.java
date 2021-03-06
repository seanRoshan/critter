package com.seanroshan.critter.repository.schedule;

import com.seanroshan.critter.entity.Employee;
import com.seanroshan.critter.entity.Pet;
import com.seanroshan.critter.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long>, CustomScheduleRepository {

}
