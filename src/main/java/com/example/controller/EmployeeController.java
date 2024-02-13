package com.example.controller;

import java.net.Inet4Address;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/employee")

public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 従業員一覧のメソッド.
     * 
     * @param model
     * @return
     */
    @GetMapping("/showList")
    public String showList(Model model) {

        List<Employee> employeeList = employeeService.showList();
        model.addAttribute("employeeList", employeeList);

        return "/employee/list";
    }

    @GetMapping("/showDetail")
    public String showDetail(String id, Model model, UpdateEmployeeForm updateEmployeeForm) {
        
        // Employee employee = employeeService.showDetail(updateEmployeeForm.getIntId()); 
        Employee employee = employeeService.showDetail(updateEmployeeForm.getIntId()); 
        // Employee employee = employeeService.showDetail(updateEmployeeForm.getId()); 
        model.addAttribute("employee", employee);

        return "/employee/detail";
    }

}
