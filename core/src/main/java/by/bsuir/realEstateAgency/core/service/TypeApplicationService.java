package by.bsuir.realEstateAgency.core.service;

import by.bsuir.realEstateAgency.core.model.TypeApplication;
import by.bsuir.realEstateAgency.core.service.common.FindAllInterface;

import java.util.List;

public interface TypeApplicationService extends FindAllInterface<TypeApplication> {
    void save(TypeApplication typeApplication);

    TypeApplication get(Long key);

    void remove(Long key);

    List<TypeApplication> getAll();
}
