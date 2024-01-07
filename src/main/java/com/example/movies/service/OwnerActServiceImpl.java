package com.example.movies.service;

import com.example.movies.entity.OwnerAct;
import com.example.movies.repository.OwnerActRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;
@Service
public class OwnerActServiceImpl implements OwnerActService {
    private OwnerActRepository ownerActRepository;

    @Autowired
    public OwnerActServiceImpl(OwnerActRepository ownerActRepository) {
        this.ownerActRepository = ownerActRepository;
    }

    @Override
    public void savelog(String status){
        Calendar calendar = new GregorianCalendar();
        OwnerAct ownerAct = new OwnerAct();
        ownerAct.setDate(calendar.getTime().toString());
        ownerAct.setAction(status);
        ownerActRepository.save(ownerAct);
    }
}

