package com.example.NBRBApi.model.dao.jdbc;

import com.example.NBRBApi.model.dao.jdbc.mapper.RateRowMapper;
import com.example.NBRBApi.model.domain.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class RateJdbc {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Rate> getAllRates(){
        String sql = "SELECT * FROM rate ";
        return jdbcTemplate.query(sql,new RateRowMapper());
    }

    public Rate findByCurAbbreviation(String curAbbreviation){
        String sql = "SELECT * FROM rate WHERE cur_abbreviation = ?";
        return jdbcTemplate.queryForObject(sql,new RateRowMapper(),curAbbreviation);
    }

    public Rate getRateByCurAbbreviationAndDate(String curAbbreviation, LocalDate date){
        String sql = "SELECT rate.id, rate.cur_abbreviation, rate.cur_name, rate.cur_scale, " +
                "rate_date.cur_official_rate, rate_date.date FROM rate " +
                "INNER JOIN rate_date ON rate.id = rate_date.curid WHERE cur_abbreviation = ? AND rate_date.date = ? GROUP BY rate.id";
        return jdbcTemplate.queryForObject(sql,new RateRowMapper(),curAbbreviation,date);
    }
}
