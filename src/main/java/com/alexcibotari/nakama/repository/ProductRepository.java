package com.alexcibotari.nakama.repository;


import com.alexcibotari.nakama.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Optional<Product> findOneById(Long id);

}
