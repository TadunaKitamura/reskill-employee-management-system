package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Administrator;

@Repository
public class AdministratorRepository {
    

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Administrator> ADMIN_ROW_MAPPER = (rs,rowNum)->{

        Administrator administrator = new Administrator();
        administrator.setId(rs.getInt("id"));
        administrator.setName(rs.getString("name"));
        administrator.setMailAddress(rs.getString("mail_address"));
        administrator.setPassword(rs.getString("password"));
        return administrator;
    };


    /**
     * 管理者情報の挿入.
     * @param administrator
     */
    public  void insert(Administrator administrator){


        String insertSql = "INSERT INTO administrators (name, mail_address, password) VALUES(:name, :mailAddress, :password);";

        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(administrator);

        template.update(insertSql, sqlParameterSource);

    }

    /**
     * メールアドレスとパスワードから管理者情報を取得する.
     * @param mailAddress
     * @param password
     * @return　指定した管理者の情報
     */
    public Administrator findByMailAddressAndPassword (String mailAddress, String password){

        String FIND_MAIL_ADDRESS_QUERY = "SELECT name,mail_address,password FROM administrators WHERE mail_address=:mailAddress AND password=:password; ";

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
        .addValue("mailAddress", mailAddress)
        .addValue("password", password);
    
        
        List<Administrator> administratorList = template.query(FIND_MAIL_ADDRESS_QUERY,sqlParameterSource, ADMIN_ROW_MAPPER);
        if (administratorList.size() == 0) {
            
            return null;
        }else{

            return administratorList.get(0);
        }
    
    }
}
