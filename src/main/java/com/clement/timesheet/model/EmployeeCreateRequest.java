package com.clement.timesheet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeCreateRequest {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;
}
