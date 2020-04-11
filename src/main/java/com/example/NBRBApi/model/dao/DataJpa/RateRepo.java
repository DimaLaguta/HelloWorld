package com.example.NBRBApi.model.dao.DataJpa;

import com.example.NBRBApi.model.domain.Rate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepo extends CrudRepository<Rate,Integer> {
    Rate findByCurAbbreviation(String curAbbreviation);
}
