package com.fandik.kino.repository;

import com.fandik.kino.entity.RezervaceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RezervaceRepository extends CrudRepository<RezervaceEntity, Long> {
}
