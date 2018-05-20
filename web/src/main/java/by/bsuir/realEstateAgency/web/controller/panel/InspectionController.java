package by.bsuir.realEstateAgency.web.controller.panel;

import by.bsuir.realEstateAgency.core.model.Client;
import by.bsuir.realEstateAgency.core.model.Realtor;
import by.bsuir.realEstateAgency.core.service.InspectionService;
import by.bsuir.realEstateAgency.web.bean.InspectionDto;
import by.bsuir.realEstateAgency.web.bean.pagedList.CheckedItem;
import by.bsuir.realEstateAgency.web.bean.pagedList.CheckedList;
import by.bsuir.realEstateAgency.web.exceptions.BadRequestException;
import by.bsuir.realEstateAgency.web.exceptions.NotFoundException;
import by.bsuir.realEstateAgency.web.facade.InspectionFacade;
import by.bsuir.realEstateAgency.web.security.AuthUserDetails;
import by.bsuir.realEstateAgency.web.service.page.PageService;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.stream.Collectors;

@Component
@RequestMapping("/panel/inspections")
public class InspectionController {
    static Logger log = Logger.getLogger(InspectionController.class.getName());

    private static final String PAGE_NUMBER_REQUEST_PARAM = "page";

    private static final String PAGED_LIST_ATTRIBUTE = "pagedList";

    private static final String CREATE_ATTRIBUTE = "create";

    private static final String INSPECTION_DTO_ATTRIBUTE = "inspectionDto";

    @Resource
    private InspectionFacade inspectionFacade;

    @Resource
    private InspectionService inspectionService;

    @Resource
    private PageService pageService;

    @GetMapping
    private String getInspections(@RequestParam(name = PAGE_NUMBER_REQUEST_PARAM, defaultValue = "1") int pageNumber,
                                  Model model) {
        model.addAttribute(PAGED_LIST_ATTRIBUTE, pageService.getPagedList(pageNumber, inspectionService));
        return "inspectionsList";
    }

    @PostMapping(params = "remove")
    private String removeInspections(@RequestParam(name = PAGE_NUMBER_REQUEST_PARAM, defaultValue = "1") int pageNumber,
                                     CheckedList checkedList, BindingResult bindingResult,
                                     Authentication authentication, Model model) throws Exception {
        AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();
        if (bindingResult.hasErrors()) {
            log.error("Bad request params for deleting Inspections. HTTP400");
            throw new BadRequestException();
        }
        if (checkedList.getCheckedList() != null) {
            inspectionService.removeList(checkedList.getCheckedList()
                    .stream().filter(CheckedItem::isChecked)
                    .map(CheckedItem::getId).collect(Collectors.toList()), userDetails.getUser());
        }
        return "redirect:/panel/inspections?page=" + pageNumber;
    }

    @GetMapping("/new")
    public String getNewForm(Authentication authentication, Model model) {
        InspectionDto inspectionDto = new InspectionDto();
        AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();
        if (userDetails.getUser() instanceof Client) {
            inspectionDto.setClientName(userDetails.getUser().getLogin());
        }
        if (userDetails.getUser() instanceof Realtor) {
            inspectionDto.setRealtorName(userDetails.getUser().getLogin());
        }
        model.addAttribute(CREATE_ATTRIBUTE, true);
        model.addAttribute(INSPECTION_DTO_ATTRIBUTE, inspectionDto);
        return "inspectionDetails";
    }

    @PostMapping("/new")
    public String createInspection(@Valid InspectionDto inspectionDto, BindingResult bindingResult,
                                   Authentication authentication, Model model) {
        AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();
        if (bindingResult.hasErrors()
                || inspectionFacade.saveOrUpdate(inspectionDto, userDetails.getUser(), bindingResult)) {
            model.addAttribute(CREATE_ATTRIBUTE, true);
            return "inspectionDetails";
        }
        return "redirect:/panel/inspections";
    }

    @GetMapping("/{id}")
    public String getInspection(@PathVariable("id") long id, Model model) {
        InspectionDto inspectionDto = inspectionFacade.getInspection(id);
        if (inspectionDto == null) {
            log.error("Inspection not found. HTTP404");
            throw new NotFoundException();
        }
        model.addAttribute(INSPECTION_DTO_ATTRIBUTE, inspectionDto);
        return "inspectionDetails";
    }

    @PostMapping(value = "/{id}", params = "save")
    public String updateImmobility(@PathVariable("id") long id,
                                   @Valid InspectionDto inspectionDto, BindingResult bindingResult,
                                   Authentication authentication, Model model) {
        AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();
        if (bindingResult.hasErrors()
                || inspectionFacade.saveOrUpdate(inspectionDto, userDetails.getUser(), bindingResult)) {
            return "inspectionDetails";
        }
        return "redirect:/panel/inspections";
    }

    @PostMapping(value = "/{id}", params = "remove")
    public String removeImmobility(@PathVariable("id") long id, Authentication authentication, Model model) {
        AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();
        inspectionService.remove(id, userDetails.getUser());
        return "redirect:/panel/inspections";
    }
}
