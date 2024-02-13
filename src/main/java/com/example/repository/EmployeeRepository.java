package com.example.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import com.example.domain.Employee;

@Repository
public class EmployeeRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Employee> EMP_ROW_MAPPER = (rs, rowNum) -> {

        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setImage(rs.getString("image"));
        employee.setGender(rs.getString("gender"));
        employee.setHireDate(rs.getDate("hire_date"));
        employee.setMailAddress(rs.getString("mail_address"));
        employee.setZipCode(rs.getString("zip_code"));
        employee.setAddress(rs.getString("address"));
        employee.setTelephone(rs.getString("telephone"));
        employee.setSalary(rs.getInt("salary"));
        employee.setCharacteristics(rs.getString("characteristics"));
        employee.setDependentsCount(rs.getInt("dependents_count"));
        return employee;
    };

    /**
     * 従業員一覧を表示する処理.
     * 
     * @return
     */
    public List<Employee> findAll() {

        String FIND_ALL_QUERY = """

                SELECT
                    id
                    ,name
                    ,image
                    ,gender
                    ,hire_date
                    ,mail_address
                    ,zip_code
                    ,address
                    ,telephone
                    ,salary
                    ,characteristics
                    ,dependents_count
                FROM
                    employees
                ORDER BY
                    hire_date;

                        """;

        List<Employee> employeeList = new ArrayList<>();

        employeeList = template.query(FIND_ALL_QUERY, EMP_ROW_MAPPER);

        if (employeeList.size() == 0) {

            return null;

        } else {

            return employeeList;
        }
    }

    /**
     * 従業員詳細情報の取得処理.
     * 
     * @param id
     * @return
     */
    public Employee load(Integer id) {

        String LOAD_ID_QUERY = """

                      SELECT
                          id
                          ,name
                          ,image
                          ,gender
                          ,hire_date
                          ,mail_address
                	,zip_code
                          ,address
                          ,telephone,salary
                          ,characteristics
                          ,dependents_count
                      FROM
                          employees
                WHERE
                	id=:id
                              """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);

        Employee employee = template.queryForObject(LOAD_ID_QUERY, sqlParameterSource, EMP_ROW_MAPPER);

        return employee;
    }

    public void update(Employee employee) {

        String UPDATE_QUERY = """

                UPDATE
                    employees
                SET

                    name=:name
                    ,image=:image
                    ,gender=:gender
                    ,hire_date=:hireDate
                    ,mail_address=:mailAddress
                    ,zip_code=:zipCode
                    ,address=:address
                    ,telephone=:telephone
                    ,salary=:salary
                    ,characteristics=:characteristics
                    ,dependents_count=:dependentsCount
                WHERE
                    id=:id;

                    """;

        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(employee);

        template.update(UPDATE_QUERY, sqlParameterSource);

    }
}
