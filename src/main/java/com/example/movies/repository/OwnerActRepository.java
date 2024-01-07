package com.example.movies.repository;

import com.example.movies.entity.OwnerAct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OwnerActRepository extends JpaRepository<OwnerAct, Long> {
}
