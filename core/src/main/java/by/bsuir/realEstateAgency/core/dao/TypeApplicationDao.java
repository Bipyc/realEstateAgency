package by.bsuir.realEstateAgency.core.dao;

import by.bsuir.realEstateAgency.core.exception.ValueNotUniqueException;
import by.bsuir.realEstateAgency.core.model.TypeApplication;

import java.util.List;

public interface TypeApplicationDao {
    void save(TypeApplication typeApplication) throws ValueNotUniqueException;

    TypeApplication get(Long key);

    List<TypeApplication> findAll(int offset, int limit);

    long count();

    void remove(Long key);
}