package dev.mohsenkohan.recipeapp.services;

import dev.mohsenkohan.recipeapp.domain.UnitOfMeasure;
import dev.mohsenkohan.recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public Set<UnitOfMeasure> getAll() {
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
        unitOfMeasureRepository.findAll().forEach(unitOfMeasures::add);
        return unitOfMeasures;
    }
}
