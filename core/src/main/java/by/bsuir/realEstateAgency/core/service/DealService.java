package by.bsuir.realEstateAgency.core.service;

import by.bsuir.realEstateAgency.core.model.Deal;
import by.bsuir.realEstateAgency.core.service.common.FindAllInterface;

import java.util.List;

public interface DealService extends FindAllInterface<Deal> {
    void save(Deal deal);

    Deal get(Long key);

    void remove(Long key);

    void removeList(List<Long> keys);
}
