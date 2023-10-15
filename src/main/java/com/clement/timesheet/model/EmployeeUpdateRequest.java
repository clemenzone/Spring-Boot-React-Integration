package com.clement.timesheet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeUpdateRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;
}
