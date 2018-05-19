package by.bsuir.realEstateAgency.web.facade.impl;

import by.bsuir.realEstateAgency.core.model.*;
import by.bsuir.realEstateAgency.core.service.ImmobilityService;
import by.bsuir.realEstateAgency.core.service.InspectionService;
import by.bsuir.realEstateAgency.core.service.UserService;
import by.bsuir.realEstateAgency.web.bean.InspectionDto;
import by.bsuir.realEstateAgency.web.facade.InspectionFacade;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.annotation.Resource;

@Service
public class InspectionFacadeImpl implements InspectionFacade {

    static Logger log = Logger.getLogger(DealFacadeImpl.class.getName());

    @Resource
    private InspectionService inspectionService;

    @Resource
    private UserService userService;

    @Resource
    private ImmobilityService immobilityService;

    @Override
    public InspectionDto getInspection(Long key) {
        Inspection inspection = inspectionService.get(key);
        if (inspection == null) {
            return null;
        }
        InspectionDto inspectionDto = new InspectionDto();
        inspectionDto.setId(inspection.getId());
        inspectionDto.setComment(inspection.getComment());
        inspectionDto.setDate(inspection.getDate());
        inspectionDto.setTime(inspectionDto.getTime());
        inspectionDto.setImmobilityId(inspection.getImmobility().getId());
        inspectionDto.setClientName(inspection.getClient().getLogin());
        inspectionDto.setRealtorName(inspection.getRealtor().getLogin());
        return inspectionDto;
    }

    @Override
    public boolean saveOrUpdate(InspectionDto inspectionDto, User user, BindingResult bindingResult) {
        Inspection inspection = null;
        if (inspectionDto.getId() != null) {
            inspection = inspectionService.get(inspectionDto.getId());
            if (inspection == null) {
                RuntimeException e = new IllegalStateException();
                log.error("Trying update a nonexistent object", e);
                throw e;
            }
        } else {
            inspection = new Inspection();
        }

        inspection.setId(inspectionDto.getId());
        inspection.setComment(inspectionDto.getComment());
        inspection.setDate(inspectionDto.getDate());
        inspection.setTime(inspectionDto.getTime());

        Immobility immobility = immobilityService.get(inspectionDto.getImmobilityId());
        if (immobility == null) {
            bindingResult.addError(new FieldError("inspectionDto", "immobilityId", inspectionDto.getImmobilityId(), false,
                    new String[]{"NotFound.inspectionDto.immobilityId"}, null, "immobility not found"));

        } else {
            inspection.setImmobility(immobility);
        }

        User client = userService.getByLoginOrEmail(inspectionDto.getClientName());
        if (client == null || !(client instanceof Client)) {
            bindingResult.addError(new FieldError("inspectionDto", "clientName", inspectionDto.getClientName(), false,
                    new String[]{"NotFound.inspectionDto.clientName"}, null, "client not found"));

        } else {
            inspection.setClient((Client) client);
        }

        User realtor = userService.getByLoginOrEmail(inspectionDto.getRealtorName());
        if (realtor == null || !(realtor instanceof Realtor)) {
            bindingResult.addError(new FieldError("inspectionDto", "realtorName", inspectionDto.getRealtorName(), false,
                    new String[]{"NotFound.inspectionDto.realtorName"}, null, "realtor not found"));

        } else {
            inspection.setRealtor((Realtor) realtor);
        }

        if (!bindingResult.hasErrors()) {
            inspectionService.save(inspection, null);
            inspectionDto.setId(inspection.getId());
        }
        return bindingResult.hasErrors();
    }
}
