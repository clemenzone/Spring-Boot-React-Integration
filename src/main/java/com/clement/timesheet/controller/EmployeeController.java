package com.clement.timesheet.controller;

import com.clement.timesheet.model.EmployeeCreateRequest;
import com.clement.timesheet.model.EmployeeResponse;
import com.clement.timesheet.model.EmployeeUpdateRequest;
import com.clement.timesheet.response.ApiResponse;
import com.clement.timesheet.response.ResponseUtil;
import com.clement.timesheet.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeResponse>> employeeCreateResponse(@RequestBody EmployeeCreateRequest employeeCreateRequest, UriComponentsBuilder builder){
        EmployeeResponse response = employeeService.createEmployee(employeeCreateRequest);
        UriComponents uriComponents = builder.path("api/employees/{id}").buildAndExpand(response.getId());
        return ResponseUtil.created(response, uriComponents.toUri());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponse>>  getEmployee(@PathVariable("id") String id) {
        EmployeeResponse response = employeeService.getEmployee(id);
        return ResponseUtil.success(response);
    }

    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable String id, @RequestBody EmployeeUpdateRequest employeeUpdateRequest) {
        EmployeeResponse response = employeeService.updateEmployee(id, employeeUpdateRequest);
        ResponseUtil.success(response);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable String id) {
       employeeService.deleteById(id);
        ResponseEntity.ok();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EmployeeResponse>>> getClients() {
        return ResponseUtil.success(employeeService.getEmployees());
    }
}
