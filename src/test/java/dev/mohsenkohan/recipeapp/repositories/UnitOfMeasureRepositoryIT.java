package dev.mohsenkohan.recipeapp.repositories;

import dev.mohsenkohan.recipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Test
    void findByDescription() {
        Optional<UnitOfMeasure> unit = unitOfMeasureRepository.findByDescription("Teaspoon");
        assertEquals("Teaspoon", unit.get().getDescription());
    }
}