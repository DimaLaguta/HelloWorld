package com.example.NBRBApi.model.dao.jdbc;

import com.example.NBRBApi.model.dao.jdbc.mapper.CurrencyMapper;
import com.example.NBRBApi.model.domain.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CurrencyJdbc {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Currency> findByCurAbbreviation (String curAbbreviation){
        String sql = "SELECT * FROM currency WHERE cur_abbreviation = ?";
        return jdbcTemplate.query(sql,new CurrencyMapper(),curAbbreviation);
    }
}
