package com.example.NBRBApi.model.dao.DataJpa;

import com.example.NBRBApi.model.domain.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepo extends CrudRepository<Currency, Integer> {
    List<Currency> findByCurAbbreviation(String curAbbreviation);
}
