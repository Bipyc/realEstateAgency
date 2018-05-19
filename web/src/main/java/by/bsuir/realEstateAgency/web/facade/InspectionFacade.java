package by.bsuir.realEstateAgency.web.facade;

import by.bsuir.realEstateAgency.core.model.User;
import by.bsuir.realEstateAgency.web.bean.InspectionDto;
import org.springframework.validation.BindingResult;

public interface InspectionFacade {
    InspectionDto getInspection(Long key);

    boolean saveOrUpdate(InspectionDto inspectionDto, User user, BindingResult bindingResult);
}
