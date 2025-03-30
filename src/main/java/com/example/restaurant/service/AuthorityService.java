package com.example.restaurant.service;

import com.example.restaurant.entity.Authority;
import com.example.restaurant.repository.AuthorityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public List<Authority> getAuthoritiesByUserId(Integer userId) {
        return authorityRepository.findByUserId(userId);
    }

    public Authority addAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }

    public void removeAuthority(Integer id) {
        authorityRepository.deleteById(id);
    }
}
