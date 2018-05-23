package by.bsuir.realEstateAgency.core.service;

import by.bsuir.realEstateAgency.core.model.Application;
import by.bsuir.realEstateAgency.core.model.User;
import by.bsuir.realEstateAgency.core.service.common.DeleteByUserService;
import by.bsuir.realEstateAgency.core.service.common.FindAllBySearchInterface;
import by.bsuir.realEstateAgency.core.service.common.FindAllByUserService;
import by.bsuir.realEstateAgency.core.service.common.FindAllInterface;

import java.util.List;

public interface ApplicationService extends FindAllInterface<Application>, DeleteByUserService, FindAllByUserService<Application>, FindAllBySearchInterface<Application> {
    void save(Application application);

    Application get(Long key);

    void remove(Long key, User user);

    /**
     * @param keys - id list
     */
    void removeList(List<Long> keys, User user);

    Application findLastApplicationByImmobility(Long key);

    Long[] applicationCountByCity(Long id);

    void deleteUser(List<Long> keys);
}
