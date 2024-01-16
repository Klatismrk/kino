package com.fandik.kino.repository;

import com.fandik.kino.entity.RezervaceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RezervaceRepository extends CrudRepository<RezervaceEntity, Long> {
    List<RezervaceEntity> findByUzivatelId(Long id);

    Optional<RezervaceEntity> findByUzivatelIdAndId(Long uzivatelId, Long id);
}
