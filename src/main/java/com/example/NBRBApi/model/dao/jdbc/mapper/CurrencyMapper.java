package com.example.NBRBApi.model.dao.jdbc.mapper;

import com.example.NBRBApi.model.domain.Currency;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrencyMapper implements RowMapper<Currency> {
      @Override
    public Currency mapRow(ResultSet resultSet, int i) throws SQLException {
        Currency currency = new Currency();

        currency.setCurID(resultSet.getInt("curid"));
        currency.setCurAbbreviation(resultSet.getString("cur_abbreviation"));
        currency.setCurName(resultSet.getString("cur_name"));
        currency.setCurNameEng(resultSet.getString("cur_name_eng"));

        return currency;
    }
}
