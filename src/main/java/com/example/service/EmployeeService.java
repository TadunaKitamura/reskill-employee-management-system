package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * 従業員一覧の処理.
     * 
     * @return
     */
    public List<Employee> showList() {

        List<Employee> employeeList = employeeRepository.findAll();

        return employeeList;
    }

    /**
     * 従業員詳細の処理.
     * 
     * @param id
     * @return
     */
    public Employee showDetail(Integer id) {

        Employee employee = employeeRepository.load(id);

        return employee;

    }

    /**
     * 従業員の更新処理.
     * @param employee
     */
    public void update(Employee employee) {

        employeeRepository.update(employee);

    }
}
