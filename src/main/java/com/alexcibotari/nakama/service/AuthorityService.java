package com.alexcibotari.nakama.service;

import com.alexcibotari.nakama.domain.Authority;

import java.util.List;

public interface AuthorityService {

    public Authority save(Authority a);

    public void delete(Authority a);

    public void delete(Long id);

    public long count();

    public List<Authority> findAll();

    public Authority findOne(Long id);

    public List<Authority> findEntries(int firstResult,
                                       int maxResults);

}