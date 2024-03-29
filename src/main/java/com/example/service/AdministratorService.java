package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.repository.AdministratorRepository;

@Service
@Transactional
public class AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    /**
     * 管理者登録の処理
     * 
     * @param administrator
     */
    public void insert(Administrator administrator) {

        administratorRepository.insert(administrator);
    }

    public Administrator login(String mailAddress, String password) {

        Administrator administrator = administratorRepository.findByMailAddressAndPassword(mailAddress, password);

        return administrator;

    }

}
