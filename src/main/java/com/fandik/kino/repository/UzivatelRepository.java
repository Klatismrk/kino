package com.fandik.kino.repository;

import com.fandik.kino.entity.UzivatelEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UzivatelRepository extends CrudRepository<UzivatelEntity, Long> {
}
