package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.entities.CharacteristicValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacteristicValueRepository extends JpaRepository<CharacteristicValue, Integer> {
    // Unique constraint business rule için lazım olacak:
    // boolean existsByCharacteristicIdAndValue(String characteristicId, String value);
}
