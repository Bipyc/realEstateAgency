package by.bsuir.realEstateAgency.web.facade;

import by.bsuir.realEstateAgency.web.bean.application.TypeApplicationDto;

public interface TypeApplicationFacade {
    TypeApplicationDto getTypeApplication(Long key);

    void saveOrUpdate(TypeApplicationDto typeApplicationDto);
}
