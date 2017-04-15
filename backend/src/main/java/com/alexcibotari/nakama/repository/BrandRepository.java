package com.alexcibotari.nakama.repository;


import com.alexcibotari.nakama.domain.Brand;
import com.alexcibotari.nakama.domain.Image;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BrandRepository extends CrudRepository<Brand, Long> {

    Optional<Brand> findOneById(Long id);

    Optional<Brand> findOneByName(String name);

}
