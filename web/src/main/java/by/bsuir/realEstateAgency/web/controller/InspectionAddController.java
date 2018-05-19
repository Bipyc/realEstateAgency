package by.bsuir.realEstateAgency.web.controller;

import by.bsuir.realEstateAgency.web.bean.InspectionDto;
import by.bsuir.realEstateAgency.web.bean.immobility.ImmobilityDto;
import by.bsuir.realEstateAgency.web.facade.InspectionFacade;
import by.bsuir.realEstateAgency.web.security.AuthUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.Valid;

@Component
@RequestMapping("/inspection")
public class InspectionAddController {

    private static final String CREATE_BY_USER_ATTRIBUTE = "createByUser";

    private static final String IMMOBILITY_DTO_ATTRIBUTE = "inspectionDto";

    @Resource
    private InspectionFacade inspectionFacade;

    @GetMapping(value = "/{id}")
    public String getInspectionForm(@PathVariable("id") long id, Model model){
        model.addAttribute(CREATE_BY_USER_ATTRIBUTE, true);
        model.addAttribute(IMMOBILITY_DTO_ATTRIBUTE, new InspectionDto());
        return "inspectionDetails";
    }

    @PostMapping(value = "/{id}")
    public String saveInspectionForm(@PathVariable("id") long id, @Valid InspectionDto inspectionDto, BindingResult bindingResult,
                                   Authentication authentication, Model model){
        inspectionDto.setId(null);
        inspectionDto.setImmobilityId(id);
        AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();
        if (bindingResult.hasErrors()
                || inspectionFacade.addByUser(inspectionDto, userDetails.getUser(), bindingResult)) {
            model.addAttribute(CREATE_BY_USER_ATTRIBUTE, true);
            return "inspectionDetails";
        }
        return "redirect:/catalog";
    }
}
