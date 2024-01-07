package com.example.movies.repository;

import com.example.movies.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner findByEmail(String email);
}
