package com.fandik.kino.repository;

import com.fandik.kino.entity.FilmEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends CrudRepository<FilmEntity, Long> {
}
