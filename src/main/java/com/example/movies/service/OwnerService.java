package com.example.movies.service;

import com.example.movies.dto.OwnerDto;
import com.example.movies.entity.Owner;
import java.util.List;
public interface OwnerService {
    void saveOwner(OwnerDto ownerDto);
    Owner findOwnerByEmail(String email);
    List<OwnerDto> findAllOwners();
}

