package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.service.AdministratorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/")
public class InsertAdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm insertAdministratorForm) {

        return "/administrator/insert";
    }

    @PostMapping("/insert")
    public String inset(InsertAdministratorForm insertAdministratorForm) {

        Administrator administrator = new Administrator();

        administrator.setId(administrator.getId());
        administrator.setName(insertAdministratorForm.getName());
        administrator.setMailAddress(insertAdministratorForm.getMailAddress());
        administrator.setPassword(insertAdministratorForm.getPassword());

        BeanUtils.copyProperties(insertAdministratorForm, administrator);
        administratorService.insert(administrator);

        return "redirect:/";
    }

}
