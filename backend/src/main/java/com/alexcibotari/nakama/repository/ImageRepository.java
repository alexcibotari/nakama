package com.alexcibotari.nakama.repository;


import com.alexcibotari.nakama.domain.Image;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ImageRepository extends CrudRepository<Image, Long> {

    Optional<Image> findOneById(Long id);

}
