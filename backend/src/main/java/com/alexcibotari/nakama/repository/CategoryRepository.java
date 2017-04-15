package com.alexcibotari.nakama.repository;


import com.alexcibotari.nakama.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findOneById(Long id);

}
