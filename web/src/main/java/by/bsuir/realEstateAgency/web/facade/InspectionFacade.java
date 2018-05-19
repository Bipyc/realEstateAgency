package by.bsuir.realEstateAgency.web.facade;

import by.bsuir.realEstateAgency.core.model.User;
import by.bsuir.realEstateAgency.web.bean.InspectionDto;
import by.bsuir.realEstateAgency.web.bean.immobility.ImmobilityDto;
import org.springframework.validation.BindingResult;

public interface InspectionFacade {
    InspectionDto getInspection(Long key);

    boolean saveOrUpdate(InspectionDto inspectionDto, User user, BindingResult bindingResult);

    boolean addByUser(InspectionDto inspectionDto, User user, BindingResult bindingResult);
}
