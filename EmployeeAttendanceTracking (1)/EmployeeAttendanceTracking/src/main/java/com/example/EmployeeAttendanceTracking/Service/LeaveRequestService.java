package com.example.EmployeeAttendanceTracking.Service;



import com.example.EmployeeAttendanceTracking.Model.EmployeeModel;
import com.example.EmployeeAttendanceTracking.Model.LeaveRequestModel;
import com.example.EmployeeAttendanceTracking.Repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class LeaveRequestService
{
    @Autowired
    LeaveRequestRepository leaveRequestRepository;

    public LeaveRequestModel createLeaveRequest(LeaveRequestModel leaveRequestModel) {
        return leaveRequestRepository.save(leaveRequestModel);
    }

    public List<LeaveRequestModel> createAllLeaveRequest(List<LeaveRequestModel> leaveRequestModel) {
        return leaveRequestRepository.saveAll(leaveRequestModel);
    }

    public List<LeaveRequestModel> readAllLeaveRequests() {
        return leaveRequestRepository.findAll();
    }

    public LeaveRequestModel readLeaveRequestById(Long employeeId) {
        return leaveRequestRepository.findById(employeeId).orElse(null);
    }

    public String updateLeaveRequest(Long employeeId, LocalDate startDate, LocalDate endDate, String status) {
        LeaveRequestModel request = leaveRequestRepository.findById(employeeId).orElse(null);
        if (request != null) {
            request.setStartDate(startDate);
            request.setEndDate(endDate);
            request.setStatus(status);
            leaveRequestRepository.save(request);
            return "Leave Request Details Updated";
        } else {
            return "Leave Request Not Found";
        }
    }

    public String deleteLeaveRequest(Long employeeId) {
        if (leaveRequestRepository.existsById(employeeId)) {
            leaveRequestRepository.deleteById(employeeId);
            return "Leave Request Deleted";
        } else {
            return "Leave Request Not Found";
        }
    }

    public String approveLeaveRequest(long employeeId){
        LeaveRequestModel leaveRequest = leaveRequestRepository.findById(employeeId).orElse(null);
            leaveRequest.setStatus("Approved");
            leaveRequestRepository.delete(leaveRequest);
        return "Leave Request Not found";
    }

    public String declineLeaveRequest(long employeeId){
        LeaveRequestModel leaveRequest = leaveRequestRepository.findById(employeeId).orElse(null);

        if (leaveRequest != null){
            leaveRequest.setStatus("Declined");
            leaveRequestRepository.save(leaveRequest);
        }
        return "Leave Request Declined.";
    }

    public long getLeaveRequestCountByEmployeeId(long employeeId) {
        return leaveRequestRepository.countByEmployeeId(employeeId);
    }
}

