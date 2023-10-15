package com.clement.timesheet.mapper;

import com.clement.timesheet.entity.EmployeeEntity;
import com.clement.timesheet.model.EmployeeCreateRequest;
import com.clement.timesheet.model.EmployeeResponse;
import com.clement.timesheet.model.EmployeeUpdateRequest;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public EmployeeEntity mapToEmployeeEntity(EmployeeCreateRequest employeeCreateRequest) {
        return EmployeeEntity.builder()
                .firstName(employeeCreateRequest.getFirstName())
                .lastName(employeeCreateRequest.getLastName())
                .phoneNumber(employeeCreateRequest.getPhoneNumber())
                .address(employeeCreateRequest.getAddress())
                .email(employeeCreateRequest.getEmail())
                .build();
    }

    public EmployeeResponse mapToCreateResponse(EmployeeEntity employeeEntity) {
        return EmployeeResponse.builder()
                .id(employeeEntity.getId())
                .firstName(employeeEntity.getFirstName())
                .lastName(employeeEntity.getLastName())
                .address(employeeEntity.getAddress())
                .phoneNumber(employeeEntity.getPhoneNumber())
                .registrationDate(employeeEntity.getRegistrationDate())
                .email(employeeEntity.getEmail())
                .build();
    }

    public EmployeeEntity mapToUpdateEmployee(EmployeeEntity employeeEntity, EmployeeUpdateRequest employeeUpdateRequest) {
        if(employeeUpdateRequest.getEmail() != null){
            employeeEntity.setEmail(employeeUpdateRequest.getEmail());
        }

        if(employeeUpdateRequest.getFirstName() != null){
            employeeEntity.setFirstName(employeeUpdateRequest.getFirstName());
        }

        if(employeeUpdateRequest.getLastName() != null){
            employeeEntity.setLastName(employeeUpdateRequest.getLastName());
        }

        if(employeeUpdateRequest.getAddress() != null){
            employeeEntity.setAddress(employeeUpdateRequest.getAddress());
        }

        if(employeeUpdateRequest.getPhoneNumber() != null){
            employeeEntity.setPhoneNumber(employeeUpdateRequest.getPhoneNumber());
        }
        return employeeEntity;
    }
}
