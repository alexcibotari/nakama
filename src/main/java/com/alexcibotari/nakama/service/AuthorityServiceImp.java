package com.alexcibotari.nakama.service;

import com.alexcibotari.nakama.domain.Authority;
import com.alexcibotari.nakama.repository.AuthorityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Service
public class AuthorityServiceImp implements AuthorityService {

    final Logger logger = LoggerFactory.getLogger(AuthorityServiceImp.class);

    @Autowired
    private AuthorityRepository authorityRepository;

    @PersistenceContext
    transient EntityManager entityManager;

    public Authority save(Authority a) {
        return authorityRepository.save(a);
    }

    public void delete(Authority a) {
        authorityRepository.delete(a);
    }

    public void delete(Long id) {
        authorityRepository.delete(id);
    }

    public long count() {
        return authorityRepository.count();
    }

    public List<Authority> findAll() {
        return (List<Authority>) authorityRepository.findAll();
    }

    public Authority findOne(Long id) {
        return authorityRepository.findOne(id);
    }

    public List<Authority> findEntries(int firstResult, int maxResults) {
        return entityManager.createQuery("SELECT o FROM Authority o", Authority.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
}
