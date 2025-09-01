package com.hr.employee.service;

import com.hr.employee.client.Department;
import com.hr.employee.client.DepartmentWebClient;
import com.hr.employee.dto.EmployeeRequestDto;
import com.hr.employee.entity.Employee;
import com.hr.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentWebClient departmentWebClient;

    public Optional<Employee> getEmployeeById(Long employeeId) {
        log.info("getting employee by id: {}", employeeId);
        return employeeRepository.findById(employeeId);
    }

    public List<Employee> getAllEmployees() {
        log.info("getting all employees");
        return employeeRepository.findAll();
    }

    public Employee saveEmployee(EmployeeRequestDto employeeRequest) {
        log.info("saving employee: {}", employeeRequest);
        Department departmentByName = departmentWebClient.getDepartmentByName(employeeRequest.getDepartmentName());
        Employee employee = new Employee();
        employee.setEmail(employeeRequest.getEmail());
        employee.setDepartmentId(departmentByName.getId());
        employee.setLastName(employeeRequest.getLastName());
        employee.setFirstName(employeeRequest.getFirstName());
        return employeeRepository.save(employee);
    }

}
