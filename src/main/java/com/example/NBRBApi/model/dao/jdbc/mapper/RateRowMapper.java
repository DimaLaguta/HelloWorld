package com.example.NBRBApi.model.dao.jdbc.mapper;

import com.example.NBRBApi.model.domain.Rate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RateRowMapper implements RowMapper<Rate> {
    @Override
    public Rate mapRow(ResultSet resultSet, int i) throws SQLException {
        Rate rate = new Rate();
        rate.setCurID(resultSet.getInt("curid"));
        rate.setCurAbbreviation(resultSet.getString("cur_abbreviation"));
        rate.setCurName(resultSet.getString("cur_name"));
        rate.setCurOfficialRate(resultSet.getDouble("cur_official_rate"));
        rate.setCurScale(resultSet.getInt("cur_scale"));
        rate.setDate(resultSet.getDate("date").toLocalDate());

        return rate;
    }
}
