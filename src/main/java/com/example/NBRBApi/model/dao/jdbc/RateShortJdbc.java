package com.example.NBRBApi.model.dao.jdbc;

import com.example.NBRBApi.model.dao.jdbc.mapper.RateShortMapper;
import com.example.NBRBApi.model.domain.Rate;
import com.example.NBRBApi.model.domain.RateShort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RateShortJdbc {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<RateShort> findByCurId(int curId){
        String sql ="SELECT * FROM rate_date WHERE curID =?";
        return jdbcTemplate.query(sql,new RateShortMapper(),curId);
    }
}
