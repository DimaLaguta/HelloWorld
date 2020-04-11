package com.example.NBRBApi.model.dao.DataJpa;

import com.example.NBRBApi.model.domain.RateShort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateShortRepo extends CrudRepository<RateShort, Integer> {
    List<RateShort> findByCurID(int curID);
}
