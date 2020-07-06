package com.seanroshan.critter.api;

import com.seanroshan.critter.dto.ScheduleDTO;
import com.seanroshan.critter.entity.Schedule;
import com.seanroshan.critter.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return ScheduleDTO.getInstance(scheduleService
                .save(Schedule.getInstance(scheduleDTO),
                        scheduleDTO.getEmployeeIds(),
                        scheduleDTO.getPetIds()));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getList();
        return schedules
                .stream()
                .map(ScheduleDTO::getInstance)
                .collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedules = scheduleService.getListByPetId(petId);
        return schedules
                .stream()
                .map(ScheduleDTO::getInstance)
                .collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = scheduleService.getListByEmployeeId(employeeId);
        return schedules
                .stream()
                .map(ScheduleDTO::getInstance)
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules = scheduleService.getListByCustomerId(customerId);
        return schedules
                .stream()
                .map(ScheduleDTO::getInstance)
                .collect(Collectors.toList());
    }
}
