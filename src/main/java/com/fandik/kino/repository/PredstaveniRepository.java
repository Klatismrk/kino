package com.fandik.kino.repository;

import com.fandik.kino.entity.PredstaveniEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredstaveniRepository extends CrudRepository<PredstaveniEntity, Long> {
}
