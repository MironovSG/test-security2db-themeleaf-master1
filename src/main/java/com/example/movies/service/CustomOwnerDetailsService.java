package com.example.movies.service;

import com.example.movies.entity.Owner;
import com.example.movies.repository.OwnerRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomOwnerDetailsService implements UserDetailsService {
    private OwnerRepository ownerRepository;

    public CustomOwnerDetailsService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Owner owner = ownerRepository.findByEmail(usernameOrEmail);
        if(owner != null)   {
            return new org.springframework.security.core.userdetails.User(owner.getEmail(),
                    owner.getPassword(), owner.getRoles().stream().map((role) -> new SimpleGrantedAuthority(
                    role.getName())).collect(Collectors.toList()));
        }
        else{
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}

