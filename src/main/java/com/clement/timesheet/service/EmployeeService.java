package com.clement.timesheet.service;

import com.clement.timesheet.entity.EmployeeEntity;
import com.clement.timesheet.mapper.EmployeeMapper;
import com.clement.timesheet.model.EmployeeCreateRequest;
import com.clement.timesheet.model.EmployeeResponse;
import com.clement.timesheet.model.EmployeeUpdateRequest;
import com.clement.timesheet.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    public EmployeeResponse createEmployee(EmployeeCreateRequest employeeCreateRequest) {
        EmployeeEntity employeeEntity = employeeMapper.mapToEmployeeEntity(employeeCreateRequest);
        employeeEntity.setRegistrationDate(LocalDate.now());
        return employeeMapper.mapToCreateResponse(employeeRepository.save(employeeEntity));
    }

    public EmployeeResponse getEmployee(String id) {
        return employeeRepository.findById(id)
                .map(employeeEntity -> employeeMapper.mapToCreateResponse(employeeEntity))
                .orElseThrow( () -> new ResourceNotFoundException("Customer with id " + id + " cannot be found"));
    }

    public EmployeeResponse updateEmployee(String id, EmployeeUpdateRequest employeeUpdateRequest) {
        return employeeRepository.findById(id)
                .map(employeeEntity -> employeeMapper.mapToUpdateEmployee(employeeEntity, employeeUpdateRequest))
                .map(employeeEntity -> employeeRepository.save(employeeEntity))
                .map(employeeEntity -> employeeMapper.mapToCreateResponse(employeeEntity))
                .orElseThrow( () -> new ResourceNotFoundException("Customer with id " + id + " cannot be found"));
    }

    public void deleteById(String id) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
        if(employeeEntity.isEmpty()){
            throw new ResourceNotFoundException("Customer with id " +  id + "not found" );
        }
        employeeRepository.deleteById(id);
    }

    public List<EmployeeResponse> getEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeEntity -> employeeMapper.mapToCreateResponse(employeeEntity))
                .collect(Collectors.toList());
    }
}
