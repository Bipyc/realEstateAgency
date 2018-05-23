package by.bsuir.realEstateAgency.core.service;

import by.bsuir.realEstateAgency.core.model.Application;
import by.bsuir.realEstateAgency.core.model.Immobility;
import by.bsuir.realEstateAgency.core.model.Inspection;
import by.bsuir.realEstateAgency.core.model.User;
import by.bsuir.realEstateAgency.core.service.common.DeleteByUserService;
import by.bsuir.realEstateAgency.core.service.common.FindAllByUserService;
import by.bsuir.realEstateAgency.core.service.common.FindAllInterface;

import java.util.List;

public interface InspectionService extends FindAllInterface<Inspection>, DeleteByUserService, FindAllByUserService<Inspection>{
    void save(Inspection inspection, User user);

    Inspection get(Long key);

    void remove(Long key, User user);

    /**
     * @param keys - id list
     */
    void removeList(List<Long> keys, User user);

    List<Inspection> getAllToday(Long userId);
}
