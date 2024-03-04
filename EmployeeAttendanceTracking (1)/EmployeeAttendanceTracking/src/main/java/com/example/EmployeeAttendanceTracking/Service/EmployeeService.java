package com.example.EmployeeAttendanceTracking.Service;



import com.example.EmployeeAttendanceTracking.Model.AttendanceRecordModel;
import com.example.EmployeeAttendanceTracking.Model.EmployeeModel;
import com.example.EmployeeAttendanceTracking.Repository.AttendanceRecordRepository;
import com.example.EmployeeAttendanceTracking.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AttendanceRecordRepository attendanceRecordRepository;

    public EmployeeModel createEmployee(EmployeeModel employeeModel) {
        return employeeRepository.save(employeeModel);
    }

    public List<EmployeeModel> addAllEmployee(List<EmployeeModel> employees) {
        return employeeRepository.saveAll(employees);
    }

    public List<EmployeeModel> readAllEmployees() {
        return employeeRepository.findAll();
    }

    public EmployeeModel readEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    public String updateEmployee(Long employeeId, String name, String department, String position, String contact) {
        EmployeeModel employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee != null) {
            employee.setName(name);
            employee.setDepartment(department);
            employee.setPosition(position);
            employee.setContact(contact);
            employeeRepository.save(employee);
            return "Employee Details Updated";
        } else {
            return "Employee not found";
        }
    }

    public String deleteEmployee(Long employeeId) {
        if (employeeRepository.existsById(employeeId)) {
            employeeRepository.deleteById(employeeId);
            return "Employee Deleted";
        } else {
            return "Employee not found";
        }
    }

    public List<EmployeeModel> getEmployeesByDepartmentId(Long departmentId) {
        return employeeRepository.findByDepartment_Id(departmentId);
    }
    public String calPayRoll(long employeeId){
        EmployeeModel employeeModel=employeeRepository.getById(employeeId);
        double basePay=employeeModel.getBasePay();
        double hikeRate=employeeModel.getHikeRate();
        double salary=basePay*hikeRate;
        return "Salary for "+employeeModel.getName()+" is "+salary;
    }

}

