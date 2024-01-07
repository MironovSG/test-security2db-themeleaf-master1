package com.example.movies.service;

import com.example.movies.dto.OwnerDto;
import com.example.movies.entity.Owner;
import com.example.movies.entity.Role;
import com.example.movies.repository.OwnerRepository;
import com.example.movies.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerRepository,
                                RoleRepository roleRepository,
                                PasswordEncoder passwordEncoder){
        this.ownerRepository = ownerRepository;
        this.roleRepository =roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveOwner(OwnerDto ownerDto){
        Owner owner = new Owner();
        owner.setUsername(ownerDto.getUsername());
        owner.setEmail(ownerDto.getEmail());
        owner.setPassword(passwordEncoder.encode(ownerDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        owner.setRoles(Arrays.asList(role));
        ownerRepository.save(owner);
    }

    @Override
    public Owner findOwnerByEmail(String email) {
        return ownerRepository.findByEmail(email);
    }

    @Override
    public List<OwnerDto> findAllOwners(){
        List<Owner> owners = ownerRepository.findAll();
        return owners.stream().map((owner)-> mapToOwnerDto(owner)).collect(Collectors.toList());
    }

    private OwnerDto mapToOwnerDto(Owner owner){
        OwnerDto ownerDto = new OwnerDto();
        String[] str = owner.getUsername().split("");
        ownerDto.setUsername(str[0]);
        ownerDto.setEmail(owner.getEmail());
        return ownerDto;
    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}

