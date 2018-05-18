package by.bsuir.realEstateAgency.web.controller;

import by.bsuir.realEstateAgency.core.model.Application;
import by.bsuir.realEstateAgency.core.model.Realtor;
import by.bsuir.realEstateAgency.core.model.TypeApplication;
import by.bsuir.realEstateAgency.core.service.ApplicationService;
import by.bsuir.realEstateAgency.core.service.ImmobilityService;
import by.bsuir.realEstateAgency.core.service.TypeApplicationService;
import by.bsuir.realEstateAgency.web.bean.application.ApplicationDto;
import by.bsuir.realEstateAgency.web.bean.immobility.ImmobilityDto;
import by.bsuir.realEstateAgency.web.bean.pagedList.CheckedItem;
import by.bsuir.realEstateAgency.web.bean.pagedList.CheckedList;
import by.bsuir.realEstateAgency.web.exceptions.BadRequestException;
import by.bsuir.realEstateAgency.web.exceptions.NotFoundException;
import by.bsuir.realEstateAgency.web.facade.ApplicationFacade;
import by.bsuir.realEstateAgency.web.facade.ImmobilityFacade;
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
@RequestMapping("/applications")
public class ApplicationController {

    static Logger log = Logger.getLogger(ApplicationController.class.getName());

    private static final String PAGE_NUMBER_REQUEST_PARAM = "page";

    private static final String PAGED_LIST_ATTRIBUTE = "pagedList";

    private static final String CREATE_ATTRIBUTE = "create";

    private static final String APPLICATIONS_DTO_ATTRIBUTE = "applicationDto";

    private static final String TYPE_APPLICATIONS_ATTRIBUTE = "typeApplications";

    @Resource
    private ApplicationFacade applicationFacade;

    @Resource
    private ApplicationService applicationService;

    @Resource
    private TypeApplicationService typeApplicationService;

    @Resource
    private PageService pageService;

    @GetMapping
    private String getApplications(@RequestParam(name = PAGE_NUMBER_REQUEST_PARAM, defaultValue = "1") int pageNumber,
                                   Model model) {
        model.addAttribute(PAGED_LIST_ATTRIBUTE, pageService.getPagedList(pageNumber, applicationService));
        return "applicationsList";
    }

    @PostMapping(params = "remove")
    private String removeApplications(@RequestParam(name = PAGE_NUMBER_REQUEST_PARAM, defaultValue = "1") int pageNumber,
                                      CheckedList checkedList, BindingResult bindingResult,
                                      Authentication authentication, Model model) throws Exception {
        AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();
        if (bindingResult.hasErrors()) {
            log.error("Bad request params for deleting Application. HTTP400");
            throw new BadRequestException();
        }
        if (checkedList.getCheckedList() != null) {
            applicationService.removeList(checkedList.getCheckedList()
                    .stream().filter(CheckedItem::isChecked)
                    .map(CheckedItem::getId).collect(Collectors.toList()), userDetails.getUser());
        }
        return "redirect:/applications?page=" + pageNumber;
    }

    @GetMapping("/new")
    public String getNewForm(Authentication authentication,Model model) {
        ApplicationDto applicationDto = new ApplicationDto();
        AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();
        if(userDetails.getUser() instanceof Realtor){
            applicationDto.setRealtorName(userDetails.getUser().getLogin());
        }
        model.addAttribute(CREATE_ATTRIBUTE, true);
        model.addAttribute(APPLICATIONS_DTO_ATTRIBUTE, applicationDto);
        model.addAttribute(TYPE_APPLICATIONS_ATTRIBUTE, typeApplicationService.getAll());
        return "applicationDetails";
    }

    @PostMapping("/new")
    public String createApplication(@Valid ApplicationDto applicationDto, BindingResult bindingResult,
                                   Authentication authentication, Model model) {
        AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();
        if (bindingResult.hasErrors()
                || applicationFacade.saveOrUpdate(applicationDto, userDetails.getUser(), bindingResult)) {
            model.addAttribute(CREATE_ATTRIBUTE, true);
            model.addAttribute(TYPE_APPLICATIONS_ATTRIBUTE, typeApplicationService.getAll());
            return "applicationDetails";
        }
        return "redirect:/applications";
    }

    @GetMapping("/{id}")
    public String getApplication(@PathVariable("id") long id, Model model) {
        ApplicationDto applicationDto = applicationFacade.getApplication(id);
        if (applicationDto == null) {
            log.error("Application not found. HTTP404");
            throw new NotFoundException();
        }
        model.addAttribute(APPLICATIONS_DTO_ATTRIBUTE, applicationDto);
        model.addAttribute(TYPE_APPLICATIONS_ATTRIBUTE, typeApplicationService.getAll());
        return "applicationDetails";
    }

    @PostMapping(value = "/{id}", params = "save")
    public String updateApplication(@PathVariable("id") long id,
                                   @Valid ApplicationDto applicationDto, BindingResult bindingResult,
                                   Authentication authentication, Model model) {
        AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();
        if (bindingResult.hasErrors()
                || applicationFacade.saveOrUpdate(applicationDto, userDetails.getUser(), bindingResult)) {
            model.addAttribute(TYPE_APPLICATIONS_ATTRIBUTE, typeApplicationService.getAll());
            return "applicationDetails";
        }
        return "redirect:/applications";
    }

    @PostMapping(value = "/{id}", params = "remove")
    public String removeApplication(@PathVariable("id") long id, Authentication authentication, Model model) {
        AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();
        applicationService.remove(id, userDetails.getUser());
        return "redirect:/applications";
    }
}
