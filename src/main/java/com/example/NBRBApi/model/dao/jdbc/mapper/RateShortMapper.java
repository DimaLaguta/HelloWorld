package com.example.NBRBApi.model.dao.jdbc.mapper;

import com.example.NBRBApi.model.domain.RateShort;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RateShortMapper implements RowMapper<RateShort> {
    @Override
    public RateShort mapRow(ResultSet resultSet, int i) throws SQLException {
        RateShort rateShort = new RateShort();
        rateShort.setCurID(resultSet.getInt("curid"));
        rateShort.setCurOfficialRate(resultSet.getDouble("cur_official_rate"));
        rateShort.setDate(resultSet.getDate("date").toLocalDate());
        rateShort.setId(resultSet.getInt("id"));
        return rateShort;
    }
}
